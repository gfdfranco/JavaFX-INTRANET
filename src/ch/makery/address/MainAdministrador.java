package ch.makery.address;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class MainAdministrador extends Application  {
    
	//CONTROLADOR DE FXML.................................................
	//DATOS INSCRIBIR ALUMNO
	@FXML TextField matriculaIA;
	@FXML TextField materiaIA;
	
	//Activar Parcilas
	@FXML RadioButton parcial1;
	@FXML RadioButton parcial2;
	@FXML RadioButton parcial3;
	@FXML RadioButton parcialFinal;
	@FXML RadioButton extra;
	
	/* Datos Profesor */
	@FXML TextField nomP;
	@FXML TextField rfcP;
	@FXML TextField telP;
	@FXML TextField dirP;
	@FXML TextField usuarioP;
	@FXML TextField emailP;
	@FXML TextField passP;
	@FXML TextField pass2P;
	
	
	/* Datos Alumno */
	@FXML TextField nomA;
	@FXML TextField matA;
	@FXML TextField telA;
	@FXML TextField dirA;
    @FXML RadioButton rITI_A;
    @FXML RadioButton rITEM_A;
    @FXML RadioButton rISTI_A;
    @FXML RadioButton rLAG_A;
    @FXML RadioButton rLMKT_A;
    @FXML RadioButton rITMA_A;
	@FXML TextField mailA;
	@FXML TextField passA;
	@FXML TextField pass2A;
	
	/* Datos Materia */
	@FXML TextField nomM;
	@FXML TextField claveM;
	@FXML TextField profM;
	@FXML TextField aulaM;
	@FXML TextField horaM;
	@FXML RadioButton rITI_M;
	@FXML RadioButton rITEM_M;
	@FXML RadioButton rISTI_M;
	@FXML RadioButton rLAG_M;
	@FXML RadioButton rLMKT_M;
	@FXML RadioButton rITMA_M;
	
	/* Datos Admin */
	@FXML TextField nomAdm;
	@FXML TextField userAdm;
	@FXML TextField mailAdm;
	@FXML TextField telAdm;
	@FXML TextField passAdm;
	@FXML TextField pass2Adm;
	
	/* Datos Eliminar */
	@FXML TextField delUserP;
	@FXML TextField delMatA;
	@FXML TextField delClaveA;
	
	/* Datos Profesor */
	
	@FXML TextField bnomP;
	@FXML TextField brfcP;
	@FXML TextField btelP;
	@FXML TextField bdirP;
	@FXML TextField bclaveP;
	@FXML TextField bemailP;
	
	/* Datos Alumno */
	@FXML TextField bnomA;
	@FXML TextField bmatA;
	@FXML TextField btelA;
	@FXML TextField bdirA;
	@FXML TextField bcarreraA;
	@FXML TextField bmailA;
	
	/* Datos Materia */
	@FXML TextField bnomM;
	@FXML TextField bclaveM;
	@FXML TextField bprofM;
	@FXML TextField baulaM;
	@FXML TextField bhoraM;
	@FXML TextField bcarreraM;
	
	/* Tabla ADMINISTRADORES*/
	@FXML private TableView<Administradores> tablaAdministradores;
    @FXML private TableColumn nombre;
    @FXML private TableColumn clave;
    @FXML private TableColumn email;
    @FXML private TableColumn telefono;
    ObservableList<Administradores> administradores;
    
	/* Tabla Alumnos*/
	@FXML private TableView<Alumnos> tablaAlumnos;
    @FXML private TableColumn tnombreA;
    @FXML private TableColumn tmatriculaA;
    @FXML private TableColumn ttelefonoA;
    @FXML private TableColumn tdireccionA;
    @FXML private TableColumn tcarreraA;
    @FXML private TableColumn temailA;
    ObservableList<Alumnos> alumnos;
    
    /* Tabla Materias*/
	@FXML private TableView<Materias> tablaMaterias;
    @FXML private TableColumn tnombreM;
    @FXML private TableColumn tclaveM;
    @FXML private TableColumn tclaveProfesorM;
    @FXML private TableColumn taulaM;
    @FXML private TableColumn thoraM;
    @FXML private TableColumn tcarreraM;
    ObservableList<Materias> materias;
    
    /* Tabla Profesores*/
	@FXML private TableView<Profesores> tablaProfesores;
    @FXML private TableColumn tnombreP;
    @FXML private TableColumn trfcP;
    @FXML private TableColumn ttelefonoP;
    @FXML private TableColumn tdireccionP;
    @FXML private TableColumn tclaveP;
    @FXML private TableColumn temailP;
    ObservableList<Profesores> profesores;
    
    /* Tabla Grupos*/
   @FXML private TableView<Grupos> tablagrupos;
   @FXML private TableColumn tclaveMateriaG;
   @FXML private TableColumn tnombreMateriaG;
   @FXML private TableColumn tclaveProfesorG;
   @FXML private TableColumn tmatriculaG;
   @FXML private TableColumn tnombreAlumnoG;
   ObservableList<Grupos> grupos;
   
   @FXML TextField claveMG;

	
    private Stage primaryStage;
    private BorderPane rootLayout;
    private String carrera;
    private String claveAdmin;
    
    public MainAdministrador(){
    	
    }
    
    public MainAdministrador(String clave){
	  claveAdmin = clave;
    }
    
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
            loader.setLocation(MainAdministrador.class.getResource("view/AdministradorBorde.fxml"));
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
    
    public void cambiarCarrera()
    {
    	
    }
    public void initTable(){
	    nombre.setCellValueFactory(new PropertyValueFactory<Administradores, String>("nombre"));
	    clave.setCellValueFactory(new PropertyValueFactory<Administradores, String>("clave"));
	    email.setCellValueFactory(new PropertyValueFactory<Administradores, String>("email"));
	    telefono.setCellValueFactory(new PropertyValueFactory<Administradores, String>("telefono"));
	    administradores = FXCollections.observableArrayList();
	    tablaAdministradores.setItems(administradores);
	    
	    fillTable();
    }
    @FXML
    public void fillTable(){
    	DataBaseSQL db = new DataBaseSQL();
		List<HashMap<String, String>> data1;
		data1 = db.getAll("ADMINISTRADOR");
		
		for(int i = 0; i < data1.size(); i++){
			
			Administradores dtA = new Administradores();
			
			dtA.nombre.set(data1.get(i).get("NOMBRE"));
			dtA.clave.set(data1.get(i).get("CLAVE"));
			dtA.email.set(data1.get(i).get("EMAIL"));
			dtA.telefono.set(data1.get(i).get("TEL"));
						
			administradores.add(dtA);
		}
    }
    public void initTableAlumnos(){
    	tnombreA.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("tnombreA"));
    	tmatriculaA.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("tmatriculaA"));
    	ttelefonoA.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("ttelefonoA"));
    	tdireccionA.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("tdireccionA"));
    	tcarreraA.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("tcarreraA"));
    	temailA.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("temailA"));
	    alumnos = FXCollections.observableArrayList();
	    tablaAlumnos.setItems(alumnos);
	    
	    fillTableAlumnos();
    }
    @FXML
    public void fillTableAlumnos(){
    	DataBaseSQL db = new DataBaseSQL();
		List<HashMap<String, String>> data1;
		data1 = db.getAll("ALUMNO");
		
		for(int i = 0; i < data1.size(); i++){
			
			Alumnos dtA = new Alumnos();
			
			dtA.tnombreA.set(data1.get(i).get("NOMBRE"));
			dtA.tmatriculaA.set(data1.get(i).get("MATRICULA"));
			dtA.ttelefonoA.set(data1.get(i).get("TEL"));
			dtA.tdireccionA.set(data1.get(i).get("DIRECCION"));
			dtA.tcarreraA.set(data1.get(i).get("CARRERA"));
			dtA.temailA.set(data1.get(i).get("EMAIL"));
						
			alumnos.add(dtA);
		}
    }
    public void initTableMaterias(){
    	tnombreM.setCellValueFactory(new PropertyValueFactory<Materias, String>("tnombreM"));
    	tclaveM.setCellValueFactory(new PropertyValueFactory<Materias, String>("tclaveM"));
    	tclaveProfesorM.setCellValueFactory(new PropertyValueFactory<Materias, String>("tclaveProfesorM"));
    	taulaM.setCellValueFactory(new PropertyValueFactory<Materias, String>("taulaM"));
    	thoraM.setCellValueFactory(new PropertyValueFactory<Materias, String>("thoraM"));
    	tcarreraM.setCellValueFactory(new PropertyValueFactory<Materias, String>("tcarreraM"));
	    materias = FXCollections.observableArrayList();
	    tablaMaterias.setItems(materias);
	    
	    fillTableMaterias();
    }
    @FXML
    public void fillTableMaterias(){
    	DataBaseSQL db = new DataBaseSQL();
		List<HashMap<String, String>> data1;
		data1 = db.getAll("MATERIA");
		
		for(int i = 0; i < data1.size(); i++){
			
			Materias dtA = new Materias();
			
			dtA.tnombreM.set(data1.get(i).get("NOMBRE"));
			dtA.tclaveM.set(data1.get(i).get("CLAVE"));
			dtA.tclaveProfesorM.set(data1.get(i).get("CLAVE_PROFESOR"));
			dtA.taulaM.set(data1.get(i).get("AULA"));
			dtA.thoraM.set(data1.get(i).get("HORA"));
			dtA.tcarreraM.set(data1.get(i).get("CARRERA"));
						
			materias.add(dtA);
		}
    }
    public void initTableProfesores(){
    	tnombreP.setCellValueFactory(new PropertyValueFactory<Profesores, String>("tnombreP"));
    	trfcP.setCellValueFactory(new PropertyValueFactory<Profesores, String>("trfcP"));
    	ttelefonoP.setCellValueFactory(new PropertyValueFactory<Profesores, String>("ttelefonoP"));
    	tdireccionP.setCellValueFactory(new PropertyValueFactory<Profesores, String>("tdireccionP"));
    	tclaveP.setCellValueFactory(new PropertyValueFactory<Profesores, String>("tclaveP"));
    	temailP.setCellValueFactory(new PropertyValueFactory<Profesores, String>("temailP"));
	    profesores = FXCollections.observableArrayList();
	    tablaProfesores.setItems(profesores);
	    
	    fillTableProfesores();
    }
    @FXML
    public void fillTableProfesores(){
    	DataBaseSQL db = new DataBaseSQL();
		List<HashMap<String, String>> data1;
		data1 = db.getAll("PROFESOR");
		
		for(int i = 0; i < data1.size(); i++){
			
			Profesores dtA = new Profesores();
			
			dtA.tnombreP.set(data1.get(i).get("NOMBRE"));
			dtA.trfcP.set(data1.get(i).get("RFC"));
			dtA.ttelefonoP.set(data1.get(i).get("TEL"));
			dtA.tdireccionP.set(data1.get(i).get("DIRECCION"));
			dtA.tclaveP.set(data1.get(i).get("CLAVE"));
			dtA.temailP.set(data1.get(i).get("EMAIL"));
						
			profesores.add(dtA);
		}
    }
    
    public void initTableGrupos(){
    	tclaveMateriaG.setCellValueFactory(new PropertyValueFactory<Grupos, String>("tclaveMateriaG"));
    	tnombreMateriaG.setCellValueFactory(new PropertyValueFactory<Grupos, String>("tnombreMateriaG"));
    	tclaveProfesorG.setCellValueFactory(new PropertyValueFactory<Grupos, String>("tclaveProfesorG"));
    	tmatriculaG.setCellValueFactory(new PropertyValueFactory<Grupos, String>("tmatriculaG"));
    	tnombreAlumnoG.setCellValueFactory(new PropertyValueFactory<Grupos, String>("tnombreAlumnoG"));
	    grupos = FXCollections.observableArrayList();
	    tablagrupos.setItems(grupos);
	    
	    fillTableGrupos();
    }
    @FXML
    public void fillTableGrupos(){
    	DataBaseSQL	db = new DataBaseSQL();
    	String claveMateria = claveMG.getText();
    	
    	List<HashMap<String, String>> data1 = db.getAll("calificaciones", "CLAVE_MATERIA", claveMateria);
    	if(data1 == null){
    		error("No hay alumnos inscritos");
    		return;
    	}
    	for(int i = 0; i < data1.size(); i++){
    		Grupos grupo = new Grupos();
    		HashMap<String, String> data2 = db.fetchArray("materia", "CLAVE", claveMateria);
    		HashMap<String, String> data3 = db.fetchArray("alumno", "MATRICULA", data1.get(i).get("MATRICULA_ALUM"));
	    	
    		grupo.tclaveMateriaG.set(claveMateria);
	    	grupo.tnombreMateriaG.set(data2.get("NOMBRE"));
	    	grupo.tclaveProfesorG.set(data2.get("CLAVE_PROFESOR"));
	    	grupo.tmatriculaG.set(data1.get(i).get("MATRICULA_ALUM"));
	    	grupo.tnombreAlumnoG.set(data3.get("NOMBRE"));
	    	
	    	grupos.add(grupo);
    	}
    	
    }
    
    public void sendPrfsr(){
    	
    	DataBaseSQL	db = new DataBaseSQL();
    	String[] data = new String[7];
		data[0] = nomP.getText();
		if(!Validaciones.isName(data[0], 50, "nombre"))
			return;
		data[1] = rfcP.getText();
		if(!Validaciones.isExactSize(data[1], 10, "RFC"))
			return;
    	data[2] = telP.getText();
		if(!Validaciones.isPhone(data[2]))
			return;
    	data[3] = dirP.getText();
    	if(!Validaciones.isCorrectSize(data[3], 50, "Direccion"))
    		return;
    	data[4] = usuarioP.getText();
    	if(!Validaciones.isPositiveInt(data[4], "Clave", 4))
    		return;
    	data[5] = emailP.getText();
    	if(!Validaciones.isMail(data[5], 30))
    		return;
    	
    	
    	if(passP.getText().equals(pass2P.getText()))
    		data[6] = passP.getText();
    	else{
    		error("Las contraseñas no concuerdan");
    		return;
    	}
    	
    	if(!Validaciones.isPass(data[6], 25))
    		return;
    	
    	/*for(String a : data)
    		if(a.equals("")){
    			error("Ingresa todos los datos");
    			return;
    		}*/
    	
		if(db.insert("profesor", data))
    		error("Profesor agregado con Exito!");
		else
    		error("Error al ingresar al Profesor");
    }
    
    public void sendAlum(){
    	String[] data = new String[7];
    	DataBaseSQL	db = new DataBaseSQL();
    	
    	data[0] = nomA.getText();
    	if(!Validaciones.isName(data[0], 50, "Nombre"))
			return;
    	data[1] = matA.getText();
    	if(!Validaciones.isPositiveInt(data[1], "Matricula", 6))
    		return;
    	if(!Validaciones.isExactSize(data[1], 6, "Matricula"))
    		return;
    	data[2] = telA.getText();
    	if(!Validaciones.isPhone(data[2]))
    		return;
    	data[3] = dirA.getText();
    	if(!Validaciones.isCorrectSize(data[3], 50, "Direccion"))
    		return;
    	
    	if(rITI_A.isSelected())
    		data[4] = "1";
    	if(rITEM_A.isSelected())
    		data[4] = "2";
    	if(rITMA_A.isSelected())
    		data[4] = "3";
    	if(rISTI_A.isSelected())
    		data[4] = "4";
    	if(rLAG_A.isSelected())
    		data[4] = "5";
    	if(rLMKT_A.isSelected())
    		data[4] = "6";
    	
    	if(!Validaciones.isExactSize(data[4], 1, "Carrera"))
    		return;
    	
    	data[5] = mailA.getText();
    	if(!Validaciones.isMail(data[5], 30))
    		return;
    	
    	if(passA.getText().equals(pass2A.getText())){
    		data[6] = passA.getText();
    	}else{
    		error("Las contraseñas no concuerdan");
    		return;
    	}
    	
    	if(!Validaciones.isPass(data[6], 25))
    		return;
    	
    	/*for(String a : data)
    		if(a.equals("")){
    			error("Ingresa todos los datos");
    			return;
    		}*/
    	
    	if(db.insert("alumno", data)){
    		error("Alumno agregada con Exito!");
    	}else
    		error("Error al ingresar al Alumno");
    }
    
    public void sendMate(){
    	String[] data = new String[6];
    	DataBaseSQL	db = new DataBaseSQL();
    	
    	data[0] = nomM.getText();
    	if(!Validaciones.isCorrectSize(data[0], 25, "Materia"))
    		return;
    	data[1] = claveM.getText();
    	if(!Validaciones.isPositiveInt(data[1], "Clave", 4))
    		return;
    	data[2] = profM.getText();
    	if(!Validaciones.isPositiveInt(data[2], "Clave Profesor", 4))
    		return;
    	data[3] = aulaM.getText();
    	if(!Validaciones.isCorrectSize(data[3], 3, "Aula"))
    		return;
    	data[4] = horaM.getText();
    	if(data[4].equals("")){
    		error("Error, ingresa un valor para hora");
    		return;
    	}
    	if(rITI_M.isSelected())
    		data[5] = "1";
    	if(rITEM_M.isSelected())
    		data[5] = "2";
    	if(rITMA_M.isSelected())
    		data[5] = "3";
    	if(rISTI_M.isSelected())
    		data[5] = "4";
    	if(rLAG_M.isSelected())
    		data[5] = "5";
    	if(rLMKT_M.isSelected())
    		data[5] = "6";
    	
    	if(!Validaciones.isExactSize(data[5], 1, "Carrera"))
    		return;
    	
    	/*for(String a : data)
    		if(a.equals("")){
    			error("Ingresa todos los datos");
    			return;
    		}*/
    	
    	if(db.insert("materia", data))
    		error("Materia agregada con Exito!");
    	else
    		error("Error al ingresar la materia");
    }
    
    public void sendAdm(){
    	String[] data = new String[5];
    	DataBaseSQL	db = new DataBaseSQL();
    	
    	data[0] = nomAdm.getText();
    	if(!Validaciones.isName(data[0], 50, "Nombre"))
    		return;
    	data[1] = userAdm.getText();
    	if(!Validaciones.isPositiveInt(data[1], "Clave", 4))
    		return;
    	data[2] = mailAdm.getText();
    	if(!Validaciones.isMail(data[2], 30))
    		return;
    	data[3] = telAdm.getText();
    	if(!Validaciones.isPass(data[3], 25))
    		return;
    	if(passAdm.getText().equals(pass2Adm.getText())){
    		data[4] = passAdm.getText();
    	}else{
    		error("Las contraseñas no concuerdan");
    		return;
    	}
    	
    	if(!Validaciones.isPass(data[4], 25))
    		return;
    	
    	/*for(String a : data)
    		if(a.equals("")){
    			error("Ingresa todos los datos");
    			return;
    		}*/
    	
    	if(db.insert("administrador", data))
    		error("Admin agregado con exito");
		else
			error("Error al ingresar el Admin");
    }
    
    public void deleteProfe(){
    	DataBaseSQL	db = new DataBaseSQL();
		
    	if(db.free("DELETE FROM PROFESOR WHERE CLAVE = " + delUserP.getText()))
			error("Usuario eliminado con exito");
		else
			error("Error al eliminar el usuario");
    }
    
    public void deleteAlum(){
    	DataBaseSQL	db = new DataBaseSQL();
    	
		if(db.free("DELETE FROM ALUMNO WHERE MATRICULA = " + delMatA.getText()))
			error("Usuario eliminado con exito");
		else
			error("Error al eliminar el usuario");
    	
    }
    
    public void deleteMat(){
    	DataBaseSQL	db = new DataBaseSQL();
    	
		if(db.free("DELETE FROM MATERIA WHERE CLAVE = " + delClaveA.getText()))
			error("Materia eliminado con exito");
		else
			error("Error al eliminar la materia");
    }
    
    public void searchProf(){
    	DataBaseSQL	db = new DataBaseSQL();
		HashMap<String, String> data;
		
		data = db.fetchArray("PROFESOR", "CLAVE", bclaveP.getText());
		
		if(data == null){
			error("El profesor no existe");
			return;
		}
		
		bnomP.setText(data.get("NOMBRE"));
		brfcP.setText(data.get("RFC"));
		bdirP.setText(data.get("DIRECCION"));
		btelP.setText(data.get("TEL"));
		bemailP.setText(data.get("EMAIL"));    		
    }
    
    public void searchAlum(){
    	DataBaseSQL	db = new DataBaseSQL();
		HashMap<String, String> data;
		
		data = db.fetchArray("ALUMNO", "MATRICULA", bmatA.getText());
		
		if(data == null){
			error("El alumno no existe");
			return;
		}
		
		bnomA.setText(data.get("NOMBRE"));
		btelA.setText(data.get("TEL"));
		bdirA.setText(data.get("DIRECCION"));
		bmailA.setText(data.get("EMAIL"));
		bcarreraA.setText(data.get("CARRERA"));
    }
    
    public void searchMat(){
    	DataBaseSQL	db = new DataBaseSQL();
    	HashMap<String, String> data;
    	
    	data = db.fetchArray("MATERIA", "CLAVE", bclaveM.getText());
		
    	if(data == null){
			error("La materia no existe");
			return;
		}
		
    	bnomM.setText(data.get("NOMBRE"));
		bprofM.setText(data.get("CLAVE_PROFESOR"));
		baulaM.setText(data.get("AULA"));
		bhoraM.setText(data.get("HORA"));
		bcarreraM.setText(data.get("CARRERA"));
    }
    
    public void setActivePartial(){
    	String selected = "1";
    	DataBaseSQL	db = new DataBaseSQL();
    	
    	if(parcial1.isSelected())
    		selected = "1";
    	if(parcial2.isSelected())
    		selected = "2";
    	if(parcial3.isSelected())
    		selected = "3";
    	if(parcialFinal.isSelected())
    		selected = "4";
    	if(extra.isSelected())
    		selected = "5";
    	
    	if(db.free("Update parcial_activo set activo = " + selected + " where id = 1"))
    		error("Parcial activado con exito");
    	else
    		error("Error, Parcial no activado");
    }
    
    public void addAlumGroup(){
    	DataBaseSQL	db = new DataBaseSQL();
    	String data[] = new String[8];
    	data[0] = materiaIA.getText();
    	data[1] = matriculaIA.getText();
    	for(int i = 2; i < 7; i++)
    		data[i] = "NULL";
    	data[7] = "default";
    	if(db.insert("calificaciones", data))
    		error("Alumno inscrito al grupo correctamente");
    	else
    		error("Alumno no inscrito al grupo");
    }
       
    public void error(String txt){
    	JOptionPane.showMessageDialog(null, txt);
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
        
    }
  
    
}
