package com.mobeedev.kajakorg.data.datasource.local.db.path

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobeedev.kajakorg.data.model.detail.EventDescriptionDto
import com.mobeedev.kajakorg.data.model.detail.EventType

@Entity
data class EventDescriptionDB(
    @PrimaryKey(autoGenerate = true)
    val eventDescriptionId: Int = 0,
    val eventId: Int,

    val type: EventType,
    val sortOrder: Int,
    val description: String
)

fun EventDescriptionDB.toDto(): EventDescriptionDto = EventDescriptionDto(
    type = type,
    sortOrder = sortOrder,
    description = description
)