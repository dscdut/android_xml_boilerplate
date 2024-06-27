package dev.gdsc_dut.android_xml_base_project.presentation.main.user_management.account

import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gdsc_dut.android_xml_base_project.core.base.BaseViewModel
import dev.gdsc_dut.android_xml_base_project.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AccountViewModel
@Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    fun currentUser() = liveData(Dispatchers.IO) {
        emit(userRepository.getCurrentUser().getOrNull())
    }

    fun updateUser(
        name: String,
        email: String,
    ) = liveData(Dispatchers.IO) {
        emit(userRepository.updateUser(name, email).getOrNull())
    }
}