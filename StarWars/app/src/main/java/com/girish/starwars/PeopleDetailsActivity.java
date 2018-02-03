package com.girish.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.girish.starwars.models.People;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleDetailsActivity extends AppCompatActivity {
    @BindView(R.id.tvNameValue) TextView tvNameValue;
    @BindView (R.id.tvHeightValue) TextView tvHeightValue;
    @BindView (R.id.tvMassValue) TextView tvMassValue;
    @BindView (R.id.tvHairValue) TextView tvHairValue;
    @BindView (R.id.tvSkinColorValue) TextView tvSkinColorValue;
    @BindView (R.id.tvBirthYearValue) TextView tvBirthYearValue;
    @BindView (R.id.tvGenderValue) TextView tvGenderValue;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);
        ButterKnife.bind(this);
        People peopleObj = (People)getIntent().getSerializableExtra("PEOPLE");


        tvNameValue.setText(peopleObj.getName().trim());
        tvHeightValue.setText(peopleObj.getHeight().trim());
        tvMassValue.setText(peopleObj.getMass().trim());
        tvHairValue.setText(peopleObj.getHairColor().trim());
        tvSkinColorValue.setText(peopleObj.getSkinColor().trim());
        tvBirthYearValue.setText(peopleObj.getBirthYear().trim());
        tvGenderValue.setText(peopleObj.getGender().trim());


    }
}
