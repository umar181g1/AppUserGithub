package org.andronitysolo.consumerapp.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
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


class GithubUserAdapter( val activity: Activity?) : RecyclerView.Adapter<GithubUserAdapter.VhUser>()  {
   private var data: List<DataItem> = java.util.ArrayList()

//    var listMovie = ArrayList<ResponseUser>()
//        set(listMovie) {
//            if (listMovie.size > 0) {
//             this.listMovie.clear()
//           }
//            this.listMovie.addAll(listMovie)
//            notifyDataSetChanged()
//       }

    fun swapData(data: List<DataItem>) {
        Log.d("umar", "data adapter : ${data.size}" )
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):VhUser{
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardviewuser, parent, false)
        return VhUser(view)
    }

    override fun getItemCount(): Int = data.size


    inner class VhUser(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(item.avatarUrl)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_photo)

                txt_name.text = item.login
                txt_deskripsi.text = item.url
               card_view.setOnClickListener {
                    val data = Gson().toJson(item)
                    val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
                    moveWithObjectIntent.putExtra("data", data)
                    activity?.startActivity(moveWithObjectIntent)
                }
            }
        }
    }
    override fun onBindViewHolder(holder: VhUser, position: Int) {
        holder.bind(data[position])
    }
}






