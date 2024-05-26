/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_tema14.practica_intermodular;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Planeta
 * @author Angel Caballero Espinosa
 */
public class Planeta {
    //Nombe del planeta
    private final String nombre;
    //Estrella al que pertenece
    private final String estrella;
    //Radio en KM
    private final double radio;
    //Distancia media al sol
    private final double distanciaMediaSol;
    //Periodo orbital en dias
    private final double periodoOrbital;
    //Temperatua media del planeta
    private final double temperaturaMedia;
    //Tipo del planeta
    private final String tipoPlaneta;
    //Cantidad de satelites que tiene el planeta
    private final int numeroSatelites;

        /**
         * Constructor de clase planeta con objeto ResultSet
         * @param planeta nombre del planeta
         * @throws SQLException 
         */
    public Planeta(ResultSet planeta) throws SQLException {
        planeta.next();
        this.nombre = planeta.getString("nombre").toUpperCase();
        this.estrella = planeta.getString("estrella");
        this.radio = planeta.getDouble("radio_km");
        this.distanciaMediaSol = planeta.getDouble("distancia_media_sol");
        this.periodoOrbital = planeta.getDouble("periodo_orbital");
        this.temperaturaMedia = planeta.getDouble("temperatura_media");
        this.tipoPlaneta = planeta.getString("tipo_planeta");
        this.numeroSatelites = planeta.getInt("numero_satelites");
    }
    
    /**
    * Obtener el nombre del planeta
    */
    public String getNombre() {
        return nombre;
    }
    /**
    * Obtener el radio del  planeta
    */
    public double getRadio() {
        return radio;
    }
    /**
     * Obtener la distancia media al sol
     * @return distanciaMediaSol
     */
    public double getDistanciaMediaSol() {
        return distanciaMediaSol;
    }
    /**
     * Obtener el periodo orbital del planeta
     * @return periodoOrbital
     */
    public double getPeriodoOrbital() {
        return periodoOrbital;
    }
    /**
     * Obtener temperatura media del planeta
     * @return temperaturaMedia
     */
    public double getTemperaturaMedia() {
        return temperaturaMedia;
    }
    /**
     * Obtener el tipo de planeta
     * @return tipoPlaneta
     */
    public String getTipoPlaneta() {
        return tipoPlaneta;
    }
    /**
     * Obtener el numero de satelites del planeta
     * @return numeroSatelites
     */
    public int getNumeroSatelites() {
        return numeroSatelites;
    }
}