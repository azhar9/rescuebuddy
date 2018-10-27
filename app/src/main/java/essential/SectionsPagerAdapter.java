package essential;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astudios.rescuebuddy.GoBagActivity;

import fragments.additional_fragment;
import fragments.recommend_fragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 1:
                additional_fragment fragment1 = new additional_fragment();
                return fragment1;
            case 0:
                recommend_fragment fragment2 = new recommend_fragment();
                return fragment2;

        }
        return null;
    }


    @Override
    public int getCount() {
        return GoBagActivity.MAX_FRAGS;
    }
}
