package vit.collegecode.mediadb.fragments;

import vit.collegecode.mediadb.MainActivity;
import vit.collegecode.mediadb.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.cards_ui.Card;
import com.example.cards_ui.CardAdapter;
import com.example.cards_ui.CardBase;
import com.example.cards_ui.CardHeader;
import com.example.cards_ui.CardListView;
import com.example.cards_ui.CardListView.CardClickListener;

public class MovieListFragment extends Fragment {

	CardListView listView;
	CardAdapter<Card> adapter;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedIstanceState) {

		rootView = inflater.inflate(R.layout.movie_list, null);

		listView = (CardListView) rootView.findViewById(R.id.movie_card_list);

		adapter = new CardAdapter<Card>(getActivity(),
				android.R.color.holo_blue_dark);

		int icon = R.drawable.ca;
		Bitmap bitmap = BitmapFactory.decodeResource(getActivity()
				.getResources(), icon);
		bitmap = Bitmap.createScaledBitmap(bitmap, 150, 184, true);

		adapter.add(new CardHeader("Movies"));

		for (int i = 0; i < 20; i++) {
			adapter.add(new Card("Movie").setThumbnail(getActivity()
					.getResources().getDrawable(icon)));
		}

		listView.setAdapter(adapter);
		listView.setOnCardClickListener(carcdclick);

		Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom);
		rootView.startAnimation(anim);
		
		return rootView;
	}

	CardClickListener carcdclick = new CardClickListener() {

		@Override
		public void onCardClick(int index, CardBase card, View view) {
			// TODO Auto-generated method stub
			if (!card.isHeader())
				((MainActivity) getActivity()).getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content_frame, new MediaItemFragment())
						.addToBackStack("testing")
						.commit();
			((MainActivity) getActivity()).getSupportFragmentManager()
			.beginTransaction().hide(MovieListFragment.this);
		}
	};
	
	@Override
	public void onDestroy(){
		Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_bottom);
		rootView.startAnimation(anim);
		super.onDestroy();
		((MainActivity) getActivity())
        .getFadingActionBarHelper()
        .setActionBarAlpha(255);
	}
}
