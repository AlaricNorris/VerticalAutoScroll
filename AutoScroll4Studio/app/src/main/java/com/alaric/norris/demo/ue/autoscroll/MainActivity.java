package com.alaric.norris.demo.ue.autoscroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnPreDrawListener, OnWindowFocusChangeListener {

    private LinearLayout mLinearLayout;

    private Handler mHandler = new Handler();

    ViewTreeObserver mViewTreeObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = (LinearLayout) findViewById(R.id.layout);
        mViewTreeObserver = getWindow().getDecorView().getViewTreeObserver();
        mViewTreeObserver.addOnPreDrawListener(this);
        mViewTreeObserver.addOnWindowFocusChangeListener(this);
        mHandler.postDelayed(ScrollRunnable, 1000);
    }

    private Runnable ScrollRunnable = new Runnable() {

        @Override
        public void run() {
            Log.i("tag", "max" + max);
            Log.i("tag", "scrollBy" + mLinearLayout.getScrollY());
            Log.i("tag", "getTranslationY" + mLinearLayout.getTranslationY());
            if (max > 0) {
//				mLinearLayout.setTranslationY(mLinearLayout.getTranslationY() + 30) ;
//				mLinearLayout.postInvalidate() ;
//				if(mLinearLayout.getTranslationY() >= max)
//					mLinearLayout.setTranslationY(0) ;
                mLinearLayout.scrollBy(0, -30);
                if (mLinearLayout.getScrollY() <= -max)
                    mLinearLayout.setScrollY(0);
                mHandler.postDelayed(this, 500);
            }
        }
    };

    int max = 0;

    /**
     * (non-Javadoc)
     *
     * @see android.view.ViewTreeObserver.OnPreDrawListener#onPreDraw()
     */
    @Override
    public boolean onPreDraw() {
        max = Math.abs(getWindow().getDecorView().getHeight() - 50);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        max = Math.abs(getWindow().getDecorView().getHeight() - 50);
    }

    /**
     * (non-Javadoc)
     *
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(ScrollRunnable);
    }
}
