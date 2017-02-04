package daniyar.com.savewords.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.Pair;
import android.util.Log;

import java.util.ArrayList;

import daniyar.com.savewords.data.CardReaderContract.CardEntry;



/**
 * Created by yernar on 30/01/17.
 */

public class CardsDataSource {
    private SQLiteDatabase database;
    private DBOpenHelper dbHelper;

    public CardsDataSource(Context context) {
        dbHelper = new DBOpenHelper(context);
    }

    public void insert(String English, String Russian) {
        ContentValues values = new ContentValues();
        values.put(CardEntry.COLUMN_WORD_ENGLISH, English);
        values.put(CardEntry.COLUMN_WORD_RUSSIAN, Russian);
        values.put(CardEntry.COLUMN_WORD_ATTEMPS, 0);
        values.put(CardEntry.COLUMN_WORD_CORRECT, 0);
        values.put(CardEntry.COLUMN_WORD_FAILURE, 0);
        database.insert(CardEntry.TABLE_NAME, null, values);

    }
    public Cursor getData(int id) {
        return database.rawQuery( "select * from CARD_TABLE where id="+id+"", null );
    }
    public boolean deleteData(int id) {
        return database.delete(CardEntry.TABLE_NAME, CardEntry._ID + "=" + id, null) > 0;
    }

    public void readDataOnLog() {
        Cursor cursor =  database.rawQuery("select * from " + CardEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        Log.d("connecting with data", "true");
        while (!cursor.isAfterLast()) {
            Card card = new Card();
            card.setEnglish(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_WORD_ENGLISH)));
            card.setRussian(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_WORD_RUSSIAN)));
            Log.d("reading data: english", card.getEnglish() + " russian" + card.getRussian());
            cursor.moveToNext();
        }
    }

    public ArrayList<Pair<String, String>> getAllCards() {
        ArrayList<Pair<String, String>> arrayCards = new ArrayList<>();
        Cursor cursor =  database.rawQuery("select * from " + CardEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Card card = new Card();
            card.setEnglish(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_WORD_ENGLISH)));
            card.setRussian(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_WORD_RUSSIAN)));
            arrayCards.add(new Pair<>(card.getEnglish(), card.getRussian()));
            cursor.moveToNext();
        }
        return arrayCards;

    }
    public ArrayList<Card> getAllData() {
        ArrayList<Card> arrayCards = new ArrayList<>();
        Cursor cursor =  database.rawQuery("select * from " + CardEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Card card = new Card();
            card.setEnglish(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_WORD_ENGLISH)));
            card.setRussian(cursor.getString(cursor.getColumnIndex(CardEntry.COLUMN_WORD_RUSSIAN)));
            card.setAttemps(cursor.getInt(cursor.getColumnIndex(CardEntry.COLUMN_WORD_ATTEMPS)));
            card.setCorrect(cursor.getInt(cursor.getColumnIndex(CardEntry.COLUMN_WORD_CORRECT)));
            card.setFailure(cursor.getInt(cursor.getColumnIndex(CardEntry.COLUMN_WORD_FAILURE)));
            arrayCards.add(card);
            cursor.moveToNext();
        }
        return arrayCards;

    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        database.close();
    }


}
