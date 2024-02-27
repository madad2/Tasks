package hfa.madad.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hfa.madad.tasks.databinding.TaskItemBinding

class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    //Вызывается каждый раз, когда потребуется создать ViewHolder (держатель представления)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder.inflateFrom(parent)
    }

    //Вызывается, когда данные должны отображаться в ViewHolder (держателе представления)
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    //Определяет ViewHolder (держатель представления)
    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            //Создает каждый ViewHolder (держатель представления) и заполняет его макет
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }

        //Данные добавляются в макет ViewHolder (держателя представления)
        fun bind(item: Task) {
            binding.task = item
        }
    }
}