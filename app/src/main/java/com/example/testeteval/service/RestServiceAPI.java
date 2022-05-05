package com.example.testeteval.service;

import com.example.testeteval.model.Etudiant;
import com.example.testeteval.model.Professeur;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestServiceAPI {
    @GET("etudiant")
    Call<Etudiant> etudiantByUserName(@Query("q") String key);

    @GET("prof")
    Call<Professeur> profByUserName(@Query("q") String key);
}
