/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author ajay
 */
public class Compile {
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
        System.out.println("File name: " + fileName);
        String result = "";
        System.out.println("Run");
        result = Process("javac " + dir + fileName + ext);
        if(result.equals(""))
            result = Process("java -cp " + dir + " " + fileName);
        return result.equals("") ? "Output empty!" : result;
    }

    public String compile(String code, String fileName) throws IOException {
        final boolean isFileCreated = createFile(fileName);
        String result = " ";
        if(isFileCreated)
            if(writeToFile(fileName, code))
                result =  runProgram(fileName);
        return  result;
    }
}
