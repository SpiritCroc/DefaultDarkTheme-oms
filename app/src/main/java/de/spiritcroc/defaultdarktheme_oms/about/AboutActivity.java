/*
 * Copyright (C) 2017 SpiritCroc
 * Email: spiritcroc@gmail.com
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license,
 * visit https://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

package de.spiritcroc.defaultdarktheme_oms.about;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class AboutActivity extends Activity {

    static final String SUBSTRATUM_PACKAGE_NAME = "projekt.substratum";
    static final String THEME_PACKAGE_NAME = "de.spiritcroc.defaultdarktheme_oms";

    private static final boolean TEST_DEPP = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = isPackageInstalled(SUBSTRATUM_PACKAGE_NAME)
                ? new AboutFragment()
                : new DeppFragment();

        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    private boolean isPackageInstalled(String package_name) {
        if (TEST_DEPP) return false;
        try {
            PackageManager pm = getPackageManager();
            pm.getPackageInfo(package_name, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
