package daniyar.com.savewords;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import daniyar.com.savewords.FlashCards.FlashCardActivity;
import daniyar.com.savewords.data.CardsDataSource;



public class MainActivity extends BaseActivity {

    static final int request_code = 200;
    Intent intent;
    CardsDataSource db;
    //@BindView(R.id.fab) FloatingActionButton fab;
    FloatingActionButton fab;
    RecyclerViewCardAdapter recyclerCardView = new RecyclerViewCardAdapter(MainActivity.this);
    RecyclerView CardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        CardList = (RecyclerView) findViewById(R.id.CardRecyclerView);
        db = new CardsDataSource(MainActivity.this);
        db.open();
        initView();
    }

    void initView() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, FlashCardActivity.class);
                startActivityForResult(intent, request_code);
            }
        });

        CardList.setAdapter(recyclerCardView);
        CardList.setHasFixedSize(true);
        CardList.setLayoutManager(new LinearLayoutManager(this));
        update();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case request_code:
                    db.insert(data.getExtras().getString(FlashCardActivity.KEY_TEXT_FRONT), data.getExtras().getString(FlashCardActivity.KEY_TEXT_BACK));
                    db.readDataOnLog();
            }
        }
        update();
    }

    protected void update() {
        recyclerCardView.removeAll();
        recyclerCardView.addAll(db.getAllCards(), db.getAllData());
        recyclerCardView.notifyDataSetChanged();
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    protected int getMenu() {
        return R.menu.menu_main;
    }

    @Override
    protected Context getContext() {
        return MainActivity.this;
    }

    protected CardsDataSource getCardsDataSource() {
        return db;
    }

    @Override
    protected FloatingActionButton getfab() {
        return fab;
    }

    protected String getAppName() {
        return "Words";
    }
}