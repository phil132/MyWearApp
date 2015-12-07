package phil.mywearapp;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by philipp on 03.12.2015.
 *
 * test2
 */
public class awListAdapter extends WearableListView.Adapter {
    private String[] mDatasetVehicle;
    private String[] mDatasetDistance;
    private final Context mContext;
    private final LayoutInflater mInflater;

    // Provide a suitable constructor (depends on the kind of dataset)
    public awListAdapter(Context context, String[] datasetVehicle, String[] datasetDistance) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatasetVehicle = datasetVehicle;
        mDatasetDistance = datasetDistance;
    }

    // Provide a reference to the type of views you're using
    public static class ItemViewHolder extends WearableListView.ViewHolder {
        private TextView car_tv;
        private TextView distance_tv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            // find the text view within the custom item's layout
            car_tv = (TextView) itemView.findViewById(R.id.car);
            distance_tv = (TextView) itemView.findViewById(R.id.distance);
        }
    }



    // Create new views for list items
    // (invoked by the WearableListView's layout manager)
    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // Inflate our custom layout for list items
        return new ItemViewHolder(mInflater.inflate(R.layout.list_item, null));
    }

    // Replace the contents of a list item
    // Instead of creating new views, the list tries to recycle existing ones
    // (invoked by the WearableListView's layout manager)
    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder,
                                 int position) {
        // retrieve the text view
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        TextView view1 = itemHolder.car_tv;
        TextView view2 = itemHolder.distance_tv;
        // replace text contents
        view1.setText(mDatasetVehicle[position]);
        view2.setText(mDatasetDistance[position]);
        // replace list item's metadata
        holder.itemView.setTag(position);
    }

    // Return the size of your dataset
    // (invoked by the WearableListView's layout manager)
    @Override
    public int getItemCount() {
        return mDatasetDistance.length;
    }
}
