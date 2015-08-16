package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class WarehouseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2286310106108872003L;
	
	/**
	 * ÁTMENETI ADATTAG, MEG KELL VÁLTOZTATNI MAJD!!!!
	 */
	String wareHouseId;
	
	private Long id;
	
	public WarehouseVO() {}
	
	public WarehouseVO(String wareHouseId) {
		// TODO A SalesPointConverterben használom ezt a konstruktort.
		// a SalesPoint(Entity)-nek van egy String salePointAddress adattagja
		// szerintem ez majd az address azonosítója lesz.
		// Ha igen akkor úgy lenne érdemes megírni majd ezt a konstruktort, hogy ha meghívják
		// ezzel az adattaggal, akkor itt(ebben a konstruktorban), az azonosító alapján, 
		// lekérdezzük az adatbázisból a többi számunkra fontos adatát az Address-nek és
		// letároljuk ebben az objektumba. (Jocó)
		// EGYENLŐRE, IDEIGLENESEN LETÁROLOM EZT A STRINGET ÉS CSINÁLOK GETTERT SETTERT
		// DE VALSZEG NEM LESZ EZ JÓ VÉGLEGES MEGDOLÁSNAK (Jocó, 2015.08.10)
		
		this.wareHouseId = wareHouseId;
	}

	/**
	 * NE HASZNÁLD!!! Átmeneti metódus, valszeg nem jó üzleti logika van mögötte.
	 * Bővebb infó az WarehouseVO egyik konstruktorában kommentként!
	 * */
	public String getWareHouseId() {
		return wareHouseId;
	}

	/**
	 * NE HASZNÁLD!!! Átmeneti metódus, valszeg nem jó üzleti logika van mögötte.
	 * Bővebb infó az WarehouseVO egyik konstruktorában kommentként!
	 * */
	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
