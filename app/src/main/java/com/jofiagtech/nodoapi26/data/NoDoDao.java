package com.jofiagtech.nodoapi26.data;

import androidx.room.Dao;
import androidx.room.Insert;

import com.jofiagtech.nodoapi26.model.NoDo;

@Dao
public interface NoDoDao {
    //CRUD
    @Insert
    void insert(NoDo noDo);


}
