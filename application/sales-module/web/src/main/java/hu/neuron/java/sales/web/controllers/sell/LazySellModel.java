package hu.neuron.java.sales.web.controllers.sell;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazySellModel extends LazyDataModel<OfferVO> {

	private static final long serialVersionUID = 58393515579651203L;

	private List<OfferVO> visibleSalesPointList;

	private OfferServiceRemote offerService;
	
	public LazySellModel(OfferServiceRemote offerService){
		super();
		this.offerService = offerService;
	}

	@Override
	public OfferVO getRowData(String rowkey) {

		return null;
	}

	@Override
	public Object getRowKey(OfferVO offerVO) {

		return null;

	}

	@Override
	public List<OfferVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		return visibleSalesPointList;

	}
}
