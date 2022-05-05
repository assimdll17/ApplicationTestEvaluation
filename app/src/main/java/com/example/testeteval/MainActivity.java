package com.example.testeteval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testeteval.Model.Etudiant;
import com.example.testeteval.Model.Professeur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String[] etudiants = {"toto", "momo", "fifi", "lili"};
    String[] professeurs = {"omar", "ali", "assimdll", "mohamed"};
    String[] mdpEtd = {"azerty", "1234", "0000", "user"};
    String[] mdpProf = {"yuiop", "12345", "abcd", "8888"};
    String[] professions = {"Etudiant", "Professeur"};
    List<Professeur>data=new ArrayList<>();

    public static int findIndex(String arr[], String mot)
    {
        // if array is Null
        if (arr == null) {
            return -1;
        }
        // find length of array
        int len = arr.length;
        int i = 0;
        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == mot) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView badcred = findViewById(R.id.badcred);
        Spinner profession = findViewById(R.id.profession);
        Button button = findViewById(R.id.button);

        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, professions);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profession.setAdapter(dataAdapterR);

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:8087/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        //
        RestApi serviceApi=retrofit.create(RestApi.class); //instanciation dynamique

       Call<List<Professeur>> callUsers=serviceApi.listProfesseurs();  //appel

        Call<List<Etudiant>>calletudiants=serviceApi.listEtudiants();

        //appel prof
        callUsers.enqueue(new Callback<List<Professeur>>() {
            @Override
            public void onResponse(Call<List<Professeur>> call, Response<List<Professeur>> response) {
                Log.i("info","sucess");
                List<Professeur> users=response.body();
               dataAdapterR.notifyDataSetChanged();
                System.out.println(users);
            }

            @Override
            public void onFailure(Call<List<Professeur>> call, Throwable t) {
                Log.i("info","echec");
                Log.i("error",t.getMessage());

            }
        });

        //appel etudiants

        calletudiants.enqueue(new Callback<List<Etudiant>>() {
            @Override
            public void onResponse(Call<List<Etudiant>> call, Response<List<Etudiant>> response) {
                Log.i("info","sucess");
                List<Etudiant> users=response.body();
                dataAdapterR.notifyDataSetChanged();
                System.out.println(users);
            }

            @Override
            public void onFailure(Call<List<Etudiant>> call, Throwable t) {
                Log.i("info","echec");
                Log.i("error",t.getMessage());
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            String selected_profession ="";
            @Override
            public void onClick(View view) {

                    profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected_profession = profession.getSelectedItem().toString();
                        System.out.println(selected_profession);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        badcred.setText("Merci de selectionner une profession");
                    }
                });

                if(profession.getSelectedItem().toString()=="Etudiant"){
                    int i = findIndex(etudiants,username.getText().toString());
                    System.out.println(i);
                    if(mdpEtd[i]==password.getText().toString()){
                        badcred.setText("Vous etes connectés en tant qu'étudiant");
                    }
                    else{
                        badcred.setText("Merci de verifier vos informations de onnexion");
                    }
                }
                if(profession.getSelectedItem().toString()=="Professeur"){
                    int i = findIndex(professeurs,username.getText().toString());
                    if(mdpProf[i]==password.getText().toString()){
                        badcred.setText("Vous etes connectés en tant que professeur");
                    }
                    else{
                        badcred.setText("Merci de verifier vos informations de onnexion");
                    }
                }

                System.out.println("Selectionnéééééééééééé "+profession.getSelectedItem().toString());
            }

        });
    }
}