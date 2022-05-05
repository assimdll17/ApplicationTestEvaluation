package com.example.testeteval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] etudiants = {"toto", "momo", "fifi", "lili"};
    String[] professeurs = {"omar", "ali", "assimdll", "mohamed"};
    String[] mdpEtd = {"azerty", "1234", "0000", "user"};
    String[] mdpProf = {"yuiop", "12345", "abcd", "8888"};
    String[] professions = {"Etudiant", "Professeur"};

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