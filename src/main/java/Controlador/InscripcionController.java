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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Fco Javier Reinaldos Chuecos
 */
public class InscripcionController extends ControladorPrincipal implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<?> cbActividad;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private RadioButton rbMañana;
    @FXML
    private ToggleGroup tgTurno;
    @FXML
    private RadioButton rbTarde;
    @FXML
    private CheckBox cbFuturasAct;
    @FXML
    private CheckBox cbNormas;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private Button btLimpiarForm;
    @FXML
    private Button btEnviarForm;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnInscripcion;
    @FXML
    private Button btnConfiguracion;
    @FXML
    private Button btnActividades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
