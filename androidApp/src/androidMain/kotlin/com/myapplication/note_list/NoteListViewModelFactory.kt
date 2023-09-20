package com.myapplication.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.NoteDatabase

class NoteListViewModelFactory(
    private val db: NoteDatabase,
    private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            //return NoteListViewModel(db, savedStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}