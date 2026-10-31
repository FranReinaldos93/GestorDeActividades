/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Main.GestorDeActividades;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Fco Javier Reinaldos Chuecos
 */
//Clase abstracta para declarar los metodos del menu lateral
public abstract class ControladorPrincipal {
    
    //metodos para el cambio de vistas en el menu lateral
    
    @FXML 
    protected void onAbrirInicio(ActionEvent event) throws IOException{
    
        GestorDeActividades.setRoot("inicio");
    }
    @FXML 
    protected void onAbrirInscripcion(ActionEvent event) throws IOException{
    
        GestorDeActividades.setRoot("inscripcion");
    }
    @FXML 
    protected void onAbrirActividades(ActionEvent event) throws IOException{
    
        GestorDeActividades.setRoot("actividades");
    }
    @FXML 
    protected void onAbrirConfiguracion(ActionEvent event) throws IOException{
        GestorDeActividades.setRoot("configuracion");
    
    }
    
    //MenuBar
    
    //boton Nuevo
    @FXML 
    private void onMenuBarNuevo(ActionEvent event){
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Pendiente");
        alerta.setHeaderText("Funcionalidad pendiente");
        alerta.setContentText("VACIO");
        alerta.showAndWait();
    
    }
            
    //boton Guardar
    @FXML 
    private void onMenuBarGuardar(ActionEvent event){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Pendiente");
        alerta.setHeaderText("Funcionalidad pendiente");
        alerta.setContentText("VACIO");
        alerta.showAndWait();
    }
            
    //boton Salir
    @FXML
    private void onMenuBarSalir(ActionEvent event) {

        //creación de la alerta de confirmación de salida
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmaar Operación");
        alerta.setHeaderText("Salir de la aplicación");
        alerta.setContentText("¿Deseas cerrar la aplicación?");
        
        // si ok, cierra la aplicación, si no, no hace nada
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {
            //para cerrar
            Platform.exit();
        }
    }
    
    //boton Edición/Preferencias
    @FXML 
    private void onMenuBarPreferencias(ActionEvent event) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Pendiente");
        alerta.setHeaderText("Funcionalidad pendiente");
        alerta.setContentText("VACIO");
        alerta.showAndWait();
    }
    
    //boton ayuda/acerca de...
    
    @FXML
    private void onMenuBarAcercaDe(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerce de...");
        alerta.setHeaderText("Información");
        alerta.setContentText("Proyecto: Gestor de Actividades\n" + "Autor: Fco Javier Reinaldos Chuecos\n"+"Versión: 1.0");
        alerta.showAndWait();

    }
    
    
}
