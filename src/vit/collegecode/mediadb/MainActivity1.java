package vit.collegecode.mediadb;

import java.util.ArrayList;
import java.util.List;

import vit.collegecode.mediadb.adapters.*;
import vit.collegecode.mediadb.data.BitmapScale;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends FragmentActivity {
	
	
	ArrayList<String> movieitems;
	ArrayList<String> tvitems;
	ListView cardList1;
	ListView cardList2;
	View movie_header;
	View tv_header;
	CustomAdapter movieadapter;
	CustomAdapter tvadapter;
	PagerFragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	int[] movies_poster={R.drawable.im,R.drawable.got,R.drawable.ca};
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Typeface robotol = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		Typeface robotoc = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf");
		
		TextView movieheader = (TextView)findViewById(R.id.textView1);
		movieheader.setTypeface(robotoc);
		
		TextView moviemore = (TextView)findViewById(R.id.textView2);
		moviemore.setTypeface(robotol);
		
		TextView tvheader = (TextView)findViewById(R.id.textView3);
		tvheader.setTypeface(robotoc);
		
		TextView tvmore = (TextView)findViewById(R.id.textView4);
		tvmore.setTypeface(robotol);
		
		
		
//		movie_header = getLayoutInflater().inflate(R.layout.header_movie, null);
//		tv_header = getLayoutInflater().inflate(R.layout.header_tv, null);
		
		mAdapter = new PagerFragmentAdapter(getSupportFragmentManager());
		
		cardList1 = (ListView)findViewById(R.id.listView1);
		cardList2 = (ListView)findViewById(R.id.listView2);
		
		movieitems=new ArrayList<String>();
		tvitems=new ArrayList<String>();
		
		for(int i=0;i<3;i++)
		{
			movieitems.add("Movie");
			tvitems.add("TV SHOW");
		}
		
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		
		mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		
//		cardList1.addHeaderView(movie_header);
//		cardList2.addHeaderView(tv_header);
		
		movieadapter = new CustomAdapter(this, movieitems);
		tvadapter = new CustomAdapter(this, tvitems);
		
		movieadapter.enableAutoMeasure(110);
		tvadapter.enableAutoMeasure(110);
		
		cardList1.setAdapter(movieadapter);
		cardList2.setAdapter(tvadapter);
		
		cardList1.setVisibility(View.VISIBLE);
		cardList2.setVisibility(View.VISIBLE);
		
		
	}
	
	public class CustomAdapter extends BucketListAdapter<String> {
		
		  private Activity mActivity;
	       private List<String> items;
		
		public CustomAdapter(Activity ctx, List<String> elements) {
			super(ctx, elements);
			this.mActivity=ctx;
			this.items=elements;
		}
		
		@Override
		protected View getBucketElement(final int position, String currentElement) {
			ViewHolder holder;
			View bucketElement = null;
			LayoutInflater inflater = mActivity.getLayoutInflater();
			bucketElement = inflater.inflate(R.layout.griditem,null);
			holder = new ViewHolder(bucketElement);
			bucketElement.setTag(holder);
			holder.gridtitle.setText(currentElement);
			
			bucketElement.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(mActivity, "Position "+Integer.toString(position) ,Toast.LENGTH_SHORT ).show();
				}
			});
			
			BitmapScale bs =new BitmapScale(getApplicationContext());
			holder.gridimage.setImageBitmap(bs.decodeSampledBitmapFromResource(getResources(), movies_poster[position], 200, 200));
			
			return bucketElement;
		}
	}
	
	class ViewHolder{
		
		Typeface robotol = Typeface.createFromAsset(getApplication().getAssets(), "fonts/Roboto-Light.ttf");
		public TextView gridtitle = null;
		public ImageView gridimage = null;

		ViewHolder(View row) {
			gridtitle = (TextView) row.findViewById(R.id.textView1);
			gridtitle.setTypeface(robotol);
	        gridimage = (ImageView) row.findViewById(R.id.imageView1);
			
		}

		void populateFrom(String s) {
			gridtitle.setText(s);
		}
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	public int calculateInSampleSize(
//            BitmapFactory.Options options, int reqWidth, int reqHeight) {
//    // Raw height and width of image
//    final int height = options.outHeight;
//    final int width = options.outWidth;
//    int inSampleSize = 1;
//
//    if (height > reqHeight || width > reqWidth) {
//
//        final int halfHeight = height / 2;
//        final int halfWidth = width / 2;
//
//        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
//        // height and width larger than the requested height and width.
//        while ((halfHeight / inSampleSize) > reqHeight
//                && (halfWidth / inSampleSize) > reqWidth) {
//            inSampleSize *= 2;
//        }
//    }
//
//    return inSampleSize;
//}
//	
//	
//	public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
//	        int reqWidth, int reqHeight) {
//
//	    // First decode with inJustDecodeBounds=true to check dimensions
//	    final BitmapFactory.Options options = new BitmapFactory.Options();
//	    options.inJustDecodeBounds = true;
//	    BitmapFactory.decodeResource(res, resId, options);
//
//	    // Calculate inSampleSize
//	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//	    // Decode bitmap with inSampleSize set
//	    options.inJustDecodeBounds = false;
//	    return BitmapFactory.decodeResource(res, resId, options);
//	}
	

}
