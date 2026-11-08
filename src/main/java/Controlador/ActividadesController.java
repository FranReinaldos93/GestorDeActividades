/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Main.GestorDeActividades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fco Javier Reinaldos Chuecos
 */
public class ActividadesController extends ControladorPrincipal implements Initializable {

    @FXML
    private Button btnInscripcion;
    @FXML
    private Button btnConfiguracion;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnActividades;
    @FXML
    private MenuItem miNuevo;
    @FXML
    private MenuItem miGuardar;
    @FXML
    private MenuItem miSalir;
    @FXML
    private MenuItem miPreferencias;
    @FXML
    private MenuItem miAcercaDe;
    @FXML
    private Button btnNuevaActividad;
    @FXML
    private TextField txfBuscar;
    @FXML
    private ComboBox<String> cbCategorias;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private Button btnBuscar;
    @FXML
    private Label lblPestañaAct;
    @FXML
    private TabPane tabpActividades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCategorias.getItems().addAll(
        "Culturales","Deportivas","Tecnológicas"
        );
        cbEstado.getItems().addAll("Abierta","Completa","Finalizada");
        //Nombre pestaña incial:
        lblPestañaAct.setText("Pestaña Activa: "+ tabpActividades.getSelectionModel().getSelectedItem().getText());
        //listener para cada vez que cambie pestaña se actuelice el label
        tabpActividades.getSelectionModel().selectedItemProperty().addListener((obs,anterior,nueva)->{
        
        if(nueva!=null){
        lblPestañaAct.setText("Pestaña Activa: "+nueva.getText());
        }
        
        });
    }    

    @FXML
    private void onDialogoNuevaActividad(ActionEvent event) throws IOException {
        //abrir ventana nueva ventana

        FXMLLoader fxmlLoader = new FXMLLoader(GestorDeActividades.class.getResource("/Vistas/dialogoActividad.fxml"));
        Parent root = fxmlLoader.load();
        //conectar con dialogo de actividad secundario
        DialogoActividadController controladorDialogo= fxmlLoader.getController();
        controladorDialogo.setControladorActividades(this);
        
        Scene nuevoDialogo = new Scene(root, 350, 380);
        Stage dialogo = new Stage();
        //ventana de dialogo modal
        dialogo.initOwner(txfBuscar.getScene().getWindow());
        dialogo.initModality(Modality.WINDOW_MODAL);
        dialogo.setTitle("Nueva Actividad");
        dialogo.setScene(nuevoDialogo);
        dialogo.show();
    }

    @FXML
    private void onBuscarActividad(ActionEvent event) {

        String buscar = txfBuscar.getText().trim();
        String categoria = cbCategorias.getValue();
        String estado = cbEstado.getValue();
        String filtros = "Filtros aplicados a la busqueda: "+"\n"
                + buscar + "\n" + categoria + "\n" + estado;

        if (buscar.isEmpty() && categoria ==null && estado==null) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Actividad buscada: ");
            alerta.setHeaderText("No has aplicado ningún filtro");
            alerta.setContentText("Se van a mostrar todas las actividades");
            alerta.showAndWait();
            
        } else {
            Alert alertainf = new Alert(Alert.AlertType.INFORMATION);
            alertainf.setTitle("Actividad buscada: ");
            alertainf.setHeaderText("Filtros usados : ");
            alertainf.setContentText(filtros);
            alertainf.showAndWait();
        }

    }



}
