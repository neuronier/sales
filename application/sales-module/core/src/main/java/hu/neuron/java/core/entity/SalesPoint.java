package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SalesPoint")
public class SalesPoint extends Base {

	private static final long serialVersionUID = 1L;

	private Long salePointId;
	private Long wareHouseId;
	private String name;
	private String salePointAdress;
	private String salePointPhoneNumber;

	public Long getSalePointId() {
		return salePointId;
	}

	public void setSalePointId(Long salesPointId) {
		this.salePointId = salesPointId;
	}

	public Long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(Long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String salesPointName) {
		this.name = salesPointName;
	}

	public String getSalePointAdress() {
		return salePointAdress;
	}

	public void setSalePointAdress(String salesPointAdress) {
		this.salePointAdress = salesPointAdress;
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
				+ ", salesPointAdress=" + salePointAdress
				+ ", salesPointPhoneNumber=" + salePointPhoneNumber + "]";
	}
}
