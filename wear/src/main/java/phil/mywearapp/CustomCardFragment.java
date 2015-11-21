package phil.mywearapp;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by philipp on 20.11.2015.
 */
public class CustomCardFragment extends CardFragment {
    @Override
    public View onCreateContentView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frame_layout, container, false);
    }
}