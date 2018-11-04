package com.example.thebv.supportdev;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout containt;
    private HomeFragment homeFragment;
    private LinearLayout llMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        containt = (FrameLayout) findViewById(R.id.containt);
        containt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMenu();
            }
        });
        containt.setClickable(false);

        llMenu = (LinearLayout) findViewById(R.id.llMenu);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        homeFragment = new HomeFragment(this);//the fragment you want to show
        fragmentTransaction.replace(R.id.containt, homeFragment);//R.id.content_frame is the layout you want to replace
//        fragmentTransaction.add(R.id.containt, homeFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void onClickMenu() {
        System.out.println(containt.getX());
        System.out.println(llMenu.getWidth());
        if (containt.getX() == llMenu.getWidth()) {
            containt.animate().x(0).setDuration(200);
            scaleViewUp(containt);
            containt.setClickable(false);
            homeFragment.showTypeNormal();
        } else {
            containt.animate().x(llMenu.getWidth()).setDuration(200);
            scaleViewDow(containt);
            containt.setClickable(true);
            homeFragment.showTypeScale();
        }
        System.out.println(containt.getX());
        System.out.println(llMenu.getWidth());
    }

    public void scaleViewUp(View v) {
        Animation anim = new ScaleAnimation(
                0.7f, 1f, // Start and end values for the X axis scaling
                0.7f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(200);
        v.startAnimation(anim);
    }

    public void scaleViewDow(View v) {
        Animation anim = new ScaleAnimation(
                1f, 0.7f, // Start and end values for the X axis scaling
                1f, 0.7f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(200);
        v.startAnimation(anim);
    }

    @Override
    public void onBackPressed() {
        if (containt.getX() == llMenu.getWidth()){
            containt.animate().x(0).setDuration(200);
            scaleViewUp(containt);
            containt.setClickable(false);
        }
        else {
            super.onBackPressed();
        }
    }
}
