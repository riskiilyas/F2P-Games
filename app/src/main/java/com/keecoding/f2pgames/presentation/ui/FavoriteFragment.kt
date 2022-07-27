package com.keecoding.f2pgames.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.databinding.FragmentFavoriteBinding
import com.keecoding.f2pgames.presentation.adapter.GamesAdapter
import com.keecoding.f2pgames.presentation.vm.MainViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: MainViewModel
    private val gamesAdapter = GamesAdapter { id, iv ->

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).getViewModel()
        viewModel.gamesLiveData.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                gamesAdapter.differ.submitList(
                    it.data?.filter { game -> game.isFav }
                ) {
                    gamesAdapter.notifyDataSetChanged()
                }
            }

            binding.lottieEmpty.visibility =
                if (gamesAdapter.differ.currentList.isNullOrEmpty()) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setOnRefreshListener { viewModel.getAllGames() }

        binding.recyclerView.apply {
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}