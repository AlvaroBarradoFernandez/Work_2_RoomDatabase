package com.utad.bbdd_clase

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ClassDao {

    fun insert(student: ClassEntity):Long

    fun insert(student: List<ClassEntity>):LongArray

    @Update
    fun update(classDao: ClassEntity): Int

    @Query(value = "DELETE FROM class")
    fun deleteAll()

    @Query(value = "SELECT * from class ORDER BY teacherName ASC")
    fun getAllStudents(): LiveData<List<ClassEntity>>

    @Query(value = "SELECT * from class WHERE subjectName = :teacherName LIMIT 1")
    fun getSubjectbyTeacherName(teacherName:String): LiveData<ClassEntity>
}