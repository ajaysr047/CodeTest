package com.au.compiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CodeCompiler {

    @Autowired
    CodeResultRepo codeResultRepo; // To save result to DB

    final private static String dir = "Code/";
    final private static String ext = ".java";
    public  boolean createFile(String fileName){
        System.out.println("Create");
        try {
            File file = new File(dir + fileName + ext);
        }
        catch (Exception e)
        {
            System.out.println("Unable to create file at destination: " + dir + fileName + ext);
            return false;
        }
        return  true;
    }

    public boolean writeToFile(String fileName, String code)
    {
        System.out.println("Write");
        try
        {
            FileWriter fileWriter = new FileWriter(dir + fileName + ext);
            fileWriter.write(code);
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("Unable to write to the file at destination: " + dir + fileName + ext);
            return false;
        }
        return true;
    }
    private String Process(String command) throws IOException {
        StringBuilder result = new StringBuilder();
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",  command);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = reader.readLine()) != null) {
            result.append(line);
            result.append("\n");
        }
        return result.toString();
    }
    private String runProgram(String fileName) throws IOException {
        String result = "";
        System.out.println("Run");
        result = Process("javac " + dir + fileName + ext);
        if(result.equals(""))
            result = Process("java -cp " + dir + " " + fileName);
        return result.equals("") ? "Output empty!" : result;
    }

    public String compile(StudentCode studentCode) throws IOException {
        String code = studentCode.getCode(), fileName = studentCode.getFileName();
        final boolean isFileCreated = createFile(fileName);
        String result = " ";
        if(isFileCreated)
            if(writeToFile(fileName, code))
                result =  runProgram(fileName);
        codeResultRepo.save(new CodeResult(studentCode.getRegisterNumber()+"+"+studentCode.getQuestionNumber(), studentCode.getQuestion(), result));
        return  result;
    }
}
