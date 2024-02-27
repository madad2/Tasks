package hfa.madad.tasks.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import hfa.madad.tasks.models.Task

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.taskId == newItem.taskId

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
}