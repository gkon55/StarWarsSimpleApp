package com.girish.starwars;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.girish.starwars.adapter.ViewPagerAdapter;
import com.girish.starwars.fragments.FilmFragment;
import com.girish.starwars.fragments.PeopleFragment;
import com.girish.starwars.models.Film;
import com.girish.starwars.models.People;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PeopleFragment.PeopleListItemClickListner,FilmFragment.FilmListItemClickListner{
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.container)ViewPager mViewPager;
    @BindView(R.id.tabs)TabLayout mTabLayout;

    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setUpVeiwPager();

    }

    public void setUpVeiwPager() {

        setSupportActionBar(mToolbar);
        mTabLayout.addTab(mTabLayout.newTab().setText("People"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Films"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
                mTabLayout.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getPeopleDetails(People people) {
        Log.e("MainActivty getPeople", people.getName());
        Intent intent = new Intent(this,PeopleDetailsActivity.class);
        intent.putExtra("PEOPLE", people);
        startActivity(intent);
    }

    @Override
    public void getFilmDetails(Film film) {
        Intent intent = new Intent(this,FilmDetailsActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
    }
}



