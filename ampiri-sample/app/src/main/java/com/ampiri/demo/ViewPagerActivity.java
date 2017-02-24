package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerActivity extends AdLifecycleActivity {

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, ViewPagerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new AdsPagerAdapter(getSupportFragmentManager()));
    }

    private static class AdsPagerAdapter extends FragmentPagerAdapter {

        AdsPagerAdapter(@NonNull final FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(final int pos) {
            switch (pos) {
                case 1:
                    return NativeFragment.newInstance();
                default:
                    return PagerFragment.newInstance(getPageTitle(pos));
            }
        }

        @Override
        public CharSequence getPageTitle(final int position) {
            return "Page " + (position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public static class PagerFragment extends Fragment {

        @NonNull
        private static final String LABEL_ARG = "label";

        @NonNull
        public static Fragment newInstance(@NonNull final CharSequence label) {
            final PagerFragment fragment = new PagerFragment();
            final Bundle args = new Bundle();
            args.putCharSequence(LABEL_ARG, label);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
            final TextView view = new TextView(inflater.getContext());
            view.setGravity(Gravity.CENTER);
            view.setText(getArguments().getCharSequence(LABEL_ARG));
            return view;
        }
    }
}
