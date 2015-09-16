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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class LazySellModel extends LazyDataModel<OfferVO> {

	private static final long serialVersionUID = 58393515579651203L;

	private List<OfferVO> allOffers;
	
	private List<OfferVO> availableOfferList;
	
	private List<OfferVO> filterList;

	private OfferServiceRemote offerService;
	
	private UserServiceRemote userService;
	
	private OfferProductTypeServiceRemote offProdTypeService;
	
	private WareWebService warehouseWebService;
	
	private ProductTypeServiceRemote productTypeService;
	
	private Comparator<OfferVO> nameComparator;
	
	private Comparator<OfferVO> priceComparator;
	
	private int dir, prevDir, allOfferSize;
	
	private String prevFilter, prevSort;
	
	private SellController sellController;
	
	public LazySellModel(OfferServiceRemote offerService, UserServiceRemote userService,
			OfferProductTypeServiceRemote offProdTypeService, WareWebService warehouseWebService,
			ProductTypeServiceRemote productTypeService,SellController sellController){
		super();
		this.offerService = offerService;
		this.userService = userService;
		this.offProdTypeService = offProdTypeService;
		this.warehouseWebService = warehouseWebService;
		this.productTypeService = productTypeService;
		this.sellController = sellController;
		
		nameComparator = new Comparator<OfferVO>(){
				@Override
				public int compare(OfferVO o1, OfferVO o2) {
					int mul = dir == 1 ? 1 : -1;
					return mul * o1.getName().compareTo(o2.getName());
				}
		};
		
		priceComparator = new Comparator<OfferVO>(){
			@Override
			public int compare(OfferVO o1, OfferVO o2) {
				int mul = dir == 1 ? 1 : -1;
				return mul * (Long.valueOf(o1.getOfferPrice()).compareTo(Long.valueOf(o2.getOfferPrice())));
			}
	};
	}

	@Override
	public OfferVO getRowData(String rowkey) {
		if (availableOfferList != null || rowkey != null) {
			for (OfferVO offerVo : availableOfferList) {
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
				
				//TODO azthiszem most jó lesz az egy sorral lentebbi dolog de MÉG NEM TESZELTEM
	@Override	//TODO ha csökken a termékszám újra kell generálni az available List-et
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
	
		dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		
		if(availableOfferList == null || allOfferSize != offerService.getRowNumber() || sellController.isSold()){
			LinkedList<OfferVO> tmpList = new LinkedList<>(); 
			allOffers = offerService.findAll();
			allOfferSize = allOffers.size();
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserVO currentUser = null;
			
			try {
				currentUser = userService.findUserByUserName(user.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			for(OfferVO offer : allOffers){
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
						try {//com.sun.xml.ws.fault.ServerSOAPFaultException-t dobhat!!!!!
							if(pt != null && opt.getQuantity() > //rövidzár kiértékelés miatt nem gond ha a pt NULL
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
						tmpList.add(offer);
					}
				}
			}
			availableOfferList = new ArrayList<>(tmpList.size());
			availableOfferList.addAll(tmpList);
			sellController.setSold(false);
		}
		
		if(sortField.equals("name")){
			Collections.sort(availableOfferList, nameComparator);;
		}
		
		if(sortField.equals("offerPrice")){
			Collections.sort(availableOfferList, priceComparator);;
		}

		List<OfferVO> returnList = new ArrayList<>(pageSize);
		
		if(!filter.equals("")){
				if(filterColumnName.equals("offerPrice")){
					if(!filter.equals(prevFilter) || !sortField.equals(prevSort) || dir!=prevDir){
						filterList = new LinkedList<>();
						for(OfferVO ofr : availableOfferList){
							if(String.valueOf(ofr.getOfferPrice()).toLowerCase().startsWith(filter.toLowerCase())){
								filterList.add(ofr);
							}
						}
						this.setRowCount(filterList.size());
						prevFilter = filter;
					}
					if(first+pageSize+1 > filterList.size()){
						returnList = filterList.subList(first, filterList.size());
					} else {
						returnList = filterList.subList(first, first+pageSize);
					}
				
				}
				if(filterColumnName.equals("name")){
					if(!filter.equals(prevFilter) || !sortField.equals(prevSort) || dir!=prevDir){
						filterList = new LinkedList<>();
						for(OfferVO ofr : availableOfferList){
							if(ofr.getName().toLowerCase().startsWith(filter.toLowerCase())){
								filterList.add(ofr);
							}
						}
						this.setRowCount(filterList.size());
						prevFilter = filter;
					}
					if(first+pageSize+1 > filterList.size()){
						returnList = filterList.subList(first, filterList.size());
					} else {
						returnList = filterList.subList(first, first+pageSize);
					}
				}
		}
		else{
			prevFilter = null;
			if(first+pageSize+1 > availableOfferList.size()){
				returnList = availableOfferList.subList(first, availableOfferList.size());
			} else {
				returnList = availableOfferList.subList(first, first+pageSize);
			}
			this.setRowCount(availableOfferList.size());
		}
		prevSort = sortField;
		prevDir = dir;
		return returnList;
	}
}
