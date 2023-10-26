package com.example.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.notes.database.NoteDao
import com.example.notes.models.AppScreens
import com.example.notes.models.NoteEvent
import com.example.notes.models.ToastState
import com.example.notes.models.entities.Note
import com.example.notes.ui.components.general.icons.DropdownOption
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NoteViewModel(
    private val noteDao: NoteDao
): ViewModel() {

    var selectedNote: Note? = null
        private set

    fun selectNote(note: Note) {
        selectedNote = note
    }

    val toastState = ToastState()

    private var _navController: NavHostController? = null
    val navController: NavHostController
        get() = _navController!!

    val isNavInitialized
        get() = _navController != null

    fun setNavController(nav: NavHostController) {
        _navController = nav
    }

    val allNotes = noteDao.getNotesByRecentEdit()

    val noteDropdownOptions = listOf(
        DropdownOption.Delete
    )


    fun onEvent(
        noteEvent: NoteEvent,
    ) {
        when (noteEvent) {
            NoteEvent.CreateNote -> {
                navController.navigate(AppScreens.NewNoteScr.name)
            }
            NoteEvent.OnSplash -> {
                viewModelScope.launch {
                    delay(2000L)
                    navController.navigate(AppScreens.NoteListScr.name) {
                        // Prevents back button from going back to splash screen
                        popUpTo(0)
                    }
                }
            }
            is NoteEvent.OnDeleteNote -> {
                var isNoteDeleted = true
                viewModelScope.launch {
                    try {
                        noteDao.deleteNote(noteEvent.note)
                    } catch (e: Exception) {
                        isNoteDeleted = false
                    }
                    toastState.setToastMessage(
                        if(isNoteDeleted) "note deleted"
                        else "error occurred: note note deleted"
                    )
                }
            }
            is NoteEvent.SaveNote -> {
                var isNoteSaved = true
                viewModelScope.launch {
                    try {
                        val note = noteEvent.note
                        note.last_edited = System.currentTimeMillis()/1000
                        noteDao.upsertNote(note)
                    } catch (e: Exception) {
                        isNoteSaved = false
                    }
                    toastState.setToastMessage(
                        if(isNoteSaved) "note saved" else "note not saved"
                    )
                }
                navController.popBackStack()
            }
            NoteEvent.OnBackPressed -> {
                navController.popBackStack()
            }
            is NoteEvent.OnViewNote -> {
                selectNote(noteEvent.note)
                navController.navigate(AppScreens.ViewNoteScr.name)
            }
        }
    }


    fun createDummyNotes() {
        viewModelScope.launch {
            listOf(
                Note(
                    note_title = "Anime Characters",
                    note_details = "Hinata\nLuffy\nSasuke\nKakashi\nGohan\nTen-Ten\nSaitama"
                ),
                Note(
                    note_title = "Favorite Movies",
                    note_details = "Inception\nThe Shawshank Redemption\nThe Godfather\nPulp Fiction\nFight Club\nThe Matrix"
                ),
                Note(
                    note_title = "To-Do List",
                    note_details = "Buy groceries\nPay bills\nClean the house\nWalk the dog"
                ),
                Note(
                    note_title = "Travel Destinations",
                    note_details = "Paris\nTokyo\nNew York\nVenice\nBarcelona\nSydney"
                ),
                Note(
                    note_title = "Programming Languages",
                    note_details = "Kotlin\nPython\nJavaScript\nSwift\nJava\nC++"
                ),
                Note(
                    note_title = "Books to Read",
                    note_details = "Dune\n1984\nTo Kill a Mockingbird\nThe Great Gatsby\nThe Catcher in the Rye"
                ),
                Note(
                    note_title = "Favorite Quotes",
                    note_details = "Carpe Diem\nTo be or not to be\nAll you need is love\nMay the Force be with you"
                ),
                Note(
                    note_title = "Grocery List",
                    note_details = "Apples\nMilk\nBread\nEggs\nChicken\nSpinach\nPasta"
                ),
                Note(
                    note_title = "Bucket List",
                    note_details = "Skydiving\nVisit the Grand Canyon\nLearn to play the guitar\nRun a marathon"
                ),
                Note(
                    note_title = "Recipe Ideas",
                    note_details = "Spaghetti Carbonara\nChocolate Chip Cookies\nChicken Stir-Fry\nGuacamole"
                ),
                Note(
                    note_title = "Project Tasks",
                    note_details = "Design UI\nImplement Backend\nWrite Tests\nDeploy to Production"
                ),
            ).forEach {note ->
                noteDao.upsertNote(note)
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NoteApplication)
                NoteViewModel(application.database.dao())
            }
        }
    }
}