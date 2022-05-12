package com.hpandro.album.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.hpandro.album.R
import com.hpandro.album.databinding.PhotosAdapterBinding
import com.hpandro.album.model.photos.PhotosItem

/**
 * This Adapter class is used to show list of photos with details
 */
class PhotosAdapter(
    var context: Context,
    private var photoList: ArrayList<PhotosItem?>?
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private lateinit var binding: PhotosAdapterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_photo, parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            if (photoList?.get(position) != null) {
                val photo = photoList!![position]

//                val url = GlideUrl(
//                    photo?.url, LazyHeaders.Builder()
//                        .addHeader("User-Agent", Constants.USER_AGENT)
//                        .build()
//                )
//                Glide.with(context)
//                    .load(url)
////                    .diskCacheStrategy(DiskCacheStrategy.DATA)
////                    .skipMemoryCache(false)
//                    .placeholder(R.drawable.thumb)
//                    .into(photoImgView)

                photoImgView.load( photo?.url) {
                    crossfade(true)
                    placeholder(R.drawable.thumb)
                    error(R.drawable.error_thumb)
                    memoryCachePolicy(CachePolicy.ENABLED)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return photoList?.size!!
    }

    class ViewHolder(val binding: PhotosAdapterBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updatedList: ArrayList<PhotosItem?>) {
        photoList = updatedList
        notifyDataSetChanged()
    }
}