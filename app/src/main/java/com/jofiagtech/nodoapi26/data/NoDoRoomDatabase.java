package com.jofiagtech.nodoapi26.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jofiagtech.nodoapi26.model.NoDo;

@Database(entities = {NoDo.class}, version = 1)
public abstract class NoDoRoomDatabase  extends RoomDatabase {

    private static volatile NoDoRoomDatabase INSTANCE;
    public abstract NoDoDao noDoDao();

    public static NoDoRoomDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (NoDoRoomDatabase.class){
                if (INSTANCE == null){
                    //Create the db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoDoRoomDatabase.class, "noDo_datatbase")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private NoDoDao noDoDao;

        public PopulateDbAsync(NoDoRoomDatabase db) {
            noDoDao = db.noDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noDoDao.deleteAllItem();

            //Test
            NoDo noDo = new NoDo("Be friend with Satira's mom");
            noDoDao.insert(noDo);

            noDo = new NoDo("Love her again");
            noDoDao.insert(noDo);

            return null;
        }
    }
}
