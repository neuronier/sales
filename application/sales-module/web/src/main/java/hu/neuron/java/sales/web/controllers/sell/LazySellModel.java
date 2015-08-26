package hu.neuron.java.sales.web.controllers.sell;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazySellModel extends LazyDataModel<OfferVO> {

	private static final long serialVersionUID = 58393515579651203L;

	private List<OfferVO> visibleOfferList;

	private OfferServiceRemote offerService;
	
	public LazySellModel(OfferServiceRemote offerService){
		super();
		this.offerService = offerService;
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
		

		visibleOfferList = offerService.getOffers(first / pageSize, pageSize,
						sortField, dir, filter, filterColumnName);

		int dataSize = offerService.getRowNumber();

		this.setRowCount(dataSize);

		return visibleOfferList;

	}
}
