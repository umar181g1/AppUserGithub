package org.andronitysolo.appusergithub.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardviewuser.view.*
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import java.util.*

class FavGithubAdapter : RecyclerView.Adapter<FavGithubAdapter.FavGit>() {

    private var data: List<DataItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavGit {
        return FavGit(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_dashboard, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FavGit, position: Int) = holder.bind(data[position])

    fun swapData(data: List<DataItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    class FavGit(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem) = with(itemView) {
            com.bumptech.glide.Glide.with(itemView.context)
                .load(item.avatarUrl)
                .apply(com.bumptech.glide.request.RequestOptions().override(350, 550))
                .into(img_photo)

            txt_name.text = item.login
            txt_deskripsi.text = item.nodeId
            setOnClickListener {
                val data = com.google.gson.Gson().toJson(item)
                val moveWithObjectIntent = android.content.Intent(
                    context,
                    org.andronitysolo.appusergithub.Activity.DetailActivity::class.java
                )
                moveWithObjectIntent.putExtra("data", data)
                context.startActivity(moveWithObjectIntent)
            }
        }
    }
}