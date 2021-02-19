package com.task.register.visitor;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visitor {
    private Integer visitorId;
    private String visitorName;
    private Integer visitingFlat;
    private LocalDate visitingDate;
    private LocalTime visitingTime;
    private String visitingStatus;

    public Visitor() {
    }

    public Visitor(Integer visitorId,
                   String visitorName,
                   Integer visitingFlat,
                   LocalDate visitingDate,
                   LocalTime visitingTime,
                   String visitingStatus) {
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.visitingFlat = visitingFlat;
        this.visitingDate = visitingDate;
        this.visitingTime = visitingTime;
        this.visitingStatus = visitingStatus;
    }


    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
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

    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(LocalDate visitingDate) {
        this.visitingDate = visitingDate;
    }

    public LocalTime getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(LocalTime visitingTime) {
        this.visitingTime = visitingTime;
    }

    public String getVisitingStatus() {
        return visitingStatus;
    }

    public void setVisitingStatus(String visitingStatus) {
        this.visitingStatus = visitingStatus;
    }

}

