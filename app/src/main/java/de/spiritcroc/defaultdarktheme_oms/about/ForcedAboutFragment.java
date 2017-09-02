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

import android.view.Menu;
import android.view.MenuInflater;

import de.spiritcroc.defaultdarktheme_oms.R;

public class ForcedAboutFragment extends AboutFragment {


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_hide_launcher).setVisible(false);
    }
}
