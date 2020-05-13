package org.andronitysolo.appusergithub.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_followers.*
import org.andronitysolo.appusergithub.Adapter.FollowersAdapter
import org.andronitysolo.appusergithub.Networking.ApiCall
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.Response.ResponseUser


class FollowersFragment : Fragment() {

    companion object {
        const val EXTRA_FOLLOWERS = "followers"
    }

    private lateinit var tAdapter: FollowersAdapter
    private var mData: MutableList<DataItem> =     ArrayList()
    private var tData : MutableList<ResponseUser> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tAdapter = FollowersAdapter()
        showRecyclerView()
        setFollowers()

    }

    private fun showRecyclerView() {
        rvFollow.layoutManager = LinearLayoutManager(activity)
        rvFollow.adapter = tAdapter
    }

    @SuppressLint("CheckResult")
    private fun setFollowers() {
        ApiCall().instance().findUserFollowersByUsername("")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.body()?.let { it1 -> tData.add(it1) }
                Log.d("wq", "umar:${it.body()}")
                tAdapter.swapData( mData)

                rvFollow.apply {
                    layoutManager = GridLayoutManager(context, 1)
                    adapter = tAdapter
                }
            }
    }
}


