package com.example.skillcinema.presentation.search.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skillcinema.databinding.ItemYearForSettingsBinding
import com.example.skillcinema.data.search.SettingsSharedPrefRepository
import com.example.skillcinema.domain.search.usecase.SettingsUseCase
import javax.inject.Inject

class YearToForSearchAdapter @Inject constructor(
    private val settingsUseCase: SettingsUseCase
) : RecyclerView.Adapter<YearToForSearchAdapter.YearForSearchViewHolder>() {
    private var years: List<Int> = emptyList()
    private var selected = -1

    fun setYears(years: List<Int>) {
        this.years = years
        notifyDataSetChanged()
    }

    class YearForSearchViewHolder(
        val binding: ItemYearForSettingsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearForSearchViewHolder {
        return YearForSearchViewHolder(
            ItemYearForSettingsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: YearForSearchViewHolder, position: Int) {
        val year = years.get(position)
        holder.binding.year.text = year.toString()
        holder.binding.year.isSelected = (position == selected)
        holder.binding.root.setOnClickListener {
            onYearClick(position)
            settingsUseCase.saveYearTo(year)
        }
    }

    override fun getItemCount(): Int = 12

    private fun onYearClick(position: Int) {
        if (selected != -1) {
            notifyItemChanged(selected)
        }
        selected = position
        notifyItemChanged(selected)
    }
}