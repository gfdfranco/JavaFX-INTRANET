package ch.makery.address;

import java.io.IOException;

import javax.swing.JOptionPane;

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

	/* Datos Profesor */
	@FXML
	TextField nomP;
	@FXML
	TextField rfcP;
	@FXML
	TextField telP;
	@FXML
	TextField dirP;
	@FXML
	TextField anioP;
	@FXML
	TextField usuarioP;
	@FXML
	TextField emailP;
	@FXML
	TextField passP;
	@FXML
	TextField pass2P;
	
	/* Datos Alumno */
	@FXML
	TextField nomA;
	@FXML
	TextField matA;
	@FXML
	TextField telA;
	@FXML
	TextField dirA;
	@FXML
	TextField anioA;
	@FXML
	TextField carreraA;
	@FXML
	TextField mailA;
	@FXML
	TextField passA;
	@FXML
	TextField pass2A;
	
	/* Datos Materia */
	@FXML
	TextField nomM;
	@FXML
	TextField claveM;
	@FXML
	TextField profM;
	@FXML
	TextField aulaM;
	@FXML
	TextField horaM;
	@FXML
	TextField carreraM;
	
	/* Datos Admin */
	@FXML
	TextField nomAdm;
	@FXML
	TextField userAdm;
	@FXML
	TextField mailAdm;
	@FXML
	TextField telAdm;
	@FXML
	TextField passAdm;
	@FXML
	TextField pass2Adm;
	
	/* Datos Eliminar */
	@FXML
	TextField delUserP;
	@FXML
	TextField delMatA;
	@FXML
	TextField delClaveA;
	

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
    	String[] data = new String[8];
    	try{
	    	data[0] = nomP.getText();
	    	data[1] = rfcP.getText();
	    	data[2] = telP.getText();
	    	data[3] = dirP.getText();
	    	data[4] = anioP.getText();
	    	data[5] = usuarioP.getText();
	    	data[6] = emailP.getText();
	    	
	    	if(passP.getText().equals(pass2P.getText())){
	    		data[7] = passP.getText();
	    	}else{
	    		JOptionPane.showMessageDialog(null, "Las contraseņas no concuerdan");
	    		return;
	    	}
	    	
	    	if(db.insert("PROFESOR", data))
	    		JOptionPane.showMessageDialog(null, "Profesor agregado con Exito!");
	    	else
	    		JOptionPane.showMessageDialog(null, "Error al ingresar al profesor ");
    	
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
    	}
    	
    }
    
    public void sendAlum(){
    	String[] data = new String[8];
    	try{
	    	data[0] = nomA.getText();
	    	data[1] = matA.getText();
	    	data[2] = telA.getText();
	    	data[3] = dirA.getText();
	    	data[4] = anioA.getText();
	    	data[5] = carreraA.getText();
	    	data[6] = emailP.getText();
	    	
	    	if(passA.getText().equals(pass2A.getText())){
	    		data[7] = passA.getText();
	    	}else{
	    		JOptionPane.showMessageDialog(null, "Las contraseņas no concuerdan");
	    		return;
	    	}
	    	
	    	if(db.insert("ALUMNO", data))
	    		JOptionPane.showMessageDialog(null, "Alumno agregado con Exito!");
	    	else
	    		JOptionPane.showMessageDialog(null, "Error al ingresar al Alumno ");
    	
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
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
	    		JOptionPane.showMessageDialog(null, "Materia agregada con Exito!");
	    	else
	    		JOptionPane.showMessageDialog(null, "Error al ingresar la materia ");
	    	
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
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
	    		JOptionPane.showMessageDialog(null, "Las contraseņas no concuerdan");
	    		return;
	    	}
	    	
	    	if(db.insert("ADMINISTRADOR", data))
	    		JOptionPane.showMessageDialog(null, "Admin agregado con Exito!");
	    	else
	    		JOptionPane.showMessageDialog(null, "Error al ingresar el Admin ");
	    	
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
    	}
    	
    }
    
    public void deleteProfe(){
    	try{
    		db.free("DELETE FROM PROFESOR WHERE USUARIO = " + delUserP.getText());
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
    	}
    }
    
    public void deleteAlum(){
    	try{
    		db.free("DELETE FROM ALUMNO WHERE MATRICULA = " + delMatA.getText());
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
    	}
    }
    
    public void deleteMat(){
    	try{
    		db.free("DELETE FROM MATERIA WHERE CLAVE = " + delClaveA.getText());
    	}catch(NullPointerException e){
    		JOptionPane.showMessageDialog(null, "Llena todos los campos");
    	}
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}