package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class MateriasProfe {
	public SimpleStringProperty nombre = new SimpleStringProperty();
	public SimpleStringProperty clave = new SimpleStringProperty();
	public SimpleStringProperty hora = new SimpleStringProperty();
	public SimpleStringProperty aula = new SimpleStringProperty();
	
	public String getNombre(){
		return nombre.get();
	}
	
	public String getClave(){
		return clave.get();
	}
	
	public String getHora(){
		return hora.get();
	}
	
	public String getAula(){
		return aula.get();
	}
}
