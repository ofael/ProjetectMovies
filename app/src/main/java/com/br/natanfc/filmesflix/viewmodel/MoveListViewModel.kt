package com.br.natanfc.filmesflix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import com.br.natanfc.filmesflix.model.Movie
import com.br.natanfc.filmesflix.repository.MovieRepository

class MoveListViewModel : ViewModel() {

    companion object{
        const val TAG = "MovieRepository"
    }


    private val movieRestApiTask = MovieRestApiTask()
    private val movieRepository = MovieRepository(movieRestApiTask)



    private var _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>>
        get() = _movieList



    fun init(){
            getAllMovie()
    }

    private fun getAllMovie(){
        Thread{
            try{
                _movieList.postValue(movieRepository.getAllMovies())
            }catch(exception : Exception){
                Log.d(TAG,exception.message.toString())
            }
        }
    }
}