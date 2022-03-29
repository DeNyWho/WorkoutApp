package com.example.workoutapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.example.workoutapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel () {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            _onBoardingCompleted.value =
//                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
//        }
    }

}