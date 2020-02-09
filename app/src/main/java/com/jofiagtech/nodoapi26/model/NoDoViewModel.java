package com.jofiagtech.nodoapi26.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jofiagtech.nodoapi26.util.NoDoRepository;

import java.util.List;

public class NoDoViewModel extends AndroidViewModel {

    private NoDoRepository mNoDoRepository;
    private LiveData<List<NoDo>> mAllNoDoItem;

    public NoDoViewModel(@NonNull Application application) {
        super(application);

        mNoDoRepository = new NoDoRepository(application);
        mAllNoDoItem = mNoDoRepository.getAllNoDoItem();
    }

    public LiveData<List<NoDo>> getAllNoDoItem() {
        return mAllNoDoItem;
    }

    public void insert(NoDo noDo){
        mNoDoRepository.insert(noDo);
    }
}