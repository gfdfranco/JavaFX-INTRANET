package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class Materias {
	public SimpleStringProperty tnombreM = new SimpleStringProperty();
	public SimpleStringProperty tclaveM = new SimpleStringProperty();
	public SimpleStringProperty tclaveProfesorM = new SimpleStringProperty();
	public SimpleStringProperty taulaM = new SimpleStringProperty();
	public SimpleStringProperty thoraM = new SimpleStringProperty();
	public SimpleStringProperty tcarreraM = new SimpleStringProperty();
	
	
	public String getTnombreM(){
		return tnombreM.get();
	}
	public String getTclaveM(){
		return tclaveM.get();
	}
	public String getTclaveProfesorM(){
		return tclaveProfesorM.get();
	}
	public String getTaulaM(){
		return taulaM.get();
	}
	public String getThoraM(){
		return thoraM.get();
	}
	public String getTcarreraM(){
		return tcarreraM.get();
	}
}
