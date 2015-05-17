//UNIVERSIDAD POLITÉCNICA DE SAN LUIS POTOSÍ
package ch.makery.address;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

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

	
    private Stage segundoStage;
    private BorderPane rootLayout;
    private String claveProfesor, passProfesor;
    private String nombreAlumno;
    // Declaramos la tabla y las columnas
    
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
    
    public MainProfesor()
    {
    	
    }
    public MainProfesor(String clave, String pass)
    {
    	claveProfesor=clave;
    	passProfesor=pass;
    }
    public void start(Stage segundoStage) {
        this.segundoStage = segundoStage;
        this.segundoStage.setTitle("Intranet UPSLP");

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
            loader.setLocation(MainProfesor.class.getResource("view/ProfesorBorde.fxml"));
            rootLayout = (BorderPane) loader.load();
            System.out.println(claveProfesor); 
            System.out.println(passProfesor); 
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            segundoStage.setScene(scene);
            segundoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
   
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainProfesor.class.getResource("view/Docente.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final ListChangeListener<Calificaciones> selectorTablaCalificaciones =
            new ListChangeListener<Calificaciones>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Calificaciones> c) {
                    ponerCalificacionSeleccionada();
                }

			
            };
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
	    // Inicializamos la tabla
	    this.inicializarTablaCalificaciones();
	
	    // Seleccionar las tuplas de la tabla de las personas
	    final ObservableList<Calificaciones> tablaClientesSel = tablaCalificaciones.getSelectionModel().getSelectedItems();
	    tablaClientesSel.addListener(selectorTablaCalificaciones);
	    
	    List<HashMap<String, String>> data;
    	data = db.getAll("CALIFICACIONES", "CLAVE_MATERIA","2");
    	
    	for(int i = 0; i < data.size(); i++){
	    	Calificaciones p1 = new Calificaciones();
		    
		    p1.alumno.set("Nombre Alumno");
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
    
    public Stage getSegundoStage() {
        return segundoStage;
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
}