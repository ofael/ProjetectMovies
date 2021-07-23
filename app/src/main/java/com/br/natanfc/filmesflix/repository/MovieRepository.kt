package com.br.natanfc.filmesflix.repository

import android.util.Log
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import com.br.natanfc.filmesflix.domain.Movie

class MovieRepository (private val movieRestApiTask: MovieRestApiTask){

    companion object{
        const val TAG = "MovieRepository"
    }


    private val movieList = arrayListOf<Movie>()

    fun getAllMovies(): ArrayList<Movie> {
        val request = movieRestApiTask.retrofitApi().getAllMovie().execute()

        if(request.isSuccessful){
            request.body()?.let{
                movieList.addAll(it)
            }
        }else{
                request.errorBody()?.let{
                    Log.d(TAG,it.toString())
                }
        }

        return movieList
    }

}