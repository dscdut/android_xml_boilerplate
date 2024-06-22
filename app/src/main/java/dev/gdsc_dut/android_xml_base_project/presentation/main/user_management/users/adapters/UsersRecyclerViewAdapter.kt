package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.databinding.ItemSingleUserBinding
import dev.gdsc_dut.android_xml_base_project.models.User

class UsersRecyclerViewAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemSingleUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(private val binding: ItemSingleUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvUsername.text = user.name
            binding.tvUserEmail.text = user.email

            val context = itemView.context
            val adapter = ArrayAdapter.createFromResource(
                context,
                R.array.user_role,
                android.R.layout.simple_spinner_item
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spiUserRole.adapter = adapter

            when (user.role) {
                1 -> binding.spiUserRole.setSelection(0) // admin
                2 -> binding.spiUserRole.setSelection(1) // member
                else -> binding.spiUserRole.setSelection(1) // Default to member if unknown role
            }
        }
    }
}