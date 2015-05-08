package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class DataAlumn{
	public SimpleStringProperty clave, p1, p2, p3, tP, fin, tFin, total, extra, inas;
	
	public DataAlumn(String a[]){
		clave = p1 = p2 = p3 = tP = fin = tFin = total = extra = inas = new SimpleStringProperty();
		clave.set(a[0]);
		p1.set(a[1]);
		p2.set(a[2]);
		p3.set(a[3]);
		Float aux1;
		aux1 = (Float.parseFloat(p1.get()) + Float.parseFloat(p2.get()) + Float.parseFloat(p3.get()))/3*0.6F;
		tP.set( String.valueOf(aux1));
		fin.set(a[4]);
		Float aux2;
		aux2 = Float.parseFloat(fin.get())*0.4F;
		tFin.set(String.valueOf(aux2));
		total.set(String.valueOf(aux1 + aux2)); 
		extra.set(a[5]);
		inas.set(a[6]);
	}
	
	public String getClave(){
		return clave.get();
	}
}