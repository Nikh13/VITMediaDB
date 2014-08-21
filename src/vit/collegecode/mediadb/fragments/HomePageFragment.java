package vit.collegecode.mediadb.fragments;

import java.util.ArrayList;
import java.util.List;

import vit.collegecode.mediadb.MainActivity;
import vit.collegecode.mediadb.ParallaxTransformer;
import vit.collegecode.mediadb.R;
import vit.collegecode.mediadb.adapters.BucketListAdapter;
import vit.collegecode.mediadb.adapters.PagerFragmentAdapter;
import vit.collegecode.mediadb.data.BitmapScale;
import vit.collegecode.mediadb.helpers.FadingActionBarHelper;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class HomePageFragment extends Fragment {
	Context context;
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
	Handler handler;
	PageIndicator mIndicator;
	int[] movies_poster = { R.drawable.im, R.drawable.got, R.drawable.ca };
	HomePageFragment frag;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedIstanceState) {

		handler = new Handler();
		frag = this;
		View home = inflater.inflate(R.layout.activity_main, null);
		Typeface robotol = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Light.ttf");
		Typeface robotoli = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-LightItalic.ttf");
		Typeface robotob = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Bold.ttf");

		// movie_header = inflater.inflate(R.layout.header_movie, null);
		// tv_header = inflater.inflate(R.layout.header_tv, null);

		mAdapter = new PagerFragmentAdapter(getChildFragmentManager());

		cardList1 = (ListView) home.findViewById(R.id.listView1);

		cardList2 = (ListView) home.findViewById(R.id.listView2);

		movieitems = new ArrayList<String>();
		tvitems = new ArrayList<String>();

		for (int i = 0; i < 3; i++) {
			movieitems.add("Movie");
			tvitems.add("TV SHOW");
		}

		// cardList1.addHeaderView(movie_header);
		// cardList2.addHeaderView(tv_header);
		final ParallaxTransformer pt = new ParallaxTransformer(R.id.imageView2,
				R.id.imageView1);
		mPager = (ViewPager) home.findViewById(R.id.pager);
		

		
		
		mIndicator = (CirclePageIndicator) home.findViewById(R.id.indicator);
		
		
		Log.i("HomeFragment", "Fragments Loaded in viewpager");

		TextView movieheader = (TextView) home.findViewById(R.id.textView1);
		movieheader.setTypeface(robotoli);

		RelativeLayout movieheaderLayout = (RelativeLayout) home
				.findViewById(R.id.relativeLayout1);

		movieheaderLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainActivity) getActivity()).getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content_frame, new MovieListFragment())
						.addToBackStack("testing").commit();
				((MainActivity) getActivity()).getSupportFragmentManager()
				.beginTransaction().hide(frag);
			}
		});

		TextView moviemore = (TextView) home.findViewById(R.id.textView2);
		moviemore.setTypeface(robotob);

		RelativeLayout tvheaderLayout = (RelativeLayout) home
				.findViewById(R.id.RelativeLayout2);

		tvheaderLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainActivity) getActivity()).getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content_frame, new TVListFragment())
						.addToBackStack("testing").commit();
				((MainActivity) getActivity()).getSupportFragmentManager()
				.beginTransaction().hide(frag);
			}
		});

		TextView tvheader = (TextView) home.findViewById(R.id.textView3);
		tvheader.setTypeface(robotoli);

		TextView tvmore = (TextView) home.findViewById(R.id.textView4);
		tvmore.setTypeface(robotob);

		movieadapter = new CustomAdapter(getActivity(), movieitems);
		tvadapter = new CustomAdapter(getActivity(), tvitems);

		movieadapter.enableAutoMeasure(110);
		tvadapter.enableAutoMeasure(110);

		
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				cardList1.setAdapter(movieadapter);

				cardList2.setAdapter(tvadapter);
				
				mPager.setAdapter(mAdapter);
				mPager.setPageTransformer(false, pt);
				
				mIndicator.setViewPager(mPager);
			}
		});
		
		Log.i("HomeFragment", "Adapters set");

		// setListViewHeightBasedOnChildren(cardList1);
		// setListViewHeightBasedOnChildren(cardList2);

		cardList1.setVisibility(View.VISIBLE);
		cardList2.setVisibility(View.VISIBLE);
		
		Log.i("HomeFragment", "Finished Loading");
		
		return home;

	}

	

	public class CustomAdapter extends BucketListAdapter<String> {

		private Activity mActivity;
		private List<String> items;

		public CustomAdapter(Activity ctx, List<String> elements) {
			super(ctx, elements);
			this.mActivity = ctx;
			this.items = elements;
		}

		@Override
		protected View getBucketElement(final int position,
				String currentElement) {
			ViewHolder holder;
			View bucketElement = null;
			LayoutInflater inflater = mActivity.getLayoutInflater();
			bucketElement = inflater.inflate(R.layout.griditem, null);
			holder = new ViewHolder(bucketElement);
			bucketElement.setTag(holder);
			holder.gridtitle.setText(currentElement);

			bucketElement.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((MainActivity) getActivity()).mActionBarHelper = new FadingActionBarHelper(
							getActivity().getActionBar(), getActivity()
									.getResources().getDrawable(
											R.drawable.actionbar_bg));
					((MainActivity) getActivity()).getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.content_frame, new MediaItemFragment())
							.addToBackStack("testing").commit();
					((MainActivity) getActivity()).getSupportFragmentManager()
					.beginTransaction().hide(frag);
					Toast.makeText(mActivity,
							"Position " + Integer.toString(position),
							Toast.LENGTH_SHORT).show();
				}
			});

			holder.gridimage.setImageBitmap(BitmapScale.decodeSampledBitmapFromResource(
					getResources(), movies_poster[position], 200, 200));

			return bucketElement;
		}
	}

	class ViewHolder {

		Typeface robotol = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Light.ttf");
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

	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
				MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, view, listView);
			if (i == 0)
				view.setLayoutParams(new AbsListView.LayoutParams(desiredWidth,
						AbsListView.LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	@Override
	public void onResume() {
		super.onResume();

	}

}
