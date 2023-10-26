package com.example.notes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.models.AppScreens
import com.example.notes.ui.screens.view_note.ViewNoteScreen
import com.example.notes.ui.screens.SplashScreen
import com.example.notes.ui.screens.new_note.NewNoteScreen
import com.example.notes.ui.screens.note_list.NoteListSearchScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: NoteViewModel = viewModel(
                factory = NoteViewModel.Factory
            )
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Navigation( viewModel = viewModel )
            }
        }
    }
}

@Composable
fun SetupNavController(
    viewModel: NoteViewModel
) {
    viewModel.setNavController(
        rememberNavController()
    )
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel
) {
    val startScreen = AppScreens.NoteListScr

    if (!viewModel.isNavInitialized) {
        SetupNavController(viewModel)
    }

    val toastState by remember { mutableStateOf(viewModel.toastState) }
    val showToast by toastState.showToast.collectAsState()

    if (showToast) {
        // Fetching the local context for using the Toast
        val mContext = LocalContext.current
        Toast.makeText(
            mContext,
            toastState.toastMessage,
            Toast.LENGTH_SHORT
        ).show()
        toastState.disableToast()
    }


    NavHost(
        navController = viewModel.navController,
        startDestination = startScreen.name,
        modifier = modifier
    ) {
        composable(AppScreens.SplashScr.name) {
            SplashScreen(onEvent = viewModel::onEvent)
        }
        composable(AppScreens.NoteListScr.name) {
            NoteListSearchScreen(
                viewModel = viewModel
            )
        }
        composable(AppScreens.NewNoteScr.name) {
            NewNoteScreen(onEvent = viewModel::onEvent)
        }
        composable(AppScreens.ViewNoteScr.name) {
            ViewNoteScreen(viewModel = viewModel)
        }
    }
}



