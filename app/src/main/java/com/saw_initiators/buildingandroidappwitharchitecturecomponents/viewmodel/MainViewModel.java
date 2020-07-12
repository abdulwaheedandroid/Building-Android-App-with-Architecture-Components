package com.saw_initiators.buildingandroidappwitharchitecturecomponents.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.NoteEntity;
import com.saw_initiators.buildingandroidappwitharchitecturecomponents.utilities.SampleData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public List<NoteEntity> getNotes = SampleData.getNotes();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
