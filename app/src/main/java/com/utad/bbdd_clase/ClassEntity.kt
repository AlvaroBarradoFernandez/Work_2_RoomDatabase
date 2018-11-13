package com.utad.bbdd_clase

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName =  "Class")
class ClassEntity (
        @PrimaryKey(autoGenerate = false)
        var teacherName: String?,
        var subjectName: String?,
        var yearNum: Int?

)