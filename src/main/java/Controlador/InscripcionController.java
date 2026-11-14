/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Main.GestorDeActividades;
import Modelo.Actividad;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;

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
    private ComboBox<Actividad> cbActividad;
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

    //declaración del metodo para validar el email
    private static final String PATRON_EMAIL = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";
    @FXML
    private Label lblActividad;
    @FXML
    private Label lblNotificacion;
    @FXML
    private Label lblFormulario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //combobox categoria para que coja las actividades creadas en DatosAplicación
        cbActividad.setItems(GestorDeActividades.getDatos().getActividades());
        //convertir el texto del combobox para lectura correcta de la actividad
        cbActividad.setConverter(new StringConverter<Actividad>() {

            @Override
            public String toString(Actividad actividad) {
                return actividad == null ? "" : actividad.getNombre();
            }

            @Override
            public Actividad fromString(String texto) {
                return null;
            }
        });
        //declaración del listener para cambiar el label en función de la actividad:
        cbActividad.valueProperty().addListener((obs, anterior, nuevo) -> {
            
            if (nuevo == null) {
                lblActividad.setText("Ninguna Actividad Seleccionada");
            } else {
                lblActividad.setText("Activdad Seleccionada: " + nuevo.getNombre());
            }
        });
        //debemos hacerlo con un listener, porque si no al usar limpiar no reinicia al hacer click en el checkbox
        cbFuturasAct.selectedProperty().addListener((obs, anterior, nuevo) -> {
            lblNotificacion.setText(nuevo ? "Recibiras notificaciones" : "No recibirás notificaciones");
        });
    }    
    
    @FXML
    private void onLimpiarForm(ActionEvent event) {
        
        if (!txtNombre.getText().trim().isEmpty()
                || !txtApellido.getText().trim().isEmpty()
                || !txtEmail.getText().trim().isEmpty()
                || dpFecha.getValue() != null
                || cbActividad.getValue() != null
                || tgTurno.getSelectedToggle() != null
                || cbFuturasAct.isSelected()
                || cbNormas.isSelected()
                || !taObservaciones.getText().trim().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Limpiar Formulario");
            alerta.setHeaderText("Limpiar Formulario");
            alerta.setContentText("¿Estás seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            
            if (resultado.isPresent()
                    && resultado.get() == ButtonType.OK) {
                
                txtNombre.clear();
                txtApellido.clear();
                txtEmail.clear();
                dpFecha.setValue(null);
                cbActividad.setValue(null);
                tgTurno.selectToggle(null);
                cbFuturasAct.setSelected(false);
                cbNormas.setSelected(false);
                taObservaciones.clear();
                lblFormulario.setText("Formulario incompleto");
            }
        }
    }
    
    @FXML
    private void onEnviarForm(ActionEvent event) {
        //obtener los datos del formulario
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String email = txtEmail.getText().trim();
        LocalDate fecha = dpFecha.getValue();
        RadioButton turno = (RadioButton) tgTurno.getSelectedToggle();
        boolean aceptarNormas = cbNormas.isSelected();
        Actividad actividad = cbActividad.getValue();
        //ArrayList para almacenar los mensajes de error al validar uno por uno
        ArrayList<String> vacios = new ArrayList<>();
        //condición, si el campo está vacio almacena el mensaje en el array
        if (nombre.isEmpty()) {
            vacios.add("El campo nombre está vacio");
        }
        if (apellido.isEmpty()) {
            vacios.add("El campo apellido está vacio");
        }
        if (email.isEmpty()) {
            vacios.add("El campo email está vacío");
        } else if (!email.matches(PATRON_EMAIL)) {
            vacios.add("El correo no tiene un formato válido");
        }
        if (fecha == null) {
            vacios.add("El campo fecha está vacio");
        } else if (fecha.isBefore(LocalDate.now())) {
            vacios.add("La fecha no puede ser anterior a hoy");
        }
        if (actividad == null) {
            vacios.add("Debes elegir alguna actividad");
        }
        if (turno == null) {
            vacios.add("Debes elegir algún turno");
        }
        if (aceptarNormas == false) {
            vacios.add("Tienes que aceptar las normas de participación");
        }
        //Creación de alerta para mostras los mensajes de error por campos sin rellenar
        if (!vacios.isEmpty()) {
            lblFormulario.setText("Formulario incompleto");
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error de validación");
            alertaError.setHeaderText("Formulario incompleto");
            alertaError.setContentText("- " + String.join("\n- ", vacios));
            alertaError.showAndWait();
            return;
        } else {

            //si la actividad ya está completa no se suma
            if (actividad.getPlazasOcupadas() >= actividad.getPlazasTotales()) {
                Alert alertaError = new Alert(Alert.AlertType.ERROR);
                alertaError.setTitle("Actvidad Completa");
                alertaError.setHeaderText("La actividad Seleccionada está completa");
                alertaError.setContentText("Elija otra actividad");
                alertaError.showAndWait();

            } else {
                actividad.setPlazasOcupadas(actividad.getPlazasOcupadas() + 1);

                lblFormulario.setText("¡Formulario Completado y enviado!");
                //si el array está vacio, quiere decir que todo está validad, entonces mostramos la info
                String datos = "Actividad Seleccionada: "+actividad.getNombre()+"\n"
                        +"Nombre: " + nombre + "\n"
                        + "Apellido: " + apellido + "\n"
                        + "Email: " + email + "\n"
                        + "Fecha: " + fecha + "\n"
                        + "Turno: " + turno.getText() + "\n"
                        + "Observaciones: " + taObservaciones.getText();
                Alert alertainf = new Alert(Alert.AlertType.INFORMATION);
                alertainf.setTitle("Actividad Inscrita");
                alertainf.setHeaderText("Datos de la actividad: ");
                alertainf.setContentText(datos);
                alertainf.showAndWait();
            }
        }
        
    }
    
}
