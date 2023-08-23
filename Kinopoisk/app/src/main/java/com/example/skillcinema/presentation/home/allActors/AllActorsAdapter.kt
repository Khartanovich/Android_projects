package com.example.skillcinema.presentation.home.allActors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemActorsForRecyclerViewBinding
import com.example.skillcinema.entity.home.AllActorsEntity

class AllActorsAdapter(
    private val onClickStaffItem: (AllActorsEntity) -> Unit
) : RecyclerView.Adapter<AllActorsViewHolder>() {

    private var allActors: List<AllActorsEntity> = emptyList()

    fun setActors(data: List<AllActorsEntity>) {
        this.allActors = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllActorsViewHolder {
        return AllActorsViewHolder(
            ItemActorsForRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllActorsViewHolder, position: Int) {
        val actor = allActors.getOrNull(position)
        with(holder.binding) {
            nameActorRu.text = actor?.nameRu
            nameActorEn.text = actor?.nameEn
            descriptionActor.text = actor?.description
            Glide.with(posterUrlActor.context).load(actor?.posterUrl).into(posterUrlActor)
        }
        holder.binding.root.setOnClickListener{
            let{
                actor?.let {
                    onClickStaffItem(it)
                }
            }
        }
    }
    override fun getItemCount(): Int = allActors.size
}


class AllActorsViewHolder(val binding: ItemActorsForRecyclerViewBinding) :
    RecyclerView.ViewHolder(binding.root)