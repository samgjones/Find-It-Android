package com.painlessshopping.mohamed.findit;

//Imports libraries
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;

import com.painlessshopping.mohamed.findit.viewmodel.LanguageHandler;
import com.painlessshopping.mohamed.findit.viewmodel.ThemeHandler;

import java.util.Locale;

/**
 * "Loading Screen" for the app
 *
 * Created by samuel on 22/11/16.
 */
public class OpeningScreen extends AppCompatActivity {


    private boolean firstLaunch;
    public static final String KEY_PREFS_NAME = "myPrefs";
    public static final String KEY_FIRST_LAUNCH = "firstLaunch";

    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(KEY_PREFS_NAME, MODE_PRIVATE);
        boolean firstLaunch = preferences.getBoolean(KEY_FIRST_LAUNCH, true);

        if(firstLaunch == true){
            SharedPreferences.Editor editor = getSharedPreferences(KEY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("language", "en");
            editor.putInt("theme", R.style.Default);
            editor.putBoolean(KEY_FIRST_LAUNCH, false);
            editor.commit();
        }


        LanguageHandler.setLang(preferences.getString("language", "en"));
        ThemeHandler.setTheme(preferences.getInt("theme", R.style.Default));


        setTheme(ThemeHandler.getTheme());
        super.onCreate(savedInstanceState);

        Locale locale = new Locale(LanguageHandler.getLang());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config,
                getResources().getDisplayMetrics());

        setContentView(R.layout.activity_opening_screen);



        //Set a 3s timer for the screen
        new CountDownTimer(3000, 1000){

            //Necessary method
            //Reports remaining time in console
            public void onTick(long millisUntilFinished){

                System.out.println("HomeScreen timer second remaining:" + millisUntilFinished / 1000);
            }

            //Once finished, open the home screen
            public void onFinish(){

                startActivity(new Intent(OpeningScreen.this, HomeScreen.class));
            }
        }.start();

    }
}
