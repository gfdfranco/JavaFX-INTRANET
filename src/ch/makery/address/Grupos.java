package ch.makery.address;

import javafx.beans.property.SimpleStringProperty;

public class Grupos {
	public SimpleStringProperty tclaveMateriaG = new SimpleStringProperty();
	public SimpleStringProperty tnombreMateriaG = new SimpleStringProperty();
	public SimpleStringProperty tclaveProfesorG = new SimpleStringProperty();
	public SimpleStringProperty tmatriculaG = new SimpleStringProperty();
	public SimpleStringProperty tnombreAlumnoG = new SimpleStringProperty();

	
	
	public String getTclaveMateriaG(){
		return tclaveMateriaG.get();
	}
	public String getclaveMateriaG(){
		return tnombreMateriaG.get();
	}
	public String getTclaveProfesorG(){
		return tclaveProfesorG.get();
	}
	public String getTmatriculaG(){
		return tmatriculaG.get();
	}
	public String getTnombreAlumnoG(){
		return tnombreAlumnoG.get();
	}
	
}
