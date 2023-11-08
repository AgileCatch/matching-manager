package com.link_up.matching_manager.domain.repository

import com.link_up.matching_manager.domain.model.ArenaEntity
import retrofit2.http.Query

interface ArenaRepository {
    suspend fun getArenaInfo(
        query: String,
        x: String,
        y: String,
        radius: Int,
        page: Int,
        size: Int,
        sort: String,
    ): ArenaEntity
}