package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users.adapters.UsersRecyclerViewAdapter
import dev.gdsc_dut.android_xml_base_project.core.base.BaseFragment
import dev.gdsc_dut.android_xml_base_project.data.remote.response.user_management.GetUsersResponse
import dev.gdsc_dut.android_xml_base_project.databinding.FragmentUsersBinding
import timber.log.Timber

@AndroidEntryPoint
class UsersFragment : BaseFragment(R.layout.fragment_users) {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: UsersRecyclerViewAdapter

    private val viewModel: UsersViewModel by viewModels()

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

        fetchAndDisplayUsers()
    }

    private fun fetchAndDisplayUsers() {
        try {
            binding.progressBar.visibility = View.VISIBLE

            val currentUser = viewModel.currentUser
            val allUsersResponse = viewModel.getUsers()

            currentUser.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    allUsersResponse.observe(viewLifecycleOwner) { users ->
                        val usersToShow: List<GetUsersResponse.User> = if (user.role.id == 1) {
                            users?.results!!
                        } else {
                            users?.results?.filter { it.role.id != 1 }!!
                        }
                        updateRecyclerView(usersToShow)

                        binding.progressBar.visibility = View.GONE
                    }
                }
            }

        } catch (e: Exception) {
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun updateRecyclerView(users: List<GetUsersResponse.User>) {
        adapter = UsersRecyclerViewAdapter(users)
        binding.recyclerViewUsers.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}