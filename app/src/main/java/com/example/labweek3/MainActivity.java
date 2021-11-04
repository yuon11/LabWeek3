package com.example.labweek3;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentTracker {

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private GestureDetectorCompat mDetector;
    private final PersonInfo pi=new PersonInfo();
    private int next=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("OnCreate", "Application Starting");
        mDetector = new GestureDetectorCompat(getApplicationContext(), new MyGestureListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart", "Loading Fragment");
        fragment1 = new Fragment1();
        loadTheFragment(fragment1);
    }

    @Override
    public void fragmentVisible(String s) {
        TextView v = findViewById(R.id.title);
        v.setText(s);
    }

    @Override
    public void goNext() {
        Log.d("Go Next", "Loading Fragment");
        if (next==1){
            if (fragment2==null)
                fragment2 = new Fragment2();
            loadTheFragment(fragment2);

            next=2;
        }
        else if (next==2){
            if (fragment3==null)
                fragment3 = new Fragment3();
            loadTheFragment(fragment3);
            next=3;
        }
    }

    @Override
    public void goBack() {
        Log.d("Go Back", "Loading Fragment");

        if (next==2){
            if (fragment1==null)
                fragment1 = new Fragment1();
            loadTheFragment(fragment1);
            next=1;
        }
        else if (next==3){
            if (fragment2==null)
                fragment2 = new Fragment2();
            loadTheFragment(fragment2);
            next=2;
        }
    }

    @Override
    public void saveNameAndLastName(String name, String lname) {
        pi.setName(name);
        pi.setLastname(lname);
    }

    @Override
    public void saveCityAndZip(String city, String zip) {
        pi.setCity(city);
        pi.setZip(zip);
    }

    @Override
    public void saveDetail(String detail) {
        Log.d("Save Detail", "Detail data added: "+ detail);
        pi.setDetail(detail);
    }

    @Override
    public void finished(){

        Intent i=new Intent(this,SummaryActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        i.putExtra("pi", pi);
        startActivity(i);
    }


    private void loadTheFragment(Fragment f)
    {
        Log.d("Load Fragment", "Loading new fragment into container");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,f);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("Dispatch Event", "going to swipe method");
        mDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,float velocityX, float velocityY) {
            Log.d("On Fling", "Fling event detection");

            if (event1.getX() < event2.getX()){
                Toast toast = Toast.makeText(MainActivity.this, "Fling right",
                        Toast.LENGTH_SHORT);
                toast.show();
                goNext();
            }
            else
            {
                Toast toast = Toast.makeText(MainActivity.this, "Fling left",
                        Toast.LENGTH_SHORT);
                toast.show();
                goBack();
            }
            return true;
        }
    }

}


