package com.example.testeteval.Model;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire {
    private Long questionnaireId;
    private String title;
    private String description;

    @Override
    public String toString() {
        return "Questionnaire{" +
                "questionnaireId=" + questionnaireId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", professeur=" + professeur +
                '}';
    }

    public Questionnaire() {
    }

    public Questionnaire(String title, String description, List<Question> questions, Professeur professeur, List<Etudiant> etudiants) {
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.professeur = professeur;
        this.etudiants = etudiants;
    }

    List<Question>questions=new ArrayList<>();

    private Professeur professeur;

    List<Etudiant>etudiants=new ArrayList<>();

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}
