package com.au.compiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CompilerController {

    @Autowired
    private CodeCompiler codeCompiler;

//    private final static String fileName = "Program";

    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public String compile(@RequestBody StudentCode studentCode) throws IOException {
        return  codeCompiler.compile(studentCode);
    }
}
