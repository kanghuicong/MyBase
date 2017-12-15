package com.litao.android.lib;


import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by 李涛 on 16/4/22.
 */
public abstract class GalleryActivity extends AppCompatActivity implements GalleryFragment.OnGalleryAttachedListener {

    private GalleryFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

    }

    protected void attachFragment(int layoutId) {
        fragment = (GalleryFragment) GalleryFragment.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(layoutId, fragment).commit();
    }

    protected void openAlbum() {
        fragment.openAlbum();
    }

    protected void sendPhotos() {
        fragment.sendPhtots();
    }

}
