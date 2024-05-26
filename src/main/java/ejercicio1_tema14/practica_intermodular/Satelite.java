/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_tema14.practica_intermodular;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ratia
 */
public class Satelite {
    private final String nombre;
    private final String planeta;
    private final double radio;
    private final double distanciaMediaPlaneta;
    private final double periodoOrbital;
    private final double temperaturaMedia;
    private final String tipoCuerpo;

    public Satelite(ResultSet sat) throws SQLException {
        this.nombre = sat.getString("nombre").toUpperCase();
        this.planeta = sat.getString("planeta");
        this.radio = sat.getDouble("radio_km");
        this.distanciaMediaPlaneta = sat.getDouble("distancia_media_planeta");
        this.periodoOrbital = sat.getDouble("periodo_orbital");
        this.temperaturaMedia = sat.getDouble("temperatura_media");
        this.tipoCuerpo = sat.getString("tipo_cuerpo");
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlaneta() {
        return planeta;
    }

    public double getRadio() {
        return radio;
    }

    public double getDistanciaMediaPlaneta() {
        return distanciaMediaPlaneta;
    }

    public double getPeriodoOrbital() {
        return periodoOrbital;
    }

    public double getTemperaturaMedia() {
        return temperaturaMedia;
    }

    public String getTipoCuerpo() {
        return tipoCuerpo;
    }
}