package com.myapplication.di

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import app.cash.sqldelight.db.SqlDriver
import com.myapplication.NoteDatabase
import com.myapplication.note_detail.NoteDetailViewModel
import com.myapplication.note_list.NoteListViewModel
import data.local.DatabaseDriverFactory
import data.note.SqlDelightNoteDataSource
import domain.note.NoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        val app: Application = get() // Get the Application instance using Koin
        DatabaseDriverFactory(app).createDriver()
    }

    single<NoteDataSource> {
        val driver: SqlDriver = get() // Get the SqlDriver dependency using Koin
        SqlDelightNoteDataSource(NoteDatabase(driver))
    }


    viewModel { NoteListViewModel(noteDataSource = get(), savedStateHandle = get()) }

    viewModel { NoteDetailViewModel(noteDataSource = get(), savedStateHandle = get()) }
}