/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Main.GestorDeActividades;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Fco Javier Reinaldos Chuecos
 */
public class ConfiguracionController extends ControladorPrincipal implements Initializable {

    @FXML
    private Button btnInscripcion;
    @FXML
    private Button btnActividades;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnConfiguracion;
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
    private ComboBox<String> cbTema;
    @FXML
    private ColorPicker cpColor;
    @FXML
    private Spinner<Integer> spZoom;
    @FXML
    private Label lblValorSlider;
    @FXML
    private Button btnRestablecer;
    @FXML
    private Button btnGuardar;
    @FXML
    private Slider slTamano;
    @FXML
    private CheckBox chbImagenes;
    @FXML
    private CheckBox chbDescripciones;
    @FXML
    private CheckBox chbDiseno;
    @FXML
    private CheckBox chbAnimaciones;
    @FXML
    private CheckBox chbConsejos;
    @FXML
    private ComboBox<String> cmbIdioma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTema.getItems().addAll("Claro(por defecto)","Oscuro");
        spZoom.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50, 1));
        double valorSlider= slTamano.getValue();
        lblValorSlider.setText((int)valorSlider+" px");
        slTamano.valueProperty().addListener((obs,anterior,nuevo)->{
        lblValorSlider.setText(nuevo.intValue()+" px");
        });
        cmbIdioma.getItems().addAll("Español(España)","Ingles","Francés","Alemán","Ruso");
    }    

    @FXML
    private void onRestablecer(ActionEvent event) {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Restablecer configuración");
        alerta.setHeaderText("Se va a restablecer la configuración por defecto");
        alerta.setContentText("¿Estás seguro?");
        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {
            cbTema.setValue(null);
            cpColor.setValue(Color.WHITE);
            slTamano.setValue(30);
            spZoom.getValueFactory().setValue(50);
            chbImagenes.setSelected(false);
            chbDescripciones.setSelected(false);
            chbDiseno.setSelected(false);
            chbAnimaciones.setSelected(false);
            chbConsejos.setSelected(false);
            cmbIdioma.setValue(null);
        }
    }

    @FXML
    private void onGuardaCambios(ActionEvent event) {

        String tema = cbTema.getValue();
        Color color = cpColor.getValue();
        int tamano = (int)slTamano.getValue();
        int zoom = spZoom.getValue();
        String idioma = cmbIdioma.getValue();
        ArrayList<String> checkbox = new ArrayList<>();
        if(chbImagenes.isSelected())checkbox.add("Mostrar imágenes en las actividades");
        if(chbDescripciones.isSelected())checkbox.add("Mostrar descripciones completas");
        if(chbDiseno.isSelected())checkbox.add("Usar diseño compacto");
        if(chbAnimaciones.isSelected())checkbox.add("Animaciones de la interfez");
        if(chbConsejos.isSelected())checkbox.add("Mostrar consejos de ayuda");
        //String marcadas: buscado en la IA para ver como darle formato al Array
        String marcadas= "- " + String.join("\n- ", checkbox);
        String nuevaConfig = "Tema: "+tema+"\n"
                +"Color: "+color+"\n"
                +"Tamaño de letra: "+tamano+"\n"
                +"% zoom: "+zoom+"\n"
                +"Idioma: "+idioma+"\n"
                +"Opciones de visualización marcadas: "+"\n"+marcadas;

        Alert alertainf = new Alert(Alert.AlertType.INFORMATION);
        alertainf.setTitle("Guardar configuración");
        alertainf.setHeaderText("Configuración cambiada: ");
        alertainf.setContentText(nuevaConfig);
        alertainf.showAndWait();

    }




}
