package hu.neuron.java.sales.web.pdf;

import hu.neuron.java.sales.service.vo.ClientOfferVO;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BillGenerator implements Serializable {

	private static final long serialVersionUID = -7194025880360831814L;

	public static ByteArrayInputStream createBill(ClientVO client, List<ClientOfferVO> clientOffers, Date date, SalesPointVO salesPoint)
			throws DocumentException, IOException {

		Document document = new Document();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, stream);

		document.open();

		Font fontbold = FontFactory.getFont("Times-Roman", 18, Font.BOLD);
		Paragraph title = new Paragraph("Számla", fontbold);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(20);
		document.add(title);

		PdfPTable customerTable = createCustomerTable(client);
		customerTable.setSpacingAfter(20);
		document.add(customerTable);

		PdfPTable salesPointTable = createSalesPointTable(salesPoint);
		salesPointTable.setSpacingAfter(20);
		document.add(salesPointTable);

		PdfPTable offersTable = createOfferTable(clientOffers);
		salesPointTable.setSpacingAfter(50);
		document.add(offersTable);

		String dateString = new SimpleDateFormat("yyyy.MM.dd").format(date);
		Paragraph creationDate = new Paragraph("Dátum: " + dateString);
		document.add(creationDate);

		document.close();

		return new ByteArrayInputStream(stream.toByteArray());

	}

	private static PdfPTable createCustomerTable(ClientVO client) throws DocumentException {

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		Font fontbold = FontFactory.getFont("Times-Roman", 14, Font.BOLD);

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Vásárló adatai:", fontbold));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Név: " + client.getName()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Azonosító: " + client.getClientId()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Email: " + client.getEmailAddress()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Telefon: " + client.getPhoneNumber()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Cím: " + client.getAddress()));
		cell.setColspan(2);
		table.addCell(cell);

		return table;
	}

	private static PdfPTable createSalesPointTable(SalesPointVO sp) throws DocumentException {

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);

		Font fontbold = FontFactory.getFont("Times-Roman", 14, Font.BOLD);

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Eladó adatai:", fontbold));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Név: " + sp.getName()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Azonosító: " + sp.getSalePointId()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Telefon: " + sp.getSalePointPhoneNumber()));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Cím: " + sp.getAddress()));
		cell.setColspan(2);
		table.addCell(cell);

		return table;
	}

	private static PdfPTable createOfferTable(List<ClientOfferVO> clientOffers) throws DocumentException {

		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.UK);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		symbols.setGroupingSeparator(' ');
		formatter.setDecimalFormatSymbols(symbols);

		Font fontbold = FontFactory.getFont("Times-Roman", 14, Font.BOLD);

		PdfPTable table = new PdfPTable(5);

		table.setTotalWidth(525);
		table.setLockedWidth(true);
		table.setWidths(new float[] { 1, 6, 3, 3, 3 });

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Termékek:", fontbold));
		cell.setColspan(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("No."));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Megnevezés"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Mennyiség"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Egységár"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Ár"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		int index = 1;
		long totalPrice = 0;
		for (ClientOfferVO clientOfferVO : clientOffers) {
			OfferVO offerVO = clientOfferVO.getOffer();
			
			cell = new PdfPCell(new Phrase(index + "."));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(offerVO.getName()));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			long count = clientOfferVO.getQuantity();
			cell = new PdfPCell(new Phrase(count + " db"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(formatter.format(offerVO.getOfferPrice()) + " HUF"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			long price = offerVO.getOfferPrice() * count;
			cell = new PdfPCell(new Phrase(formatter.format(price) + " HUF"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			totalPrice += price;
			index += 1;
		}

		cell = new PdfPCell(new Phrase("Összesen: ", fontbold));
		cell.setColspan(4);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(formatter.format(totalPrice) + " HUF", fontbold));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		return table;
	}

}
