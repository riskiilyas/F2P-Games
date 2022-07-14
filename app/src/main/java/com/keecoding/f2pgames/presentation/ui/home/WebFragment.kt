package com.keecoding.f2pgames.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.databinding.FragmentWebBinding
import com.keecoding.f2pgames.presentation.adapter.GamesAdapter
import com.keecoding.f2pgames.presentation.ui.MainActivity
import com.keecoding.f2pgames.presentation.vm.MainViewModel

class WebFragment : Fragment() {
    private lateinit var binding: FragmentWebBinding
    private lateinit var viewModel: MainViewModel
    private val gamesAdapter = GamesAdapter{}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).getViewModel()
        viewModel.gamesLiveData.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                val webGames = it.data?.filter { game ->
                    game.platform == "Web Browser"
                }
                gamesAdapter.differ.submitList(webGames) {
                    gamesAdapter.notifyDataSetChanged()
                }

            }
            binding.swipeRefresh.isRefreshing = false
        }

        binding.recyclerView.apply {
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllGames()
        }
    }

}