package com.keecoding.f2pgames.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keecoding.f2pgames.R
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.databinding.FragmentAllBinding
import com.keecoding.f2pgames.presentation.adapter.GamesAdapter
import com.keecoding.f2pgames.presentation.ui.MainActivity
import com.keecoding.f2pgames.presentation.vm.MainViewModel
import javax.inject.Inject

class AllFragment : Fragment() {
    private lateinit var binding: FragmentAllBinding
    private val gamesAdapter = GamesAdapter{ id, iv ->
        val extras = FragmentNavigatorExtras(iv to "image_thumbnail_big")
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_allFragment_to_gameDetailFragment,bundle,null,extras)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        gamesAdapter = (activity as MainActivity).getGameAdapter()
        viewModel = (activity as MainActivity).getViewModel()
        viewModel.gamesLiveData.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                gamesAdapter.differ.submitList(it.data) {
                    gamesAdapter.notifyDataSetChanged()
                }
            }
            binding.swipeRefresh.isRefreshing = false
        }

        binding.ivFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_allFragment_to_favoriteFragment)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllGames()
        }

        binding.recyclerView
            .apply {
                adapter = gamesAdapter
                layoutManager = LinearLayoutManager(context)
            }
    }
}