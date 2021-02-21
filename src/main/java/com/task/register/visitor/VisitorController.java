package com.task.register.visitor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/visitors")
    public List<Visitor> getAllVisitorList(){
        return visitorService.getAllVisitorList();
    }

    @PostMapping(value = "/visitors")
    public Visitor addVisitor(@RequestBody Visitor visitor){
        return visitorService.addVisitor(visitor);
    }

    @GetMapping("/visitors/{visitingDate}")
    public List<Visitor> getVisitorByDate(@PathVariable@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate visitingDate){
        return visitorService.getVisitorByDate(visitingDate);
    }

    @GetMapping("visitors/check/{visitorId}")
    public String checkVisitor(@PathVariable("visitorId") Integer visitorId){
        return visitorService.checkVisitor(visitorId);
    }

    @PutMapping(value = "visitors/updateStatus/{visitorId}")
    @ResponseBody
    public Visitor updateVisitor(@PathVariable("visitorId") Integer visitorId, @RequestBody Visitor visitor){
        return visitorService.updateVisitor(visitorId, visitor);
    }






}
