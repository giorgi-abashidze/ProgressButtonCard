package ga.progress_button_card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import java.util.Arrays;


public class Gradient extends CardView{
    boolean loading = false;
    LinearLayout linearLayout;
    TextView textView;
    ProgressBar progressBar;
    String PBCText;
    float PBCTextSize;
    int PBCTextStyle;
    String PBCTextColor;
    String PBCStartColor;
    String PBCEndColor;
    float PBCRadius;
    int PBCGradientOrientation;

    private static final int[] PBC_TEXT = {R.attr.PBC_Text};
    private static final int[] PBC_TEXT_COLOR = {R.attr.PBC_TextColor};
    private static final int[] PBC_TEXT_STYLE = {R.attr.PBC_TextStyle};
    private static final int[] PBC_TEXT_SIZE = {R.attr.PBC_TextSize};
    private static final int[] PBC_RADIUS = {R.attr.PBC_Radius};
    private static final int[] PBC_START_COLOR = {R.attr.PBC_StartColor};
    private static final int[] PBC_END_COLOR = {R.attr.PBC_EndColor};
    private static final int[] PBC_GRADIENT_ORIENTATION = {R.attr.PBC_Gradient_Orientation};


    public Gradient(Context context) {

        super(context);

    }

    public String getPBCText() {
        return PBCText;
    }

    public void setPBCText(String PBCText) {
        this.PBCText = PBCText;
    }

    @BindingAdapter("PBC_Text")
    public static void setText(Gradient view, String value) {
        view.textView.setText(value);
    }

    @InverseBindingAdapter(attribute = "PBC_Text")
    public static CharSequence getText(Gradient view) {
        return view.textView.getText();
    }

    @BindingAdapter(value = "textAttrChanged")
    public static void setListener(Gradient btn, final InverseBindingListener listener) {
        if (listener != null) {
            btn.textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }


    public Gradient(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.setClickable(true);

        CardView.LayoutParams cwParams = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(cwParams);
        addView(linearLayout);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;

        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);

        textView.setIncludeFontPadding(false);
        linearLayout.addView(textView);
        progressBar = new ProgressBar(context);
        progressBar.setVisibility(GONE);
        progressBar.setLayoutParams(params);

        linearLayout.addView(progressBar);

        int[] attrsArray = new int[] {
                android.R.attr.layout_height,
        };


        //get custom attributes
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Gradient, 0, 0);
        try {


            PBCRadius = ta.getDimension(R.styleable.Gradient_PBC_Radius,0);
            PBCStartColor = ta.getString(R.styleable.Gradient_PBC_StartColor);
            PBCEndColor = ta.getString(R.styleable.Gradient_PBC_EndColor);
            PBCGradientOrientation = ta.getInt(R.styleable.Gradient_PBC_Gradient_Orientation,0);
            PBCText = ta.getString(R.styleable.Gradient_PBC_Text);
            PBCTextColor = ta.getString(R.styleable.Gradient_PBC_TextColor);
            PBCTextSize = ta.getInt(R.styleable.Gradient_PBC_TextSize,16);
            PBCTextStyle = ta.getInt(R.styleable.Gradient_PBC_TextStyle,0);

            textView.setText(PBCText);
            textView.setTextSize(PBCTextSize);
            progressBar.setPadding(PBCTextSize/4);
            textView.setPadding(PBCTextSize/4);


                switch (PBCTextStyle){
                    case 1:
                        textView.setTypeface(textView.getTypeface(),Typeface.BOLD);
                        break;
                    case 2:
                        textView.setTypeface(textView.getTypeface(),Typeface.ITALIC);
                        break;
                    default:
                        textView.setTypeface(textView.getTypeface(),Typeface.NORMAL);
                        break;
                }


            setForeground(getResources().getDrawable(R.drawable.ripple_light));

            textView.setTextColor(PBCTextColor == null ? Color.BLACK : Color.parseColor(PBCTextColor));

            progressBar.getIndeterminateDrawable()
                    .setColorFilter(PBCTextColor == null ? Color.BLACK : Color.parseColor(PBCTextColor), PorterDuff.Mode.SRC_IN );

            if (PBCStartColor != null && PBCEndColor != null) {


                GradientDrawable.Orientation orientaion = GradientDrawable.Orientation.TOP_BOTTOM;

                switch (PBCGradientOrientation){
                    case 1:
                        orientaion = GradientDrawable.Orientation.LEFT_RIGHT;
                        break;
                    case 2:
                        orientaion = GradientDrawable.Orientation.BL_TR;
                        break;
                    case 3:
                        orientaion = GradientDrawable.Orientation.TL_BR;
                }

                GradientDrawable gd = new GradientDrawable(
                        orientaion,
                        new int[] {Color.parseColor(PBCStartColor),Color.parseColor(PBCEndColor)});
                gd.setCornerRadius(PBCRadius);
                linearLayout.setBackground(gd);
            }

            setRadius(PBCRadius);
            refreshDrawableState();
        } finally {
            ta.recycle();
        }


    }

    public Gradient(Context context, @Nullable AttributeSet attrs, int defStyle){

        super(context, attrs, defStyle);


    }

    public void Dispose(){

        setOnTouchListener(null);
        setOnClickListener(null);

    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 8);
        mergeDrawableStates(drawableState, PBC_TEXT);
        mergeDrawableStates(drawableState, PBC_TEXT_COLOR);
        mergeDrawableStates(drawableState, PBC_TEXT_STYLE);
        mergeDrawableStates(drawableState, PBC_RADIUS);
        mergeDrawableStates(drawableState, PBC_TEXT_SIZE);
        mergeDrawableStates(drawableState, PBC_START_COLOR);
        mergeDrawableStates(drawableState, PBC_END_COLOR);
        mergeDrawableStates(drawableState, PBC_GRADIENT_ORIENTATION);

        return drawableState;
    }

    public void loading(){
        loading = true;
        this.textView.setVisibility(GONE);
        this.progressBar.setVisibility(VISIBLE);
        this.setClickable(false);
        refreshDrawableState();
    }

    public void notLoading(){
        loading = false;
        this.textView.setVisibility(VISIBLE);
        this.progressBar.setVisibility(GONE);
        this.setClickable(true);
        refreshDrawableState();
    }



}
