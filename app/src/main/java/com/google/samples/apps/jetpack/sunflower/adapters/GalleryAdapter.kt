/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.jetpack.sunflower.adapters

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.samples.apps.jetpack.sunflower.GalleryFragment
import com.google.samples.apps.jetpack.sunflower.data.UnsplashPhoto
import com.google.samples.apps.jetpack.sunflower.ui.layout.photo.ItemPhoto

/**
 * Adapter for the [RecyclerView] in [GalleryFragment].
 */

class GalleryAdapter :
    PagingDataAdapter<UnsplashPhoto, RecyclerView.ViewHolder>(GalleryDiffCallback()) {
    var itemClicked: ((UnsplashPhoto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ComposeView(parent.context)
        val holder = object : RecyclerView.ViewHolder(view) {}
        if (itemClicked == null)
            itemClicked = {
                val uri = Uri.parse(it.user.attributionUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                parent.context.startActivity(intent)
            }
        if (holder.bindingAdapterPosition >= 0)
            getItem(holder.bindingAdapterPosition)?.let {
                view.setContent { ItemPhoto(it, itemClicked ?: {}) }
            }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder.itemView as? ComposeView)?.setContent {
                ItemPhoto(it, itemClicked ?: {})
            }
        }
    }
}

private class GalleryDiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem == newItem
    }
}
