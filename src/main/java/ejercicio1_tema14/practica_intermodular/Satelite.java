/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_tema14.practica_intermodular;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Satelite
 * @author Angel Caballero Espinosa
 */
public class Satelite {
    //Nombre del satelite
    private final String nombre;
    //Planeta al que pertenece
    private final String planeta;
    //Radio en km
    private final double radio;
    //Distancia media al planeta al que pertenece
    private final double distanciaMediaPlaneta;
    //Periodo orbital en dias
    private final double periodoOrbital;
    //Temperatura media en ÂºC
    private final double temperaturaMedia;
    //Tipo de cuerpo
    private final String tipoCuerpo;

    /**
     * Constructor de clase Satelite con objeto ResultSet
     * @param sat ResultSet resultado de query de satelites en base de datos
     * @throws SQLException
     */
    public Satelite(ResultSet sat) throws SQLException {
        this.nombre = sat.getString("nombre").toUpperCase();
        this.planeta = sat.getString("planeta");
        this.radio = sat.getDouble("radio_km");
        this.distanciaMediaPlaneta = sat.getDouble("distancia_media_planeta");
        this.periodoOrbital = sat.getDouble("periodo_orbital");
        this.temperaturaMedia = sat.getDouble("temperatura_media");
        this.tipoCuerpo = sat.getString("tipo_cuerpo");
    }

    /**
     * Obtener nombre satelite
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener planeta al que pertenece el satelite
     * @return planeta
     */
    public String getPlaneta() {
        return planeta;
    }

    /**
     * Obtener radio del satelite
     * @return radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Obtener distancia media del satelite al planeta la que pertenece
     * @return distanciaMediaPlaneta;

     */
    public double getDistanciaMediaPlaneta() {
        return distanciaMediaPlaneta;
    }

    /**
     * Obtener periodo orbital
     * @return periodoOrbital
     */
    public double getPeriodoOrbital() {
        return periodoOrbital;
    }

    /**
     * Obtener temperatura media
     * @return temperaturaMedia
     */
    public double getTemperaturaMedia() {
        return temperaturaMedia;
    }

    /**
     * Obtener tipo de cuerpo
     * @return  tipoCuerpo
     */
    public String getTipoCuerpo() {
        return tipoCuerpo;
    }
}