package com.girish.starwars.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.girish.starwars.R;
import com.girish.starwars.models.Film;

import com.girish.starwars.models.SWModelList;
import com.girish.starwars.sw.StarWarsApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FilmFragment extends ListFragment {

    private ArrayList<Film> mFilmArrayList;
    private FilmAdapter mFilmListAdapter;
    private FilmListItemClickListner ifilmClickListner;
    private ProgressBar filmProgressBar;
    public interface FilmListItemClickListner {
        /** This method will be invoked when an item in the ListFragment is clicked */
        void getFilmDetails(Film film);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film, container, false);
       filmProgressBar = (ProgressBar)view.findViewById(R.id.filmProgressBar);
        getFilmList();
        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FilmListItemClickListner) {
            ifilmClickListner = (FilmListItemClickListner) context;
        } else {
            throw new RuntimeException(context.toString()+" must implement FilmListItemClickListner");
        }
    }


    public void getFilmList()
    {
        StarWarsApi.getApi().getAllFilms(1, new Callback<SWModelList<Film>>() {
            @Override
            public void success(SWModelList<Film> filmSWModelList, Response response) {
                //
                filmProgressBar.setVisibility(View.INVISIBLE);
                if (response.getBody() != null) {

                    mFilmArrayList = filmSWModelList.results;
                    mFilmListAdapter = new FilmAdapter(getActivity(),android.R.layout.simple_list_item_1, mFilmArrayList);
                    setListAdapter(mFilmListAdapter);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("FilmFrag", error.toString());
            }
        });
    }
    public void onListItemClick(ListView listView, View view, int position, long id){

        if(ifilmClickListner != null) {
            ifilmClickListner.getFilmDetails(mFilmArrayList.get(position));
        }
    }

}



class FilmAdapter extends ArrayAdapter<Film> {

    private ArrayList<Film> filmArrayList;
    private Context context;

    public FilmAdapter(@NonNull Context context, int resource, ArrayList<Film> array) {
        super(context, resource, array);
        this.filmArrayList = array;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.film_row, null);
        TextView textName = (TextView) layout.findViewById(R.id.tvFilmName);
        Film filmObj = filmArrayList.get(position);
        textName.setText(filmObj.getTitle());
        return layout;

    }

    @Override
    public int getCount() {
        return this.filmArrayList.size();
    }
}
