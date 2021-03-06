package com.pearl.about;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.pearl.about.fragments.RomDialog;
import com.pearl.about.viewpager.ViewPagerAdapter;
import com.pearl.about.viewpager.ViewPagerContainer;
import com.pearl.about.viewpager.ZoomInTransformer;
import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {


    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 886;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private ArrayList<ViewPagerContainer> mContents;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        fm = getSupportFragmentManager();

        setContentView(R.layout.main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            SetBackground();
        }

        mViewPager = findViewById(R.id.viewPager);
        mContents = new ArrayList<>();

        ImageView topimg = findViewById(R.id.topimg);
        topimg.setOnLongClickListener(this);
        topimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog(fm);
            }
        });

        //                 sagar,          satyam,         Oveno,           rahul,         aryan,            parth,           nikhil,          dimitry,        billou-senpai
        int icon[] = {R.drawable.dev1, R.drawable.dev2, R.drawable.dev3, R.drawable.dev4, R.drawable.dev8, R.drawable.dev5, R.drawable.dev6, R.drawable.tester1, R.drawable.extras};
        String cat[] = {getString(R.string.dev1), getString(R.string.dev2), getString(R.string.dev3), getString(R.string.dev4), getString(R.string.dev8), getString(R.string.dev5), getString(R.string.dev6), getString(R.string.billou), getString(R.string.extras)};
        String desc[] = {getString(R.string.dev_short1), getString(R.string.dev_short2), getString(R.string.dev_short3), getString(R.string.dev_short4), getString(R.string.dev_short8), getString(R.string.dev_short5), getString(R.string.dev_short6), getString(R.string.billou_short), getString(R.string.extras_short)};

        for (int i = 0; i < icon.length; i++) {
            ViewPagerContainer viewpagercontainer = new ViewPagerContainer();

            viewpagercontainer.icon = icon[i];
            viewpagercontainer.cat = cat[i];
            viewpagercontainer.desc = desc[i];

            mContents.add(viewpagercontainer);
        }


        mViewPagerAdapter = new ViewPagerAdapter(mContents, this, fm);
        mViewPager.setOffscreenPageLimit(11);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(150);
        mViewPager.setPageTransformer(false, new ZoomInTransformer());
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.topimg:
                new ParticleSystem(this, 100, R.drawable.kek, 5000)
                        .setSpeedRange(0.1f, 0.25f)
                        .oneShot(findViewById(R.id.center), 100);
                break;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SetBackground();
                    return;
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Live with the basic background then :''(",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void displayDialog(FragmentManager fm) {
        DialogFragment newFragment = new RomDialog();
        newFragment.show(fm, "dialog");
    }


    private void SetBackground() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();

        CoordinatorLayout container = findViewById(R.id.container);
        container.setBackground(wallpaperDrawable);

        View dimmer = findViewById(R.id.dimmer);
        dimmer.getBackground().setAlpha(150);
    }
}