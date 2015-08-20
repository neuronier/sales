package hu.neuron.java.sales.web.controllers.salespoint;

import hu.neuron.java.sales.service.AddressServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazySalesPointModel extends LazyDataModel<SalesPointVO> {

	private static final long serialVersionUID = 58393515579651203L;

	private List<SalesPointVO> visibleSalesPointList;
	
	private List<AddressVO> visibleAddressList;

	private SalesPointServiceRemote salesPointService;
	
	private AddressServiceRemote addressService;
	
	public LazySalesPointModel(SalesPointServiceRemote salesPointService,AddressServiceRemote addressService){
		super();
		this.salesPointService = salesPointService;
		this.addressService = addressService;
	}

	@Override
	public SalesPointVO getRowData(String rowkey) {
		if (visibleSalesPointList != null || rowkey != null) {
			for (SalesPointVO salesPointVO : visibleSalesPointList) {
				if (salesPointVO.getId().toString().equals(rowkey)) {
					return salesPointVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(SalesPointVO salesPointVO) {
		if (salesPointVO == null) {
			return null;
		}
		return salesPointVO.getId();
	}

	@Override
	public List<SalesPointVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		
		if (sortField == null) {
			sortField = "";		//csak akkor lesz null, ha az első oszlopon van először rendezés nélkül,
								//amúgy mindenképp lesz kiválasztva sort, mert csak akkor változik amikor
								//másik oszlopon kattintják be a rendezést és olyankor sose lesz null.
		}
	
		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		
		if(filterColumnName.equals("address")){	//város alapján akarok rendezni, de az xhtml-ből (sortBy és filterBy)
			sortField = "city";	//vagy address, vagy address.city-t tudok szerezni, ezért a rövidebbel
			filterColumnName = "city";			//dolgozok inkább és beállítom, hogy a megfelelő oszlopot használja.
			queryByAddress(first, pageSize, sortField, filter, filterColumnName, dir);
			
		} else {
			if(sortField.equals("address")){
				sortField = "city";
				queryByAddress(first, pageSize, sortField, filter, filterColumnName, dir);	
				
			} else {
				sortField = "name";
				visibleSalesPointList = salesPointService.getSalePoints(first / pageSize, pageSize,
						sortField, dir, filter, filterColumnName);
			}
		}
		
		int dataSize = salesPointService.getRowNumber();

		this.setRowCount(dataSize);

		return visibleSalesPointList;

	}
	
	private void queryByAddress(int first, int pageSize, String sortField,
			String filter, String filterColumnName, int dir) {
		
		visibleAddressList = addressService.getAddresses(first / pageSize, pageSize,
				sortField, dir, filter, filterColumnName);

		visibleSalesPointList.clear();
		
		int i, addressSize = visibleAddressList.size();
		for(i = 0 ; i < addressSize; i++){
			try {
				//TODO Mi van ha több SalesPoint is van ugyan azon a címen? -- egynlőre szívás
				SalesPointVO sp = salesPointService.findSalePointByAddress(visibleAddressList.get(i));
				if(sp != null){
					visibleSalesPointList.add(sp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
