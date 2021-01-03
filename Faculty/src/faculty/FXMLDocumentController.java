/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ajay
 */
public class FXMLDocumentController implements Initializable {
    
    private final String baseCodeString = "class Program\n {\n \tpublic static String print()\n \t{\n /* Write your code here. Do not modify this function definition */ \n\n\t} \n}";
    private final String sampleTestAreaString = "class ProgramRunner\n {\n \tpublic static void main(String args[])throws Exception\n \t{\n \t\tProgram obj = new Program();\n \t}\n }";
    private final String mainTestAreaString = "class ProgramRunner\n {\n \tpublic static void main(String args[])throws Exception\n \t{\n \t\tProgram obj = new Program();\n \t}\n }";
    
    private boolean isSampleTestChecked = false, isMainTestChecked = false;
    
    @FXML
    private JFXListView questionList, studentList, studentResultList, studentResultQuestionList;
    
    @FXML
    private JFXTextField fromText, toText, removeRegisterNumber, addOne, questionNumber, removeQuestionNumber, viewQuestionNumber;
    
    @FXML
    private JFXTextArea questionArea, baseCodeArea, sampleTestArea, mainTestArea, testOutputArea, viewQuestionArea, viewBaseCodeArea, viewSampleTestRunnerArea, viewMainTestRunnerArea, studentResultArea;
    
    @FXML
    private Label infoLabel, testInfo;
    
