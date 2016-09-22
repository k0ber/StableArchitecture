package com.nik.noveo.retain_fragment.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nik.noveo.retain_fragment.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_layout, NewsViewFragment.newInstance())
                    .commit();
        }
    }

    @OnClick(R.id.fab)
    public void loadNewsClicked() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_layout);
        if (fragment instanceof NewsViewFragment) {
            ((NewsViewFragment) fragment).loadNewsClicked();
        }
    }

}
