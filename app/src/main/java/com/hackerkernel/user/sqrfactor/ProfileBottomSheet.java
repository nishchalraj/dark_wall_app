package com.hackerkernel.user.sqrfactor;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ProfileBottomSheet extends BottomSheetDialogFragment{

    LinearLayout ll1, ll2, ll3, ll4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_profile_bottom_sheet, container, false);

        ll1 = (LinearLayout)view.findViewById(R.id.ll1);
        ll2 = (LinearLayout)view.findViewById(R.id.ll2);
        ll3 = (LinearLayout)view.findViewById(R.id.ll3);
        ll4 = (LinearLayout)view.findViewById(R.id.ll4);

        return view;

    }

}
