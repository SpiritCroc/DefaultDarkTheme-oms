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

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import de.spiritcroc.defaultdarktheme_oms.R;
import de.spiritcroc.defaultdarktheme_oms.SubstratumLauncher;

public class AboutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.about, container, false);

        WebView aboutWebView = (WebView) v.findViewById(R.id.about_web_view);
        View openSubstratumButton = v.findViewById(R.id.open_substratum_button);

        aboutWebView.setBackgroundColor(Color.TRANSPARENT);
        aboutWebView.loadData(styleHtml(getActivity(), R.string.about_html), "text/html", "UTF-8");

        openSubstratumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SubstratumLauncher.class));
            }
        });

        // Remember which version of this screen the user has seen;
        AboutActivity.markAboutSeen(getActivity());

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.about, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_hide_launcher:
                promptHideLauncher();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static String styleHtml(Context context, @StringRes int resourceId) {
        TypedArray ta = context.obtainStyledAttributes(new int[] {
                android.R.attr.textColorPrimary,
                android.R.attr.colorAccent,
                R.attr.colorWarning}
        );
        String textColorPrimary = String.format("#%06X", (0xFFFFFF & ta.getColor(0, Color.GRAY)));
        String accentColor = String.format("#%06X", (0xFFFFFF & ta.getColor(1, Color.GRAY)));
        String warning_color = String.format("#%06X", (0xFFFFFF & ta.getColor(2, Color.GRAY)));
        String substratumVersion = getVersionName(context, AboutActivity.SUBSTRATUM_PACKAGE_NAME);
        String themeVersion = getVersionName(context, AboutActivity.THEME_PACKAGE_NAME);
        String androidVersion = getVersionName(context, null);
        ta.recycle();
        String html = context.getString(resourceId);
        html = html.replaceAll("\\?android:attr/textColorPrimary", textColorPrimary);
        html = html.replaceAll("\\?android:attr/colorAccent", accentColor);
        html = html.replaceAll("\\?attr/colorWarning", warning_color);
        html = html.replaceAll("\\?attr/substratumVersion", substratumVersion);
        html = html.replaceAll("\\?attr/themeVersion", themeVersion);
        html = html.replaceAll("\\?attr/androidVersion", androidVersion);
        return html;
    }

    private static String getVersionName(Context context, String packageName) {
        if (packageName == null) {
            return Build.VERSION.RELEASE;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return context.getString(R.string.versoin_name_and_code, packageInfo.versionName,
                    String.valueOf(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return context.getString(R.string.unknown_version);
        }
    }

    private void promptHideLauncher() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.hide_launcher_title)
                .setMessage(R.string.hide_launcher_message)
                .setPositiveButton(R.string.hide_launcher_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AboutActivity.disableLauncher(getActivity());
                    }
                })
                .setNegativeButton(R.string.hide_launcher_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Only dismiss
                    }
                })
                .show();
    }
}
