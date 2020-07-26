package app.com.eLearningApp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import app.com.eLearningApp.fragments.ChannelFragment;
import app.com.eLearningApp.fragments.LiveFragment;
import app.com.eLearningApp.fragments.PlayListFragment;

/**
 * Created by mdmunirhossain on 12/18/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ChannelFragment tab1 = new ChannelFragment();
                return tab1;
            case 1:
                PlayListFragment tab2 = new PlayListFragment("PLdo5W4Nhv31YIeatFDBuAvPlNwaKejIKC");
                return tab2;
            case 2:
                LiveFragment tab3 = new LiveFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
