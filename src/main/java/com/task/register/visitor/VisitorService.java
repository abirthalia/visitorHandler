package com.task.register.visitor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final List<Visitor> visitorList = new ArrayList<>(Arrays.asList(
            new Visitor(
                    "New",
                    "Anosh B",
                    102,
                    LocalDate.of(2021, Month.JANUARY,31),
                    "Entry"
            ),
            new Visitor(
                    "New",
                    "Sach R",
                    203,
                    LocalDate.of(2021,Month.JANUARY,31),
                    "Exit"
            )
    ));
    public List<Visitor>getAllVisitorList(){
        return visitorList;
    }

    public List<Visitor>addVisitor(Visitor visitor){
        visitorList.add(visitor);
        return visitorList;
    }

    public List<Visitor> getVisitor(LocalDate visitorDate){
        return visitorList.stream().filter(visitor -> visitor.getVisitorDate().equals(visitorDate)).collect(Collectors.toList());

    }

}
