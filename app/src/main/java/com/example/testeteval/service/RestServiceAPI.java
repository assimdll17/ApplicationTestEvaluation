package com.example.testeteval.service;

import com.example.testeteval.Model.Etudiant;
import com.example.testeteval.Model.Professeur;
import com.example.testeteval.Model.Questionnaire;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestServiceAPI {
    @GET("etudiant")
    Call<Etudiant> etudiantByUserName(@Query("q") String key);

    @GET("prof")
    Call<Professeur> profByUserName(@Query("q") String key);

    @GET("questionnaires")
    Call<List<Questionnaire>> getAllquestionnaire();
}
