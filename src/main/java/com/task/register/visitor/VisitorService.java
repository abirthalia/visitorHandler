package com.task.register.visitor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final List<Visitor> visitorList = new ArrayList<>(Arrays.asList(
            new Visitor(
                    001,
                    "Anosh B",
                    102,
                    LocalDate.of(2021, Month.JANUARY,31),
                    LocalTime.of(9,30),
                    "Entering"
            ),
            new Visitor(
                    002,
                    "Sach R",
                    203,
                    LocalDate.of(2021,Month.JANUARY,31),
                    LocalTime.of(10,00),
                    "Entering"
            )
    ));
    public List<Visitor>getAllVisitorList(){
        return visitorList;
    }

    public Visitor addVisitor(Visitor visitor){
        visitorList.add(visitor);
        return visitor;
    }

    public List<Visitor> getVisitorByDate(LocalDate visitingDate){
        return visitorList.stream().filter(visitor -> visitor.getVisitingDate().equals(visitingDate)).collect(Collectors.toList());

    }

    public String checkVisitor(Integer visitorId) {
        for (Visitor visitor : visitorList)
            if (visitor.getVisitorId() == visitorId) {
                return "Visitor already Exists";
            }
            else {
                return "Visitor does not Exists";
            }
        return null;
    }

    public Visitor updateVisitor(Integer visitorId, Visitor visitor) {
        for (int i=0; i< visitorList.size() ; i++){
            Visitor visitor1 = visitorList.get(i);
            if (visitor1.getVisitorId().equals(visitorId)){
                visitorList.set(i, visitor);
                return visitor;
            }
        }
        return null;

    }
}
