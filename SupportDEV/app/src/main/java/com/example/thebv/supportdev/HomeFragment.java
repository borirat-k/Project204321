package com.example.thebv.supportdev;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    private View view;
    private MainActivity activityParent;

    public HomeFragment() {

    }

    public HomeFragment(MainActivity activityParent) {
        this.activityParent = activityParent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.ivMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityParent.onClickMenu();
            }
        });
    }

    private void init() {
        view.findViewById(R.id.ivMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityParent.onClickMenu();
            }
        });
    }

    public void showTypeScale() {
        view.findViewById(R.id.llToolBar).setBackgroundResource(R.drawable.bg_cornors_top_left);
        view.findViewById(R.id.llContent).setBackgroundResource(R.drawable.bg_cornors_bottom_left);
    }

    public void showTypeNormal() {
        view.findViewById(R.id.llToolBar).setBackgroundResource(R.color.colorAccent);
        view.findViewById(R.id.llContent).setBackgroundResource(R.color.white);
    }
}
