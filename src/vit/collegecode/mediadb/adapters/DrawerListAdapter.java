package vit.collegecode.mediadb.adapters;


import vit.collegecode.mediadb.CommonLib;
import vit.collegecode.mediadb.R;
import vit.collegecode.mediadb.data.BitmapScale;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerListAdapter extends BaseAdapter {

	Context context;
	Typeface robotol;
	
	public DrawerListAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		robotol = Typeface.createFromAsset(context.getAssets(),
				"fonts/Roboto-Light.ttf");
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rootView = null;
		LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (position == 0) {
			rootView = inflater.inflate(R.layout.drawer_profile_layout, null);
			ImageView profile_pic = (ImageView)rootView.findViewById(R.id.profile_pic);
			int icon = R.drawable.profile_pic;
			
			Bitmap bitmap = BitmapScale.decodeSampledBitmapFromResource(context.getResources(), icon, 48, 48);
			Bitmap thumb = CommonLib.getRoundedCornerBitmap(bitmap,
					bitmap.getHeight() / 2);
			profile_pic.setImageBitmap(thumb);
			
			int cover = R.drawable.profile_cover;
			ImageView cover_pic = (ImageView)rootView.findViewById(R.id.profile_cover);
			Drawable cover_drawable = context.getResources().getDrawable(cover);
			cover_drawable.setAlpha(179);
			cover_pic.setImageDrawable(cover_drawable);
			
			TextView text = (TextView)rootView.findViewById(R.id.profile_name);
			text.setText("Nikhil Loney");
			text.setTypeface(robotol);
			
			return rootView;
			
		} else if (position == 1) {
			
			rootView = inflater.inflate(R.layout.simple_drawer_list_item, null);
			TextView text = (TextView)rootView.findViewById(R.id.drawer_item_text);
			text.setText("Home");
			text.setTypeface(robotol);
			return rootView;
			
		} else if (position == 2) {
			
			rootView = inflater.inflate(R.layout.simple_drawer_list_item, null);
			TextView text = (TextView)rootView.findViewById(R.id.drawer_item_text);
			text.setText("Trending Movies");
			text.setTypeface(robotol);
			return rootView;
		} else if (position == 3) {
			
			rootView = inflater.inflate(R.layout.simple_drawer_list_item, null);
			TextView text = (TextView)rootView.findViewById(R.id.drawer_item_text);
			text.setText("Trending TV Shows");
			text.setTypeface(robotol);
			return rootView;
		} else {
			
			rootView = inflater.inflate(R.layout.simple_drawer_list_item, null);
			TextView text = (TextView)rootView.findViewById(R.id.drawer_item_text);
			text.setText("Default");
			return rootView;
		}
	}

}
