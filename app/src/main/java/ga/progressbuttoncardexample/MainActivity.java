package ga.progressbuttoncardexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ga.progress_button_card.Default buttonDefault;
    ga.progress_button_card.Gradient buttonGradient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDefault = findViewById(R.id.progress_button_card);
        buttonGradient = findViewById(R.id.progress_button_card_gradient);

        buttonDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonDefault.loading();

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonDefault.notLoading();
                    }
                }, 5000);
            }
        });

        buttonGradient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonGradient.loading();

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonGradient.notLoading();
                    }
                }, 5000);
            }
        });




    }


}