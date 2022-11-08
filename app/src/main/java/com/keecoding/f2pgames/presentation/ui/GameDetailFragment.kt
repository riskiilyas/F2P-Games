package com.keecoding.f2pgames.presentation.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.keecoding.f2pgames.R
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.databinding.FragmentGameDetailBinding
import com.keecoding.f2pgames.presentation.vm.MainViewModel

class GameDetailFragment : Fragment() {
    private lateinit var binding: FragmentGameDetailBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = (activity as MainActivity).getViewModel()

//        binding.ivThumbnail.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.ffade_in)

        vm.getGameDetail(arguments?.getInt("id") ?: -1)

        vm.gameDetailLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    when(it.data?.platform) {
                        "pc" -> {
                            binding.ivPlatform.setBackgroundResource(R.drawable.ic_baseline_computer_24)
                            binding.ivPlatform.setImageResource(R.drawable.ic_baseline_computer_24)
                        }

                        "browser" -> {
                            binding.ivPlatform.setBackgroundResource(R.drawable.ic_baseline_web_24)
                            binding.ivPlatform.setImageResource(R.drawable.ic_baseline_web_24)
                        }
                    }

                    Glide.with(requireContext()).load(it.data?.thumbnail ?: "").into(binding.ivThumbnail)

                    binding.tvTitle.text = it.data?.title

                    binding.ivBack.setOnClickListener {
                        findNavController().popBackStack()
                        vm.clearGameDetail()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }

                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "EMPTY", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}