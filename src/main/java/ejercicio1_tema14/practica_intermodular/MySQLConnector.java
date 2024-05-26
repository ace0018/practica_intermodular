/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_tema14.practica_intermodular;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Clase de conexión con base de datos MySQL y ejecución de querys
 * @author Angel Caballero
 */
public class MySQLConnector {
    //Nombre del esquema de base de datos
    private final String bbdd;
    //Usuario de base de datos
    private final String usuario;
    //Contraseña de base de datos
    private final String password;
    //Host/IP de base de datos
    private final String host;
    //Puerto de conexión de base de datos
    private final String puerto;
    //Cadena de conexión completa a la base de datos
    private final String urlConnection;
    //Objecto Connection con la base de datos
    private Connection connection;
    //Objecto Statement para la ejecución de querys
    private Statement stmt;
    
    /**
     * Constructor de la clase con los parámetros de conexión
     * @param bbdd esquema de base de datos
     * @param usuario
     * @param password
     * @param host
     * @param puerto
     */
    public MySQLConnector(String bbdd, String usuario, String password, String host, String puerto){
        this.bbdd = bbdd;
        this.usuario = usuario;
        this.password = password;
        this.host = host;
        this.puerto = puerto;
        
        Calendar now = Calendar.getInstance();
        TimeZone zonaHoraria = now.getTimeZone();
        
        this.urlConnection = "jdbc:mysql://" + this.host + ":" + puerto + "/" + this.bbdd + "?user=" + this.usuario 
                + "&password=" + this.password + "&useLegacyDatetimeCode=false&serverTimezone=" 
                + zonaHoraria.getID();
    }
    
    /**
     * Metodo de conexión a la base de datos
     * @return boolean devuelve true/false dependiendo del resultado de la conexión
     */
    public boolean connect(){
        try{
            connection = (Connection) DriverManager.getConnection(this.urlConnection);
            stmt = connection.createStatement();
            System.out.println("Conectado con exito a la base de datos: " + this.bbdd);
            return true;
        }catch(SQLException sql){
            System.out.println("Error: " + sql.getLocalizedMessage());
            return false;
        }
    }
    
    /**
     * Metodo de ejecución de querys tipo SELECT
     * @param query petición SELECT a base de datos
     * @return ResultSet devuelve el resultado de la query
     */
    public ResultSet selectQuery(String query){
        if(connection != null && stmt != null){
            try{
                ResultSet resultSet = stmt.executeQuery(query);
                System.out.println("Query realizada con éxito: " + query);
                return resultSet;
            }catch(SQLException sql){
                System.out.println("Error: " + sql.getLocalizedMessage());
                return null;
            }
        }else{
            System.out.println("Tienes que conectarte previamente a la base de datos");
            return null;
        }
    }
    
    /**
     * Metodo de ejecución de querys tipo CREATE, UPDATE y DELETE
     * @param query petición CREATE, UPDATE ó DELETE a base de datos
     * @return int devuelve el número de filas afectadas
     */
    public int createUpdateDeleteQuery(String query){
        if(connection != null && stmt != null){
            try{
                int count = stmt.executeUpdate(query);
                System.out.println("Query realizada con éxito: " + query);                
                System.out.println("Filas afectadas: " + count);
                return count;
            }catch(SQLException sql){
                System.out.println("Error: " + sql.getLocalizedMessage());
                return -1;
            }
        }else{
            System.out.println("Tienes que conectarte previamente a la base de datos");
            return -1;
        }
    }
    
    /**
     * Metodo de desconexión de la base de datos
     * @return boolean devuelve true/false dependiendo del resultado de la desconexión
     */
    public boolean disconnect(){
        try{
            connection.close();
            return true;
        }catch(SQLException sql){
            return false;
        }
    }
}
