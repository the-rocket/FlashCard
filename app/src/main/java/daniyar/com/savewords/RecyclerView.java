package daniyar.com.savewords;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import java.util.ArrayList;

import daniyar.com.savewords.FlashCards.FlipAnimator;
import daniyar.com.savewords.data.Card;

import static daniyar.com.savewords.R.layout.flashcard;


/**
 * Created by yernar on 01/02/17.
 */

class RecyclerViewCardAdapter extends RecyclerView.Adapter<RecyclerViewCardAdapter.ViewHolder> {

    private ArrayList<Pair<String, String>> myArray;
    private ArrayList<Card> myArrayData;
    private Context context;

    private FlipAnimator flipAnimator;

    RecyclerViewCardAdapter(Context context) {
        this.context = context;
        myArray = new ArrayList<>();
        myArrayData = new ArrayList<>();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.cardtextview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View front) {
            execute(getAdapterPosition(), front);
        }

        void execute(int position, final View CardFrontView) {
            final View LayoutAnimation = CardFrontView;
            final View CardBackView = CardFrontView;

            ((TextView) CardBackView.findViewById(R.id.cardtextview)).setText(myArrayData.get(position).getRussian());

            flipAnimator = new FlipAnimator(CardFrontView, CardBackView, CardFrontView.getWidth() / 2, CardBackView.getHeight() / 2);

            initFlipAnimation();

            LayoutAnimation.startAnimation(flipAnimator);
        }

        private void initFlipAnimation() {
            flipAnimator.setInterpolator(new OvershootInterpolator(0.5f));
            flipAnimator.setTranslateDirection(FlipAnimator.DIRECTION_Z);
            flipAnimator.setRotationDirection(FlipAnimator.DIRECTION_Y);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(flashcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(myArray.get(position).first);
    }

    void addAll(ArrayList<Pair<String, String>> array, ArrayList<Card> data) {
        myArray.addAll(array);
        myArrayData.addAll(data);
    }

    void removeAll() {
        myArray.clear();
    }

    @Override
    public int getItemCount() {
        return myArray.size() - 1;
    }
}
