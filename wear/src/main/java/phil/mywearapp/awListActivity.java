package phil.mywearapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;

/**
 * Created by philipp on 03.12.2015.
 *
 * test
 */
public class awListActivity extends Activity
        implements WearableListView.ClickListener {
    // Sample dataset for the list
    String[] elements = {"List Item 1", "List Item 2", "Mini"};
    String[] distances = {"300", "400", "5000m"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awlistview);

        // Get the list component from the layout of the activity
        WearableListView alistView =
                (WearableListView) findViewById(R.id.awearable_list);

        // Assign an adapter to the list
        alistView.setAdapter(new awListAdapter(this, elements, distances));

        // Set a click listener
        alistView.setClickListener(this);
    }

    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer tag = (Integer) v.itemView.getTag();
        // use this data to complete some action ...
    }

    @Override
    public void onTopEmptyRegionClick() {
    }
}
