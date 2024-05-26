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
public class Planeta {
    private final String nombre;
    private final String estrella;
    private final double radio;
    private final double distanciaMediaSol;
    private final double periodoOrbital;
    private final double temperaturaMedia;
    private final String tipoPlaneta;
    private final int numeroSatelites;

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

    public String getNombre() {
        return nombre;
    }

    public double getRadio() {
        return radio;
    }

    public double getDistanciaMediaSol() {
        return distanciaMediaSol;
    }

    public double getPeriodoOrbital() {
        return periodoOrbital;
    }

    public double getTemperaturaMedia() {
        return temperaturaMedia;
    }

    public String getTipoPlaneta() {
        return tipoPlaneta;
    }

    public int getNumeroSatelites() {
        return numeroSatelites;
    }
}