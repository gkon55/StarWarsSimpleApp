package com.girish.starwars;

import android.app.Application;

import com.girish.starwars.sw.StarWarsApi;

/**
 * Created by girish on 1/26/2018.
 */

public class StarWarsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StarWarsApi.init();
    }
}
