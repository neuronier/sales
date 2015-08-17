package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SalesPoint")
public class SalesPoint extends Base {

	private static final long serialVersionUID = 1L;

	private String salePointId;
	private String wareHouseId;
	private String name;
	private String addressId;
	private String salePointPhoneNumber;

	public String getSalePointId() {
		return salePointId;
	}

	public void setSalePointId(String salesPointId) {
		this.salePointId = salesPointId;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String salesPointName) {
		this.name = salesPointName;
	}

	public String getAdressId() {
		return addressId;
	}

	public void setAdressId(String addressId) {
		this.addressId = addressId;
	}

	public String getSalePointPhoneNumber() {
		return salePointPhoneNumber;
	}

	public void setSalePointPhoneNumber(String salesPointPhoneNumber) {
		this.salePointPhoneNumber = salesPointPhoneNumber;
	}

	@Override
	public String toString() {
		return "SalesPoint [salesPointId=" + salePointId + ", wareHouseId="
				+ wareHouseId + ", salesPointName=" + name
				+ ", salesPointAdress=" + addressId
				+ ", salesPointPhoneNumber=" + salePointPhoneNumber + "]";
	}
}
