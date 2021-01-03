package com.au.compiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResultController {

    @Autowired
    CodeResultRepo codeResultRepo;

    @RequestMapping(value = "/getResults", method = RequestMethod.GET)
    public List<CodeResult> getResult()
    {
        List<CodeResult> results = new ArrayList<>();
        codeResultRepo.findAll().forEach(results::add);
        return results;
    }
}
