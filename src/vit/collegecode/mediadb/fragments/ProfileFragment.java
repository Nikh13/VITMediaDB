package vit.collegecode.mediadb.fragments;

import java.lang.ref.WeakReference;

import vit.collegecode.mediadb.CommonLib;
import vit.collegecode.mediadb.MainActivity;
import vit.collegecode.mediadb.R;
import vit.collegecode.mediadb.data.BitmapScale;
import vit.collegecode.mediadb.fragments.HomePageFragment.CustomAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cards_ui.Card;
import com.example.cards_ui.CardAdapter;
import com.example.cards_ui.CardHeader;
import com.example.cards_ui.CardListView;

public class ProfileFragment extends HeaderFragment {

	private CardListView mListView;
	private CardAdapter<Card> adapter;
    private String[] mListViewTitles;
    private AsyncLoadSomething mAsyncLoadSomething;
    private CustomAdapter page_adapter;
    Typeface robotol;
    
	  @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        robotol = Typeface.createFromAsset(getActivity().getAssets(),
					"fonts/Roboto-Light.ttf");
	        adapter = new CardAdapter<Card>(getActivity(),android.R.color.holo_blue_dark);
	        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
	        setOnHeaderScrollChangedListener(new OnHeaderScrollChangedListener() {
	            @Override
	            public void onHeaderScrollChanged(float progress, int height, int scroll) {
	                height -= getActivity().getActionBar().getHeight();

	                progress = (float) scroll / height;
	                
	                
	                if (progress > 1f) progress = 1f;

	                // *
	                // `*
	                // ```*
	                // ``````*
	                // ````````*
	                // `````````*
	                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

	                Log.i("Profile", "Progress: "+progress);
	                Log.i("Profile", "FadingHelper: "+ ((MainActivity) getActivity())
	                        .getFadingActionBarHelper());
	                
	                ((MainActivity) getActivity())
	                        .getFadingActionBarHelper()
	                        .setActionBarAlpha((int) (255 * progress));
	            }
	        });
	        cancelAsyncTask(mAsyncLoadSomething);
	        mAsyncLoadSomething = new AsyncLoadSomething(this);
	        mAsyncLoadSomething.execute();
	    }
	
	  
	  @Override
	    public void onDetach() {
		  cancelAsyncTask(mAsyncLoadSomething);
	        super.onDetach();
	    }

	@Override
	public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		mListView = (CardListView) inflater.inflate(R.layout.fragment_listview, container, false);
		mListView.setPadding(0, 0, 0, 0);
//		addCards();
		return mListView;
	}

	@Override
	public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View header = inflater.inflate(R.layout.profile_layout, container, false);
		ImageView profile_pic = (ImageView)header.findViewById(R.id.profile_pic);
		int icon = R.drawable.profile_pic;
		
		Bitmap bitmap = BitmapScale.decodeSampledBitmapFromResource(getActivity().getResources(), icon, 48, 48);
		Bitmap thumb = CommonLib.getRoundedCornerBitmap(bitmap,
				bitmap.getHeight() / 2);
		profile_pic.setImageBitmap(thumb);
		
		TextView text = (TextView)header.findViewById(R.id.title);
		text.setText("Nikhil Loney");
		text.setTypeface(robotol);
		
		return header;
	}

	@Override
	public View onCreateContentOverlayView(LayoutInflater arg0, ViewGroup arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void addCards() {
		int icon = R.drawable.ca;
		
		adapter.add(new CardHeader("Recent Activity"));
		for(int i=0;i<5;i++){
			adapter.add(new Card("Recent Activity "+i));
		}
		adapter.add(new CardHeader("Movies recently added"));
		for(int i=0;i<5;i++){
			adapter.add(new Card("Movie "+i).setThumbnail(getActivity()
					.getResources().getDrawable(icon)));
		}
		adapter.add(new CardHeader("TV Shows recently added"));
		for(int i=0;i<5;i++){
			adapter.add(new Card("Recent Activity "+i).setThumbnail(getActivity()
					.getResources().getDrawable(icon)));
		}
        mListView.setVisibility(View.VISIBLE);
        mListView.setAdapter(adapter);
        setListViewAdapter(mListView, adapter);
    }
	
	private void cancelAsyncTask(AsyncTask task) {
        if (task != null) task.cancel(false);
    }
	
	private static class AsyncLoadSomething extends AsyncTask<Void, Void, String[]> {

        private static final String TAG = "AsyncLoadSomething";

        final WeakReference<ProfileFragment> weakFragment;

        public AsyncLoadSomething(ProfileFragment fragment) {
            this.weakFragment = new WeakReference<ProfileFragment>(fragment);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            final ProfileFragment audioFragment = weakFragment.get();
//            if (audioFragment.mListView != null) audioFragment.mListView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String[] doInBackground(Void... voids) {

            return new String[]{"Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder"};
        }

        @Override
        protected void onPostExecute(String[] titles) {
            super.onPostExecute(titles);
            final ProfileFragment audioFragment = weakFragment.get();
            audioFragment.addCards();
        }
    }
	
	
	@Override
	public void onDestroy(){
		Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom);
		this.getContainer().startAnimation(anim);
		super.onDestroy();
		((MainActivity) getActivity())
        .getFadingActionBarHelper()
        .setActionBarAlpha(255);
	}
	
}
