package business;

import hu.neuron.java.core.dao.SalesPointDAO;
import hu.neuron.java.sales.service.salepoint.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless(mappedName = "SalesPointService", name = "SalesPointService")
@Remote(SalesPointServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SalesPointServiceImpl implements SalesPointServiceRemote,
		Serializable {

	private static final long serialVersionUID = -541159372291863297L;
	
	@EJB
	SalesPointDAO salesDao;

	@Override
	public void saveSalePoint(SalesPointVO salePoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSalePoint(SalesPointVO salePoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSalePoint(SalesPointVO salePoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SalesPointVO> getSalePoints(int i, int pageSize,
			String sortField, int dir, String filter, String filterColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

}
