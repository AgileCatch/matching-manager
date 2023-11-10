package com.link_up.matching_manager.domain.usecase.sharedpreference

import android.content.Context
import com.link_up.matching_manager.domain.repository.SharedPreferenceRepository
import com.link_up.matching_manager.ui.calender.CalendarModel
import com.link_up.matching_manager.ui.home.alarm.AlarmModel

class SaveCalendarDataUseCase(
    private val repository: SharedPreferenceRepository
) {
    operator fun invoke(
        context: Context,
        list : List<CalendarModel>
    ) = repository.saveCalendarData(
        context,
        list
    )
}