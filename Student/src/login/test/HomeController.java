/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.test;

import com.jfoenix.controls.JFXTextArea;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.input.MouseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXListView;

//import com.google.gson.reflect.TypeToken;
//import static com.mashape.unirest.http.HttpClientHelper.request;
import com.mashape.unirest.http.JsonNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * FXML Controller class
 *
 * @author ajay
 */
public class HomeController implements Initializable {

       private String sampleTestRunner, code, mainTestRunner, questionNumber, question;
    @FXML
    private JFXListView questionList;
    
    @FXML
    private JFXTextArea codeArea;
    
    @FXML
    private JFXTextArea outputArea;
    
    @FXML
    private JFXTextArea questionArea;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
    }    
    
    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException, UnirestException
    {
        code = codeArea.getText();
        code  = code.replaceAll("\"", "\\\"");
        System.out.println(code + " test");
//        String data = "{\"registerNumber\":\""+ FXMLDocumentController.registerNumberGlobal +"\", \"code\": \""+ code +"\"}";
        
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        JsonObject data = new JsonObject();
        JsonElement jsonData = gson.toJsonTree(code + "\n" + mainTestRunner );
        JsonElement regis = gson.toJsonTree(FXMLDocumentController.registerNumberGlobal);
        JsonElement fileName = gson.toJsonTree("ProgramRunner"+FXMLDocumentController.registerNumberGlobal);
        JsonElement qNo = gson.toJsonTree(questionNumber);
        JsonElement ques = gson.toJsonTree(question);
        data.add("code", jsonData);
        data.add("registerNumber", regis);
        data.add("fileName", fileName);
        data.add("questionNumber", qNo);
        data.add("question", ques);

        String json = gson.toJson(data);
        System.out.println(json);
        
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/compile")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
          .body(json)
          .asString();

        System.out.println(response.getBody());
        outputArea.setText(response.getBody());
    }
    
    @FXML
    public void getQuestion(ActionEvent event) throws UnirestException
    {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/getQuestions")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
                .asJson();

        JsonNode respObj = response.getBody();
        JSONArray arr;
        arr = respObj.getArray();
        List<Question> questions = new ArrayList<>();
        
        if(arr != null)
        {
            int l = arr.length();
            for(int i=0; i<l;i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                questions.add(new Question(obj.getInt("qNo"), obj.getString("question"), obj.getString("baseCode"), obj.getString("sampleTestRunner"), obj.getString("testRunner")));
            }
        }
        ObservableList<Integer> questionNumbers = FXCollections.observableArrayList();
        questions.stream().forEach((q) -> {
            questionNumbers.add(q.getqNo());
           });
        questionNumbers.sort(Comparator.<Integer>naturalOrder());
        questionList.setItems(questionNumbers);
        questionList.setOnMouseClicked((MouseEvent event1) -> {
            System.out.println("clicked on " + questionList.getSelectionModel().getSelectedItem());
            Object i = questionList.getSelectionModel().getSelectedItem();
            questions.stream().filter((q) -> (i.equals(q.getqNo()))).forEach((q) -> {
                System.out.print(q.getBaseCode());
                question = q.getQuestion();
                questionArea.setText(question);
                codeArea.setText(q.getBaseCode());
                sampleTestRunner = q.getSampleTestRunner().replaceFirst("class ProgramRunner", "class ProgramRunner"+FXMLDocumentController.registerNumberGlobal);
                mainTestRunner = q.getTestRunner().replaceFirst("class ProgramRunner", "class ProgramRunner"+FXMLDocumentController.registerNumberGlobal);
                questionNumber = String.valueOf(q.getqNo());
                outputArea.setText("");
            });
        });
    }
    
    @FXML
    public void handleRun(ActionEvent event)throws Exception
    {
        Compile obj = new Compile();
        code = codeArea.getText();
        String result = obj.compile(code + "\n" + sampleTestRunner, "ProgramRunner"+FXMLDocumentController.registerNumberGlobal);
        System.out.println(code + "\n" + sampleTestRunner);
        outputArea.setText(result);
    }
    
    
}
