package ch.makery.address;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Calificaciones {

	    public SimpleStringProperty alumno = new SimpleStringProperty();
	    public SimpleStringProperty matricula = new SimpleStringProperty();
	    public SimpleStringProperty parcial1 = new SimpleStringProperty();
	    public SimpleStringProperty parcial2 = new SimpleStringProperty();
	    public SimpleStringProperty parcial3 = new SimpleStringProperty();
	    public SimpleStringProperty parcialFinal = new SimpleStringProperty();
	    public SimpleStringProperty extra = new SimpleStringProperty();
	    public SimpleStringProperty faltas = new SimpleStringProperty();
	    	    
	    public String getAlumno(){
	        return alumno.get();
	    }
   	    
	    public String getMatricula(){
	        return matricula.get();
	    }
   	    
	    public String getParcial1(){
	        return parcial1.get();
	    }
   	    
	    public String getParcial2(){
	        return parcial2.get();
	    }
	    public String getParcial3(){
	        return parcial3.get();
	    }
	    public String getParcialFinal(){
	        return parcialFinal.get();
	    }
	    public String getExtra(){
	        return extra.get();
	    }
   	    
	    public String getFaltas(){
	        return faltas.get();
	    }
	    
	
}
