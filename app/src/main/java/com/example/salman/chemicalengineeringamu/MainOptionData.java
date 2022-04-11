package com.example.salman.chemicalengineeringamu;

import java.util.ArrayList;

public class    MainOptionData {
    private String subjectName = "";
    private ArrayList<String> notes;
    private ArrayList<String> examPaper;
    private ArrayList<String> material;

    public void setSubjectName(String s){
        this.subjectName = s;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setNotes(String note){
        if(notes == null){
            notes = new ArrayList<>();
        }
        notes.add(note);
    }

    public ArrayList<String> getNotes() {
        if(notes == null){
            notes = new ArrayList<>();
        }
        return notes;
    }
    public void setExamPaper(String examPaper1){
        if(examPaper == null){
            examPaper = new ArrayList<>();
        }
        examPaper.add(examPaper1);
    }

    public ArrayList<String> getExamPaper() {
        if(examPaper == null){
            examPaper = new ArrayList<>();
        }
        return examPaper;
    }
    public void setMaterial(String material1){
        if(material == null){
            material = new ArrayList<>();
        }
        material.add(material1);
    }

    public ArrayList<String> getMaterial() {
        if(material == null){
            material = new ArrayList<>();
        }
        return material;
    }
}
