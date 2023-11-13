package com.link_up.matching_manager.domain.usecase.team

import com.google.firebase.database.DatabaseReference
import com.link_up.matching_manager.domain.repository.TeamRepository
import com.link_up.matching_manager.ui.team.TeamItem

class TeamEditRecruitDataUseCase(val repository: TeamRepository) {
    suspend operator fun invoke (databaseRef: DatabaseReference, data: TeamItem.RecruitmentItem, newData: TeamItem.RecruitmentItem) = repository.editRecruitData(databaseRef, data, newData)
}