package com.jofiagtech.nodoapi26.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jofiagtech.nodoapi26.model.NoDo;

import java.util.List;

@Dao
public interface NoDoDao {
    //CRUD
    @Insert
    void insert(NoDo noDo);

    @Query("SELECT * FROM noDo_table ORDER BY noDo_column DESC")
    LiveData<List<NoDo>> getAllItem();

    @Query("UPDATE noDo_table SET noDo_column = :newNodoText WHERE id = :id")
    void updateItem(int id, String newNodoText);

    @Query("DELETE FROM noDo_table WHERE id = :idOfItemToDelete")
    void deleteItem(int idOfItemToDelete);

    @Query("DELETE FROM noDo_table")
    void deleteAllItem();


}
