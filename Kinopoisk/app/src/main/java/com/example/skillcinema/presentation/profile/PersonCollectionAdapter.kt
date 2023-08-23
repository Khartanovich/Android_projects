package com.example.skillcinema.presentation.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skillcinema.databinding.ItemForCollectionBinding
import com.example.skillcinema.entity.profile.CollectionsAndFilms

const val NAME_COLLECTION_FAVORITE = "Любимые"
const val NAME_COLLECTION_WANT_TO_SEE = "Хочу посмотреть"

class PersonCollectionAdapter(private val onItemClick: OnItemClick) :
    RecyclerView.Adapter<PersonCollectionAdapter.PersonCollectionViewHolder>() {

    private var data: List<CollectionsAndFilms> = emptyList()
    fun setData(collection: List<CollectionsAndFilms>) {
        this.data = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonCollectionViewHolder {
        return PersonCollectionViewHolder(
            ItemForCollectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonCollectionViewHolder, position: Int) {
        val collection = data.getOrNull(position)
        with(holder.binding) {

            textNameCollection.text = collection?.collection?.collectionName
            if (collection?.films?.size != null) {
                sumPersonCollection.text = collection.films?.size.toString()
            }
            imageClose.setOnClickListener {
                if (collection != null) {
                    onItemClick.onCloseClick(collection)
                }
            }
            if ((collection?.collection?.collectionName == NAME_COLLECTION_FAVORITE)
                || (collection?.collection?.collectionName == NAME_COLLECTION_WANT_TO_SEE)
            ) {
                imageClose.visibility = View.GONE
            }
        }
        holder.binding.root.setOnClickListener {
            collection?.let {
                onItemClick.onCollectionClick(it)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class PersonCollectionViewHolder(val binding: ItemForCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)

}

interface OnItemClick {
    fun onCollectionClick(collection: CollectionsAndFilms)
    fun onCloseClick(collection: CollectionsAndFilms)
}