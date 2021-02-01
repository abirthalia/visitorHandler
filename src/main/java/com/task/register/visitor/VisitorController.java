package com.task.register.visitor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @RequestMapping("/visitors")
    public List<Visitor> all_visitors(){
        return visitorService.getAllVisitorList();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/visitors")
    public List<Visitor> addVisitor(@RequestBody Visitor visitor){
        return visitorService.addVisitor(visitor);
    }

    @RequestMapping("/visitors/{visitorDate}")
    public List<Visitor> getVisitor(@PathVariable@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate visitorDate){
        return visitorService.getVisitor(visitorDate);
    }




}
