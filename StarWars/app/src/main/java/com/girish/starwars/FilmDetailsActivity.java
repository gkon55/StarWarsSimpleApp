package com.girish.starwars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.girish.starwars.models.Film;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmDetailsActivity extends AppCompatActivity {
    @BindView(R.id.tvTitleValue) TextView tvTitleValue;
    @BindView(R.id.tvEpisodeValue) TextView tvEpisodeValue;
    @BindView(R.id.tvDirectorValue) TextView tvDirectorValue;
    @BindView(R.id.tvProducerValue) TextView tvProducerValue;
    @BindView(R.id.tvPlotValue) TextView tvPlotValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);
        ButterKnife.bind(this);

        Film filmObj = (Film)getIntent().getSerializableExtra("FILM");
        tvTitleValue.setText(filmObj.getTitle().trim());
        tvEpisodeValue.setText(Integer.toString(filmObj.getEpisodeId()).trim());
        tvDirectorValue.setText(filmObj.getDirector().trim());
        tvProducerValue.setText(filmObj.getProducer().trim());
        tvPlotValue.setText(filmObj.getOpeningCrawl().trim());
    }
}
