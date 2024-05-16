import java.util.Scanner

// Represents a note with a title and content
data class Note(val title: String, val content: String)

// A simple note-taking application
class NoteTakingApp {
    // Scanner to read user input
    private val scanner = Scanner(System.`in`)
    // List to store notes
    private val notes = mutableListOf<Note>()

    // Main function to run the application
    fun run() {
        var running = true

        // Main loop to display options and handle user input
        while (running) {
            println("Choose an option:")
            println("1. Create a new note")
            println("2. View all notes")
            println("3. Edit a note")
            println("4. Delete a note")
            println("5. Exit")
            // Handle user input
            when (scanner.nextLine()) {
                "1" -> createNote()
                "2" -> viewNotes()
                "3" -> editNote()
                "4" -> deleteNote()
                "5" -> running = false
                else -> println("Invalid option")
            }
        }
    }

    // Function to create a new note
    private fun createNote() {
        println("Enter note title:")
        val title = scanner.nextLine()
        println("Enter note content:")
        val content = scanner.nextLine()
        // Add the new note to the list
        notes.add(Note(title, content))
        println("Note created successfully!")
    }

    // Function to view all notes
    private fun viewNotes() {
        if (notes.isEmpty()) {
            println("No notes available.")
        } else {
            println("All notes:")
            // Display each note's title and content
            notes.forEachIndexed { index, note ->
                println("${index + 1}. Title: ${note.title}")
                println("   Content: ${note.content}")
            }
        }
    }

    // Function to edit a note
    private fun editNote() {
        if (notes.isEmpty()) {
            println("No notes available to edit.")
        } else {
            viewNotes()
            println("Enter the index of the note you want to edit:")
            val index = scanner.nextLine().toIntOrNull()?.minus(1)
            if (index != null && index in 0 until notes.size) {
                println("Enter new title (leave empty to keep the same):")
                val newTitle = scanner.nextLine()
                println("Enter new content (leave empty to keep the same):")
                val newContent = scanner.nextLine()
                // Update the selected note with new title and content
                val oldNote = notes[index]
                val editedNote = oldNote.copy(
                    title = if (newTitle.isNotEmpty()) newTitle else oldNote.title,
                    content = if (newContent.isNotEmpty()) newContent else oldNote.content
                )
                notes[index] = editedNote
                println("Note edited successfully!")
            } else {
                println("Invalid index.")
            }
        }
    }

    // Function to delete a note
    private fun deleteNote() {
        if (notes.isEmpty()) {
            println("No notes available to delete.")
        } else {
            viewNotes()
            println("Enter the index of the note you want to delete:")
            val index = scanner.nextLine().toIntOrNull()?.minus(1)
            if (index != null && index in 0 until notes.size) {
                // Remove the selected note from the list
                notes.removeAt(index)
                println("Note deleted successfully!")
            } else {
                println("Invalid index.")
            }
        }
    }
}

fun main() {
    // Create an instance of the NoteTakingApp and run it
    val app = NoteTakingApp()
    app.run()
}
