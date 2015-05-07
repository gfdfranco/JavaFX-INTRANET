package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAdministrador extends Application {
    
	//CONTROLADOR DE FXML.................................................

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
	@FXML MenuButton carreraA;
	@FXML TextField mailA;
	@FXML TextField passA;
	@FXML TextField pass2A;
	
	/* Datos Materia */
	@FXML TextField nomM;
	@FXML TextField claveM;
	@FXML TextField profM;
	@FXML TextField aulaM;
	@FXML TextField horaM;
	@FXML TextField carreraM;
	
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
	

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private DataBaseSQL db;
    

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
    
    
    public void sendPrfsr(){
    	String[] data = new String[7];
    	try{
	    	data[0] = nomP.getText();
	    	data[1] = rfcP.getText();
	    	data[2] = telP.getText();
	    	data[3] = dirP.getText();
	    	data[4] = usuarioP.getText();
	    	data[5] = emailP.getText();
	    	
	    	if(passP.getText().equals(pass2P.getText())){
	    		data[6] = passP.getText();
	    	}else{
	    		error("Las contraseñas no concuerdan");
	    		return;
	    	}
	    	
	    	if(db.insert("PROFESOR", data))
	    		error("Profesor agregado con Exito!");
	    	else
	    		error("Error al ingresar al Profesor");
    	
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    	
    }
    
    public void sendAlum(){
    	String[] data = new String[7];
    	try{
	    	data[0] = nomA.getText();
	    	data[1] = matA.getText();
	    	data[2] = telA.getText();
	    	data[3] = dirA.getText();
	    	data[4] = carreraA.getText();
	    	data[5] = mailA.getText();
	    	
	    	if(passA.getText().equals(pass2A.getText())){
	    		data[6] = passA.getText();
	    	}else{
	    		error("Las contraseñas no concuerdan");
	    		return;
	    	}
	    	System.out.println("hola");
	    	if(db.insert("ALUMNO", data)){
	    		error("Alumno agregada con Exito!");
	    	}else
	    		error("Error al ingresar al Alumno");
    	
    	}catch(NullPointerException e){
    		error("Llena todos los campos \n" + e);
    	}
    	
    }
    
    public void sendMate(){
    	String[] data = new String[6];
    	try{
	    	data[0] = nomM.getText();
	    	data[1] = claveM.getText();
	    	data[2] = profM.getText();
	    	data[3] = aulaM.getText();
	    	data[4] = horaM.getText();
	    	data[5] = carreraM.getText();
	    	
	    	if(db.insert("MATERIA", data))
	    		error("Materia agregada con Exito!");
	    	else
	    		error("Error al ingresar la materia");
	    	
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    	
    }
    
    public void sendAdm(){
    	String[] data = new String[5];
    	try{
	    	data[0] = nomAdm.getText();
	    	data[1] = userAdm.getText();
	    	data[2] = mailAdm.getText();
	    	data[3] = telAdm.getText();
	    	
	    	if(passAdm.getText().equals(pass2Adm.getText())){
	    		data[4] = passAdm.getText();
	    	}else{
	    		error("Las contraseñas no concuerdan");
	    		return;
	    	}
	    	
	    	if(db.insert("ADMINISTRADOR", data))
	    		error("Admin eliminado con exito");
    		else
    			error("Error al eliminar el Admin");
	    	
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    	
    }
    
    public void deleteProfe(){
    	try{
    		if(db.free("DELETE FROM PROFESOR WHERE USUARIO = " + delUserP.getText()))
    			error("Usuario eliminado con exito");
    		else
    			error("Error al eliminar el usuario");
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    }
    
    public void deleteAlum(){
    	try{
    		if(db.free("DELETE FROM ALUMNO WHERE MATRICULA = " + delMatA.getText()))
    			error("Usuario eliminado con exito");
    		else
    			error("Error al eliminar el usuario");
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    }
    
    public void deleteMat(){
    	try{
    		if(db.free("DELETE FROM MATERIA WHERE CLAVE = " + delClaveA.getText()))
    			error("Materia eliminado con exito");
    		else
    			error("Error al eliminar la materia");
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    }
    
    public void searchProf(){
    	try{
    		HashMap<String, String> data;
    		data = db.fetchArray("PROFESOR", "CLAVE", bclaveP.getText());
    		if(data == null){
    			error("El profesor no existe");
    			return;
    		}
    		bnomP.setText(data.get("NOMBRE"));
    		brfcP.setText(data.get("RFC"));
    		bdirP.setText(data.get("DIRECCION"));
    		btelP.setText(data.get("TELEFONO"));
    		bemailP.setText(data.get("EMAIL"));    		
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    }
    
    public void searchAlum(){
    	try{
    		HashMap<String, String> data;
    		data = db.fetchArray("ALUMNO", "MATRICULA", bmatA.getText());
    		if(data == null){
    			error("El alumno no existe");
    			return;
    		}
    		bnomA.setText(data.get("NOMBRE"));
    		btelA.setText(data.get("TELEFONO"));
    		bdirA.setText(data.get("DIRECCION"));
    		bmailA.setText(data.get("EMAIL"));
    		bcarreraA.setText(data.get("CARRERA"));
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    }
    
    public void searchMat(){
    	try{
    		HashMap<String, String> data;
    		data = db.fetchArray("MATERIA", "CLAVE", bclaveM.getText());
    		if(data == null){
    			error("La materia no existe");
    			return;
    		}
    		bnomM.setText(data.get("NOMBRE"));
    		bprofM.setText(data.get("ID_PROFESOR"));
    		baulaM.setText(data.get("AULA"));
    		bhoraM.setText(data.get("HORA"));
    		bcarreraM.setText(data.get("CARRERA"));
    	}catch(NullPointerException e){
    		error("Llena todos los campos");
    	}
    }
    
    public void help()
    {
    	  
    	System.out.println("YEII");
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