package phil.mywearapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;

/**
 * Created by philipp on 20.11.2015.
 */
public class SimpleCardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardlayout);

        String description = "Text viel text und noch mehr Text.Text viel text und noch mehr Text.Text viel text und noch mehr Text.Text viel text und noch mehr Text.Text viel text und noch mehr Text.Text viel text und noch mehr Text.";
        String title = "Überschrift";

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CardFragment cardFragment = CardFragment.create(title, description, R.mipmap.ic_launcher);
        cardFragment.setExpansionEnabled(true);
        cardFragment. setExpansionFactor(4);
        fragmentTransaction.add(R.id.frame_layout, cardFragment);
        fragmentTransaction.commit();
    }
}