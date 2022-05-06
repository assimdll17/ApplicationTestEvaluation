package com.example.testeteval.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testeteval.Model.Questionnaire;
import com.example.testeteval.R;

import java.util.List;

public class AdapterQuestionnaire extends ArrayAdapter<Questionnaire> {

    private List<Questionnaire>questionnaires;
    private int resource;
    public AdapterQuestionnaire(@NonNull Context context, int resource,List<Questionnaire>quest) {
        super(context, resource,quest);
        this.questionnaires=quest;
        this.resource=resource;
        System.out.println(this.questionnaires);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listViewItem=convertView;
        System.out.println("nullll 2");
        //recup du layout des objets
        if(listViewItem==null){
            //contruire la vue  en fonction des elements du fihier xml==onsetview de oncreate
            System.out.println("nullll");
            listViewItem= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }

        TextView titre=listViewItem.findViewById(R.id.titreQuestionnaire);
        TextView des=listViewItem.findViewById(R.id.description);
        TextView profeseur=listViewItem.findViewById(R.id.prof);
        RatingBar rat=listViewItem.findViewById(R.id.ratingBar);

        titre.setText(questionnaires.get(position).getTitle());
        des.setText(questionnaires.get(position).getDescription());

        //profeseur.setText(questionnaires.get(position).getProfesseur().toString());
        rat.setRating(Math.random()>0.6? 3:2);
        System.out.println("ok view");

        return  listViewItem;
    }
}
