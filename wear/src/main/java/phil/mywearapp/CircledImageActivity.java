package phil.mywearapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.CircledImageView;
import android.view.View;
import android.widget.Toast;


/**
 * Created by philipp on 04.12.2015.
 *
 * Contains CircledImageViews with Progress Bar
 */
public class CircledImageActivity extends Activity {

    private float mValue = 0f;
    private Handler handler1;
    private CircledImageView circledImageViewCancel;
    private boolean tbusy = false;
    private MyProgressThread myThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circled_image_view);

        // Cancel Circled Image View
        circledImageViewCancel =
                (CircledImageView) findViewById(R.id.circledImageViewCancel);

        handler1 = new Handler();
        circledImageViewCancel.setProgress(0.0f);

        circledImageViewCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //new Thread(new Runnable() {
                myThread =  new MyProgressThread();
                new Thread(myThread).start();
                Toast.makeText(getBaseContext(), "OK clicked", Toast.LENGTH_SHORT).show();
            }

        });

                // OK Circled Image View
        final CircledImageView circledImageViewOK =
                        (CircledImageView) findViewById(R.id.circledImageViewOK);

                circledImageViewOK.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //Toast.makeText(getBaseContext(), "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                });
            }

    public class MyProgressThread implements Runnable {

                    @Override
                    public void run() {

                        while (mValue < 1000) {
                            mValue += 0.01f;

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            //update progress bar
                            handler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    circledImageViewCancel.setProgress(mValue);
                                }
                            });
                        }
                    }
            }


}