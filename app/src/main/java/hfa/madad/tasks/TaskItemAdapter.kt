package hfa.madad.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    //Для данных RecycleView (представления с переработкой)
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //Количество элементов
    override fun getItemCount() = data.size

    //Вызывается каждый раз, когда потребуется создать ViewHolder (держатель представления)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder.inflateFrom(parent)
    }

    //Вызывается, когда данные должны отображаться в ViewHolder (держателе представления)
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    //Определяет ViewHolder (держатель представления)
    class TaskItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {
        companion object {
            //Создает каждый ViewHolder (держатель представления) и заполняет его макет
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as TextView
                return TaskItemViewHolder(view)
            }
        }

        //Данные добавляются в макет ViewHolder (держателя представления)
        fun bind(item: Task) {
            rootView.text = item.taskName
        }
    }
}