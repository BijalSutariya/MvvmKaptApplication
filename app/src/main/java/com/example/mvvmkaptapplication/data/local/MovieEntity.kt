package com.example.mvvmkaptapplication.data.local

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["id"])
data class MovieEntity( var userId: Int,
                        var id: Int,
                        var title: String)