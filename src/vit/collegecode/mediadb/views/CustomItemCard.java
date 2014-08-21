package vit.collegecode.mediadb.views;

import vit.collegecode.mediadb.R;
import android.content.Context;

import com.example.cards_ui.Card;

public class CustomItemCard extends Card {

	private Context context;
	private String title;
	private String type;
	private static String desc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	/**
	 * 
	 */
	private static final long serialVersionUID = -1255735259930293895L;

	public CustomItemCard(Context context, String title) {
		super(title,getDesc());
		this.context = context;
		this.setCustomTitle(title);
	}

	public int getLayout() {
		// Replace with your layout
			return R.layout.description_card;
		
	}

	public String getCustomTitle() {
		return title;
	}

	public void setCustomTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String getDesc() {
		return desc;
	}

	public static void setDesc(String desc) {
		CustomItemCard.desc = desc;
	}
}
