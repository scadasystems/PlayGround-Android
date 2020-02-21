package org.lulzm.playexoplayer;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.lulzm.playexoplayer.fragments.MainFragment;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn_perfume, btn_smoothie, btn_cosmetic, btn_wine;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
//        int newUiOptions = uiOptions;
//        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
//        if (isImmersiveModeEnabled) {
//            Log.i("Is on?", "Turning immersive mode mode off. ");
//        } else {
//            Log.i("Is on?", "Turning immersive mode mode on.");
//        }
//        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
//        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

        btn_perfume = findViewById(R.id.btn_perfume);
        btn_perfume.setOnClickListener(this);
        btn_smoothie = findViewById(R.id.btn_smoothie);
        btn_smoothie.setOnClickListener(this);
        btn_cosmetic = findViewById(R.id.btn_cosmetic);
        btn_cosmetic.setOnClickListener(this);
        btn_wine = findViewById(R.id.btn_wine);
        btn_wine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MainFragment mediaFragment = new MainFragment();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.btn_perfume:
//                animation = AnimationUtils.loadAnimation(this, R.anim.animation);
//                btn_smoothie.startAnimation(animation);
                bundle.putString("media_url", getString(R.string.media_perfume));
                break;
            case R.id.btn_smoothie:
                bundle.putString("media_url", getString(R.string.media_smoothie));
                break;
            case R.id.btn_cosmetic:
                bundle.putString("media_url", getString(R.string.media_cosmetic));
                break;
            case R.id.btn_wine:
                bundle.putString("media_url", getString(R.string.media_wine));
        }
        mediaFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_container, mediaFragment);
        ft.commit();

    }
}
