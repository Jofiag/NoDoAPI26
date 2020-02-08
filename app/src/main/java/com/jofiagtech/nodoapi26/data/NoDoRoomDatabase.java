package com.jofiagtech.nodoapi26.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jofiagtech.nodoapi26.model.NoDo;

@Database(entities = {NoDo.class}, version = 1)
public abstract class NoDoRoomDatabase  extends RoomDatabase {

    private static volatile NoDoRoomDatabase INSTANCE;
    public abstract NoDoDao noDoDao();

    static NoDoRoomDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (NoDoRoomDatabase.class){
                if (INSTANCE == null){
                    //Create the db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoDoRoomDatabase.class, "noDo_datatbase")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
