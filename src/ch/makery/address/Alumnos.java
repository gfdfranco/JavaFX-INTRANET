package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class Alumnos{
	public SimpleStringProperty tnombreA = new SimpleStringProperty();
	public SimpleStringProperty tmatriculaA = new SimpleStringProperty();
	public SimpleStringProperty ttelefonoA = new SimpleStringProperty();
	public SimpleStringProperty tdireccionA = new SimpleStringProperty();
	public SimpleStringProperty tcarreraA = new SimpleStringProperty();
	public SimpleStringProperty temailA = new SimpleStringProperty();
	
	
	public String getTnombreA(){
		return tnombreA.get();
	}
	public String getTmatriculaA(){
		return tmatriculaA.get();
	}
	public String getTtelefonoA(){
		return ttelefonoA.get();
	}
	public String getTdireccionA(){
		return tdireccionA.get();
	}
	public String getTcarreraA(){
		return tcarreraA.get();
	}
	public String getTemailA(){
		return temailA.get();
	}
}