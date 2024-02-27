package hfa.madad.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hfa.madad.tasks.ui.adapters.TaskItemAdapter
import hfa.madad.tasks.viewmodels.TaskViewModelFactory
import hfa.madad.tasks.databinding.FragmentTasksBinding
import hfa.madad.tasks.models.TaskDatabase
import hfa.madad.tasks.viewmodels.TaskViewModel

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
            viewModel.onTaskClicked(taskID)
        }
        binding.tasksList.adapter = adapter

        //Наблюдаем за свойством tasks модели представления
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        //Этот код должен выполняться тогда, когда navigateToTask присваивается новое значение taskId, отличное от null.
        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskID ->
            taskID?.let {
                val action = TasksFragmentDirections.actionTasksFragmentToEditTaskFragment(taskID)
                this.findNavController().navigate(action)
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}