   @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }
    
    private void postStudent(String registerNumber, String password) throws UnirestException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        JsonObject data = new JsonObject();
        JsonElement passwd = gson.toJsonTree(password);
        JsonElement regis = gson.toJsonTree(registerNumber);
        data.add("pass", passwd);
        data.add("registerNumber", regis);

        String json = gson.toJson(data);
        System.out.println(json);
        
       
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/add")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
          .body(json)
          .asString();
    }
    
    private void updateStudentList() throws UnirestException
    {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/getStudents")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
                .asJson();
                
        JsonNode respObj = response.getBody();
        JSONArray arr;
        arr = respObj.getArray();
        
        List<Student> students = new ArrayList<>();
        
        if(arr != null)
        {
            int l = arr.length();
            for(int i=0; i<l;i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                students.add(new Student( obj.getString("registerNumber"), obj.getString("pass")));
            }
        }
        ObservableList<String> studentObList = FXCollections.observableArrayList();
        students.stream().forEach((q) -> {
            studentObList.add(q.getRegisterNumber());
           });

        studentObList.sort(Comparator.<String>naturalOrder());
        studentList.setItems(studentObList);
    }
    
    @FXML
    private void addRange(ActionEvent event) throws UnirestException
    {
        String from = fromText.getText();
        String to = toText.getText();
       if(!from.equals("") && !to.equals("") && from.length() == 10 && to.length() == 10)
       {
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            JsonObject data = new JsonObject();
            JsonElement fromReg = gson.toJsonTree(Integer.parseInt(from));
            JsonElement toReg = gson.toJsonTree(Integer.parseInt(to));
            data.add("fromRegisterNumber", fromReg);
            data.add("toRegisterNumber", toReg);

            String json = gson.toJson(data);
            System.out.println(json);


            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:8080/addRange")
              .header("Content-Type", "application/json")
              .header("Content-Type", "text/plain")
              .body(json)
              .asString();
            infoLabel.setText("Register Numbers added!");
            updateStudentList();
            fromText.setText("");
            toText.setText("");
       }
       else
           infoLabel.setText("Register Number not valid!");
       
    }
    
    @FXML
    private void addOneReg(ActionEvent event) throws UnirestException
    {
        String regisNum = addOne.getText();
        String password = regisNum.substring(6);
         if(!regisNum.equals("") && regisNum.length() == 10)
       {
           postStudent(regisNum, password);
           infoLabel.setText("Register Numbers added!");
           addOne.setText("");
       }
         updateStudentList();
    }
    
    @FXML
    private void removeStudent(ActionEvent event) throws UnirestException
    {
        String regisNum = removeRegisterNumber.getText();
        String password = regisNum.substring(6);
        if(!regisNum.equals("") && regisNum.length() == 10)
       {
           Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            JsonObject data = new JsonObject();
            JsonElement passwd = gson.toJsonTree(password);
            JsonElement regis = gson.toJsonTree(regisNum);
            data.add("pass", passwd);
            data.add("registerNumber", regis);
            String json = gson.toJson(data);
            System.out.println(json);

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:8080/remove")
              .header("Content-Type", "application/json")
              .header("Content-Type", "text/plain")
              .body(json)
              .asString();
            removeRegisterNumber.setText("");
           }
        updateStudentList();
        infoLabel.setText("Register Number removed!");
    }
    
    @FXML
    private void removeAllStudent(ActionEvent event) throws UnirestException
    {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:8080/removeAll")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
                .asString();
        updateStudentList();
        infoLabel.setText("All Register Numbers removed!");
    }
    
    
    @FXML
    private void sampleTest(ActionEvent event) throws IOException
    {
        String baseCode = baseCodeArea.getText();
        String sampleTest = sampleTestArea.getText();
        Compile compiler = new Compile();
        String result = compiler.compile(baseCode + "\n" + sampleTest, "ProgramRunner");
        
        testOutputArea.setText(result);
        isSampleTestChecked = true;
    }
    
    @FXML
    private void mainTest(ActionEvent event) throws IOException
    {
        String baseCode = baseCodeArea.getText();
        String sampleTest = mainTestArea.getText();
        Compile compiler = new Compile();
        String result = compiler.compile(baseCode + "\n" + sampleTest, "ProgramRunner");
        
        testOutputArea.setText(result);
        isMainTestChecked = true;
    }
    
    @FXML
    private void addQuestion(ActionEvent event) throws UnirestException
    {
        String questionNum = questionNumber.getText();
        String question = questionArea.getText();
        if(isSampleTestChecked && isMainTestChecked && !questionNum.equals("") && !question.equals(""))
        {
            //Post question
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            JsonObject data = new JsonObject();
            JsonElement qNo = gson.toJsonTree(Integer.parseInt(questionNum));
            JsonElement ques = gson.toJsonTree(question);
            JsonElement baseCode = gson.toJsonTree(baseCodeArea.getText());
            JsonElement sampleTestRunner = gson.toJsonTree(sampleTestArea.getText());
            JsonElement mainTestRunner = gson.toJsonTree(mainTestArea.getText());
            data.add("qNo", qNo);
            data.add("question", ques);
            data.add("baseCode", baseCode);
            data.add("sampleTestRunner", sampleTestRunner);
            data.add("testRunner", mainTestRunner);
            String json = gson.toJson(data);
            System.out.println(json);

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:8080/addQuestion")
              .header("Content-Type", "application/json")
              .header("Content-Type", "text/plain")
              .body(json)
              .asString();
            
            questionBaseState();
        }
        else
            testInfo.setText("Run main & test sample cases!!");
        
        
    }
    
    @FXML
    private void removeQuestion(ActionEvent event) throws UnirestException
    {
       String qno = removeQuestionNumber.getText();
       if(!qno.equals(""))
       {
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            JsonObject data = new JsonObject();
            JsonElement qNo = gson.toJsonTree(Integer.parseInt(qno));
            data.add("qNo", qNo);
            String json = gson.toJson(data);
            System.out.println(json);

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:8080/removeQuestion")
              .header("Content-Type", "application/json")
              .header("Content-Type", "text/plain")
              .body(json)
              .asString();
       }   
    }
    
     @FXML
    private void removeAllQuestion(ActionEvent event) throws UnirestException
    {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:8080/removeAllQuestion")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
          .asString();  
    }
    
    private void questionBaseState()
    {
        questionNumber.setText("");
        questionArea.setText("");
        baseCodeArea.setText(baseCodeString);
        sampleTestArea.setText(sampleTestAreaString);
        mainTestArea.setText(mainTestAreaString);
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
                viewQuestionNumber.setText(String.valueOf(q.getqNo()));
                viewBaseCodeArea.setText(q.getBaseCode());
                viewQuestionArea.setText(q.getQuestion());
                viewSampleTestRunnerArea.setText(q.getSampleTestRunner());
                viewMainTestRunnerArea.setText(q.getTestRunner());
            });
        });
    }
    
    @FXML
    public void getResult(ActionEvent event) throws UnirestException
    {
         Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/getResults")
          .header("Content-Type", "application/json")
          .header("Content-Type", "text/plain")
                .asJson();
                
        JsonNode respObj = response.getBody();
        JSONArray arr;
        
        arr = respObj.getArray();
        
        List<CodeResult> codeResults = new ArrayList<>();
        
        if(arr != null)
        {
            int l = arr.length();
            for(int i=0; i<l;i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                codeResults.add(new CodeResult( obj.getString("registerNumberAndQuestionNumber"), obj.getString("question"), obj.getString("result")));
            }
        }
        ObservableList<String> studentResultQues = FXCollections.observableArrayList();

        studentResultQuestionList.setItems(studentResultQues);
        
        ObservableSet<String> studentObSet = FXCollections.observableSet();
        codeResults.stream().forEach((codeResult) -> {
            studentObSet.add(codeResult.getRegisterNumberAndQuestionNumber().split("\\+")[0]);
           });
        ObservableList<String> studentObList = FXCollections.observableArrayList(studentObSet);
        studentObList.sort(Comparator.<String>naturalOrder());
        
        studentResultList.setItems(studentObList);
        
        HashMap<String, String> questionResult = new HashMap<>();
        
        studentResultList.setOnMouseClicked((MouseEvent event1) -> {
            System.out.println("clicked on " + studentResultList.getSelectionModel().getSelectedItem());
                questionResult.clear();
                String reg = String.valueOf(studentResultList.getSelectionModel().getSelectedItem());
                studentResultQues.clear();
                codeResults.stream().forEach((codeResult) -> {
                        if(reg.equals(codeResult.getRegisterNumberAndQuestionNumber().split("\\+")[0]))
                        {
                            studentResultQues.add(codeResult.getRegisterNumberAndQuestionNumber().split("\\+")[1]);
                            questionResult.put(codeResult.getRegisterNumberAndQuestionNumber().split("\\+")[1], codeResult.getResult());
                        }
                });
                studentResultQues.sort(Comparator.<String>naturalOrder());
                studentResultQuestionList.setItems(studentResultQues);
                
                studentResultArea.clear();
            });
       
        studentResultQuestionList.setOnMouseClicked((MouseEvent event1) -> {
            
            String quesNo = String.valueOf(studentResultQuestionList.getSelectionModel().getSelectedItem());
            String result = questionResult.get(quesNo);
            studentResultArea.setText(result);
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        baseCodeArea.setText(baseCodeString);
        sampleTestArea.setText(sampleTestAreaString);
        mainTestArea.setText(mainTestAreaString);
    }    
    
}
