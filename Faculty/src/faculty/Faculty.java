/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ajay
 */
public class Faculty extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        
        stage.setScene(scene);
        //Make Undecorated scene dragable
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   xOffset = event.getSceneX();
                   yOffset = event.getSceneY();
               }
           });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   stage.setX(event.getScreenX() - xOffset);
                   stage.setY(event.getScreenY() - yOffset);
               }
           });
        //
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
