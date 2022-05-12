package com.hpandro.album.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import com.hpandro.album.R
import com.hpandro.album.databinding.ActivityMainBinding
import com.hpandro.album.model.album.AlbumItem
import com.hpandro.album.model.photos.PhotosItem
import com.hpandro.album.ui.adapter.AlbumAdapter
import com.hpandro.album.utils.Constants
import com.hpandro.album.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AlbumAdapter.OnItemClickListener {

    lateinit var albumAdapter: AlbumAdapter
    private var albumList: ArrayList<AlbumItem?>? = null
    private var photosList: ArrayList<PhotosItem?>? = null

    lateinit var binding: ActivityMainBinding
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    /**
     * UI initialization
     */
    fun init() {
        albumList = ArrayList()
        photosList = ArrayList()
        viewModel.init()
        observeData()
        getAlbumListFromServer()
    }

    /**
     * Set data to recyclerview
     */
    private fun setAlbumRecyclerView() {
        albumAdapter = AlbumAdapter(this@MainActivity, albumList, this@MainActivity)
        binding.apply {
            allAlbumsRecyclerView.setHasFixedSize(true)
            allAlbumsRecyclerView.layoutManager =
                LoopingLayoutManager(this@MainActivity, LoopingLayoutManager.VERTICAL, false)
            allAlbumsRecyclerView.adapter = albumAdapter
        }
        binding.progress.visibility = View.GONE
    }

    /**
     * Observe data to update in UI
     */
    private fun observeData() {
        viewModel.photosLiveList!!.observe(this) { updatedPhotoList: List<PhotosItem?>? ->
            if (updatedPhotoList != null && updatedPhotoList.isNotEmpty()) {
                photosList!!.clear()
                photosList!!.addAll(updatedPhotoList)
                binding.allAlbumsRecyclerView.apply {
                    (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
                }
                albumAdapter.notifyDataSetChanged()
            }
        }

        viewModel.albumLiveList!!.observe(this) { updatedAlbumList: List<AlbumItem?>? ->
            if (updatedAlbumList != null && updatedAlbumList.isNotEmpty()) {
                albumList!!.clear()
                albumList!!.addAll(updatedAlbumList)
                setAlbumRecyclerView()
                albumAdapter.updateList(albumList!!)
            }
        }
    }

    /**
     * Get data from server
     */
    private fun getAlbumListFromServer() {
        binding.progress.visibility = View.VISIBLE
        if (!viewModel.photosList.hasObservers()) {
            viewModel.photosList.observe(this) {
//                for (photo in it) {
//                    val url = GlideUrl(
//                        photo.url, LazyHeaders.Builder()
//                            .addHeader("User-Agent", Constants.USER_AGENT)
//                            .build()
//                    )
//                    Glide.with(this@MainActivity)
//                        .downloadOnly()
//                        .load(url)
//                        .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                }
                if (it.isNotEmpty()) {
                    viewModel.getPhotosListByIDAPI(it[0].albumId + 1)
                }
            }
        }
        if (!viewModel.albumList.hasObservers()) {
            viewModel.albumList.observe(this) {
                binding.progress.visibility = View.GONE
                if (it.isNotEmpty()) {
                    viewModel.getPhotosListByIDAPI(it[0].id)  // Get all photos
                }
            }
        }
        viewModel.getAlbumListAPI()  // Get all albums
    }

    override fun onItemClick(view: View?, position: Int) {

    }

    /**
     * Get photos of album by album id
     */
    fun getPhotosByID(id: Int): List<PhotosItem?>? {
        return viewModel.repository.getPhotosListByID(id)
    }
}