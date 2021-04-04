package com.example.weatherapp.home.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.base.repo.local.dto.HistoryImages
import com.example.weatherapp.home.view.contract.onImageClickedCallback
import kotlinx.android.synthetic.main.item_image.view.*


class HistoryImagesAdapter(val callback: onImageClickedCallback) :
    RecyclerView.Adapter<HistoryImagesAdapter.HistoryImagesViewHolder>() {
    var data = arrayListOf<HistoryImages>()
    lateinit var context: Context

    inner class HistoryImagesViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var image: ImageView = item.image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryImagesViewHolder {
        context = parent.context
        return HistoryImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size
    fun addData(arrayList: ArrayList<HistoryImages>) {
        data.clear()
        data.addAll(arrayList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HistoryImagesViewHolder, position: Int) {

        var image = data.get(position).thumbail
        val bmp = BitmapFactory.decodeByteArray(image, 0, image!!.size)

        holder.image.setImageBitmap(
            Bitmap.createScaledBitmap(
                bmp,
                90,
                90,
                false
            )
        )
        holder.itemView.setOnClickListener { callback.onImageClicked(data.get(position).data) }

    }
}