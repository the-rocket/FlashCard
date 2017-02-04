package daniyar.com.savewords.FlashCards;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import daniyar.com.savewords.BaseActivity;
import daniyar.com.savewords.R;
import daniyar.com.savewords.data.CardsDataSource;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * Created by yernar on 22/01/17.
 */

public class FlashCardActivity extends BaseActivity {

    FlashCardFragment cardFragmentFront, cardFragmentBack;

    public final static String KEY_TEXT_FRONT = "KEY_TEXT_FRONT";
    public final static String KEY_TEXT_BACK = "KEY_TEXT_BACK";


    FloatingActionButton fab;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        init();
    }

    private void init() {
        cardFragmentFront = new FlashCardFragment(1);
        cardFragmentBack = new FlashCardFragment(2);
        Log.d("okay", "good");
        initView();
    }

    private void initView() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(KEY_TEXT_FRONT, FlashCardFragment.data.getString(KEY_TEXT_FRONT));
                intent.putExtra(KEY_TEXT_BACK, FlashCardFragment.data.getString(KEY_TEXT_BACK));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        try {
            getSupportFragmentManager().beginTransaction().add(R.id.front, cardFragmentFront).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.back, cardFragmentBack).commit();
            flip(flag, false);
            flag = !flag;
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            flip(flag, false);
            flag = !flag;
        }
        return true;
    }

    private void flip(final boolean ltr, boolean isImmediate) {
        View layoutContentContainer = findViewById(R.id.root);
        final View frontContentView = cardFragmentFront.getView();
        final View backContentView = cardFragmentBack.getView();

        if (isImmediate) {
            frontContentView.setVisibility(ltr ? VISIBLE : GONE);
            backContentView.setVisibility(ltr ? GONE : VISIBLE);
        } else {

            int duration = 600;

            FlipAnimator flipAnimator = new FlipAnimator(frontContentView, backContentView, frontContentView.getWidth() / 2, backContentView.getHeight() / 2);
            flipAnimator.setInterpolator(new OvershootInterpolator(0.5f));
            flipAnimator.setDuration(duration);

            if (ltr) {
                flipAnimator.reverse();
            }

            flipAnimator.setTranslateDirection(FlipAnimator.DIRECTION_Z);
            flipAnimator.setRotationDirection(FlipAnimator.DIRECTION_Y);

            layoutContentContainer.startAnimation(flipAnimator);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.flashcard_main;
    }

    @Override
    protected int getMenu() {
        return R.menu.menu_flashcard;
    }

    @Override
    protected String getAppName() {
        return "Create Card";
    }

    @Override
    protected CardsDataSource getCardsDataSource() {
        return null;
    }
}
