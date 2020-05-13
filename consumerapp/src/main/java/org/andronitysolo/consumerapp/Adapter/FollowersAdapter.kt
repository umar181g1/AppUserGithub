package org.andronitysolo.consumerapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.cardviewuser.view.*
import org.andronitysolo.consumerapp.R
import org.andronitysolo.consumerapp.Response.DataItem

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder>() {

    private lateinit var mData: ArrayList<DataItem>


    fun setData(items: ArrayList<DataItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowersAdapter.FollowerViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.cardviewuser, parent, false)
        return FollowerViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: FollowersAdapter.FollowerViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class FollowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem) {
            Log.d("umar", "data title adapter : ${item.login}")
            with(itemView) {
                Glide.with(itemView.context)
                    .load(item.avatarUrl)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_photo)

                txt_name.text = item.followersUrl.toString()

            }
        }
    }
}