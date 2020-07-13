package com.saw_initiators.buildingandroidappwitharchitecturecomponents.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.AppRepository;
import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.NoteEntity;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity noteEntity = mRepository.getNoteById(noteId);
                mLiveNote.postValue(noteEntity);
            }
        });
    }

    public void deleteNote() {
        mRepository.deleteNote(mLiveNote.getValue());
    }

    public void saveNote(String toString) {
        NoteEntity noteEntity = mLiveNote.getValue();
        if (noteEntity == null) {
            if (TextUtils.isEmpty(toString.trim())) {
                return;
            }
            noteEntity = new NoteEntity(new Date(), toString.trim());
        } else {
            noteEntity.setText(toString.trim());
        }

        mRepository.insertNote(noteEntity);
    }
}
