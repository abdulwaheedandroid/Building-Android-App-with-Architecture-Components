package com.saw_initiators.buildingandroidappwitharchitecturecomponents;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.AppDatabase;
import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.NoteDao;
import com.saw_initiators.buildingandroidappwitharchitecturecomponents.database.NoteEntity;
import com.saw_initiators.buildingandroidappwitharchitecturecomponents.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private static final String TAG = "DatabaseTest";
    private AppDatabase mDataBase;
    private NoteDao noteDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDataBase = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();

        noteDao = mDataBase.noteDao();
        Log.d(TAG, "createDb: ");
    }

    @After
    public void closeDB(){
        mDataBase.close();
        Log.d(TAG, "closeDB: ");
    }

    @Test
    public void createAndRetriveNotes() {
        noteDao.insertNoteAll(SampleData.getNotes());
        int count = noteDao.getCount();
        Log.d(TAG, "createAndRetriveNotes: count = " + count);
        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings() {
        noteDao.insertNoteAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDB = noteDao.getNoteById(1);
        assertEquals(original.getText(), fromDB.getText());
        assertEquals(1, fromDB.getId());
    }


}
