package daniyar.com.savewords.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import daniyar.com.savewords.data.CardReaderContract.*;

/**
 * Created by yernar on 30/01/17.
 */

class DBOpenHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT ";
    private static final String INTEGER_TYPE = " INTEGER NOT NULL ";
    private static final String INTEGER_TYPE_AUTO = " INTEGER PRIMARY KEY AUTOINCREMENT ";


    private static final String COMMA_SEP = ", ";

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "cards.db";

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_ENTRIES = "CREATE TABLE " + CardEntry.TABLE_NAME +
                                          " (" + CardEntry._ID + INTEGER_TYPE_AUTO + COMMA_SEP +
                                          CardEntry.COLUMN_WORD_ENGLISH + TEXT_TYPE + COMMA_SEP +
                                          CardEntry.COLUMN_WORD_RUSSIAN + TEXT_TYPE + COMMA_SEP +
                                          CardEntry.COLUMN_WORD_ATTEMPS + INTEGER_TYPE + COMMA_SEP +
                                          CardEntry.COLUMN_WORD_CORRECT + INTEGER_TYPE + COMMA_SEP +
                                          CardEntry.COLUMN_WORD_FAILURE + INTEGER_TYPE +  " );";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CardEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
