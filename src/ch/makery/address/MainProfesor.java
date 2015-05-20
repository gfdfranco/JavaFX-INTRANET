//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
package ch.makery.address;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainProfesor extends Application implements Initializable {

	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private static  String claveProfesor;
	private String nombreAlumno;
	private String claveMateria;
    // Declaramos la tabla y las columnas
	@FXML private TextField bnomP;
	@FXML private TextField brfcP;
	@FXML private TextField btelP;
	@FXML private TextField bdirP;
	@FXML private TextField bclaveP;
	@FXML private TextField bemailP;
	@FXML private TextField claveMat;
	@FXML private TextField txtparcial1;
	@FXML private TextField txtparcial2;
	@FXML private TextField txtparcial3;
	@FXML private TextField txtparcialFinal;
	@FXML private TextField txtextra;
	@FXML private TextField txtfaltas;
	@FXML private TextField txtmatricula;
	@FXML private TextField txtnombreAlumno;
	@FXML private  Button closeButton;
	@FXML private TableView<Calificaciones> tablaCalificaciones;
	@FXML private TableColumn alumno;
	@FXML private TableColumn matricula;
	@FXML private TableColumn parcial1;
	@FXML private TableColumn parcial2;
	@FXML private TableColumn parcial3;
	@FXML private TableColumn parcialFinal;
	@FXML private TableColumn extra;
	@FXML private TableColumn faltas;
	ObservableList<Calificaciones> calificaciones;

	private int posicionCalificacionEnTabla;
	
	private final ListChangeListener<Calificaciones> selectorTablaCalificaciones =
	    new ListChangeListener<Calificaciones>() {
	    	@Override
	    	public void onChanged(ListChangeListener.Change<? extends Calificaciones> c) {
	    		ponerCalificacionSeleccionada();
	    	}
    };



	public MainProfesor(){

	}

	public MainProfesor(String clave){
		claveProfesor = clave;
	}

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
	            loader.setLocation(MainAlumno.class.getResource("view/ProfesorBorde.fxml"));
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
	            loader.setLocation(MainAlumno.class.getResource("view/Docente.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    
    public void buscarDatos(){
    	DataBaseSQL	db = new DataBaseSQL();
    	HashMap<String, String> data;
    	data = db.fetchArray("PROFESOR", "CLAVE", claveProfesor);

    	bnomP.setText(data.get("NOMBRE"));
    	bclaveP.setText(data.get("CLAVE"));
    	brfcP.setText(data.get("RFC"));
    	bdirP.setText(data.get("DIRECCION"));
    	btelP.setText(data.get("TEL"));
    	bemailP.setText(data.get("EMAIL"));   
    }
    
    public Calificaciones getTablaCalificacionSeleccionada() {
    	if (tablaCalificaciones != null) {
    		List<Calificaciones> tabla = tablaCalificaciones.getSelectionModel().getSelectedItems();
    		if (tabla.size() == 1) {
    			final Calificaciones competicionSeleccionada = tabla.get(0);
    			return competicionSeleccionada;
    		}
    	}
    	return null;
    }

    private void ponerCalificacionSeleccionada() {
    	final Calificaciones cal = getTablaCalificacionSeleccionada();
    	posicionCalificacionEnTabla = calificaciones.indexOf(cal);

    	if (cal != null) {
                // Pongo los textFields con los datos correspondientes
    		txtnombreAlumno.setText(cal.getAlumno());
    		txtmatricula.setText(cal.getMatricula());
    		txtparcial1.setText(cal.getParcial1());
    		txtparcial2.setText(cal.getParcial2());
    		txtparcial3.setText(cal.getParcial3());
    		txtparcialFinal.setText(cal.getParcialFinal());
    		txtextra.setText(cal.getExtra());
    		txtfaltas.setText(cal.getFaltas());
    	}
    }

    @FXML private void modificar(ActionEvent event) {
    	Calificaciones calificacion = new Calificaciones();
    	calificacion.alumno.set(txtnombreAlumno.getText());
    	calificacion.matricula.set(txtmatricula.getText());
    	
    	if(!Validaciones.isGrade((txtparcial1.getText())))
    		return;
    	if(!Validaciones.isGrade((txtparcial2.getText())))
    		return;
    	if(!Validaciones.isGrade((txtparcial3.getText())))
    		return;
    	if(!Validaciones.isGrade((txtparcialFinal.getText())))
    		return;
    	if(!Validaciones.isGrade((txtextra.getText())))
    		return;
    	if(!Validaciones.isCorrectSize(txtfaltas.getText(), 2, "Inasistencias"))
    		return;
    	
    	calificacion.parcial1.set(txtparcial1.getText());
    	calificacion.parcial2.set(txtparcial2.getText());
    	calificacion.parcial3.set(txtparcial3.getText());
    	calificacion.parcialFinal.set(txtparcialFinal.getText());
    	calificacion.extra.set(txtextra.getText());
    	calificacion.faltas.set(txtfaltas.getText());
    	calificaciones.set(posicionCalificacionEnTabla, calificacion);
    }

    public void agregarDatos() {
    	DataBaseSQL db = new DataBaseSQL();
    	HashMap<String, String> aux;
    	if((aux = db.fetchArray("materia", "CLAVE", claveMat.getText())) != null){
    		System.out.println(claveProfesor);
    		if(!aux.get("CLAVE_PROFESOR").equals(claveProfesor)){
    			error("Error, No puedes acceder a esa materia");
    			return;
    		}
    	}else{
    		error("Error, materia no encontrada");
    		return;
    	}
    	claveMateria = claveMat.getText();
    	aux = db.fetchArray("parcial_activo", "id", "1");
    	txtparcial1.setDisable(true);
    	txtparcial2.setDisable(true);
    	txtparcial3.setDisable(true);
    	txtparcialFinal.setDisable(true);
    	txtextra.setDisable(true);

    	if(aux.get("activo").equals("1"))
    		txtparcial1.setDisable(false);
    	if(aux.get("activo").equals("2"))
    		txtparcial2.setDisable(false);
    	if(aux.get("activo").equals("3"))
    		txtparcial3.setDisable(false);
    	if(aux.get("activo").equals("final"))
    		txtparcialFinal.setDisable(false);
    	if(aux.get("activo").equals("extra"))
    		txtextra.setDisable(false);

			// Inicializamos la tabla
    	this.inicializarTablaCalificaciones();

			// Seleccionar las tuplas de la tabla de las personas
    	final ObservableList<Calificaciones> tablaClientesSel = tablaCalificaciones.getSelectionModel().getSelectedItems();
    	tablaClientesSel.addListener(selectorTablaCalificaciones);
    	db = new DataBaseSQL();
    	List<HashMap<String, String>> data;
    	data = db.getAll("CALIFICACIONES", "CLAVE_MATERIA", claveMat.getText());

    	for(int i = 0; i < data.size(); i++){
    		Calificaciones p1 = new Calificaciones();
    		aux = db.fetchArray("alumno", "MATRICULA", data.get(i).get("MATRICULA_ALUM"));

    		p1.alumno.set(aux.get("NOMBRE"));
    		p1.matricula.set(data.get(i).get("MATRICULA_ALUM") );
    		p1.parcial1.set(data.get(i).get("CAL1"));
    		p1.parcial2.set(data.get(i).get("CAL2"));
    		p1.parcial3.set(data.get(i).get("CAL3"));
    		p1.parcialFinal.set(data.get(i).get("FINAL"));
    		p1.extra.set(data.get(i).get("EXTRA"));
    		p1.faltas.set(data.get(i).get("INASISTENCIAS"));

    		calificaciones.add(p1);
    	}
    }

    public void salir()
    {
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();
    }
    public void manualUsuario()
    {
    	if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("Manual.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
    	}   
    }
    private void inicializarTablaCalificaciones() {
    	alumno.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("alumno"));
    	matricula.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("matricula"));
    	parcial1.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("parcial1"));
    	parcial2.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("parcial2"));
    	parcial3.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("parcial3"));
    	parcialFinal.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("parcialFinal"));
    	extra.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("extra"));
    	faltas.setCellValueFactory(new PropertyValueFactory<Calificaciones, String>("faltas"));
    	calificaciones = FXCollections.observableArrayList();
    	tablaCalificaciones.setItems(calificaciones);
    }
    
    public void sendData(){
    	DataBaseSQL db = new DataBaseSQL();
    	int cant = tablaCalificaciones.getItems().size();
    	for(int i = 0; i < cant; i++){
    		Calificaciones cal = tablaCalificaciones.getItems().get(i);
    		String[] cals = new String[7];
    		cals[0] = cal.getMatricula();
    		cals[1] = cal.getParcial1();
    		cals[2] = cal.getParcial2();
    		cals[3] = cal.getParcial3();
    		cals[4] = cal.getParcialFinal();
    		cals[5] = cal.getExtra();
    		cals[6] = cal.getFaltas();
    		/*for(String x : cals)
    			if(x.equals(""))
    				x = "default";*/
    		String q = "UPDATE calificaciones set CAL1 = " + cals[1] + ", CAL2 = " + cals[2];
    		q += ", CAL3 = " + cals[3] + ", FINAL = " + cals[4] + ", EXTRA = " + cals[5];
    		q += ", INASISTENCIAS = " + cals[6] + " WHERE MATRICULA_ALUM = " + cals[0];
    		q += " AND CLAVE_MATERIA = " + claveMateria;
    		if(!db.free(q)){
    			error("Error al modificar los datos");
    		}
    	}
    }
    
    public void error(String txt){
    	JOptionPane.showMessageDialog(null, txt);
    }
    
    public Stage getPrimaryStage() {
    	return primaryStage;
    }
    
    public String getClaveProfesor()
    {
    	return claveProfesor;
    }
    public void setClaveProfesor(String aux)
    {
    	aux=claveProfesor;
    }

  
    public static void main(String[] args) {
    	launch(args);

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		buscarDatos();
	}


}
