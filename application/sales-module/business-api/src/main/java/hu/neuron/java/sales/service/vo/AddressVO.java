package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class AddressVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4037424070007872953L;
	
	/**
	 * ÁTMENETI ADATTAG, MEG KELL VÁLTOZTATNI MAJD!!!!
	 */
	String salePointAdress;
	
	private String zipCode;
	
	private String city;
	
	private String sreet;
	
	private String houseNumber;
	
	private String addresId;
	
	public AddressVO() {}
	
	public AddressVO(String salePointAdress) {
		// TODO A SalesPointConverterben használom ezt a konstruktort.
		// a SalesPoint(Entity)-nek van egy String salePointAddress adattagja
		// szerintem ez majd az address azonosítója lesz.
		// Ha igen akkor úgy lenne érdemes megírni majd ezt a konstruktort, hogy ha meghívják
		// ezzel az adattaggal, akkor itt(ebben a konstruktorban), az azonosító alapján, 
		// lekérdezzük az adatbázisból a többi számunkra fontos adatát az Address-nek és
		// letároljuk ebben az objektumba. (Jocó)
		// EGYENLŐRE, IDEIGLENESEN LETÁROLOM EZT A STRINGET ÉS CSINÁLOK GETTERT SETTERT
		// DE VALSZEG NEM LESZ EZ JÓ VÉGLEGES MEGDOLÁSNAK (Jocó, 2015.08.10)
		
		this.salePointAdress = salePointAdress;
	}

	/**
	 * NE HASZNÁLD!!! Átmeneti metódus, valszeg nem jó üzleti logika van mögötte.
	 * Bővebb infó az AdressVO egyik konstruktorában kommentként!
	 * */
	public String getSalePointAdress() {
		return salePointAdress;
	}

	/**
	 * NE HASZNÁLD!!! Átmeneti metódus, valszeg nem jó üzleti logika van mögötte.
	 * Bővebb infó az AdressVO egyik konstruktorában kommentként!
	 * */
	public void setSalePointAdress(String salePointAdress) {
		this.salePointAdress = salePointAdress;
	}
	
	

}
