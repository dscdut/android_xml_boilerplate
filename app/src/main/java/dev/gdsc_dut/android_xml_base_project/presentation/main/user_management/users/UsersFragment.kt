package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users.adapters.UsersRecyclerViewAdapter
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentUsersBinding
import dev.gdsc_dut.android_xml_base_project.models.User

class UsersFragment : BaseFragment(R.layout.fragment_users) {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UsersRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this.context)

        val users = arrayListOf(
            User(id = 0, name = "Harry Potter", email = "abc@gmail.com", role = 1, password = "abc123"),
            User(id = 1, name = "Hermione Granger", email = "hermione@gmail.com", role = 2, password = "hermione123"),
            User(id = 2, name = "Ron Weasley", email = "ron@gmail.com", role = 1, password = "ron123")
        )

        adapter = UsersRecyclerViewAdapter(users)
        binding.recyclerViewUsers.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}