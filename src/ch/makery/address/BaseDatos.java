package ch.makery.address;

import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

public class BaseDatos {
	private static String servidor="jdbc:mysql://localhost/INTRANET";
	private static String user="root";
	private static String pass="";
	private static String driver="com.mysql.jdbc.Driver";
	private static Connection conexion;

	public BaseDatos(){
		try{
		Class.forName(driver);
		conexion=DriverManager.getConnection(servidor,user,pass);
		System.out.println("Conexion realizada con exito");
		}catch(ClassNotFoundException | SQLException e){
		System.out.println("conexion fallada");
		}
	}

	public Connection getConnection(){
		return conexion;
	}

	public PreparedStatement prepareStatement(String sSQL) {
		// TODO Auto-generated method stub
		return null;
	}

}
