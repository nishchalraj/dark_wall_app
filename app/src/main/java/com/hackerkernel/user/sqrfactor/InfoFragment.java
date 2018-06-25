package com.hackerkernel.user.sqrfactor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    TextView brief;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_info, container, false);

        String s = getContext().getResources().getString(R.string.comp_brief);
        brief = (TextView)view.findViewById(R.id.brief);
        brief.setText(Html.fromHtml(s));

        return view;

    }

}
