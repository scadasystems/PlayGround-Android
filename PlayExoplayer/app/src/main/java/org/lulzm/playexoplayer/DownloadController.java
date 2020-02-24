package org.lulzm.playexoplayer;

import android.accessibilityservice.GestureDescription;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.File;

/*********************************************************
 *   ,--.           ,--.       ,--.   ,--.
 *   |  |   ,--.,--.|  |,-----.|   `.'   |
 *   |  |   |  ||  ||  |`-.  / |  |'.'|  |
 *   |  '--.'  ''  '|  | /  `-.|  |   |  |
 *   `-----' `----' `--'`-----'`--'   `--'
 *
 * Project : PlayExoplayer                             
 * Created by Android Studio                           
 * Developer : Lulz_M                                    
 * Date : 2020-02-24                                        
 * Time : 11:25                                       
 * GitHub : https://github.com/scadasystems              
 * E-mail : redsmurf@lulzm.org                           
 *********************************************************/
public final class DownloadController {
    private final Context context;
    private final String url;

    private static final String FILE_NAME = "SampleDownloadApp.apk";
    private static final String FILE_BASE_PATH = "file://";
    private static final String MIME_TYPE = "application/vnd.android.package-archive";
    private static final String PROVIDER_PATH = ".provider";
    private static final String APP_INSTALL_PATH = "\"application/vnd.android.package-archive\"";

    public DownloadController(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public void enqueueDownload() {
        String destination = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/";
        destination += FILE_NAME;
        Uri uri = Uri.parse(FILE_BASE_PATH + destination);
        File file = new File(destination);

        if (file.exists()) {
            file.delete();
        }

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(url);
        Request request = new Request(downloadUri);
        request.setMimeType(MIME_TYPE);
        request.setTitle(context.getString(R.string.title_file_download));
        request.setDescription(context.getString(R.string.downloading));
        // set destination
        request.setDestinationUri(uri);
        showInstallOption(destination, uri);
        downloadManager.enqueue(request);
    }

    // 오토클릭이벤트. 고쳐야함.
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static GestureDescription createClick(float x, float y) {
        final int Duration = 1;
        Path clickPath = new Path();
        clickPath.moveTo(x, y);
        GestureDescription.StrokeDescription clickStroke = new GestureDescription.StrokeDescription(clickPath, 0, Duration);
        GestureDescription.Builder clickBuilder = new GestureDescription.Builder();
        clickBuilder.addStroke(clickStroke);
        return clickBuilder.build();
    }

    private void showInstallOption(final String destination, final Uri uri) {
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + PROVIDER_PATH, new File(destination));
                    Log.w("버전: ", "N 버전보다 높음");
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    install.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    install.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                    install.setData(contentUri);
                    context.startActivity(install);
                    context.unregisterReceiver(this);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /*todo 안먹힘. 고쳐야 함. */
                            Log.w("오토클릭이벤트", "확인, "+createClick(1300, 980).toString());
                            createClick(1300, 980);
                        }
                    },3000);
                } else {
                    Log.w("버전: ", "N 버전보다 낮음");
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    install.setDataAndType(uri, APP_INSTALL_PATH);
                    context.startActivity(install);
                    context.unregisterReceiver(this);
                }
            }
        };
        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
