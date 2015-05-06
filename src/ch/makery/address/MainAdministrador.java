//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAdministrador extends Application {
    
	//CONTROLADOR DE FXML.................................................
	
		//PARA REGISTRAR PROFESOR............................
			@FXML
			TextField nomProfesor;
			@FXML
			TextField rfcProfesor;
			@FXML
			TextField telProfesor;
			@FXML
			TextField dirProfesor;
			@FXML
			TextField anoProfesor;
			@FXML
			TextField usuarioProfesor;
			@FXML
			TextField emailProfesor;
			@FXML
			TextField passProfesor;
			@FXML
			TextField pass2Profesor;
		//................................................
		
		//PARA REGISTRAR ALUMNO..................
			@FXML
			TextField nomAlumno;
			@FXML
			TextField matricula;
			@FXML
			TextField telAlumno;
			@FXML
			TextField dirAlumno;
			@FXML
			TextField anoAlumno;
			@FXML
			TextField carrera;
			@FXML
			TextField emailAlumno;
			@FXML
			TextField passAlumno;
			@FXML
			TextField pass2Alumno;
		//.......................................
		
		//PARA REGISTRAR MATERIAS......................................
			@FXML
			TextField nomMateria;
			@FXML
			TextField claveMateria;
			@FXML
			TextField aula;
			@FXML
			TextField hora;
			@FXML
			TextField carreraMateria;
		//............................................................	
		
		//PARA REGISTRAR ADMINISTRADOR......................................
			@FXML
			TextField nomAdministrador;
			@FXML
			TextField usuarioAdministrador;
			@FXML
			TextField emailAdministrador;
			@FXML
			TextField telAdministrador;
			@FXML
			TextField passAdministrador;
			@FXML
			TextField pass2Administrador;
		//............................................................	
	//.................................................................
    private Stage primaryStage;
    private BorderPane rootLayout;

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
            loader.setLocation(MainAdministrador.class.getResource("view/RootLayoutBig.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
   
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAdministrador.class.getResource("view/Administrador.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}