package com.astudios.rescuebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import essential.CustomViewPager;
import essential.SectionsPagerAdapter;


public class GoBagActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MAX_FRAGS = 2;
    private android.widget.TextView gobagcrntWght;
    private android.widget.TextView gobagtotalWght;
    private android.widget.ProgressBar gobagprgrsBar;
    private android.widget.ImageView gobagsave;
    private essential.CustomViewPager gobagviewPager;
    private android.widget.TextView gobagrecommended;
    private android.widget.TextView gobagadditional;

    CustomViewPager viewPager;
    SectionsPagerAdapter sectionsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_bag);

        findIds();

        setUpViewPager();

    }

    private void setUpViewPager() {
        //setting up the viewpager with different tabs
        viewPager = (CustomViewPager) findViewById(R.id.gobag_viewPager);
        viewPager.disableScroll(true);

        //setting up the adapter
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //setting in the adapter to viewpager
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.setCurrentItem(0, true);
    }

    private void findIds() {

        this.gobagadditional = (TextView) findViewById(R.id.gobag_additional);
        gobagadditional.setOnClickListener(this);
        this.gobagrecommended = (TextView) findViewById(R.id.gobag_recommended);
        gobagrecommended.setOnClickListener(this);
        this.gobagviewPager = (CustomViewPager) findViewById(R.id.gobag_viewPager);
        this.gobagsave = (ImageView) findViewById(R.id.gobag_save);
        this.gobagprgrsBar = (ProgressBar) findViewById(R.id.gobag_prgrsBar);
        this.gobagtotalWght = (TextView) findViewById(R.id.gobag_totalWght);
        this.gobagcrntWght = (TextView) findViewById(R.id.gobag_crntWght);
    }

    @Override
    public void onClick(View view) {
        // essential.show("clicked", Essential.SUCCESS);
        switch (view.getId()) {
            case R.id.gobag_recommended:
                viewPager.setCurrentItem(0, true);
                gobagrecommended.setBackgroundColor(getResources().getColor(R.color.new_btn_color));
                gobagadditional.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                gobagadditional.setTextColor(getResources().getColor(R.color.gray));
                gobagrecommended.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.gobag_additional:
                viewPager.setCurrentItem(1, true);
                gobagrecommended.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                gobagadditional.setBackgroundColor(getResources().getColor(R.color.new_btn_color));
                gobagadditional.setTextColor(getResources().getColor(R.color.colorAccent));
                gobagrecommended.setTextColor(getResources().getColor(R.color.gray));
                break;
        }
    }
}
