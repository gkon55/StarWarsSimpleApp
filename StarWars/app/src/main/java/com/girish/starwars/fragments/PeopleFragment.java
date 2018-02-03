package com.girish.starwars.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.girish.starwars.models.People;
import com.girish.starwars.models.SWModelList;
import com.girish.starwars.sw.StarWarsApi;

import java.util.ArrayList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PeopleFragment extends android.support.v4.app.ListFragment {

   // private ListView mListView;
    private ArrayList<People> mPeopleArrayList;
    private PeopleAdapter mPeopleListAdapter;
    private PeopleListItemClickListner ipeopleClickListner;
    private ProgressBar peopleProgressBar;
    public interface PeopleListItemClickListner {
        /** This method will be invoked when an item in the ListFragment is clicked */
        void getPeopleDetails(People people);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        peopleProgressBar = (ProgressBar)view.findViewById(R.id.peopleProgressBar);

        getPeopleList();
        return view;

    }

       @Override
    public void onAttach(Context context) {
        super.onAttach(context);
           if (context instanceof PeopleListItemClickListner) {
               ipeopleClickListner = (PeopleListItemClickListner) context;
           } else {
               throw new RuntimeException(context.toString()+" must implement PeopleListItemClickListner");
           }
    }


    public void getPeopleList()
    {
        StarWarsApi.getApi().getAllPeople(2, new Callback<SWModelList<People>>() {
            @Override
            public void success(SWModelList<People> peopleSWModelList, Response response) {
                //
                peopleProgressBar.setVisibility(View.INVISIBLE);
                if (response.getBody() != null) {

                    mPeopleArrayList = peopleSWModelList.results;
                    mPeopleListAdapter = new PeopleAdapter(getActivity(),android.R.layout.simple_list_item_1, mPeopleArrayList);
                    setListAdapter(mPeopleListAdapter);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("MainActivity", error.toString());
            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
        ipeopleClickListner = null;
    }

    public void onListItemClick(ListView listView, View view, int position, long id){
        Log.e("PEOPLE FRAG", "Following List Item has been clicked position " + position + " and id " + id);
        if(ipeopleClickListner != null) {
            ipeopleClickListner.getPeopleDetails(mPeopleArrayList.get(position));
        }
    }


}
class PeopleAdapter extends ArrayAdapter<People> {

    private ArrayList<People> peopleArrayList;
    private Context context;

    public PeopleAdapter(@NonNull Context context, int resource, ArrayList<People> array) {
        super(context, resource, array);
        this.peopleArrayList = array;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.people_row, null);
        TextView textName = (TextView) layout.findViewById(R.id.tvPeopleName);
        People peopleObj = peopleArrayList.get(position);
        textName.setText(peopleObj.getName());


        return layout;

    }

    @Override
    public int getCount()  {
        return this.peopleArrayList.size();
    }
}
