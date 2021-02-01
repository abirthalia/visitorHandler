package com.task.register.visitor;

import java.time.LocalDate;

public class Visitor {
    private String newVisitor;
    private String visitorName;
    private Integer visitingFlat;
    private LocalDate visitorDate;
    private String visitorEntryOrExit;

    public Visitor() {
    }

    public Visitor(String newVisitor, String visitorName, Integer visitingFlat, LocalDate visitorDate, String visitorEntryOrExit) {
        this.newVisitor = newVisitor;
        this.visitorName = visitorName;
        this.visitingFlat = visitingFlat;
        this.visitorDate = visitorDate;
        this.visitorEntryOrExit = visitorEntryOrExit;
    }


    public String getNewVisitor() {
        return newVisitor;
    }

    public void setNewVisitor(String newVisitor) {
        this.newVisitor = newVisitor;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Integer getVisitingFlat() {
        return visitingFlat;
    }

    public void setVisitingFlat(Integer visitingFlat) {
        this.visitingFlat = visitingFlat;
    }

    public LocalDate getVisitorDate() {
        return visitorDate;
    }

    public void setVisitorDate(LocalDate visitorDate) {
        this.visitorDate = visitorDate;
    }

    public String getVisitorEntryOrExit() {
        return visitorEntryOrExit;
    }

    public void setVisitorEntryOrExit(String visitorEntryOrExit) {
        this.visitorEntryOrExit = visitorEntryOrExit;
    }

}
