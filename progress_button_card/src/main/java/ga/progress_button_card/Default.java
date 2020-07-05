package ga.progress_button_card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.MotionEventCompat;


public class Default extends CardView implements View.OnTouchListener, View.OnClickListener {
    boolean loading = false;
    LinearLayout ll;
    String cardText;
    float cardTextSize;
    String textStyle;
    String cardTextColor;
    TextView tw;
    ProgressBar pb;

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

    private static final int[] CARD_TEXT = {R.attr.cardButtonText};
    private static final int[] CARD_TEXT_COLOR = {R.attr.cardButtonTextColor};
    private static final int[] CARD_TEXT_STYLE = {R.attr.cardTextStyle};

    public Default(Context context) {

        super(context);

    }


    public Default(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        setOnTouchListener(this);

        this.setClickable(true);

        CardView.LayoutParams cwParams = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(cwParams);
        addView(ll);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;

        tw = new TextView(context);
        tw.setGravity(Gravity.CENTER);
        tw.setLayoutParams(params);

        tw.setIncludeFontPadding(false);
        ll.addView(tw);
        pb = new ProgressBar(context);
        pb.setVisibility(GONE);
        pb.setLayoutParams(params);

        ll.addView(pb);

        int[] attrsArray = new int[] {
                android.R.attr.layout_height,
                R.attr.cardButtonText,
                R.attr.cardButtonTextColor,
                R.attr.cardTextStyle
        };

        TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray, 0, 0);
        try {

            int tmpSize = ta.getLayoutDimension(0, 1);

            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();

            int dp = Math.round(tmpSize / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

            pb.setPadding(dp / 2,dp / 2,dp / 2,dp / 2);
            tw.setPadding(dp / 2,dp / 2,dp / 2,dp / 2);

            cardTextSize = (dp / 2) - (dp / 8);
            cardText = ta.getString(1);
            cardTextColor = ta.getString(2);
            textStyle = ta.getString(3);

            tw.setText(cardText);
            tw.setTextSize(cardTextSize);

            if(textStyle != null){
                switch (textStyle.toLowerCase()){
                    case "bold":
                        tw.setTypeface(tw.getTypeface(),Typeface.BOLD);
                        break;
                    case "italic":
                        tw.setTypeface(tw.getTypeface(),Typeface.ITALIC);
                        break;
                    default:
                        tw.setTypeface(tw.getTypeface(),Typeface.NORMAL);
                        break;
                }
            }


            tw.setTextColor(cardTextColor == null ? Color.BLACK : Color.parseColor(cardTextColor));
            pb.getIndeterminateDrawable()
                    .setColorFilter(cardTextColor == null ? Color.BLACK : Color.parseColor(cardTextColor), PorterDuff.Mode.SRC_IN );

        } finally {
            ta.recycle();
        }


    }

    public Default(Context context, @Nullable AttributeSet attrs, int defStyle){

        super(context, attrs, defStyle);

    }

    public void onDestroy(){

        setOnTouchListener(null);
        setOnClickListener(null);

    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 3);
        mergeDrawableStates(drawableState, CARD_TEXT);
        mergeDrawableStates(drawableState, CARD_TEXT_COLOR);
        mergeDrawableStates(drawableState, CARD_TEXT_STYLE);
        return drawableState;
    }

    public void loading(){
        loading = true;
        this.tw.setVisibility(GONE);
        this.pb.setVisibility(VISIBLE);
        this.setClickable(false);
        refreshDrawableState();
    }

    public void notLoading(){

        loading = false;
        this.tw.setVisibility(VISIBLE);
        this.pb.setVisibility(GONE);
        this.setClickable(true);
        refreshDrawableState();
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!loading){


            int action = MotionEventCompat.getActionMasked(event);

            if(action == MotionEvent.ACTION_DOWN) {
                v.setAlpha(1.0f);
                v.animate()
                        .alpha(0.5f)
                        .setDuration(500)
                        .setListener(null);
            }
            else if(action == MotionEvent.ACTION_UP){
                v.setAlpha(0.5f);
                v.animate()
                        .alpha(1.0f)
                        .setDuration(500)
                        .setListener(null);
            }

        }

        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
