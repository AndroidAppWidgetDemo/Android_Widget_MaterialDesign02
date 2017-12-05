package com.yifeng.mdstudysamples.fab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.yifeng.mdstudysamples.R;
import com.yifeng.mdstudysamples.palette.PaletteDetailActivity;

public class TabFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String EXTRA_CONTENT = "content";

    private ListView mListView;

    public static TabFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabFragment tabContentFragment = new TabFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.tab_fragment, null);

        mListView = (ListView) contentView.findViewById(R.id.lv_content);
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(new ContentAdapter());
        //
        ViewCompat.setNestedScrollingEnabled(mListView, true);

        return contentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO
    }

    private class ContentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // View
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_simple_2, null);
            contentView.findViewById(R.id.cv_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(getActivity(), PaletteDetailActivity.class);
                    detailIntent.putExtra(PaletteDetailActivity.EXTRA_INDEX, position);
                    startActivity(detailIntent);
                }
            });
            // 图片
            ImageView coverIv = (ImageView) contentView.findViewById(R.id.iv_cover);
            // 设置显示内容
            coverIv.setImageResource(getResources().getIdentifier("ic_palette_0" + position % 4, "mipmap", getActivity().getPackageName()));
            return contentView;
        }
    }
}
