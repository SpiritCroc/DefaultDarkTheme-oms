package de.spiritcroc.defaultdarktheme_oms.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import de.spiritcroc.defaultdarktheme_oms.R;

public class ForcedAboutFragment extends AboutFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);

        View openSubstratumButton = v.findViewById(R.id.open_substratum_button);
        openSubstratumButton.setVisibility(View.GONE);

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_hide_launcher).setVisible(false);
    }
}
