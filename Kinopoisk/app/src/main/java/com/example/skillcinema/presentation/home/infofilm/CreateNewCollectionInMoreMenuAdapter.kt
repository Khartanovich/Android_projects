package com.example.skillcinema.ui.home.informationAboutFilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skillcinema.databinding.ItemForRecyclerMoreMenuBinding
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.profile.CollectionsAndFilms

class CreateNewCollectionInMoreMenuAdapter(
    private val onCheckBoxClick: OnCheckBoxClick,
    private val filmInfo: InformationAboutFilmEntity
) :
    RecyclerView.Adapter<CreateNewCollectionInMoreMenuAdapter.CreateNewCollectionInMoreMenuViewHolder>() {

    private var data: List<CollectionsAndFilms> = emptyList()
    fun setData(collection: List<CollectionsAndFilms>) {
        this.data = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateNewCollectionInMoreMenuViewHolder {
        return CreateNewCollectionInMoreMenuViewHolder(
            ItemForRecyclerMoreMenuBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CreateNewCollectionInMoreMenuViewHolder, position: Int) {
        val collection = data.getOrNull(position)
        with(holder.binding) {
            labelCollection.text = collection?.collection?.collectionName
            countFilmInCollection.text = collection?.films?.size.toString()
            collection?.let {
                checkCollection.isChecked = onCheckBoxClick.checkDataBase(collection, filmInfo)
            }

            checkCollection.setOnClickListener {
                if (collection != null) {
                    if (checkCollection.isChecked) {
                        onCheckBoxClick.addFilm(collection, filmInfo)
                    } else {
                        onCheckBoxClick.deleteFilm(collection, filmInfo)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class CreateNewCollectionInMoreMenuViewHolder(val binding: ItemForRecyclerMoreMenuBinding) :
        RecyclerView.ViewHolder(binding.root)
}

interface OnCheckBoxClick {
    fun addFilm(collection: CollectionsAndFilms, filmInfo: InformationAboutFilmEntity)
    fun deleteFilm(collection: CollectionsAndFilms, filmInfo: InformationAboutFilmEntity)
    fun checkDataBase(collection: CollectionsAndFilms, filmInfo: InformationAboutFilmEntity): Boolean
}


