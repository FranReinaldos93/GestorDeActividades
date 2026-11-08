/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fco Javier Reinaldos Chuecos
 */
public class DialogoActividadController implements Initializable {

    @FXML
    private TextField txfNombre;
    @FXML
    private ComboBox<String> cbCategoria;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private Spinner<Integer> spPlazasTotal;
    @FXML
    private Spinner<Integer> spPlazasOcupadas;
    
        //constructor para conectar con la ventana principal de actividades
    private ActividadesController ControladorActividades;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbCategoria.getItems().addAll("Culturales","Deportivas","Tecnológicas");
        
        //inicializar los spinner,(mínimo, máximo, valor inicial, paso)
        spPlazasTotal.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        spPlazasOcupadas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        // TODO
    }    
    
    //metodo para conectar ambos controladores
        public void setControladorActividades(ActividadesController ControladorActividades) {
        this.ControladorActividades = ControladorActividades;
        
        
    }

    @FXML
    private void onGuardarActividad(ActionEvent event) {
        
        Alert alertainf = new Alert(Alert.AlertType.INFORMATION);
        alertainf.setTitle("Nueva Actividad");
        alertainf.setHeaderText("Más Adelante");
        alertainf.setContentText("Se creará en la practica 3");
        alertainf.showAndWait();
    }

    @FXML
    private void onCancelarActividad(ActionEvent event) {
    }
    
}
