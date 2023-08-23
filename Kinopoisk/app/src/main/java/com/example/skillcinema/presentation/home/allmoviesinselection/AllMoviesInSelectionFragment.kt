package com.example.skillcinema.presentation.home.allmoviesinselection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentAllMoviesInSelectionBinding
import com.example.skillcinema.entity.home.MovieEntity
import com.example.skillcinema.presentation.home.MainHomeMovieAdapter
import com.example.skillcinema.presentation.home.OnItemClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AllMoviesInSelectionFragment : Fragment(), OnItemClick {
    private var _binding: FragmentAllMoviesInSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllMovieInSelectionViewModel by viewModels()
    private val allMovieAdapter = MainHomeMovieAdapter(this, false, null)
    private val allMoviePagingAdapter =
        SelectionsFilmsPagingAdapter() { movieEntity -> onPagingItemClick(movieEntity) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllMoviesInSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameSelection = arguments?.getString(KEY_NAME_SELECTION)
        if (nameSelection != null) {
            setMovies(nameSelection)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMovies(nameSelection: String) {
        binding.titleAllPremiers.text = nameSelection
        when (nameSelection) {
            PREMIERS -> {
                viewModel.loadPremieres()
                binding.recyclerAllPremiers.adapter = allMovieAdapter
                viewModel.moviesPremier.onEach {
                    allMovieAdapter.setData(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            TOP -> {
                binding.recyclerAllPremiers.adapter = allMoviePagingAdapter
                viewModel.loadTopFilms.onEach {
                    allMoviePagingAdapter.submitData(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
            POPULAR -> {
                binding.recyclerAllPremiers.adapter = allMoviePagingAdapter
                viewModel.loadPopularFilms.onEach {
                    allMoviePagingAdapter.submitData(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun onClick(movie: MovieEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_FILM, movie.kinopoiskId)
        }
        findNavController().navigate(
            R.id.action_allMoviesInSelectionFragment_to_informationAboutFilmFragment,
            bundle
        )
    }

    override fun onClickShowAll(nameSelection: String) {
    }

    private fun onPagingItemClick(movie: MovieEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_FILM, movie.filmId)
        }
        findNavController().navigate(
            R.id.action_allMoviesInSelectionFragment_to_informationAboutFilmFragment,
            bundle
        )
    }

    companion object {
        const val KEY_NAME_SELECTION = "nameSelection"
        const val PREMIERS = "Премьеры"
        const val TOP = "Топ"
        const val POPULAR = "Популярные"
        private const val KEY_ID_FILM = "idFilm"
    }
}