package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.List;

public interface ProductTypeWebClient {
	public List<ProductTypeVO> refreshProductTypes();
}
