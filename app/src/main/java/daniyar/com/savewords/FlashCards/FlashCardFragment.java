package daniyar.com.savewords.FlashCards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import daniyar.com.savewords.R;

import static daniyar.com.savewords.FlashCards.FlashCardActivity.KEY_TEXT_BACK;
import static daniyar.com.savewords.FlashCards.FlashCardActivity.KEY_TEXT_FRONT;

public class FlashCardFragment extends Fragment {
    private int index;
    //@BindView(R.id.WordID) EditText wordID;
    EditText wordID;
    View view;

    static Bundle data = new Bundle();

    FlashCardFragment(int index) {
        this.index = index;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void init() {
        wordID = (EditText) view.findViewById(R.id.WordID);

        initView();
    }

    private void initView() {

        wordID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (index == 1) {
                    data.putString(KEY_TEXT_FRONT, String.valueOf(charSequence));
                } else {
                    data.putString(KEY_TEXT_BACK, String.valueOf(charSequence));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (index == 1) {
            view = inflater.inflate(R.layout.flashcard_fragment_front, container, false);
        } else {
            view = inflater.inflate(R.layout.flashcard_fragment_back, container, false);
        }

        init();

        return view;
    }
}