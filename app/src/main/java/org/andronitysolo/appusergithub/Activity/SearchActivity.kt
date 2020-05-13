package org.andronitysolo.appusergithub.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.andronitysolo.appusergithub.Adapter.GithubUserAdapter
import org.andronitysolo.appusergithub.Networking.ApiCall
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.Response.ResponseUser


class SearchActivity : AppCompatActivity() {
    private lateinit var uAdapter: GithubUserAdapter
    private var mData: MutableList<DataItem> =     ArrayList()
    private var tData : MutableList<ResponseUser> = ArrayList()
    private var dt = CompositeDisposable()
    var ok = ""


    companion object {
        const val CARI = "from"
    }

    override fun onDestroy() {
        super.onDestroy()
        dt.clear()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
        back.setOnClickListener { finish() }

        ok = intent.getStringExtra(CARI) ?: ""
        Log.d("wq", "umar:${ok}")

        uAdapter = GithubUserAdapter(Activity())

        btnsearchView.isIconified = false
        showRecycleview()
        searchData(btnsearchView)


    }

    private fun showRecycleview() {
        rvSeacrh.layoutManager = LinearLayoutManager(this)
        rvSeacrh.adapter = uAdapter

    }


    private fun searchData(searchView: SearchView?) {

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                findUserSearchByUsername(query)
                Log.d("wq", "umar:${query}")


                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }

        })
    }

    private fun findUserSearchByUsername(query: String) {
        dt.add(
            ApiCall().instance().findUserSearchByUsername(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        mData.clear()
                        mData=it.body()!!.items!!.toMutableList()
                        it.body()?.let { it1 -> tData.add(it1) }
                        Log.d("wq","umar:${it.body()}")
                        uAdapter.swapData(mData)

                        rvSeacrh.apply {
                            layoutManager = GridLayoutManager(context,1)
                            adapter = uAdapter
                        }



                    } else Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                })

        )
    }
}