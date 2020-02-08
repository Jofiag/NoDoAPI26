package com.jofiagtech.nodoapi26.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.jofiagtech.nodoapi26.data.NoDoDao;
import com.jofiagtech.nodoapi26.data.NoDoRoomDatabase;
import com.jofiagtech.nodoapi26.model.NoDo;

import java.util.List;

public class NoDoRepository{
    private NoDoDao mNoDoDao;
    private LiveData<List<NoDo>> mAllNoDoItem;

    public NoDoRepository(Application application) {
        NoDoRoomDatabase db = NoDoRoomDatabase.getDatabase(application);

        mNoDoDao = db.noDoDao();
        mAllNoDoItem = mNoDoDao.getAllItem();
    }

    public LiveData<List<NoDo>> getAllNoDoItem() {
        return mAllNoDoItem;
    }

    public void insert(NoDo noDo){
        new InsertAsyncTask(mNoDoDao).execute(noDo);
    }

    private class InsertAsyncTask extends AsyncTask<NoDo, Void, Void> {
        private NoDoDao asyncNoDoDao;

        public InsertAsyncTask(NoDoDao noDoDao) {
            asyncNoDoDao = noDoDao;
        }

        @Override
        protected Void doInBackground(NoDo... noDos) {
            asyncNoDoDao.insert(noDos[0]);
            return null;
        }
    }
}