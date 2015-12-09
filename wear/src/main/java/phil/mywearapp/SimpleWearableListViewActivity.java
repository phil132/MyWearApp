package phil.mywearapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SimpleWearableListViewActivity extends Activity {
    private String[] mItems;
    private WearableListView mWearableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlistview);
        mItems = new String[]{"Simple Card", "Custom Cards", "CardFrame", "AdvancedListView", "CircledImageView", "DelayedConfirmationView"};
        mWearableListView = (WearableListView) findViewById(R.id.list);
        mWearableListView.setAdapter(new SampleAdapter(this, mItems));
        mWearableListView.setClickListener(mClickListener);
    }

    private WearableListView.ClickListener mClickListener = new WearableListView.ClickListener() {
        @Override
        public void onClick(WearableListView.ViewHolder viewHolder) {
            int position = viewHolder.getAdapterPosition();

            Class activityClass;
            switch (position) {
                case 0:
                    activityClass = SimpleCardActivity.class;
                    switchActivity(activityClass);
                    break;
                case 1:
                    activityClass = CardFragmentActivity.class;
                    switchActivity(activityClass);
                    break;
                case 2:
                    activityClass = CardFrameActivity.class;
                    switchActivity(activityClass);
                    break;
                case 3:
                    activityClass = awListActivity.class;
                    switchActivity(activityClass);
                    break;
                case 4:
                    activityClass = CircledImageActivity.class;
                    switchActivity(activityClass);
                    break;
                case 5:
                    activityClass = DelayedConfirmationViewActivity.class;
                    switchActivity(activityClass);
                    break;
            }
        }

        public void switchActivity(Class activityClass) {

            Intent intent = new Intent(SimpleWearableListViewActivity.this, activityClass);
            //intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

        @Override
        public void onTopEmptyRegionClick() {
            Toast.makeText(SimpleWearableListViewActivity.this, "Tapped on top empty region", Toast.LENGTH_LONG).show();
        }
    };

    public static class SampleAdapter extends WearableListView.Adapter {
        private LayoutInflater mInflater;
        private String[] mSampleItems;

        public SampleAdapter(Context context, String[] sampleItems) {
            mInflater = LayoutInflater.from(context);
            mSampleItems = sampleItems;
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                              int viewType) {
            View view = mInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            return new WearableListView.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int position) {
            TextView textView = (TextView) viewHolder.itemView;
            textView.setText(mSampleItems[position]);
        }

        @Override
        public int getItemCount() {
            return mSampleItems.length;
        }

    }
}