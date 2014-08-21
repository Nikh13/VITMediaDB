package vit.collegecode.mediadb.adapters;

import vit.collegecode.mediadb.fragments.PagerFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.IconPagerAdapter;


public class PagerFragmentAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter{

    public PagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }
    
      @Override
    public int getIconResId(int index) {
        // TODO Auto-generated method stub
        return 0;
    }

      @Override
    public Fragment getItem(int position) 
    {
        // TODO Auto-generated method stub
        Fragment fragment = new PagerFragment(position);
        
        return fragment;
    }

      @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }
    
      @Override
    public CharSequence getPageTitle(int position){
        return null;
    }

}
