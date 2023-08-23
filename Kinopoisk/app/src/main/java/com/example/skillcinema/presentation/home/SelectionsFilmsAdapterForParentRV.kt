package com.example.skillcinema.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skillcinema.databinding.ItemParentRvMainScreenBinding
import com.example.skillcinema.entity.home.NameAndListMovieEntity

class SelectionsFilmsAdapterForParentRV(
    private val onItemClick: OnItemClick,
) :
    RecyclerView.Adapter<SelectionsFilmsAdapterForParentRV.SelectionsFilmsParentRVViewHolder>() {
    private var nameAndListMovieList: List<NameAndListMovieEntity> = emptyList()
    fun getNameAndListMovie(data: List<NameAndListMovieEntity>) {
        this.nameAndListMovieList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectionsFilmsParentRVViewHolder {
        return SelectionsFilmsParentRVViewHolder(
            ItemParentRvMainScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SelectionsFilmsParentRVViewHolder, position: Int) {
        val nameAndListMovie = nameAndListMovieList.getOrNull(position)
        if (nameAndListMovie != null) {
            val childRVAdapter = MainHomeMovieAdapter(onItemClick, true, nameAndListMovie.name)
            with(holder) {
                binding.childRecyclerView.adapter = childRVAdapter
                binding.label.text = nameAndListMovie?.name
                nameAndListMovie?.listMovie?.let {
                    childRVAdapter.setData(it)
                }
            }
        }

    }

    override fun getItemCount(): Int = nameAndListMovieList.size

    class SelectionsFilmsParentRVViewHolder(
        val binding: ItemParentRvMainScreenBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}



