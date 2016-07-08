package com.ampiri.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ampiri.sdk.utils.AmpiriLogger;

public class MainActivity extends Activity implements OnItemClickListener {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AmpiriLogger.setDebugMode(true);

        final ListView listView = new ListView(this);
        listView.setAdapter(new MainAdapter(this));
        listView.setOnItemClickListener(this);
        setContentView(listView);
    }

    @Override
    public void onItemClick(@NonNull final AdapterView<?> parent, @NonNull final View view, final int position, final long id) {
        switch (position) {
            case 0:
                startActivity(StandardBannerActivity.buildIntent(this));
                break;
            case 1:
                startActivity(BigBannerActivity.buildIntent(this));
                break;
            case 2:
                startActivity(InterstitialActivity.buildIntent(this));
                break;
            case 3:
                startActivity(VideoActivity.buildIntent(this));
                break;
            case 4:
                startActivity(NativeActivity.buildIntent(this));
                break;
            case 5:
                startActivity(VideoNativeActivity.buildIntent(this));
                break;
            case 6:
                startActivity(FeedCardInfeedActivity.buildIntent(this));
                break;
            case 7:
                startActivity(StoryCardInfeedActivity.buildIntent(this));
                break;
            case 8:
                startActivity(VideoCardInfeedActivity.buildIntent(this));
                break;
            default:
                throw new IllegalStateException("Unknown position [" + position + "]");
        }
    }

    private static final class MainAdapter extends ArrayAdapter<String> {

        private MainAdapter(@NonNull final Context context) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, context.getResources().getStringArray(R.array.ad_types));
        }
    }
}
