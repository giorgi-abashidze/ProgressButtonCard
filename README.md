# ProgressButtonCard
Progress Button Based on CardView

![](progresscardbutton.gif)

### Install (Gradle)
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency
```
dependencies {
	implementation 'com.github.giorgi-abashidze:ProgressButtonCard:1.0'
}
```

### Properties:
"Button" is based on a CardView, you can use every property that
CardView have, for example **cardCornerRadius**, **cardElevation** and more.

* **cardButtonText** provides the text of the button
* **cardTextStyle** provides the style of the text. possible values is: **bold, normal, italic** default is normal
* **cardButtonTextColor** provides te color of the button text **! This property working only with hex color strings like: #ffffff**

#### Text size is depending on button height you dont need to set the text size manually.

### Example in XML:
```
<ga.progress_button_card.Default
        android:id="@+id/progress_button_card"
        app:cardElevation="3dp"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:backgroundTint="@android:color/holo_blue_light"
        app:cardButtonText="Click me"
        app:cardTextStyle="normal"
        app:cardCornerRadius="10dp"
        app:cardButtonTextColor="#ffffff"
        />
```
### You can switch loading and not loading modes with functions: loading() and notLoading()
### Example:
```
ga.progress_button_card.Default button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.progress_button_card);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.loading();

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.notLoading();
                    }
                }, 5000);
            }
        });


    }
```

### ! important: Don't add onTouch listener to the button, onTouch is used inside the button to provide touch highlight.
