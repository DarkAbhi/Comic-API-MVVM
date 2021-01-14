package com.darkabhi.randomapi.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.darkabhi.randomapi.data.api.ComicClient
import com.darkabhi.randomapi.data.api.ComicDBInterface
import com.darkabhi.randomapi.databinding.ActivityComicBinding
import com.darkabhi.randomapi.repository.NetworkState
import com.darkabhi.randomapi.vo.ComicDetails

class ComicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComicBinding
    private lateinit var viewModel: SingleComicViewModel
    private lateinit var repository: ComicDetailRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService: ComicDBInterface = ComicClient.getClient()
        repository = ComicDetailRepository(apiService)
        viewModel = getViewModel()
        viewModel.comicDetails.observe(this, Observer {
            updateUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            binding.progressCircular.visibility =
                if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.errorText.visibility =
                if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun updateUI(it: ComicDetails) {
        binding.comicImage.load(it.img)
        binding.comicName.text = it.title
        binding.comicDesc.text = it.transcript
    }

    private fun getViewModel(): SingleComicViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleComicViewModel(repository) as T
            }
        })[SingleComicViewModel::class.java]
    }
}