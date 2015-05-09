package ch.makery.address;

import java.sql.*;

import com.mysql.jdbc.Statement;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class DataBaseSQL implements Connection {
    private final String server;
    private final String user;
    private final String pass;
    private final String driver;
    private Connection connection;
    PreparedStatement pstmt;

    public DataBaseSQL(){
        this.driver = "com.mysql.jdbc.Driver";
        this.pass = "";
        this.user = "root";
        this.server = "jdbc:mysql://localhost/intranet";
            try{
                Class.forName(driver);
                connection = DriverManager.getConnection(server,user,pass);
                System.out.println("Conexion realizada con exito");
            }catch(ClassNotFoundException | SQLException e){
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Conexion no realizada a la base de datos.");
            }
    }

    public static void main(String[] args){
        //DataBaseSQL db = new DataBaseSQL();
    }

    public boolean free(String q){
        try {
            Statement query = (Statement) connection.createStatement();
            System.out.println("free: " + q);
            query.executeUpdate(q);
            System.out.println("Exito");
            query.close();
            return true;
        } catch (SQLException e) {
            System.out.println("FAIL");
            return false;
        }
    }
    
    public boolean insert(String tabla, String[] values){
        
        try {
            Statement query = (Statement) connection.createStatement();
            String q1;
            
            if( tabla.equals("producto") ){
                q1 = "select * from producto where NOMBRE = '"+ values[1].toUpperCase() +"'";
                ResultSet rs = query.executeQuery(q1);
                if( rs.next() )  
                    return false;
            }
            
            q1 = "insert into " + tabla + " values(";
            
            for(String txt : values){
                if(txt.equals("NULL"))
                    q1 += txt + ", ";
                else
                    q1 += "'" + txt.toUpperCase() + "', ";
            }
            q1 = q1.substring(0, q1.length()-2);
            q1 += ")";
            System.out.println("insert: " + q1);
            query.executeUpdate(q1);
            System.out.println("Exito");
            query.close();
        } catch (SQLException e) {
            System.out.println("FAIL \n error: " + e.getMessage());
        }
        return true;
    }
       
    public int selectID(String tabla){
        int numberRow = 0;

        try{

            Statement query = (Statement) connection.createStatement();
            ResultSet rs;
            String q1;
            q1 = "select count(*) from " + tabla;
            System.out.println("-" + q1 + "-");
            rs = query.executeQuery(q1);
            while(rs.next()){
                numberRow = rs.getInt("count(*)");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return numberRow;
    }
    
    public HashMap<String, String>fetchArray(String table, String delimiter, String index){
        HashMap<String, String> mapa = new HashMap<String, String>();
        
        try{
            Statement query = (Statement) connection.createStatement();
            String comando = "SELECT * FROM " + table + " WHERE ´" + delimiter + "´ = " + index;
            System.out.println("fetchArray: " + comando);
            ResultSet rs = query.executeQuery(comando);
            
            if( !rs.next() ){
                System.out.println("VACIO");
                return null;
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.first();
            String valor, key;
 
            int cant = rsmd.getColumnCount();
            
            for(int i = 1; i <= cant; i++){
                key = rsmd.getColumnName(i);
                valor = rs.getString(key);
                //System.out.println(key + "-->" + valor);
                mapa.put(key, valor);
            }  
            
        }catch(Exception e){
            System.out.print("Error " + e);
            return null;
        }
        return mapa;
    }
    
    public List<HashMap<String, String>> GetAll(String table, String  delimiter, String index){
        List<HashMap<String,String>> Lista = new ArrayList<HashMap<String, String>>();
        
    	HashMap<String, String> mapa = new HashMap<String, String>();
        
        try{
            Statement query = (Statement) connection.createStatement();
            String comando = "SELECT * FROM " + table + " WHERE "+ "´" + delimiter + "´" + " = " + index;
            System.out.println("fetchArray: " + comando);
            ResultSet rs = query.executeQuery(comando);
            
            if( !rs.next() ){
                System.out.println("VACIO");
                return null;
            }
            
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.first();
            String valor, key;
 
            int cant = rsmd.getColumnCount();
            while(rs.next()){
	            mapa = new HashMap<String, String>();
            	for(int i = 1; i <= cant; i++){
	                key = rsmd.getColumnName(i);
	                valor = rs.getString(key);
	                //System.out.println(key + "-->" + valor);
	                mapa.put(key, valor);
	            }
            	Lista.add(mapa);
            }
            
        }catch(Exception e){
            System.out.print("Error " + e);
            return null;
        }
        return Lista;
    }
    
    public int getIndexOf(String table, String column, String value){
        try{
            Statement query = (Statement) connection.createStatement();
            String comando = "SELECT ID FROM " + table + " WHERE " + column + " = '" + value + "'";

            ResultSet rs = query.executeQuery(comando);
            rs.beforeFirst();
            String cadena;
            int indice;
            
            if( rs.next() ){
                cadena = rs.getString(1);
                indice = Integer.valueOf(cadena);
                return indice;
            }
            else
                return 0;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
    public String getValueOf(String table, String column, int index){
        try{
            Statement query = (Statement) connection.createStatement();
            String comando = "SELECT " + column + " FROM " + table + " WHERE ID = " + String.valueOf(index);
            System.out.println("getValueOf: " + comando);
            ResultSet rs = query.executeQuery(comando);
            rs.first();
            String cadena = rs.getString(1);
            return cadena;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }     
    }
    
    public List<String[]> getProductos(String  idV){
        List<String[]> data = new ArrayList<String[]>();
        try{
            Statement query = (Statement) connection.createStatement();
            ResultSet rs;
            String q1;
            q1 = "select * from desc_venta where id_venta = " + idV ;
            System.out.println("getProductos: " + q1);
            rs = query.executeQuery(q1);
            while(rs.next()){
                String[] a = new String[2];
                a[0] = rs.getString("ID_PRODUCTO");
                a[1] = rs.getString("CANTIDAD");
                data.add(a);
            }
            return data;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
        
    
    public Connection getConnection(){
        return connection;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void clearWarnings() throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void close() throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void commit() throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public Clob createClob() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public java.sql.Statement createStatement() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public java.sql.Statement createStatement(int resultSetType,
                    int resultSetConcurrency) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public java.sql.Statement createStatement(int resultSetType,
                    int resultSetConcurrency, int resultSetHoldability)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public String getCatalog() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public int getHoldability() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
    }

    @Override
    public String getSchema() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public boolean isClosed() throws SQLException {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public boolean isReadOnly() throws SQLException {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType,
                    int resultSetConcurrency) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType,
                    int resultSetConcurrency, int resultSetHoldability)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType,
                    int resultSetConcurrency) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType,
                    int resultSetConcurrency, int resultSetHoldability)
                    throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void rollback() throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setClientInfo(Properties properties)
                    throws SQLClientInfoException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setClientInfo(String name, String value)
                    throws SQLClientInfoException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds)
                    throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public void setSchema(String schema) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
            // TODO Auto-generated method stub

    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            // TODO Auto-generated method stub

    }

}

