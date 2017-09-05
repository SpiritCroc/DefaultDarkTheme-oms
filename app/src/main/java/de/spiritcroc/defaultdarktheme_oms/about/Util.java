/*
 * Copyright (C) 2017 SpiritCroc
 * Email: spiritcroc@gmail.com
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license,
 * visit https://creativecommons.org/licenses/by-sa/4.0/.
 */

package de.spiritcroc.defaultdarktheme_oms.about;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import de.spiritcroc.defaultdarktheme_oms.R;
import substratum.theme.template.SubstratumLauncher;

public final class Util {

    // Don't instantiate
    private Util() {}

    static final String EXTRA_FORCE_ABOUT = "de.spiritcroc.defaultdarktheme_oms.force_about";

    static final String SUBSTRATUM_PACKAGE_NAME = "projekt.substratum";
    static final String THEME_PACKAGE_NAME = "de.spiritcroc.defaultdarktheme_oms";

    private static final String SP_KEY_ABOUT_VIEWED = "sc.about_viewed";

    static final boolean TEST_DEPP = false;

    static boolean isPackageInstalled(Context context, String package_name) {
        if (TEST_DEPP) return false;
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(package_name, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static void disableLauncher(Context context) {
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(context, AboutActivity.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

    }

    static void enableLauncher(Context context) {
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(context, AboutActivity.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    static void markAboutSeen(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(SP_KEY_ABOUT_VIEWED, context.getResources()
                .getInteger(R.integer.about_version)).apply();
    }

    public static boolean checkAboutUpdate(Activity activity) {
        // If the user hasn't seen the current version of the about screen, show the theme in
        // launcher so they can access it in case they have hidden it before
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        if (sp.getInt(SP_KEY_ABOUT_VIEWED, -1) != activity.getResources()
                .getInteger(R.integer.about_version)) {
            enableLauncher(activity);
            promptViewAbout(activity);
            return true;
        }
        return false;
    }

    private static void promptViewAbout(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle(R.string.prompt_view_current_about_title)
                .setMessage(R.string.prompt_view_current_about_message)
                .setPositiveButton(R.string.prompt_view_current_about_yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                activity.finish();
                                activity.startActivity(new Intent(activity, AboutActivity.class));
                            }
                        })
                .setNegativeButton(R.string.prompt_view_current_aboutr_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Don't show this message again
                                Util.markAboutSeen(activity);
                                // Continue to SubstratumLauncher
                                activity.finish();
                                activity.startActivity(new Intent(activity,
                                        SubstratumLauncher.class));
                            }
                        })
                .show();
    }
}
