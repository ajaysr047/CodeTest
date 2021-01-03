/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONObject;


/**
 *
 * @author ajay
 */
public class FXMLDocumentController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private JFXTextField registerNumber;
    
    @FXML
    private JFXPasswordField password;
    
    @FXML
    private Label invalidWarning;
    
    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }
    public static String registerNumberGlobal;
    @FXML
    private void handleLogin(ActionEvent event) throws IOException, UnirestException
    {
//        String data = "{\"registerNumber\":\""+ registerNumber.getText() +"\", \"pass\": \""+ password.getText() +"\"}";
        
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        JsonObject test = new JsonObject();
        JsonElement passwd = gson.toJsonTree(password.getText());
        JsonElement regis = gson.toJsonTree(registerNumber.getText());
        test.add("pass", passwd);
        test.add("registerNumber", regis);

        String json = gson.toJson(test);
        System.out.println(json);
        
        registerNumberGlobal = registerNumber.getText();
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/authStudent")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
          .body(json)
          .asJson();
        JSONObject respObj = response.getBody().getObject();

//        System.out.println(response.getBody());

        if(respObj.getString("auth").equals("Failure"))
        {
            invalidWarning.setText("Invalid Register number/Password");            
        }
            
        else
        {
            
            
            registerNumberGlobal = registerNumber.getText();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
//            stage.initStyle(StageStyle.UTILITY);
            stage.setMaximized(false);
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
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
            
            
            stage.show();
        }
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
