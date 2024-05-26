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
 *
 * @author Ratia
 */
public class MySQLConnector {
    private String bbdd;
    private String usuario;
    private String password;
    private String host;
    private String puerto;
    private String urlConnection;
    private Connection connection;
    private Statement stmt;
    
    public MySQLConnector(String bbdd, String usuario, String password, String host, String puerto){
        this.bbdd = bbdd;
        this.usuario = usuario;
        this.password = password;
        this.host = host;
        this.puerto = puerto;
        
        Calendar now = Calendar.getInstance();
        TimeZone zonaHoraria = now.getTimeZone();
        
        this.urlConnection = "jdbc:mysql://" + this.host +":"+puerto+ "/" + this.bbdd + "?user=" + this.usuario 
                + "&password=" + this.password + "&useLegacyDatetimeCode=false&serverTimezone=" 
                + zonaHoraria.getID();
    }
    
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
    
    //SELECT  --> ResultSet
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
    
    //INSERT, DELETE, UPDATE --> int
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
    
    
    public boolean disconnect(){
        try{
            connection.close();
            return true;
        }catch(SQLException sql){
            return false;
        }
    }
}
