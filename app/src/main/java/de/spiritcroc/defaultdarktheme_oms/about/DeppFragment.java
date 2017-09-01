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

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import de.spiritcroc.defaultdarktheme_oms.R;

public class DeppFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.sc_depp, container, false);

        WebView deppWebView = (WebView) v.findViewById(R.id.depp_web_view);
        View uninstallButton = v.findViewById(R.id.uninstall_button);

        deppWebView.setBackgroundColor(Color.TRANSPARENT);
        deppWebView.loadData(AboutFragment.styleHtml(getActivity(), R.string.depp_html),
                "text/html", "UTF-8");

        uninstallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageString = "package:" + AboutActivity.THEME_PACKAGE_NAME;
                startActivity(new Intent(Intent.ACTION_UNINSTALL_PACKAGE,
                        Uri.parse(packageString)));
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sc_depp, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_force_about:
                startActivity(new Intent(getActivity(), AboutActivity.class)
                        .putExtra(AboutActivity.EXTRA_FORCE_ABOUT, true));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
