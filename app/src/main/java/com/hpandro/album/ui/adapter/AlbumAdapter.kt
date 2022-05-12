package com.hpandro.album.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import com.hpandro.album.R
import com.hpandro.album.databinding.AlbumAdapterBinding
import com.hpandro.album.model.album.AlbumItem
import com.hpandro.album.model.photos.PhotosItem
import com.hpandro.album.ui.activity.MainActivity

/**
 * This Adapter class is used to show list of Albums with details
 */
class AlbumAdapter(
    var context: Context,
    private var albumList: ArrayList<AlbumItem?>?,
    private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private lateinit var binding: AlbumAdapterBinding
    lateinit var photosAdapter: PhotosAdapter
    private var photosList: ArrayList<PhotosItem?>?= ArrayList()

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_album, parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            if(albumList != null){
                if (albumList?.get(position) != null) {
                    val album = albumList!![position]
                    albumTitleTxtView.text = album?.id.toString().plus(
                        ".  ${
                            album?.title?.substring(0, 1)?.uppercase() + album?.title?.substring(1)
                                ?.lowercase()
                        }"
                    )

                    photosList = (context as MainActivity).getPhotosByID(album?.id!!) as ArrayList<PhotosItem?>?
                    if (photosList != null && photosList?.size!! > 0) {
                        photosAdapter = PhotosAdapter(context, ArrayList())
                        photosRecyclerView.setHasFixedSize(true)
                        photosRecyclerView.layoutManager =
                            LoopingLayoutManager(context, LoopingLayoutManager.HORIZONTAL, false)
                        photosRecyclerView.adapter = photosAdapter
                        photosAdapter.updateList(photosList!!)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return albumList?.size!!
    }

    class ViewHolder(val binding: AlbumAdapterBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updatedList: ArrayList<AlbumItem?>) {
        albumList = updatedList
        notifyDataSetChanged()
    }
}