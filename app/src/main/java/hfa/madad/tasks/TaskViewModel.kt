package hfa.madad.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""
    private val tasks = dao.getAll()

    //Преобразует задачу в значение LiveData<String>, которое присваивается tasksString.
    val tasksString = tasks.map { tasks ->
        formatTasks(tasks)
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }
}

fun formatTasks(tasks: List<Task>): String {
    return tasks.fold("") { str, item ->
        str + '\n' + formatTask(item)
    }
}

fun formatTask(task: Task): String {
    return """
    ID: ${task.taskId}
    Name: ${task.taskName}
    Complete: ${task.taskDone}
    
    """.trimMargin()
}