/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Francisco Javier Reinaldos Chuecos
 */
public class GestorDeActividades extends Application{
    //declaración de la escena 
    private static Scene escena;
    
    //Metodo del hilo de nuestra app
        @Override
    public void start(Stage stage) throws Exception {
        escena = new Scene(loadFXML("inicio"), 1100, 750);
        stage.setTitle("Gestor de Actividades");
        stage.setScene(escena);
        stage.show();
        }
    
    //Metodo que permite intercambiar entre escenarios
        public static void setRoot(String fxml) throws IOException {
        escena.setRoot(loadFXML(fxml));
    }

    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GestorDeActividades.class.getResource("/Vistas/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
    

    public static void main(String[] args) {
        launch(args);
    }


}
