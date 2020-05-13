package org.andronitysolo.consumerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.andronitysolo.consumerapp.Adapter.FavGithubAdater
import org.andronitysolo.consumerapp.Response.DataItem


class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: FavGithubAdater
    private var dataItem: DataItem? = null
    var rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = FavGithubAdater()
        rv = root.findViewById(R.id.crcFv)
        getData()
    }

}
