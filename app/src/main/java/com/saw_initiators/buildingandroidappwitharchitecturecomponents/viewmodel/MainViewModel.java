package com.saw_initiators.buildingandroidappwitharchitecturecomponents.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.AppRepository;
import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.NoteEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotes;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }
}
