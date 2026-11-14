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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.print.StreamPrintServiceFactory;

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
    //Pestaña Todas
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
    @FXML
    private TableView<Actividad> tablaActividades;
    @FXML
    private TableColumn<Actividad, String> colActividad;
    @FXML
    private TableColumn<Actividad, String> colCategoria;
    @FXML
    private TableColumn<Actividad, LocalDate> colFecha;
    @FXML
    private TableColumn<Actividad, String> colPlazas;
    @FXML
    private TableColumn<Actividad, String> colEstado;
    @FXML
    private Button btEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label lblActividadSelec;
    @FXML
    private Label lblTotalActividades;

    /**
     * Initializes the controller class.
     */
    //Metodo para contar el numero de actividades totales
    private void actualizarTotal() {
        int sumActividad = tablaActividades.getItems().size();
        lblTotalActividades.setText("Total Actividades: " + sumActividad);
    }
    
    public void refrescarTabla(){
    tablaActividades.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configuración del combobox categorias
        cbCategorias.getItems().add("Todas");
        
        for(Actividad actividad: GestorDeActividades.getDatos().getActividades()){
        String categoria = actividad.getCategoria();
        if (!cbCategorias.getItems().contains(categoria)){
        cbCategorias.getItems().add(categoria);
        }
        }
        cbCategorias.setValue("Todas");
        //configuración combobox estado
        cbEstado.getItems().add("Todas");

        for (Actividad actividad : GestorDeActividades.getDatos().getActividades()) {
            String estado = actividad.getEstado();
            if (!cbEstado.getItems().contains(estado)) {
                cbEstado.getItems().add(estado);
            }
        }

        //Nombre pestaña incial:
        lblPestañaAct.setText("Pestaña Activa: "+ tabpActividades.getSelectionModel().getSelectedItem().getText());
        //listener para cada vez que cambie pestaña se actuelice el label
        tabpActividades.getSelectionModel().selectedItemProperty().addListener((obs,anterior,nueva)->{
        if(nueva!=null){
        lblPestañaAct.setText("Pestaña Activa: "+nueva.getText());
        }
        });
       
        //creamos la instancia de la tabla
        colActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colPlazas.setCellValueFactory(new PropertyValueFactory<>("plazas"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        //Llamamos al objeto creado desde el main
        
        //Aplicar filtros
        FilteredList<Actividad> filtradas = new FilteredList<>(GestorDeActividades.getDatos().getActividades());
        tablaActividades.setItems(filtradas);

        cbCategorias.valueProperty().addListener((obs, antigua, nueva) -> {
            if (nueva == null || nueva.equals("Todas")) {

                filtradas.setPredicate(a -> true);
            } else {
                filtradas.setPredicate(a -> a.getCategoria().equals(nueva));
            }
        });
        cbEstado.valueProperty().addListener((obs, antigua, nueva) -> {
            if (nueva == null || nueva.equals("Todas")) {

                filtradas.setPredicate(a -> true);
            } else {
                filtradas.setPredicate(a -> a.getEstado().equals(nueva));
            }
        });
        txfBuscar.textProperty().addListener((obs, antigua, nueva) -> {
            if (nueva.isEmpty()) {

                filtradas.setPredicate(a -> true);
            } else {
                filtradas.setPredicate(a -> a.getNombre().toLowerCase().contains(nueva.toLowerCase()));
            }
        });
        
        
        
        //Listener
        tablaActividades.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nueva) -> {

            //botones editar y eliminar disponibles al usar una linea
            boolean seleccion = nueva != null;
            btEditar.setDisable(!seleccion);
            btnEliminar.setDisable(!seleccion);
            //cambio del label según actividad seleccionada
            if (nueva != null) {
                lblActividadSelec.setText("Actividad: " + nueva.getNombre());
            }

        
        });
        //Llamada al metodo para que calcule el total de actividades
        actualizarTotal();
        
        //Listener suma del total de actividades en la tabla (buscado con IA)
        tablaActividades.getItems().addListener((javafx.collections.ListChangeListener<Actividad>) cambio-> {

            actualizarTotal();
        });

        
        
    }    
    
    //hacemos un metodo para abrir el dialogo, ya que lo vamos a reutilizar en editar y nueva actividad
    public void abrirDialogo(Actividad actividad) throws IOException {
        //abrir ventana nueva ventana

        FXMLLoader fxmlLoader = new FXMLLoader(GestorDeActividades.class.getResource("/Vistas/dialogoActividad.fxml"));
        Parent root = fxmlLoader.load();
        //conectar con dialogo de actividad secundario
        DialogoActividadController controladorDialogo = fxmlLoader.getController();
        controladorDialogo.setControladorActividades(this);
        controladorDialogo.setActividad(actividad);

        Scene nuevoDialogo = new Scene(root, 350, 380);
        Stage dialogo = new Stage();
        //ventana de dialogo modal
        dialogo.initOwner(txfBuscar.getScene().getWindow());
        dialogo.initModality(Modality.WINDOW_MODAL);
        dialogo.setTitle(actividad == null ? "Nueva Actividad" : "Editar Actividad");
        dialogo.setScene(nuevoDialogo);
        dialogo.show();

    }

    @FXML
    private void onDialogoNuevaActividad(ActionEvent event) throws IOException {

        //parametro a null para que cree una nueva
        abrirDialogo(null);
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

    @FXML
    private void onEditar(ActionEvent event) throws IOException {
        
       Actividad seleccionada = tablaActividades.getSelectionModel().getSelectedItem();
        abrirDialogo(seleccionada);

        
        
    }

    @FXML
    private void onEliminar(ActionEvent event) {
        Actividad seleccionada = tablaActividades.getSelectionModel().getSelectedItem();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Borrar Actividad");
        alerta.setHeaderText("Se va a borrar la actividad: " + seleccionada.getNombre());
        alerta.setContentText("¿Estás seguro?");
        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            GestorDeActividades.getDatos().getActividades().remove(seleccionada);

        }

    }


}
