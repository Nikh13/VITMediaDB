package vit.collegecode.mediadb.fragments;

import vit.collegecode.mediadb.R;
import vit.collegecode.mediadb.data.BitmapScale;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PagerFragment extends Fragment {
	
	int[] movies_poster={R.drawable.im,R.drawable.got,R.drawable.ca};
	int[] movies_landscape={R.drawable.iml,R.drawable.gotl,R.drawable.cal};
	int position;
	Context mActivity;
	ImageView poster;
	ImageView landscape;
	Bitmap posterBitmap;
	Bitmap landscapeBitmap;
        
	public PagerFragment(){
		
	}
	
	public PagerFragment(int _position){
		position=_position;
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		RelativeLayout v = (RelativeLayout)inflater.inflate(R.layout.pageritem, null);
		TextView movietype =(TextView)v.findViewById(R.id.textView1);
		movietype.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf"));
		poster = (ImageView)v.findViewById(R.id.imageView1);
		landscape = (ImageView)v.findViewById(R.id.imageView2);
		
		Thread t = new Thread(){
			
			public void run() {
				posterBitmap = BitmapScale.decodeSampledBitmapFromResource(getResources(), movies_poster[position], 100, 184);
				landscapeBitmap = BitmapScale.decodeSampledBitmapFromResource(getResources(), movies_landscape[position], 250, 184);
				mHandler.sendEmptyMessage(0);
			}
		};
		t.run();
		return v;
		
	}
	
	Handler mHandler = new Handler() {
		
		@Override
        public void handleMessage(Message msg) {
        	super.handleMessage(msg);
        	
        	poster.setScaleType(ScaleType.FIT_XY);
        	poster.setImageBitmap(posterBitmap);
        	
        	landscape.setScaleType(ScaleType.FIT_XY);
    		landscape.setImageBitmap(landscapeBitmap);
    		
        }
	};
	
}
