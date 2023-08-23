package com.example.skillcinema.presentation.search.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skillcinema.data.search.CountrySearchDto
import com.example.skillcinema.databinding.ItemCountryOrGenresForSearchBinding
import com.example.skillcinema.entity.search.OverallCountryAndGenres
import com.example.skillcinema.domain.search.usecase.SettingsUseCase
import javax.inject.Inject

class CountryAndGenresForSearchAdapter @Inject constructor(private val settingsUseCase : SettingsUseCase,
private val onCountryOrGenresClick: (OverallCountryAndGenres) -> Unit) :
    RecyclerView.Adapter<CountryAndGenresForSearchAdapter.CountryAndGenresForSearchViewHolder>() {

    private var data: List<OverallCountryAndGenres> = emptyList()
    fun setData(data: List<OverallCountryAndGenres>) {
        this.data = data
        notifyDataSetChanged()
    }

    class CountryAndGenresForSearchViewHolder(val binding: ItemCountryOrGenresForSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryAndGenresForSearchViewHolder {
        return CountryAndGenresForSearchViewHolder(
            ItemCountryOrGenresForSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryAndGenresForSearchViewHolder, position: Int) {
        val item = data.getOrNull(position)
        holder.binding.countryOrGenres.text = item?.name.toString()
        holder.binding.root.setOnClickListener {
            if (item is CountrySearchDto) {
                item?.let {
                    settingsUseCase.saveCountryName(item.name)
                    settingsUseCase.saveCountryId(item.id)
                }
            } else {
                item?.let {
                    settingsUseCase.saveGenresName(item.name)
                    settingsUseCase.saveGenresId(item.id)
                }
            }
            item?.let { it1 -> onCountryOrGenresClick(it1) }
        }
    }

    override fun getItemCount(): Int = data.size
}