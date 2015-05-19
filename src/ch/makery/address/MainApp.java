//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
/*
 * PROYECTO FINAL DE PROGRAMACION III
*/

//paquetes para separar por carpetas la vista y el controlador
package ch.makery.address;

//LIBRERIAS NECESARIAS.........................................
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//......................................................................

//clase MainApp que hereda de Application que ya tiene sus metodos necesarios
public class MainApp extends Application {

    private Stage primaryStage;//Escenario que se usa
    private BorderPane rootLayout;//Objeto vista del layOut
    //Atributos necesarios de tipo String
    private String claveUsuario, passUsuario;
    
    //SE UNE EL CONTROLADOR CON LA VISTA SE RELACIONA CON EL ESTILO FXML
    @FXML private TextField clave;
    @FXML private TextField pass;
    @FXML private RadioButton radioAlumno;
    @FXML private RadioButton radioProfesor;
    @FXML private RadioButton radioAdministrador;
   
    
    //Este metodo es obligatorio ponerlo viene de la clase Application
    @Override
    public void start(Stage primaryStage) {
    	//Se iniciar el escenario de la aplicacion
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Intranet UPSLP"); //Se cambia titulo de la aplicacion
        //SE CREAN LOS BORDES DE LA APLICACION
        initRootLayout();
        //SE CREA ANCHO QUE VA DENTRO DE LOS BORDES DE LA APLICACION
        showPersonOverview();
    }

   //Se crean bordes de la aplicacion
    public void initRootLayout() {
        //Como puede ver excepciones ponemos try y catch
    	try {
            // Carga el FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InicioBorde.fxml"));//Se da la direccion del archivo FXML
            //Se dice que es bordePane y se carga la aplicacion
            rootLayout = (BorderPane) loader.load();

            // Se muestra el contenido dentro del escenario
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
          
        //EXCEPCIONES POSIBLES............................    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Se crea anchor, el contenido que va adentro de los bordes:
    public void showPersonOverview() {
        try {
        	// Carga el FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));//direccion de FXML
            //Se dice que es AnchorPane y se carga la aplicacion
            AnchorPane personOverview = (AnchorPane) loader.load();
            
         // Se muestra el contenido dentro del escenario dentro de los bordes
            rootLayout.setCenter(personOverview);
            
        //EXCEPCIONES POSIBLES............................    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //CUANDO ES PRESIONADO EL BOTON ENTRAR
    @FXML
    private void handleButtonAction(ActionEvent event) {
    	//Base de datos......
    	DataBaseSQL	db = new DataBaseSQL();
    	HashMap<String, String> dataS;
		String data;
		
			
    	//Si es seleccionado el alumno en radio button...............
    	if(radioAlumno.isSelected()){
    		//Se guardan en variables lo ingresado en los textfield
    		claveUsuario = clave.getText();
    		passUsuario = pass.getText();
    		System.out.println("-" + passUsuario);
    		if((dataS = db.fetchArray("alumno", "MATRICULA", claveUsuario)) != null)
	    		if(dataS.get("PASSWORD").equals(passUsuario)){
	    		    //Con polimorfismo se adapta a la forma de la clase hijo, del usuario que es
	    			Application app2 = new MainAlumno(claveUsuario); 
	    			
	    			//Inicia esenario nuevo
	                Stage anotherStage = new Stage();
	               //Por si hay excepciones se pone try and catch
	                try {
	                	app2.start(anotherStage);//Se inicia la aplicacion
	                	//primaryStage.close();
	                } catch (Exception e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();//IMPRIME ERROR
	                }
	    		}else{
					JOptionPane.showMessageDialog(null, "Contraseña no valida");
					System.out.println(dataS.get("PASSWORD"));
	    		}
			else
				JOptionPane.showMessageDialog(null, "Usuario no valido");
    		
    	}
    	//Si es seleccionado el profesor en radio button...............
    	if(radioProfesor.isSelected()){
    		//Se guardan en variables lo ingresado en los textfield
    		claveUsuario = clave.getText();
    		passUsuario = pass.getText();
    		
    		if((dataS = db.fetchArray("profesor", "CLAVE", claveUsuario)) != null)
	    		if(dataS.get("PASSWORD").equals(passUsuario)){
		    		//Se manda en el constructor la clave y contraseña
		    		Application app2 = new MainProfesor(claveUsuario); 
		    		//Inicia esenario nuevo
		            Stage anotherStage = new Stage();
		           //Por si hay excepciones se pone try and catch
		            try {
		            	app2.start(anotherStage);//Se inicia la aplicacion
		            	//primaryStage.close();
		            } catch (Exception e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();//IMPRIME ERROR
		            }
	    		}else
					JOptionPane.showMessageDialog(null, "Contraseña no valida");
			else
				JOptionPane.showMessageDialog(null, "Usuario no valido");
    	}
    	//Si es seleccionado el administrador en radio button...............
    	if(radioAdministrador.isSelected()){
    		//Se guardan en variables lo ingresado en los textfield
    		claveUsuario = clave.getText();
    		passUsuario = pass.getText();
    		
    		if((dataS = db.fetchArray("administrador", "CLAVE", claveUsuario)) != null)
	    		if(dataS.get("PASSWORD").equals(passUsuario)){
		    		//Se manda en el constructor la clave y contraseña
		    		Application app2 = new MainAdministrador(claveUsuario); 
		    		//Inicia esenario nuevo
		            Stage anotherStage = new Stage();
		            //primaryStage.close();
		           //Por si hay excepciones se pone try and catch
		            try {
		            	app2.start(anotherStage);//Se inicia la aplicacion
		            	
		            } catch (Exception e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();//IMPRIME ERROR
		            }
	    		}else
	    			JOptionPane.showMessageDialog(null, "Contraseña no valida");
    		else
    			JOptionPane.showMessageDialog(null, "Usuario no valido");
    	}
    	
        
    }
    
    //Metodo para imprimir mensaje de advertencia....
    public void error(String txt){
    	JOptionPane.showMessageDialog(null, txt);
    }
    //Metodo necesario al heredar de application    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    //main.....
    public static void main(String[] args) {
        launch(args); //Se inicia aplicacion
        
    }
}