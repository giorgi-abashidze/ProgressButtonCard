# ProgressButtonCard
Progress Button Based on CardView

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

### Make sure you have android material components installed or it will not work

### Properties:
"Button" is based on a CardView, you can use every property that
CardView have, for example cardCornerRadius, cardElevation, backgroundTint and more.

* **cardButtonText** provides the text of the button
* **cardTextStyle** provides the style of the text. possible values is: **bold, normal, italic** default is normal
* **cardButtonTextColor** provides te color of the button text **! This property working only with hex color strings like: #ffffff**

#### Text size is depending on button height you dont need to set the text height manually.

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

