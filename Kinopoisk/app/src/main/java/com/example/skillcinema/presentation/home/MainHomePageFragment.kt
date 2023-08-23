package com.example.skillcinema.presentation.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentMainHomePageBinding
import com.example.skillcinema.entity.home.MovieEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainHomePageFragment : Fragment(), OnItemClick {

    private var _binding: FragmentMainHomePageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainHomePageViewModel by viewModels()
    private val homeMovieAdapter = MainHomeMovieAdapter(this, true, SELECTION_NAME_PREMIERS)
    private val parenAdapter = SelectionsFilmsAdapterForParentRV(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPremiers.adapter = homeMovieAdapter
        binding.parentRV.adapter = parenAdapter
        stateProgressBar()

        viewModel.moviesPremier.onEach {
            val listFirstMovie = it.take(20)
            homeMovieAdapter.setData(listFirstMovie)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.combineFlow.onEach {
            parenAdapter.getNameAndListMovie(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            false // default to enabled
        ) {
            override fun handleOnBackPressed() {}
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun stateProgressBar() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    when (it) {
                        false -> {
                            binding.progressBar.isVisible = false
                            binding.premiers.isVisible = true
                            binding.parentRV.isVisible = true
                        }
                        true -> {
                            binding.progressBar.isVisible = true
                            binding.premiers.isVisible = false
                            binding.parentRV.isVisible = false
                        }
                    }
                }
            }
        }
    }

    override fun onClick(movie: MovieEntity) {
        val bundle = Bundle().apply {
            if (movie.kinopoiskId != 0) {
                putInt(KEY_ID_FILM, movie.kinopoiskId)
            } else {
                putInt(KEY_ID_FILM, movie.filmId)
            }
        }
        findNavController().navigate(
            R.id.action_mainHomePageFragment_to_informationAboutFilmFragment,
            bundle
        )
    }

    override fun onClickShowAll(nameSelection: String) {
        val bundle = Bundle().apply {
            putString(KEY_SELECTION_NAME, nameSelection)
        }
        findNavController().navigate(
            R.id.action_mainHomePageFragment_to_allMoviesInSelectionFragment,
            bundle
        )
    }

    companion object {
        const val SELECTION_NAME_PREMIERS = "Премьеры"
        const val KEY_ID_FILM = "idFilm"
        const val KEY_SELECTION_NAME = "nameSelection"
    }
}