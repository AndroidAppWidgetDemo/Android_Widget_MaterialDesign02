package com.yifeng.mdstudysamples.tablayout_top;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yifeng.mdstudysamples.R;


public class TabLayoutFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";

    public static TabLayoutFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        tabLayoutFragment.setArguments(arguments);
        return tabLayoutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.tablayout_fragment, null);
        ((TextView) contentView.findViewById(R.id.tv_content)).setText(getArguments().getString(EXTRA_CONTENT));
        return contentView;
    }
}
