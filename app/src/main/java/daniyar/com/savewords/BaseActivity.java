package daniyar.com.savewords;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniyar.com.savewords.FlashCards.FlashCardActivity;
import daniyar.com.savewords.FlashCards.FlashCardFragment;
import daniyar.com.savewords.data.CardsDataSource;

import static daniyar.com.savewords.FlashCards.FlashCardActivity.KEY_TEXT_BACK;
import static daniyar.com.savewords.FlashCards.FlashCardActivity.KEY_TEXT_FRONT;
import static daniyar.com.savewords.MainActivity.request_code;

/**
 * Created by yernar on 22/01/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getAppName());
        }
        initfab();
    }

    void initfab() {
        if (getAppName() == "CreateCard") {
            getfab().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra(KEY_TEXT_FRONT, FlashCardFragment.data.getString(KEY_TEXT_FRONT));
                    intent.putExtra(KEY_TEXT_BACK, FlashCardFragment.data.getString(KEY_TEXT_BACK));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
        if (getAppName() == "Words") {
            getfab().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent = new Intent(getContext(), FlashCardActivity.class);
                    startActivityForResult(intent, request_code);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenu(), menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getAppName().equals("Words")) {
            getCardsDataSource().close();
        }
    }

    protected abstract
    @LayoutRes
    int getLayout();

    protected abstract
    @MenuRes
    int getMenu();

    protected abstract Context getContext();

    protected abstract String getAppName();

    protected abstract CardsDataSource getCardsDataSource();

    protected abstract FloatingActionButton getfab();
}
