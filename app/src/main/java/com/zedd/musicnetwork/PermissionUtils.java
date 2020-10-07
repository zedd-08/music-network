package com.zedd.musicnetwork;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import java.util.Set;

class PermissionUtils {
    static boolean hasNotificationAccess(final Context context) {
        String packageName = context.getPackageName();
        Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(context);
        return enabledPackages.contains(packageName);
    }
}