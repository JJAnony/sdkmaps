package com.example.sdkmapsgoogle;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiltondamasceno
 */

public class Permissions {

    public static boolean valid(String[] permissions, Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionsList = new ArrayList<>();
            for (String permission : permissions) {
                Boolean isPermission = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if (!isPermission) permissionsList.add(permission);
            }
            if (permissionsList.isEmpty()) return true;
            String[] novasPermissoes = new String[permissionsList.size()];
            permissionsList.toArray(novasPermissoes);
            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);
        }
        return true;
    }

}
