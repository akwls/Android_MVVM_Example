package com.example.viewmodelexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

// 데이터의 변경
// view model은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있음
class MyNumberViewModel : ViewModel() {
    companion object {
        const val TAG: String = "로그"
    }
    // mutable 라이브 데이터 - 수정 가능한 데이터
    // 라이브 데이터 - 값 변동 안됨

    // 내부에서 설정하는 자료형은 mutable로
    // 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기값 설정
    init {
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }

    // viewmodel이 가지고 있는 값을 변경
    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType) {
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}