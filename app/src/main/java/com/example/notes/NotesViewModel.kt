package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {
     private val repository:NotesRepository
    val allNotes:LiveData<List<Notes>>

    init {
        val dao =NotesDatabase.getDatabase(application).getNotesDao()
        repository =NotesRepository(dao)
        allNotes=repository.allNotes
    }
    fun deleteNotes(notes: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }
    fun insertNotes(notes: Notes) = viewModelScope.launch ( Dispatchers.IO) {
        repository.insert(notes)
    }
}
//activity takes with viewModel and viewModel talks with repository and repository takes from where
//data should come ,it is not the work of the view model to take care
//repository provide the the insert and delete function to the viewModel
//repository talks with the dao
//dao takes care of the operations inside the room
