package org.andronitysolo.appusergithub.ui.home

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.andronitysolo.appusergithub.Adapter.GithubUserAdapter
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.ResponseUser
import org.andronitysolo.appusergithub.viewmodel.ViewModelUser


class HomeFragment : Fragment() {
    private lateinit var viewModelUser: ViewModelUser
    private lateinit var userAdapter: GithubUserAdapter
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        userAdapter = context?.let { GithubUserAdapter(Activity()) }!!
        root.rv_category.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(root.context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
        val factory = SavedStateViewModelFactory(requireActivity().application, this)
        viewModelUser = ViewModelProviders.of(this, factory)[ViewModelUser::class.java]

        viewModelUser.getDataBundle?.observe(
            viewLifecycleOwner,
            Observer { b ->
                Log.d("umar sebelum masuk", Gson().toJson(b))
                val data: ResponseUser? = b.getParcelable("dataUmar")
                if (data != null) {
                    userAdapter.swapData(data.items)
                } else {
                    viewModelUser.User()
                    val pd = ProgressDialog(activity)
                    pd.setMessage("Loading")
                    viewModelUser.isLoading.observe(viewLifecycleOwner, Observer {
                        if (it) pd.show()
                        else pd.dismiss()
                    })
                }
            }
        )
        viewModelUser.User()

        viewModelUser.data.observe(viewLifecycleOwner, Observer {
            val bundle = Bundle()
            bundle.putParcelable("dataUmar", it)
            Log.d("umar sebelum masukIt", Gson().toJson(it))
            Log.d("umar sebelum masukBun", Gson().toJson(bundle))

            viewModelUser.getDataBundle?.observe(viewLifecycleOwner, Observer {

            })
            viewModelUser.saveBundle(bundle)
            viewModelUser.getDataBundle!!.observe(viewLifecycleOwner, Observer { b ->
                if (b != null) {
                    val data: ResponseUser? = b.getParcelable("dataUmar")
                    userAdapter.swapData(data!!.items)
                } else {

                }
            })
            it.items.let { it1 ->
                userAdapter.swapData(it1)
            }

        })
        userAdapter.notifyDataSetChanged()



        return root
    }


}
