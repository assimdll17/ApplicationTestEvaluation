package com.example.testeteval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testeteval.model.Etudiant;
import com.example.testeteval.model.Professeur;
import com.example.testeteval.service.RestServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String[] professions = {"Etudiant", "Professeur"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy strictMode=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView badcred = findViewById(R.id.badcred);
        Spinner profession = findViewById(R.id.profession);
        Button button = findViewById(R.id.button);

        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, professions);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profession.setAdapter(dataAdapterR);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://localhost:8087/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RestServiceAPI serviceAPI=retrofit.create(RestServiceAPI.class);

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

                String key = username.getText().toString() ;

                if(profession.getSelectedItem().toString()=="Etudiant"){
                    Call<Etudiant> callEtudiant = serviceAPI.etudiantByUserName(key);
                    callEtudiant.enqueue(new Callback<Etudiant>() {
                        @Override
                        public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                            Etudiant etud = response.body();
                            if(etud.getPassword()==password.getText().toString()){
                                badcred.setText("Vous etes connectés en tant qu'étudiant");
                            }
                            else{
                                badcred.setText("Merci de verifier vos informations de onnexion");
                            }

                        }

                        @Override
                        public void onFailure(Call<Etudiant> call, Throwable t) {
                            Log.e("error","Erreur de réseau");
                        }
                    });
                }


                if(profession.getSelectedItem().toString()=="Professeur"){
                    Call<Professeur> callProf = serviceAPI.profByUserName(key);
                    callProf.enqueue(new Callback<Professeur>() {
                        @Override
                        public void onResponse(Call<Professeur> call, Response<Professeur> response) {
                            Professeur prof = response.body();
                            if(prof.getPassword()==password.getText().toString()){
                                badcred.setText("Vous etes connectés en tant que professeur");
                            }
                            else{
                                badcred.setText("Merci de verifier vos informations de onnexion");
                            }
                        }

                        @Override
                        public void onFailure(Call<Professeur> call, Throwable t) {
                            Log.e("error","Erreur de réseau");
                        }
                    });



                }

                System.out.println("Selectionnéééééééééééé "+profession.getSelectedItem().toString());
            }

        });
    }
}