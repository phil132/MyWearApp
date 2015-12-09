package phil.mywearapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 07.12.2015.
 */
public class DelayedConfirmationViewActivity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {

    // Timeout delay for confirmation
    private static final long DELAY_TIMEOUT = 2500L;

    private DelayedConfirmationView mConfirmationView;
    private TextView mTextView;
    private boolean mIsAnimating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_confirmation_view);

        mConfirmationView = (DelayedConfirmationView) findViewById(R.id.delayed_confirm);
        mConfirmationView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_send));
        mTextView = (TextView) findViewById(R.id.label);
        mTextView.setText("Send Message");

        //listener when view is tapped or elapsed
        mConfirmationView.setListener(this);

    }


    @Override
    public void onTimerFinished(View view) {
        mIsAnimating = false;

        //Starting confirmation screen
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Message sent");
        startActivity(intent);

        mConfirmationView.reset();
        mConfirmationView.setImageResource(R.drawable.ic_action_send);
        mTextView.setText("Send Message");
    }

    @Override
    public void onTimerSelected(View view) {
        mConfirmationView.reset();
    }

    public void onClickLayout(View v) {
        if (mIsAnimating) {
            mConfirmationView.setImageResource(R.drawable.ic_action_send);
            mTextView.setText("Send Message");
            mIsAnimating = false;
            return;
        }
        mIsAnimating = true;
        mConfirmationView.setImageResource(R.drawable.ic_full_cancel);
        mTextView.setText("Sending...");
        mConfirmationView.setTotalTimeMs(DELAY_TIMEOUT);
        mConfirmationView.start();
    }
}
