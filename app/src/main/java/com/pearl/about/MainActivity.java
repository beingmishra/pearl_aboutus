package com.pearl.about;

import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{


    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private ArrayList<ViewPagerContainer> mContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();

        setContentView(R.layout.main);

        CoordinatorLayout container = findViewById(R.id.container);
        container.setBackground(wallpaperDrawable);

        View dimmer = findViewById(R.id.dimmer);
        dimmer.getBackground().setAlpha(180);

        mViewPager = findViewById(R.id.viewPager);
        mContents = new ArrayList<>();

        ImageView topimg = findViewById(R.id.topimg);
        topimg.setOnLongClickListener(this);


        int icon[] = {R.drawable.dev1, R.drawable.dev2, R.drawable.dev3};
        String cat[] = {getString(R.string.dev1), getString(R.string.dev2), getString(R.string.dev3)};

        for (int i = 0; i < icon.length; i++) {
            ViewPagerContainer viewpagercontainer = new ViewPagerContainer();

            viewpagercontainer.icon = icon[i];
            viewpagercontainer.cat = cat[i];

            mContents.add(viewpagercontainer);
        }


        mViewPagerAdapter = new ViewPagerAdapter(mContents, this);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(150);
        mViewPager.setPageTransformer(false, new ZoomInTransformer());
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public boolean onLongClick(View v)  {
        switch(v.getId()) {
            case R.id.topimg:
                new ParticleSystem(this, 100, R.drawable.kek, 5000)
                        .setSpeedRange(0.1f, 0.25f)
                        .oneShot(findViewById(R.id.center), 100);
                break;
        }

        return true;
    }
}