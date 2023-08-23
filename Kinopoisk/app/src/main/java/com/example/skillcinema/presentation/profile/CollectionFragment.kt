package com.example.skillcinema.presentation.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentCollectionBinding
import com.example.skillcinema.data.App
import com.example.skillcinema.data.profile.ProfileMainScreenRepositoryImpl
import com.example.skillcinema.domain.profile.usecase.GetAllCollectionsDataBaseUseCase
import com.example.skillcinema.entity.profile.FilmEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private val collectionAdapter = CollectionAdapter{ filmEntity -> onFilmClick(filmEntity) }
    private val viewModel: CollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFilmsInCollection.adapter = collectionAdapter
        val nameCollection = arguments?.getString(KEY_SELECTION_NAME)
        if (nameCollection != null){
            binding.nameCollection.text = nameCollection
            insertFilms(nameCollection)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertFilms(nameCollection: String){
        viewModel.filmsInCollection.onEach {
            for (collection in it){
                if (collection.collection.collectionName == nameCollection){
                    collection.films?.let { films -> collectionAdapter.getFilms(films) }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onFilmClick(filmEntity: FilmEntity){
            val bundle = bundleOf(KEY_ID_FILM to filmEntity.kinopoiskId)
        findNavController().navigate(R.id.action_collectionFragment_to_informationAboutFilmFragment, bundle)
    }

    companion object {
        const val KEY_ID_FILM = "idFilm"
        const val KEY_SELECTION_NAME = "nameSelection"
    }
}