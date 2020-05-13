package org.andronitysolo.appusergithub.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.andronitysolo.appusergithub.Adapter.FavGithubAdapter
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.SQLITE.Mapping.GithubjHelper

class DashboardFragment : Fragment() {
    private lateinit var mAdapter: FavGithubAdapter
    private var dataItem: DataItem? = null
    private lateinit var hhalper: GithubjHelper
    var rv: RecyclerView? = null
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        mAdapter = FavGithubAdapter()
        rv = root.findViewById(R.id.crcFv)
        getData()
        return root
    }

    private fun getData() {
        hhalper = context?.let { GithubjHelper.getInstance(it) }!!
        hhalper.open()
        mAdapter = FavGithubAdapter()
        dataItem?.let {
            hhalper.getAllNotes()
        }
        val data = hhalper.getAllNotes()
        Log.d("data tvsho", data.toString())
        mAdapter.swapData(data)
        this.dataItem?.let { mAdapter.swapData(data = listOf(it)) }
        rv?.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
//        dataItem?.let {
//            hhalper.getAllNotes(
//
//        }
        this.dataItem?.let { mAdapter.swapData(data = listOf(it)) }
    }

    override fun onDestroy() {
        super.onDestroy()
        hhalper.close()
    }

}
