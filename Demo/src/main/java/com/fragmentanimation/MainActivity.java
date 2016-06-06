package com.fragmentanimation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.hideOverflowMenu();
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layout_main, FragmentExample.newInstance(FragmentExample.NODIR));
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentExample f = (FragmentExample) getSupportFragmentManager().findFragmentById(R.id.layout_main);
        switch (id) {
            case R.id.style_cube:
                f.setAnimationStyle(FragmentExample.CUBE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
