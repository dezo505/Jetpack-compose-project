package com.example.jetpackcomposeproject.data.repository

import android.content.Context
import com.example.jetpackcomposeproject.data.AthleteDatabase
import com.example.jetpackcomposeproject.data.dao.AthleteDAO
import com.example.jetpackcomposeproject.data.model.Athlete

class AthleteRepository private constructor(context: Context) {

    private var athleteDAO: AthleteDAO
    private var db: AthleteDatabase

    init {
        db = AthleteDatabase.getDatabase(context)!!
        athleteDAO = db.athleteDAO()!!
    }

    companion object {
        private var R_INSTANCE: AthleteRepository? = null

        fun getInstance(context: Context): AthleteRepository {
            if (R_INSTANCE == null) {
                R_INSTANCE = AthleteRepository(context)
            }
            return R_INSTANCE as AthleteRepository
        }
    }

    fun findAll(): List<Athlete> {
        return athleteDAO.findAll()
    }

    fun findById(id: Int): Athlete? {
        return athleteDAO.findById(id)
    }

    fun insert(item: Athlete): Boolean {
        return athleteDAO.insert(item) >= 0
    }

    fun remove(item: Athlete) {
        athleteDAO.delete(item)
    }

    fun update(item: Athlete): Boolean {
        return athleteDAO.update(item) > 0
    }


}
