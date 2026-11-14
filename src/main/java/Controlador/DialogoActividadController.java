/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Main.GestorDeActividades;
import Modelo.Actividad;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.stage.Stage;

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

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    
        
    //constructor para conectar con la ventana principal de actividades y su setter
    ActividadesController controladorActividades;

    public void setControladorActividades(ActividadesController ControladorActividades) {
        this.controladorActividades = ControladorActividades;

    }
   
    
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
    

    private Actividad actividadEditar;  

    public void setActividad(Actividad actividad) {
        this.actividadEditar = actividad;
        if (actividad != null) {
            txfNombre.setText(actividad.getNombre());
            cbCategoria.setValue(actividad.getCategoria());
            dpFecha.setValue(actividad.getFecha());
            spPlazasTotal.getValueFactory().setValue(actividad.getPlazasTotales());
            spPlazasOcupadas.getValueFactory().setValue(actividad.getPlazasOcupadas());
        }
    }

    @FXML
    private void onGuardarActividad(ActionEvent event) {

        String nombre = txfNombre.getText().trim();
        String categoria = cbCategoria.getValue();
        LocalDate fecha = dpFecha.getValue();
        int plazastotal = spPlazasTotal.getValue();
        int plazasOcup = spPlazasOcupadas.getValue();

        ArrayList<String> vacios = new ArrayList<>();
        if (nombre.isEmpty()) {
            vacios.add("El campo nombre debe estar relleno");
        }
        if (categoria == null) {
            vacios.add("Debe elegir una categoria");
        }
        if (fecha == null) {
            vacios.add("Debe seleccionar una fecha");
        }
        if (plazastotal == 0) {
            vacios.add("Debe asignar un numero total de plazas");
        }
        if (!vacios.isEmpty()) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error: Faltan datos");
            alertaError.setHeaderText("Error: Faltan datos");
            alertaError.setContentText(" - " + String.join("\n-", vacios));
            alertaError.showAndWait();
            return;

        }

        if (actividadEditar != null) {
            //funcionalidad para cuando es editar
            actividadEditar.setNombre(nombre);
            actividadEditar.setCategoria(categoria);
            actividadEditar.setFecha(fecha);
            if(plazastotal<plazasOcup){
                Alert alertaError= new Alert(Alert.AlertType.ERROR);
                alertaError.setTitle("ERROR");
                alertaError.setHeaderText("El número de plazas total no puede ser menor al ya ocupadas");
                alertaError.setContentText("Revise el número total de plazas");
                alertaError.showAndWait();
                return;
            }
            actividadEditar.setPlazasTotales(plazastotal);
            actividadEditar.setPlazasOcupadas(plazasOcup);
            controladorActividades.refrescarTabla();
        } else {
            //funcionalidad cuando es nueva activdad

            Actividad nueva = new Actividad();
            nueva.setId(GestorDeActividades.getDatos().siguienteId());
            nueva.setNombre(nombre);
            nueva.setCategoria(categoria);
            nueva.setFecha(fecha);
            nueva.setPlazasTotales(plazastotal);
            nueva.setPlazasOcupadas(plazasOcup);
            nueva.setEstado("Abierta");
            GestorDeActividades.getDatos().getActividades().add(nueva);
            controladorActividades.refrescarTabla();

        }
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void onCancelarActividad(ActionEvent event) {
        
        Stage stage= (Stage) btnCancelar.getScene().getWindow();
        stage.close();
        
    }
    
}
