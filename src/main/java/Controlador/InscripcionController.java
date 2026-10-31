/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
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
 * @author xicoc
 */
public class InscripcionController implements Initializable {

    @FXML
    private Button btInscripcion;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
