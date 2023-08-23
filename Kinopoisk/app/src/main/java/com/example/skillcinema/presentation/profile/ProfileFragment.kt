package com.example.skillcinema.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentProfileBinding
import com.example.skillcinema.data.App
import com.example.skillcinema.data.profile.ProfileMainScreenRepositoryImpl
import com.example.skillcinema.domain.profile.usecase.*
import com.example.skillcinema.entity.profile.CollectionsAndFilms
import com.example.skillcinema.entity.profile.FilmEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment : Fragment(), OnItemClick {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewedAdapter = RecyclerViewedAdapter { filmEntity -> onFilmClick(filmEntity) }
    private val personCollectionAdapter = PersonCollectionAdapter(this)
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewed.adapter = viewedAdapter
        binding.recyclerPersonCollection.adapter = personCollectionAdapter
        getFilmsIsViewed()
        getAllPersonCollection()
        binding.linearAddCollection.setOnClickListener {
            showDialogCreateNewCollection()
        }
        setupDialogListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFilmsIsViewed() {
        viewModel.getFilmsIsViewed.onEach {
            if (it.isNotEmpty())
                viewedAdapter.setFilms(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onFilmClick(filmEntity: FilmEntity) {
        val bundle = Bundle().apply {
            putInt(KEY_ID_FILM, filmEntity.kinopoiskId)
        }
        findNavController().navigate(
            R.id.action_navigation_profile_to_informationAboutFilmFragment,
            bundle
        )
    }

    private fun showDialogCreateNewCollection() {
        val dialogFragment = DialogFragmentCreateNewCollection()
        dialogFragment.show(parentFragmentManager, DialogFragmentCreateNewCollection.TAG)
    }

    private fun setupDialogListener() {
        parentFragmentManager.setFragmentResultListener(
            DialogFragmentCreateNewCollection.REQUEST_KEY,
            viewLifecycleOwner,
            FragmentResultListener { requestKey, result ->
                val string = result.getString(DialogFragmentCreateNewCollection.KEY_RESPONSE)
                if (string != null) {
                    viewModel.createNewCollection(string)
                }
            })
    }

    override fun onCollectionClick(collection: CollectionsAndFilms) {
        val bundle = bundleOf(KEY_SELECTION_NAME to collection.collection.collectionName)
        findNavController().navigate(R.id.action_navigation_profile_to_collectionFragment, bundle)
    }

    override fun onCloseClick(collection: CollectionsAndFilms) {
        viewModel.deleteCollection(collection.collection.collectionName)
    }

    private fun getAllPersonCollection() {
        viewModel.getCollectionsAndFilms.onEach {
            personCollectionAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        const val KEY_ID_FILM = "idFilm"
        const val KEY_SELECTION_NAME = "nameSelection"
    }
}