package hackathon.picky.feature.home

import android.app.Activity
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hackathon.picky.core.model.Category
import hackathon.picky.feature.home.model.HomeUiState
import hackathon.picky.feature.home.model.HomeUiTest
import hackathon.picky.feature.home.model.PolicyDetail
import hackathon.picky.feature.home.model.policyDetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiTest)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun clickDetail(policyId: Int) = viewModelScope.launch {
        _uiState.update { prev ->
            HomeUiState.Detail(
                previousUiState = prev,
                policyDetail = policyDetailData,
                daysRemaining = calculateDaysRemaining("2024.09.28"),
                isBookmarked = false
            )
        }
    }


    fun clickList(category: Category) = viewModelScope.launch {
        _uiState.update {prev ->
            HomeUiState.ListScreen(
                previousUiState = prev,
                list = HomeUiTest.infoSectionList[0].infoList,
                category = category
            )
        }
    }
    fun toggleBookmark() {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Detail) {
            _uiState.value = currentState.copy(
                isBookmarked = !currentState.isBookmarked
            )
        }
    }

    private fun calculateDaysRemaining(endDate: String): Int {
        return try {
            val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            val end = formatter.parse(endDate) ?: return 0
            val today = Calendar.getInstance().time
            val diffInMillis = end.time - today.time
            TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
        } catch (e: Exception) {
            0
        }
    }

    fun goBack(context: Context) {
        when (val state = _uiState.value) {
            is HomeUiState.Detail -> {
                _uiState.value = state.previousUiState
            }
            is HomeUiState.ListScreen -> {
                _uiState.value = state.previousUiState
            }
            else -> {
                (context as? Activity)?.finish()
            }
        }
    }
}