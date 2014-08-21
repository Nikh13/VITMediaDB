package vit.collegecode.mediadb.fragments;

import java.lang.ref.WeakReference;

import vit.collegecode.mediadb.MainActivity;
import vit.collegecode.mediadb.R;
import vit.collegecode.mediadb.data.BitmapScale;
import vit.collegecode.mediadb.fragments.HomePageFragment.CustomAdapter;
import vit.collegecode.mediadb.views.CustomItemCard;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.AsyncTask;
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

public class MediaItemFragment extends HeaderFragment {

	private CardListView mListView;
	private CardAdapter<Card> adapter;
	private String[] mListViewTitles;
	private AsyncLoadSomething mAsyncLoadSomething;
	private CustomAdapter page_adapter;
	private Typeface robotol;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		robotol = Typeface.createFromAsset(activity.getAssets(),
				"fonts/Roboto-Light.ttf");
		adapter = new CardAdapter<Card>(getActivity(),
				android.R.color.holo_blue_dark);
		setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
		setOnHeaderScrollChangedListener(new OnHeaderScrollChangedListener() {
			@Override
			public void onHeaderScrollChanged(float progress, int height,
					int scroll) {
				height -= getActivity().getActionBar().getHeight();

				progress = (float) scroll / height;
				if (progress > 1f)
					progress = 1f;

				// *
				// `*
				// ```*
				// ``````*
				// ````````*
				// `````````*
				progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

				((MainActivity) getActivity()).getFadingActionBarHelper()
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
		mListView = (CardListView) inflater.inflate(R.layout.fragment_listview,
				container, false);
		return mListView;
	}

	@Override
	public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View header = inflater.inflate(R.layout.fragment_header, container,
				false);

		ImageView background = (ImageView) header
				.findViewById(android.R.id.background);
		Bitmap bitmap = BitmapScale.decodeSampledBitmapFromResource(
				getActivity().getResources(), R.drawable.iml, 48, 48);
		Bitmap transBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(transBitmap);
		canvas.drawARGB(0, 0, 0, 0);
		Paint paint = new Paint();
		paint.setAlpha(190);
		canvas.drawBitmap(bitmap, 0, 0, paint);   
		// background.getDrawable().setAlpha(190);

		TextView title = (TextView) header.findViewById(R.id.title);
		title.setText("Iron Man");
		title.setTypeface(robotol);

		return header;
	}

	@Override
	public View onCreateContentOverlayView(LayoutInflater arg0, ViewGroup arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	private void addCards(String[] titles) {
		mListViewTitles = titles;

		adapter.add(new CustomItemCard(getActivity(), "Description"));
		adapter.add(new CardHeader("Users"));
		for (int i = 0; i < mListViewTitles.length; i++) {
			adapter.add(new Card(mListViewTitles[i])
					.setThumbnail(getResources()
							.getDrawable(R.drawable.profile)));
		}

		mListView.setVisibility(View.VISIBLE);
		mListView.setAdapter(adapter);
		setListViewAdapter(mListView, adapter);
	}

	private void cancelAsyncTask(AsyncTask task) {
		if (task != null)
			task.cancel(false);
	}

	private static class AsyncLoadSomething extends
			AsyncTask<Void, Void, String[]> {

		private static final String TAG = "AsyncLoadSomething";

		final WeakReference<MediaItemFragment> weakFragment;

		public AsyncLoadSomething(MediaItemFragment fragment) {
			this.weakFragment = new WeakReference<MediaItemFragment>(fragment);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			final MediaItemFragment audioFragment = weakFragment.get();
			// if (audioFragment.mListView != null)
			// audioFragment.mListView.setVisibility(View.INVISIBLE);
		}

		@Override
		protected String[] doInBackground(Void... voids) {

			return new String[] { "Placeholder", "Placeholder", "Placeholder",
					"Placeholder", "Placeholder", "Placeholder", "Placeholder",
					"Placeholder", "Placeholder", "Placeholder", "Placeholder",
					"Placeholder", "Placeholder", "Placeholder", "Placeholder",
					"Placeholder", "Placeholder", "Placeholder", "Placeholder",
					"Placeholder", "Placeholder", "Placeholder", "Placeholder",
					"Placeholder" };
		}

		@Override
		protected void onPostExecute(String[] titles) {
			super.onPostExecute(titles);
			final MediaItemFragment audioFragment = weakFragment.get();
			audioFragment.addCards(titles);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Animation anim = AnimationUtils.loadAnimation(getActivity(),
				R.anim.slide_in_bottom);
		this.getContainer().startAnimation(anim);
		((MainActivity) getActivity()).getFadingActionBarHelper()
				.setActionBarAlpha(255);
	}
}
