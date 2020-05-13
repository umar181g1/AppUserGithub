package org.andronitysolo.appusergithub.Activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import org.andronitysolo.appusergithub.Adapter.ViewPagerAdapter
import org.andronitysolo.appusergithub.Fragment.FollowersFragment
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.SQLITE.Mapping.GithubjHelper

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = " extra_movie"
    }

    private var menu: Menu? = null
    private var isfavorite = false
    private lateinit var movieHelper: GithubjHelper
    private var dataItem: DataItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val sectionsPagerAdaper = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdaper
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
        val fragment = FollowersFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        movieHelper = GithubjHelper.getInstance(this)
        movieHelper.open()

        checkFavorite()
        checkData()

    }



    private fun checkFavorite() {
            val moviesInDatabase = movieHelper.getAllNotes()
            for (movie in moviesInDatabase) {
                if (this.dataItem?.login == movie.login) {
                    isfavorite = true
                }
                if (isfavorite) {
                    break
                }
            }
            Toast.makeText(this, isfavorite.toString(), Toast.LENGTH_SHORT).show()
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_favorit, menu)
            setIconFavorite(menu)
            this.menu = menu
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.getItemId() == R.id.menu_item_add_favorite_menu_detail) {
                favoriteButtonPressed()
            }
            return super.onOptionsItemSelected(item)
        }

        private fun favoriteButtonPressed() {
            if (isfavorite) {
                dataItem?.let {
                    val del=  movieHelper.deleteById("${it.id}")
                    Log.d("umar", "del : $del id=${it.id}")
                    menu?.let { mn ->
                        isfavorite = false
                        setIconFavorite(mn)
                    }
                }
                Toast.makeText(this, "menghapus data", Toast.LENGTH_LONG).show()
            } else {
                dataItem?.let {
                    movieHelper.insertData(it)
                    menu?.let { mn ->
                        isfavorite = true
                        setIconFavorite(mn)
                    }
                }
                Toast.makeText(this, "tambahkan data", Toast.LENGTH_LONG).show()
            }
            isfavorite = !!isfavorite

        }

        private fun setIconFavorite(menu: Menu) {
            if (isfavorite) {
                menu.getItem(0).icon = resources.getDrawable(R.drawable.ic_favorite_black_24dp)
            } else {
                menu.getItem(0).icon =
                    resources.getDrawable(R.drawable.ic_favorite_border_black_24dp)
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            movieHelper.close()
        }

    private fun checkData() {
        dataItem = Gson().fromJson(intent.getStringExtra("data"), DataItem::class.java)
        dataItem?.let {
            Glide.with(this)
                .load(it.avatarUrl)
                .apply(RequestOptions().override(350, 550))
                .into(img_photo)

            val txtJudul = txt_name
            txtJudul.setText(it.login)

            val txtDeskripsi = txt_deskripsi
            txtDeskripsi.setText(it.nodeId)
        }
    }
}
