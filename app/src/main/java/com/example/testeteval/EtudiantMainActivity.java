package com.example.testeteval;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testeteval.Adapter.AdapterQuestionnaire;
import com.example.testeteval.Model.Questionnaire;
import com.example.testeteval.service.RestServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EtudiantMainActivity extends AppCompatActivity {
    List<Questionnaire> questionnaires=new ArrayList<>();
    List<String> data=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant);

        ListView listView=findViewById(R.id.questionnairelistev);
       /* ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);*/
        questionnaires.add(new Questionnaire("test","des",null,null ,null));

        AdapterQuestionnaire userAdapter=new AdapterQuestionnaire(this,R.layout.questionnaire_layout,questionnaires);
        userAdapter.notifyDataSetChanged();
        listView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();


        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:8087/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RestServiceAPI serviceApi=retrofit.create(RestServiceAPI.class);

        Call<List<Questionnaire> > callUsers=serviceApi.getAllquestionnaire();

        callUsers.enqueue(new Callback<List<Questionnaire>>() {
            @Override
            public void onResponse(Call<List<Questionnaire>> call, Response<List<Questionnaire>> response) {
                Log.i("info","sucess");
                List<Questionnaire> users=response.body();
                if(!response.isSuccessful()){

                    Log.i("indo",String.valueOf(response.code()));
                    return;
                }
                for (Questionnaire a:users){
                    questionnaires.add(a);
                    data.add(a.getTitle());
                }
                userAdapter.notifyDataSetChanged();
               // adapter.notifyDataSetChanged();
                System.out.println(questionnaires);
               //attention adapter

            }

            @Override
            public void onFailure(Call<List<Questionnaire>> call, Throwable t) {
                Log.e("error","Error");
                Log.e("error",t.getMessage());
            }


        });


    }
}
