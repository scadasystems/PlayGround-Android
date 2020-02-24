package org.lulzm.playexoplayer;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
 * Time : 12:12                                       
 * GitHub : https://github.com/scadasystems              
 * E-mail : redsmurf@lulzm.org                           
 *********************************************************/

public final class Utils extends Activity {
    private static Context context;

    public static final int checkSelfPermissionCompat(String permission) {
        return ActivityCompat.checkSelfPermission(context, permission);
    }

    public static final boolean shouldShowRequestPermissionRationaleCompat(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
    }

    public static final void requestPermissionsCompat(String[] permissionsArray, int requestCode) {
        ActivityCompat.requestPermissions((Activity) context, permissionsArray, requestCode);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
