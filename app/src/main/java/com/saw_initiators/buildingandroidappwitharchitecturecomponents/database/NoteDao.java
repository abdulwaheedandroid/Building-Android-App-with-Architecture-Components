package com.saw_initiators.buildingandroidappwitharchitecturecomponents.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNoteAll(List<NoteEntity> notes);

    @Delete
    void delete(NoteEntity entity);

    @Query("SELECT * FROM notes WHERE id = :id")
    NoteEntity getNoteById(int id);

    @Query("SELECT * FROM  notes ORDER BY date DESC")
    LiveData<List<NoteEntity>> getAll();

    @Query("DELETE FROM notes")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();
}
