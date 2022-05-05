package com.example.testeteval;

import com.example.testeteval.Model.Etudiant;
import com.example.testeteval.Model.Professeur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("profs")
    Call<List<Professeur>> listProfesseurs();

    @GET("etudiants")
    Call<List<Etudiant>> listEtudiants();
}
