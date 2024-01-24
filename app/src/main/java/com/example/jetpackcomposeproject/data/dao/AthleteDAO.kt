package com.example.jetpackcomposeproject.data.dao

import androidx.room.*
import com.example.jetpackcomposeproject.data.model.Athlete

@Dao
interface AthleteDAO {
    @Query("SELECT * FROM athletes ORDER BY id ASC")
    fun findAll(): List<Athlete>

    @Query("SELECT * FROM athletes WHERE id = :id")
    fun findById(id: Int): Athlete?

    @Query("DELETE FROM athletes")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Athlete): Long

    @Update
    fun update(item: Athlete): Long

    @Delete
    fun delete(item: Athlete)
}
