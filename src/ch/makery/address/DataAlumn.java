package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class DataAlumn{
	public SimpleStringProperty clave = new SimpleStringProperty();
	public SimpleStringProperty p1 = new SimpleStringProperty();
	public SimpleStringProperty p2 = new SimpleStringProperty();
	public SimpleStringProperty p3 = new SimpleStringProperty();
	public SimpleStringProperty tP = new SimpleStringProperty();
	public SimpleStringProperty fin = new SimpleStringProperty();
	public SimpleStringProperty tFin = new SimpleStringProperty();
	public SimpleStringProperty total = new SimpleStringProperty();
	public SimpleStringProperty extra = new SimpleStringProperty();
	public SimpleStringProperty inas = new SimpleStringProperty();
	
	public DataAlumn(){
		
	}
	
	public String getClave(){
		return clave.get();
	}
	public String getP1(){
		return p1.get();
	}
	public String getP2(){
		return p2.get();
	}
	public String getP3(){
		return p3.get();
	}
	public String getTP(){
		return tP.get();
	}
	public String getFin(){
		return fin.get();
	}
	public String getTFin(){
		return tFin.get();
	}
	public String getTotal(){
		return total.get();
	}
	public String getExtra(){
		return extra.get();
	}
	public String getInas(){
		return inas.get();
	}
}