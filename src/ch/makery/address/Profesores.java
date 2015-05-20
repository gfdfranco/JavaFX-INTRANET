package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class Profesores {
	public SimpleStringProperty tnombreP = new SimpleStringProperty();
	public SimpleStringProperty trfcP = new SimpleStringProperty();
	public SimpleStringProperty ttelefonoP = new SimpleStringProperty();
	public SimpleStringProperty tdireccionP = new SimpleStringProperty();
	public SimpleStringProperty tclaveP = new SimpleStringProperty();
	public SimpleStringProperty temailP = new SimpleStringProperty();
	
	
	public String getTnombreP(){
		return tnombreP.get();
	}
	public String getTrfcP(){
		return trfcP.get();
	}
	public String getTtelefonoP(){
		return ttelefonoP.get();
	}
	public String getTdireccionP(){
		return tdireccionP.get();
	}
	public String getTclaveP(){
		return tclaveP.get();
	}
	public String getTemailP(){
		return temailP.get();
	}
}
