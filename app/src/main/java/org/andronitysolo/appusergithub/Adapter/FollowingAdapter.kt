package org.andronitysolo.appusergithub.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.cardviewuser.view.*
import org.andronitysolo.appusergithub.Activity.DetailActivity
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import java.util.*

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.VHFollowing>() {

    private var data: List<DataItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHFollowing {
        return VHFollowing(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_following, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VHFollowing, position: Int) = holder.bind(data[position])

    fun swapData(data: List<DataItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    class VHFollowing(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(item: DataItem) = with(itemView) {
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .apply(RequestOptions().override(350, 550))
                .into(img_photo)

            txt_name.text = item.login            
            setOnClickListener {
                val data = Gson().toJson(item)
                Log.d("qw","umar sesu:${data}")
                val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
                moveWithObjectIntent.putExtra("data", data)
                context.startActivity(moveWithObjectIntent)
            }
        }
    }
}