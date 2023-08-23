package com.example.skillcinema.presentation.search.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentSettingsSearchBinding
import com.example.skillcinema.data.search.SettingsSharedPrefRepository
import com.example.skillcinema.domain.search.usecase.SettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsSearchFragment : Fragment() {

    private var _binding: FragmentSettingsSearchBinding? = null
    private val binding get() = _binding!!
    private var sharedPrefRepository = SettingsSharedPrefRepository()
    private val settingsUseCase = SettingsUseCase(sharedPrefRepository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefRepository.initSettingsSharedPrefRepository(requireContext())
        initStateViews()

        binding.rangeSlider.values[0] =
            settingsUseCase.getRatingFrom()?.toFloat()//
        binding.rangeSlider.values[1] = settingsUseCase.getRatingTo()?.toFloat()//
        saveRating()
        changeCountry()
        changeGenres()
        changeYear()
        binding.applySettings.setOnClickListener {
            findNavController().navigate(R.id.action_settingsSearchFragment_to_navigation_search)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initStateViews() {
        if (settingsUseCase.getType() == TYPE_FILM) {
            binding.films.isSelected = true
        } else if (settingsUseCase.getType() == TYPE_ALL) {
            binding.searchAll.isSelected = true
        } else binding.serials.isSelected = true

        if (settingsUseCase.getOrder() == ORDER_RATING) {
            binding.rating.isSelected = true
        } else if (settingsUseCase.getOrder() == ORDER_YEAR) {
            binding.date.isSelected = true
        } else binding.popular.isSelected = true

        binding.serials.setOnClickListener {
            binding.serials.isSelected = true
            binding.searchAll.isSelected = false
            binding.films.isSelected = false
            settingsUseCase.saveType(TYPE_TV_SERIES)
        }
        binding.searchAll.setOnClickListener {
            binding.serials.isSelected = false
            binding.searchAll.isSelected = true
            binding.films.isSelected = false
            settingsUseCase.saveType(TYPE_ALL)
        }
        binding.films.setOnClickListener {
            binding.serials.isSelected = false
            binding.searchAll.isSelected = false
            binding.films.isSelected = true
            settingsUseCase.saveType(TYPE_FILM)
        }
        binding.popular.setOnClickListener {
            binding.popular.isSelected = true
            binding.date.isSelected = false
            binding.rating.isSelected = false
            settingsUseCase.saveOrder(ORDER_NUM_VOTE)
        }
        binding.date.setOnClickListener {
            binding.popular.isSelected = false
            binding.date.isSelected = true
            binding.rating.isSelected = false
            settingsUseCase.saveOrder(ORDER_YEAR)
        }
        binding.rating.setOnClickListener {
            binding.popular.isSelected = false
            binding.date.isSelected = false
            binding.rating.isSelected = true
            settingsUseCase.saveOrder(ORDER_RATING)
        }
        binding.country.text = settingsUseCase.getCountryName()
        binding.genres.text = settingsUseCase.getGenresName()
        binding.yearFrom.text = settingsUseCase.getYearFrom().toString()
        binding.yearTo.text = settingsUseCase.getYearTo().toString()
    }

    private fun saveRating() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.rangeSlider.addOnChangeListener { slider, value, fromUser ->
                    settingsUseCase.saveRatingFrom(slider.values[0].toInt())
                    settingsUseCase.saveRatingTo(slider.values[1].toInt())
                    binding.ratingFrom.text = settingsUseCase.getRatingFrom().toString()
                    binding.ratingTo.text = settingsUseCase.getRatingTo().toString()
                }
            }
        }
    }

    private fun changeCountry() {
        binding.linearCountry.setOnClickListener {
            val bundle = Bundle().apply {
                putString(BUNDLE_KEY_COUNTRY_AND_GENRES, binding.labelCountry.text.toString())
            }
            findNavController().navigate(
                R.id.action_settingsSearchFragment_to_settingsSearchCountryOrGenresFragment,
                bundle
            )
        }
    }

    private fun changeGenres() {
        binding.linearGenres.setOnClickListener {
            val bundle = Bundle().apply {
                putString(BUNDLE_KEY_COUNTRY_AND_GENRES, binding.labelGenres.text.toString())
            }
            findNavController().navigate(
                R.id.action_settingsSearchFragment_to_settingsSearchCountryOrGenresFragment,
                bundle
            )
        }
    }

    private fun changeYear() {
        binding.linearYear.setOnClickListener {
            findNavController().navigate(R.id.action_settingsSearchFragment_to_settingsYearFragment)
        }
    }

    companion object {
        const val TYPE_ALL = "ALL"
        const val TYPE_FILM = "FILM"
        const val TYPE_TV_SERIES = "TV_SERIES"
        const val ORDER_RATING = "RATING"
        const val ORDER_NUM_VOTE = "NUM_VOTE"
        const val ORDER_YEAR = "YEAR"
        const val BUNDLE_KEY_COUNTRY_AND_GENRES = "label"
    }
}