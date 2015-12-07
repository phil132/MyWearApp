package phil.mywearapp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by philipp on 03.12.2015.
 */
public class WearableListItemLayout extends LinearLayout
        implements WearableListView.OnCenterProximityListener {
    private CircledImageView mCircle;

   // private ImageView mCircledImage;

    private TextView mCar;
    private TextView mDistance;
    private final float mFadedTextAlpha;
    private final int mUnselectedCircleColor, mSelectedCircleColor, mPressedCircleColor;
    private boolean mIsInCenter;
    private float mBigCircleRadius;
    private float mSmallCircleRadius;
    private ObjectAnimator mScalingDownAnimator;
    private ObjectAnimator mScalingUpAnimator;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mFadedTextAlpha = 40 / 100f;
        mUnselectedCircleColor = Color.parseColor("#c1c1c1");
        mSelectedCircleColor = Color.parseColor("#3185ff");
        mPressedCircleColor = Color.parseColor("#2955c5");
        mSmallCircleRadius = getResources().getDimensionPixelSize(R.dimen.small_circle_radius);
        mBigCircleRadius = getResources().getDimensionPixelSize(R.dimen.big_circle_radius);
        // when expanded, the circle may extend beyond the bounds of the view
        setClipChildren(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mCircle = (CircledImageView) findViewById(R.id.circle);

       // mCircledImage = (ImageView) findViewById(R.id.circledimgage);

        mCar = (TextView) findViewById(R.id.car);
        mDistance = (TextView) findViewById(R.id.distance);
        mScalingUpAnimator = ObjectAnimator.ofFloat(mCircle, "circleRadius", mBigCircleRadius);
        mScalingUpAnimator.setDuration(150L);
        mScalingDownAnimator = ObjectAnimator.ofFloat(mCircle, "circleRadius",
                mSmallCircleRadius);
        mScalingDownAnimator.setDuration(150L);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        if (animate) {
            mScalingDownAnimator.cancel();
            if (!mScalingUpAnimator.isRunning() && mCircle.getCircleRadius() !=
                    mBigCircleRadius) {
                mScalingUpAnimator.start();
            }
        } else {
            mCircle.setCircleRadius(mBigCircleRadius);
        }
        mCar.setAlpha(1f);
        mDistance.setAlpha(1f);
        mCircle.setCircleColor(mSelectedCircleColor);
        mIsInCenter = true;
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        if (animate) {
            mScalingUpAnimator.cancel();
            if (!mScalingDownAnimator.isRunning() && mCircle.getCircleRadius() != mSmallCircleRadius) {
                mScalingDownAnimator.start();
            }
        } else {
            mCircle.setCircleRadius(mSmallCircleRadius);
        }
        mCar.setAlpha(mFadedTextAlpha);
        mDistance.setAlpha(mFadedTextAlpha);
        mCircle.setCircleColor(mUnselectedCircleColor);
        mIsInCenter = false;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (mIsInCenter && pressed) {
            mCircle.setCircleColor(mPressedCircleColor);
        }
        if (mIsInCenter && !pressed) {
            mCircle.setCircleColor(mSelectedCircleColor);
        }
    }
}