package daniyar.com.savewords.data;

import android.provider.BaseColumns;

/**
 * Created by yernar on 30/01/17.
 */

public final class CardReaderContract {

    CardReaderContract() {

    }

    public static class CardEntry implements BaseColumns {
        public static final String TABLE_NAME = "CARD_TABLE";
        public static final String COLUMN_WORD_ENGLISH = "CARD_WORD_ENGLISH";
        public static final String COLUMN_WORD_RUSSIAN = "CARD_WORD_RUSSIAN";
        public static final String COLUMN_WORD_ATTEMPS = "CARD_WORD_ATTEMPS";
        public static final String COLUMN_WORD_CORRECT = "CARD_WORD_CORRECT";
        public static final String COLUMN_WORD_FAILURE = "CARD_WORD_FAILURE";
    }

}
