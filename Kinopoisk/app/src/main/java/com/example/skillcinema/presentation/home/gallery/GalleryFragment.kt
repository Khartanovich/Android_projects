package com.example.skillcinema.presentation.home.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.LoadState
import com.example.skillcinema.data.home.InfoAboutFilmScreenRepositoryIml
import com.example.skillcinema.data.App
import com.example.skillcinema.databinding.FragmentGalleryBinding
import com.example.skillcinema.domain.home.usecaseinfofilm.GetImagesFilmUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val imagesAdapter = ImagesGallerySeparatePagingAdapter()
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idFilm = arguments?.getInt(KEY_ID_FILM)
        binding.recyclerFragmentGallery.adapter = imagesAdapter
        createCipGroup(idFilm)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                imagesAdapter.loadStateFlow.collect {
                    if (it.source.refresh is LoadState.Error) {
                        Toast.makeText(
                            requireContext(),
                            (it.source.refresh as LoadState.Error).error.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createCipGroup(idFilm: Int?) {
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            for (id in checkedIds) {
                when (id) {
                    binding.still.id -> {
                        idFilm?.let {
                            viewModel.getImagesGallery(it, TYPE_STILL).onEach { imageItem ->
                                imagesAdapter.submitData(imageItem)
                            }.launchIn(viewLifecycleOwner.lifecycleScope)
                        }
                    }
                    binding.shooting.id -> {
                        idFilm?.let {
                            viewModel.getImagesGallery(it, TYPE_SHOOTING).onEach { imageItem ->
                                imagesAdapter.submitData(imageItem)
                            }.launchIn(viewLifecycleOwner.lifecycleScope)
                        }
                    }
                    binding.poster.id -> {
                        idFilm?.let {
                            viewModel.getImagesGallery(it, TYPE_POSTER).onEach { imageItem ->
                                imagesAdapter.submitData(imageItem)
                            }.launchIn(viewLifecycleOwner.lifecycleScope)
                        }
                    }
                    binding.cover.id -> {
                        idFilm?.let {
                            viewModel.getImagesGallery(it, TYPE_COVER).onEach { imageItem ->
                                imagesAdapter.submitData(imageItem)
                                if (imageItem == null) {
                                    Toast.makeText(
                                        requireContext(),
                                        imageItem.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }.launchIn(viewLifecycleOwner.lifecycleScope)
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val KEY_ID_FILM = "idFilm"
        private const val TYPE_STILL = "STILL"
        private const val TYPE_SHOOTING = "SHOOTING"
        private const val TYPE_POSTER = "POSTER"
        private const val TYPE_COVER = "COVER"
    }
}