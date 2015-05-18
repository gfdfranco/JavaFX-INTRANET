//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
//PROGRAMACIÓN III
package ch.makery.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAlumno extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private String matricula;
    
    /* Datos Alumno */
	@FXML TextField nomA;
	@FXML TextField matA;
	@FXML TextField telA;
	@FXML TextField dirA;
	@FXML TextField mailA;
	@FXML TextField carreraA;
	
	/* Tabla */
	@FXML private TableView<DataAlumn> tableMaterias;
    @FXML private TableColumn claveM;
    @FXML private TableColumn p1;
    @FXML private TableColumn p2;
    @FXML private TableColumn p3;
    @FXML private TableColumn tP;
    @FXML private TableColumn fin;
    @FXML private TableColumn tFin;
    @FXML private TableColumn calificacionFinal;
    @FXML private TableColumn extra;
    @FXML private TableColumn inas;
    ObservableList<DataAlumn> materias;
    
    public MainAlumno(){
    	
    }
    public MainAlumno(String clave){
    	matricula = clave;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Intranet UPSLP");

        initRootLayout();
        showPersonOverview();
        showData();
        
    }

    /**
     * Initializes the root layout.
     */
   
    public void showData(){
		HashMap<String, String> data;
		DataBaseSQL db = new DataBaseSQL();
		
		data = db.fetchArray("ALUMNO", "MATRICULA", matricula);
		
		nomA.setText(data.get("NOMBRE"));
		telA.setText(data.get("TELEFONO"));
		dirA.setText(data.get("DIRECCION"));
		mailA.setText(data.get("EMAIL"));
		matA.setText(data.get("MATRICULA"));
		carreraA.setText(data.get("CARRERA"));
    }
    
    public void initTable(){
	    claveM.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("clave"));
	    p1.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("p1"));
	    p2.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("p2"));
	    p3.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("p3"));
	    tP.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("tP"));
	    fin.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("fin"));
	    tFin.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("tFin"));
	    calificacionFinal.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("total"));
	    extra.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("extra"));
	    inas.setCellValueFactory(new PropertyValueFactory<DataAlumn, String>("inas"));
	    
	    materias = FXCollections.observableArrayList();
	    tableMaterias.setItems(materias);
	    
	    fillTable();
    }
    
    @FXML
    public void fillTable(){
    	DataBaseSQL db = new DataBaseSQL();
		List<HashMap<String, String>> data1;
		data1 = db.getAll("CALIFICACIONES", "MATRICULA_ALUM", matricula);
		
		for(int i = 0; i < data1.size(); i++){
			
			DataAlumn dtA = new DataAlumn();
			
			dtA.clave.set(data1.get(i).get("CLAVE_MATERIA"));
			dtA.p1.set(data1.get(i).get("CAL1"));
			dtA.p2.set(data1.get(i).get("CAL2"));
			dtA.p3.set(data1.get(i).get("CAL3"));
			
			float aux1 = (Float.parseFloat(data1.get(i).get("CAL1")) + Float.parseFloat(data1.get(i).get("CAL2")) + Float.parseFloat(data1.get(i).get("CAL3")));
			aux1 = aux1 / 3 * 0.6F;
			
			dtA.tP.set(String.valueOf(aux1));
			dtA.fin.set(data1.get(i).get("FINAL"));
			
			float aux2 = Float.parseFloat(data1.get(i).get("FINAL")) * 0.4F;
			
			dtA.tFin.set(String.valueOf(aux2));
			dtA.total.set(String.valueOf(aux1 + aux2));
			dtA.extra.set(data1.get(i).get("EXTRA"));
			dtA.inas.set(data1.get(i).get("INASISTENCIAS"));
			
			materias.add(dtA);
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