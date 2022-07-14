package com.keecoding.f2pgames.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.databinding.FragmentPcBinding
import com.keecoding.f2pgames.presentation.adapter.GamesAdapter
import com.keecoding.f2pgames.presentation.ui.MainActivity
import com.keecoding.f2pgames.presentation.vm.MainViewModel

class PCFragment : Fragment() {
    private lateinit var binding: FragmentPcBinding
    private lateinit var viewModel: MainViewModel
    private val gamesAdapter = GamesAdapter{}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).getViewModel()

        viewModel.gamesLiveData.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                val pcGames = it.data?.filter { game ->
                    game.platform == "PC (Windows)"
                }
                gamesAdapter.differ.submitList(pcGames) {
                    gamesAdapter.notifyDataSetChanged()
                }
            } else if(it is Resource.Error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
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