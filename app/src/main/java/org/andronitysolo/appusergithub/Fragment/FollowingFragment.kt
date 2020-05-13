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
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.fragment_following.*
import org.andronitysolo.appusergithub.Adapter.FollowingAdapter
import org.andronitysolo.appusergithub.Networking.ApiCall
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.Response.ResponseUser


@Suppress("UNREACHABLE_CODE")
class FollowingFragment : Fragment() {
    companion object {
        const val EXTRA_FOLLOWERS = "followers"
    }

    private lateinit var tAdapter: FollowingAdapter
    private var mData: MutableList<DataItem> =     ArrayList()
    private var tData : MutableList<ResponseUser> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
        tAdapter = FollowingAdapter()
        showRecyclerView()
        setFollowers()

    }

    private fun showRecyclerView() {
        rvFollowing.layoutManager = LinearLayoutManager(activity)
        rvFollowing.adapter = tAdapter
    }


    @SuppressLint("CheckResult")
    private fun setFollowers() {
        ApiCall().instance().findUserFollowingByUsername("")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                mData.clear()
                it.body()?.let { it1 -> tData.add(it1) }
                Log.d("wq", "umarfolo:${it.body()}")
                tAdapter.swapData(mData)

                rvFollowing.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = tAdapter
                }
            }
    }
}