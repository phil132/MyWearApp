package phil.mywearapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.CardScrollView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by philipp on 20.11.2015.
 */
public class CardFrameActivity extends Activity {
    static boolean mStateOnOff = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_scroll_view);
        final BoxInsetLayout boxInsetLayout = (BoxInsetLayout) findViewById(R.id.box_inset_layout);
        final ImageButton imageButton = (ImageButton) findViewById(R.id.btn_onoff);
        final CardScrollView cardScrollView = (CardScrollView) findViewById(R.id.card_scroll_view);
        cardScrollView.setCardGravity(Gravity.BOTTOM);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStateOnOff) {
                    mStateOnOff = false;
                    boxInsetLayout.setBackgroundResource(R.drawable.light_off_background);
                    imageButton.setBackgroundResource(R.drawable.button_off);
                } else {
                    mStateOnOff = true;
                    boxInsetLayout.setBackgroundResource(R.drawable.light_on_background);
                    imageButton.setBackgroundResource(R.drawable.button_on);
                }
            }
        });
    }
}