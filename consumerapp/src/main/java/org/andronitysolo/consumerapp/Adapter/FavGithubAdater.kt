package org.andronitysolo.consumerapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.cardviewuser.view.*
import org.andronitysolo.consumerapp.Activity.DetailActivity
import org.andronitysolo.consumerapp.R
import org.andronitysolo.consumerapp.Response.DataItem

class FavGithubAdater :  RecyclerView.Adapter<FavGithubAdater.FMAdapter>() {
    private var data: List<DataItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FMAdapter {
        return FMAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cardviewuser, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FMAdapter, position: Int) = holder.bind(data[position])

    fun swapData(data: List<DataItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    class FMAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem) = with(itemView) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(item.avatarUrl)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_photo)

                txt_name.text = item.nodeId
                txt_deskripsi.text = item.login
                setOnClickListener {
                    val data = Gson().toJson(item)
                    val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
                    context.startActivity(moveWithObjectIntent)
                }
            }
        }
    }
}