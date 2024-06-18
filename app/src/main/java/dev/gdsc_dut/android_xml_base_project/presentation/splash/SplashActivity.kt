package dev.gdsc_dut.android_xml_base_project.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.gdsc_dut.android_xml_base_project.R
import dev.gdsc_dut.android_xml_base_project.core.base.BaseActivity
import dev.gdsc_dut.android_xml_base_project.core.extensions.startActivity
import dev.gdsc_dut.android_xml_base_project.databinding.ActivitySplashBinding
import dev.gdsc_dut.android_xml_base_project.presentation.auth.AuthActivity
import dev.gdsc_dut.android_xml_base_project.presentation.main.MainActivity
import kotlinx.coroutines.launch


@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    private val binding by viewBinding<ActivitySplashBinding>()

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition {
            true
        }

        viewModel.getAuthenticationState()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authState.collect {
                    when (it) {
                        AuthenticationState.UNKNOWN -> {}
                        AuthenticationState.AUTHENTICATED -> {
                            startActivity<MainActivity>()
                            finish()
                        }
                        AuthenticationState.UNAUTHENTICATED -> {
                            startActivity<AuthActivity>()
                            finish()
                        }
                    }
                }
            }
        }
    }

}