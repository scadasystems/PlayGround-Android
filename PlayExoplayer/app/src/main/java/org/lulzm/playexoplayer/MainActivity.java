package org.lulzm.playexoplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.lulzm.playexoplayer.fragments.MainFragment;

import static org.lulzm.playexoplayer.Utils.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_STORAGE = 0;
    private DownloadController downloadController;

    private ImageButton btn_perfume, btn_smoothie, btn_cosmetic, btn_wine;
    private Animation animation;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideNavigationBar();

        String apkUrl = "https://github.com/scadasystems/PlayGround-Android/raw/master/PlayExoplayer/app/src/APK/DownloadExample.apk";
        downloadController = new DownloadController(this, apkUrl);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.w(TAG, "다운로드 완료");
                downloadController.enqueueDownload();
            }
        }, 4000);

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

    private void hideNavigationBar() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
        } else {
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}
