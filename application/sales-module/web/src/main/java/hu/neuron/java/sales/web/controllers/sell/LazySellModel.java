package hu.neuron.java.sales.web.controllers.sell;

import hu.neuron.java.sales.service.OfferProductTypeServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.OfferProductTypeVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.warehouse.whweb.web.service.WareWebService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class LazySellModel extends LazyDataModel<OfferVO> {

	private static final long serialVersionUID = 58393515579651203L;

	private List<OfferVO> visibleOfferList;

	private OfferServiceRemote offerService;
	
	private UserServiceRemote userService;
	
	private OfferProductTypeServiceRemote offProdTypeService;
	
	private WareWebService warehouseWebService;
	
	private ProductTypeServiceRemote productTypeService;
	
	public LazySellModel(OfferServiceRemote offerService, UserServiceRemote userService,
			OfferProductTypeServiceRemote offProdTypeService, WareWebService warehouseWebService,
			ProductTypeServiceRemote productTypeService){
		super();
		this.offerService = offerService;
		this.userService = userService;
		this.offProdTypeService = offProdTypeService;
		this.warehouseWebService = warehouseWebService;
		this.productTypeService = productTypeService;
	}

	@Override
	public OfferVO getRowData(String rowkey) {
		if (visibleOfferList != null || rowkey != null) {
			for (OfferVO offerVo : visibleOfferList) {
				if (offerVo.getOfferId().toString().equals(rowkey)) {
					return offerVo;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(OfferVO offerVO) {
		if (offerVO == null) {
			return null;
		}
		return offerVO.getOfferId();
	}

	@Override
	public List<OfferVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		
		if (sortField == null) {
			sortField = "name";	
		}
	
		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		
		
		//Az összeset felszedjük és leválogatjuk azt ami van az adott raktárban. TODO
		//mert ez most csak 1 lapnyi
		visibleOfferList = offerService.getOffers(first / pageSize, pageSize,
						sortField, dir, filter, filterColumnName);
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO currentUser = null;
		LinkedList<OfferVO> returnList = new LinkedList<>();
		try {
			currentUser = userService.findUserByUserName(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		for(OfferVO offer : visibleOfferList){
			List<OfferProductTypeVO> optList = null;
			try {
				optList = offProdTypeService.findOfferProductTypeByOfferId(offer.getOfferId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(optList != null){
				boolean OK = true;
				for(OfferProductTypeVO opt : optList){
					ProductTypeVO pt = null;
					try {
						pt = productTypeService.
							findProductTypeByProductTypeId(opt.getProductTypeId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {	//com.sun.xml.ws.fault.ServerSOAPFaultException-t dobhat!!!!!
						System.out.println(currentUser.getSalesPoint().getWarehouse().getWarehouseId());
						if(pt != null && opt.getQuantity() > //rövidzár kiértékelés miatt nem gond ha null a pt
								warehouseWebService.getNumberOfWares(currentUser.getSalesPoint().
										getWarehouse().getWarehouseId(),pt.getName())){
							OK=false;
						}
					} catch (Exception e) {
						OK=false;
						e.printStackTrace();
					}
				}
				if(OK){
					returnList.add(offer);
				}
			}
		}

//		int dataSize = offerService.getRowNumber();
//
//		this.setRowCount(dataSize);
		
		this.setRowCount(returnList.size());

		return returnList;

	}
}
