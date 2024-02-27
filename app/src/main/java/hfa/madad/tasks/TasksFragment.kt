package hfa.madad.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hfa.madad.tasks.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        //requireNotNull() — функция Kotlin, которая выдает исключение IllegalArgumentException, если ее аргумент равен null
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao

        val viewModelFactory = TaskViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(TaskViewModel::class.java)
        //Включает связывание данных, чтобы макет мог использовать его для обращения к свойствам и методам модели представления
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter { taskID ->
            Toast.makeText(
                context,
                "Clicked task $taskID",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.tasksList.adapter = adapter

        //Наблюдаем за свойством tasks модели представления
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}