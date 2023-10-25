package com.example.matching_manager.ui.calender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel(
) : ViewModel() {
    private val _list: MutableLiveData<List<CalendarModel>> = MutableLiveData() // 뷰모델 내에서만 변경가능
    val list: LiveData<List<CalendarModel>> get() = _list // 읽기전용 리스트

    fun addMemoItem(model:CalendarModel){
        val currentList = list.value.orEmpty().toMutableList() // 리스트 변경이 가능함
        currentList.add(model)
        _list.value = currentList
    }

    fun removeMemoItem(model: CalendarModel, position: Int){
        val currentList = list.value.orEmpty().toMutableList() // 리스트 변경이 가능함
        currentList.removeAt(position)
        _list.value = currentList
    }

}