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
import android.os.Bundle;

import substratum.theme.template.internal.SystemInformation;

public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean forceAbout = getIntent().getBooleanExtra(Util.EXTRA_FORCE_ABOUT, false);

        Fragment fragment = forceAbout ? new ForcedAboutFragment()
                : !Util.TEST_DEPP && SystemInformation.INSTANCE.getSelfVerifiedThemeEngines(this)
                    ? new AboutFragment()
                    : new DeppFragment();

        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

}
