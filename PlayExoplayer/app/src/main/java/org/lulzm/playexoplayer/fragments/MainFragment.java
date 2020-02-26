package org.lulzm.playexoplayer.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.lulzm.playexoplayer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;
    private Boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playBackPosition = 0;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        exoPlayerView = getActivity().findViewById(R.id.exo_play_view);
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(requireActivity());
        exoPlayerView.setPlayer(player);

        String media_url = null;
        if (getArguments() != null) {
            media_url = getArguments().getString("media_url");
        } else {
            media_url = getString(R.string.media_perfume);
        }

        Uri uri = Uri.parse(media_url);
        MediaSource mediaSource = buildMediaSource(uri);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playBackPosition);
        player.prepare(mediaSource, false, false);
        player.setRepeatMode(Player.REPEAT_MODE_ONE);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), "PlayExoplayer");
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) initializePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) releasePlayer();
    }

    /* UI 숨기기 */
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        exoPlayerView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LOW_PROFILE
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playBackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }
}
