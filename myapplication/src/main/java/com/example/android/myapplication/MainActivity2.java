package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    private void displayThemWords(String message) {
        TextView myWordsTextView = (TextView) findViewById(R.id.what_i_want);
        myWordsTextView.setText(message);
    }

    public void sayThis (View view) {
        displayThemWords("what up boo!");
    }

}
