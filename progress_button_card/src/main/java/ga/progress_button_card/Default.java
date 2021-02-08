
        package ga.progress_button_card;

        import android.content.Context;
        import android.content.res.TypedArray;
        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.graphics.Typeface;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.AttributeSet;
        import android.util.DisplayMetrics;
        import android.view.Gravity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.FrameLayout;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.cardview.widget.CardView;
        import androidx.databinding.BindingAdapter;
        import androidx.databinding.InverseBindingAdapter;
        import androidx.databinding.InverseBindingListener;

        import java.util.Arrays;


public class Default extends CardView {
    boolean loading = false;
    LinearLayout linearLayout;
    TextView textView;
    ProgressBar progressBar;
    String PBCText;
    float PBCTextSize;
    int PBCTextStyle;
    String PBCTextColor;
    float PBCRadius;


    public String getPBCText() {
        return PBCText;
    }

    public void setPBCText(String PBCText) {
        this.PBCText = PBCText;
    }

    private static final int[] PBC_TEXT = {R.attr.PBC_Text};
    private static final int[] PBC_TEXT_COLOR = {R.attr.PBC_TextColor};
    private static final int[] PBC_TEXT_STYLE = {R.attr.PBC_TextStyle};
    private static final int[] PBC_RADIUS = {R.attr.PBC_Radius};
    private static final int[] PBC_TEXT_SIZE = {R.attr.PBC_TextSize};

    public Default(Context context) {

        super(context);

    }

    @BindingAdapter("PBC_Text")
    public static void setPbcText(Default view, CharSequence value) {
        view.textView.setText(value);
    }

    @InverseBindingAdapter(attribute = "PBC_Text")
    public static CharSequence getPbcText(Default view) {
        return view.textView.getText();
    }


    @BindingAdapter(value = "textAttrChanged")
    public static void setListener(Default btn, final InverseBindingListener listener) {
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

    public Default(Context context, @Nullable AttributeSet attrs) {
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
        TypedArray ta = context.obtainStyledAttributes(attrs,  R.styleable.Default, 0, 0);
        try {
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            PBCRadius = ta.getDimension(R.styleable.Default_PBC_Radius,0);

            PBCText = ta.getString(R.styleable.Default_PBC_Text);
            PBCTextColor = ta.getString(R.styleable.Default_PBC_TextColor);
            PBCTextStyle = ta.getInt(R.styleable.Default_PBC_TextStyle,0);
            PBCTextSize = ta.getInt(R.styleable.Default_PBC_TextSize,16);

            textView.setText(PBCText);
            textView.setTextSize(PBCTextSize);

            Float scale  = getResources().getDisplayMetrics().density;
            LinearLayout.LayoutParams pbLayoutParams = new LinearLayout.LayoutParams((int)(PBCTextSize+(PBCTextSize*(scale == 1.0?1:scale*1.5))), ViewGroup.LayoutParams.MATCH_PARENT);
            pbLayoutParams.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER;
            progressBar.setLayoutParams(pbLayoutParams);

            textView.setPadding((int)PBCTextSize/4,(int)PBCTextSize/4,(int)PBCTextSize/4,(int)PBCTextSize/4);

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



            //set highlight color depend on background tint
            if(getBackgroundTintList() != null){
                if(isWhite(getBackgroundTintList().getColorForState(new int[]{android.R.attr.state_enabled},Color.parseColor("#ffffff")))){
                    linearLayout.setBackground(getResources().getDrawable(R.drawable.ripple_dark));
                }
                else{
                    linearLayout.setBackground(getResources().getDrawable(R.drawable.ripple_light));

                }
            }
            else{
                if(getCardBackgroundColor() != null){
                    if(isWhite(getCardBackgroundColor().getColorForState(new int[]{android.R.attr.state_enabled},Color.parseColor("#ffffff")))){
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.ripple_dark));
                    }
                    else{
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.ripple_light));

                    }
                }
                else{
                    linearLayout.setBackground(getResources().getDrawable(R.drawable.ripple_dark));
                }


            }



            textView.setTextColor(PBCTextColor == null ? Color.BLACK : Color.parseColor(PBCTextColor));

            progressBar.getIndeterminateDrawable()
                    .setColorFilter(PBCTextColor == null ? Color.BLACK : Color.parseColor(PBCTextColor), PorterDuff.Mode.SRC_IN );

            setRadius(PBCRadius);
            refreshDrawableState();

        } finally {
            ta.recycle();
        }


    }

    public Default(Context context, @Nullable AttributeSet attrs, int defStyle){

        super(context, attrs, defStyle);

    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        refreshDrawableState();

    }

    public void Dispose(){

        setOnTouchListener(null);
        setOnClickListener(null);

    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 5);
        mergeDrawableStates(drawableState, PBC_TEXT);
        mergeDrawableStates(drawableState, PBC_TEXT_COLOR);
        mergeDrawableStates(drawableState, PBC_TEXT_STYLE);
        mergeDrawableStates(drawableState, PBC_TEXT_SIZE);
        mergeDrawableStates(drawableState, PBC_RADIUS);
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

    //if color is white or not
    public boolean isWhite(int color){
        double rgb = (Color.red(color) + Color.green(color) + Color.blue(color));
        if(rgb > 700){
            return true;
        }else{
            return false;
        }
    }
}

