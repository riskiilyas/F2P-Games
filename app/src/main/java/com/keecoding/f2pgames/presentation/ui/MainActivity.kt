package com.keecoding.f2pgames.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.keecoding.f2pgames.R
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.databinding.ActivityMainBinding
import com.keecoding.f2pgames.presentation.factory.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView
        binding.shimmerGame.bringToFront()

        observeGames()
    }

    private fun observeGames() {
        viewModel.getAllGames()
        viewModel.gamesLiveData.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.shimmerGame.visibility = View.GONE
//                    Toast.makeText(this, if (it.data.isNullOrEmpty()) " " else it.data[0].toString(), Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.shimmerGame.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    Toast.makeText(this, "ERRORRR", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    fun getViewModel() = viewModel
}