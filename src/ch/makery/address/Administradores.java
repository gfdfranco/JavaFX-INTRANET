package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class Administradores{
	public SimpleStringProperty nombre = new SimpleStringProperty();
	public SimpleStringProperty clave = new SimpleStringProperty();
	public SimpleStringProperty email = new SimpleStringProperty();
	public SimpleStringProperty telefono = new SimpleStringProperty();

	
	
	public String getNombre(){
		return nombre.get();
	}
	public String getClave(){
		return clave.get();
	}
	public String getEmail(){
		return email.get();
	}
	public String getTelefono(){
		return telefono.get();
	}
	
}