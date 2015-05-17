//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
//


package ch.makery.address;

import java.io.IOException;

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

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    @SuppressWarnings("unused")
	private MainAlumno alumno;
    private String claveUsuario, passUsuario;
    @FXML private TextField clave;
    @FXML private TextField pass;
    @FXML private RadioButton radioAlumno;
    @FXML private RadioButton radioProfesor;
    @FXML private RadioButton radioAdministrador;
   
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Intranet UPSLP");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InicioBorde.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
          
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    	if(radioAlumno.isSelected())
    	{
    		claveUsuario=clave.getText();
    		passUsuario=pass.getText();
    		Application app2 = new MainAlumno(claveUsuario,passUsuario); 
            Stage anotherStage = new Stage();
            try {
            	app2.start(anotherStage);
            } catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
            }
    	}
    	if(radioProfesor.isSelected())
    	{
    		
    		claveUsuario=clave.getText();
    		passUsuario=pass.getText();
    		Application app2 = new MainProfesor(claveUsuario,passUsuario); 
    		Stage anotherStage = new Stage();
           
            try {
            	
            	app2.start(anotherStage);
          
            
            } catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
            }
    	}
    	if(radioAdministrador.isSelected())
    	{
    		claveUsuario=clave.getText();
    		passUsuario=pass.getText();
    		Application app2 = new MainAdministrador(claveUsuario,passUsuario); 
            Stage anotherStage = new Stage();
            try {
            	app2.start(anotherStage);
            } catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
            }
    	}
    	
        
    }
        
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}