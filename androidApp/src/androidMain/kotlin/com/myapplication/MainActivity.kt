package com.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.cash.sqldelight.db.SqlDriver
import com.myapplication.note_detail.NoteDetailScreen
import com.myapplication.note_detail.NoteDetailViewModel
import com.myapplication.note_detail.NoteDetailViewModelFactory
import com.myapplication.note_list.NoteListScreen
import com.myapplication.note_list.NoteListViewModel
import com.myapplication.note_list.NoteListViewModelFactory
import data.local.DatabaseDriverFactory
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*        val driver: SqlDriver = DatabaseDriverFactory(applicationContext).createDriver()
        val db = NoteDatabase(driver)
        val savedStateHandle = SavedStateHandle()

        val noteListViewModelFactory = NoteListViewModelFactory(db, savedStateHandle)
        val noteDetailsViewModelFactory = NoteDetailViewModelFactory(db,savedStateHandle)

        val noteListViewModel = ViewModelProvider(this, noteListViewModelFactory)[NoteListViewModel::class.java]

        val noteDetailsViewModel = ViewModelProvider(this,noteDetailsViewModelFactory)[NoteDetailViewModel::class.java]*/

        //val viewModel : NoteListViewModel by viewModels()

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                //val noteListViewModel = getViewModel<NoteListViewModel>()
                //val noteDetailsViewModel = getViewModel<NoteDetailViewModel>()
                NavHost(navController = navController, startDestination = "note_list") {
                    composable(route = "note_list") {
                        NoteListScreen(navController = navController/*,viewModel= noteListViewModel*/)
                    }
                    composable(
                        route = "note_detail/{noteId}",
                        arguments = listOf(
                            navArgument(name = "noteId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L
                        NoteDetailScreen(noteId = noteId, navController = navController/*, viewModel = noteDetailsViewModel*/)
                    }
                }
            }
        }
    }
}