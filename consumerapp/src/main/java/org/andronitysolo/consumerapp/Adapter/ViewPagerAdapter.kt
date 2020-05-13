package org.andronitysolo.consumerapp.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.andronitysolo.consumerapp.Fragment.FollowersFragment
import org.andronitysolo.consumerapp.Fragment.FollowingFragment

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        FollowingFragment(),
        FollowersFragment()

    )

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }



    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movie "
            else -> "TvShow "
        }
    }
}