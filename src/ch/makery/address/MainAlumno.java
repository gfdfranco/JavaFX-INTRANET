//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
//PROGRAMACIÓN III
package ch.makery.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAlumno extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private String matricula;
    private DataBaseSQL db;
    
    /* Datos Alumno */
	@FXML TextField nomA;
	@FXML TextField matA;
	@FXML TextField telA;
	@FXML TextField dirA;
	@FXML ComboBox<String> carreraA;
	@FXML TextField mailA;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Intranet UPSLP");

        initRootLayout();

        showPersonOverview();
        
        db = new DataBaseSQL();
    }

    /**
     * Initializes the root layout.
     */
   

    public void showData(){
		HashMap<String, String> data;
		data = db.fetchArray("ALUMNO", "MATRICULA", matricula);
		nomA.setText(data.get("NOMBRE"));
		telA.setText(data.get("TELEFONO"));
		dirA.setText(data.get("DIRECCION"));
		mailA.setText(data.get("EMAIL"));
		//carreraA.setText(data.get("CARRERA"));
    }
    
    public void fillTable(){
    	List<HashMap<String, String>> data1;
		data1 = db.GetAll("GRUPO", "MATRICULA_ALUM", matricula);
		for(int i = 0; i < data1.size(); i++){
			HashMap<String, String> data2;
			data2 = db.fetchArray("CALIFICACIONES", "ID_GPO", data1.get(i).get("ID_GPO"));
			String[] a = {
				data2.get("CLAVE_MATERIA"),
				data2.get("CAL1"),
				data2.get("CAL2"),
				data2.get("CAL3"),
				data2.get("FINAL"),
				data2.get("EXTRA"),
				data2.get("INSASISTENCIAS")
			};
		}
    }
    
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAlumno.class.getResource("view/RootLayoutBig.fxml"));
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
            loader.setLocation(MainAlumno.class.getResource("view/Alumno.fxml"));
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