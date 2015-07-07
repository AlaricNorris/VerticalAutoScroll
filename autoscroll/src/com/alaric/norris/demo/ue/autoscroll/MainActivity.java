package com.alaric.norris.demo.ue.autoscroll ;

import android.annotation.SuppressLint ;
import android.app.Activity ;
import android.os.Bundle ;
import android.os.Handler ;
import android.util.Log ;
import android.view.ViewTreeObserver ;
import android.view.ViewTreeObserver.OnPreDrawListener ;
import android.widget.LinearLayout ;
import com.alaric.norris.demo.ue.autoscroll.R ;

@ SuppressLint ( "NewApi" )
public class MainActivity extends Activity implements OnPreDrawListener {

	private LinearLayout mLinearLayout ;

	private final Handler mHandler = new Handler() ;

	ViewTreeObserver mViewTreeObserver ;

	@ SuppressLint ( "NewApi" )
	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
//		mLinearLayout = (LinearLayout) findViewById(R.id.layout) ;
//		mViewTreeObserver = mLinearLayout.getViewTreeObserver() ;
//		mViewTreeObserver.addOnPreDrawListener(this) ;
//		mHandler.postDelayed(ScrollRunnable , 1000) ;
	}

	private Runnable ScrollRunnable = new Runnable() {

		@ Override
		public void run() {
			if(max > 0) {
				mLinearLayout.scrollBy(0 , 30) ;
				if(mLinearLayout.getScrollY() >= max) {
					mLinearLayout.setScrollY(0) ;
				}
				else {
					mHandler.postDelayed(this , 500) ;
				}
			}
		}
	} ;

	int max = 0 ;

	/**
	 * 	(non-Javadoc)
	 * 	@see android.view.ViewTreeObserver.OnPreDrawListener#onPreDraw()
	 */
	@ Override
	public boolean onPreDraw() {
		max = Math.abs(getWindow().getDecorView().getHeight()
				- mLinearLayout.getChildAt(0).getHeight()) ;
		Log.i("tag" , "max" + max) ;
		return false ;
	}
}
