package com.example.skillcinema.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentSearchBinding
import com.example.skillcinema.entity.home.MovieEntity
import com.example.skillcinema.data.search.SettingsSharedPrefRepository
import com.example.skillcinema.domain.search.usecase.SettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchPagingAdapter { film -> onClickMovie(film) }
    private val settingsSharedPrefRepository = SettingsSharedPrefRepository()
    private val settingsUseCase = SettingsUseCase(settingsSharedPrefRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsSharedPrefRepository.initSettingsSharedPrefRepository(requireContext())
        binding.recyclerSearch.adapter = searchAdapter
        startSearchFilm(null)
        settingsUseCase.getCountryId()
        binding.searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    startSearchFilm(newText.toString())
                    return true
                }
            }
        )

        binding.buttonShowSettings.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_search_to_settingsSearchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startSearchFilm(keyword: String?) {
        val country = settingsUseCase.getCountryId()
        val genres = settingsUseCase.getGenresId()
        val ratingFrom = settingsUseCase.getRatingFrom()
        val ratingTo = settingsUseCase.getRatingTo()
        val yearFrom = settingsUseCase.getYearFrom()
        val yearTo = settingsUseCase.getYearTo()
        val order = settingsUseCase.getOrder()
        val type = settingsUseCase.getType()
        viewModel.searchFilm(
            keyword,
            country!!,
            genres!!,
            ratingFrom!!,
            ratingTo!!,
            yearFrom!!,
            yearTo!!,
            order!!,
            type!!
        ).onEach {
            searchAdapter.submitData(it)
            binding.notFound.isVisible = searchAdapter.itemCount > 0 //не правильно/непонятно работает
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClickMovie(film: MovieEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_FILM, film.kinopoiskId)
        }
        findNavController().navigate(
            R.id.action_navigation_search_to_informationAboutFilmFragment,
            bundle
        )
    }

    companion object {
        const val KEY_ID_FILM = "idFilm"
    }
}