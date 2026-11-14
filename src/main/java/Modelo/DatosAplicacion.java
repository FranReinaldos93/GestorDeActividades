/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fco Javier Reinaldos Chuecos
 */
public class DatosAplicacion {

    public DatosAplicacion() {
        cargarActividades();
    }
    
    private final ObservableList<Actividad> actividades = FXCollections.observableArrayList();
    
    private void cargarActividades(){
        
        actividades.addAll(
        
        crearActividadesIniciales(1, "Taller de JavaFX", "Tecnológica", LocalDate.of(2026, 11, 12), 20, 8, "Abierta"),
        crearActividadesIniciales(2, "Taller de Teatro", "Cultural", LocalDate.of(2026, 11, 18), 25, 12, "Abierta"),
        crearActividadesIniciales(3, "Torneo de Futbol sala", "Deportiva", LocalDate.of(2026, 11, 21), 16, 16, "Completa"),
        crearActividadesIniciales(4, "Voluntariado Ambiental", "Solidaria", LocalDate.of(2026, 11, 27), 30, 5, "Abierta"),
        crearActividadesIniciales(5, "Concurso de Fotografia", "Cultural", LocalDate.of(2026, 12, 2), 20, 9, "Abierta"),
        crearActividadesIniciales(6, "Robótica educativa", "Tecnológica", LocalDate.of(2026, 12, 10), 15, 0, "Abierta")
        );
    
    }
    public ObservableList<Actividad> getActividades() {
        return actividades;
    }
    
    //creamos un método para crear actividades nuevas como indica en el enunciado
    private Actividad crearActividadesIniciales(int id,String nombre, String categoria,LocalDate fecha, int totales,int ocupadas,String estado){
    
    Actividad a = new Actividad();
    a.setId(id);
    a.setNombre(nombre);
    a.setCategoria(categoria);
    a.setFecha(fecha);
    a.setPlazasTotales(totales);
    a.setPlazasOcupadas(ocupadas);
    a.setEstado(estado);
    return a;
    }
    
    //metodo para calcular id automático
    
    public int siguienteId() {
        int max = 0;
        for (Actividad a : actividades) {
            if (a.getId() > max) {
                max = a.getId();
            }
        }
        return max + 1;
    }
    
}
