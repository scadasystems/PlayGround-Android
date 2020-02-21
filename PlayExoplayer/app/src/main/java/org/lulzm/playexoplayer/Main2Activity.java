package org.lulzm.playexoplayer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.lulzm.playexoplayer.fragments.MainFragment;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn_dog, btn_cat, btn_rabbit;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_dog = findViewById(R.id.btn_dog);
        btn_dog.setOnClickListener(this);
        btn_cat = findViewById(R.id.btn_cat);
        btn_cat.setOnClickListener(this);
        btn_rabbit = findViewById(R.id.btn_rabbit);
        btn_rabbit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MainFragment mediaFragment = new MainFragment();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.btn_dog:
                animation = AnimationUtils.loadAnimation(this, R.anim.animation);
                btn_cat.startAnimation(animation);
                bundle.putString("media_url", getString(R.string.media_dog));
                break;
            case R.id.btn_cat:
                bundle.putString("media_url", getString(R.string.media_cat));
                break;
            case R.id.btn_rabbit:
                bundle.putString("media_url", getString(R.string.media_rabbit));
                break;
        }
        mediaFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_container, mediaFragment);
        ft.commit();

    }
}
