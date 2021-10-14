package com.alexyuzefovich.pillbox.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pill_table")
data class PillData(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "notes")
    val notes: String?,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "quantity_metric")
    val quantityMetric: String,

    @ColumnInfo(name = "dosage")
    val dosage: Int?,

    @ColumnInfo(name = "dosage_metric")
    val dosageMetric: String?,

    @ColumnInfo(name = "best_before_date")
    val bestBeforeDate: Long,

    @ColumnInfo(name = "type")
    val type: String
)