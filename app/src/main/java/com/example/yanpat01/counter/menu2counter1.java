package com.example.yanpat01.counter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class menu2counter1 extends AppCompatActivity implements View.OnClickListener {

    CheckBox chkTTO;
    CheckBox chkTO;
    EditText MVTO_E;
    TextView MVTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2counter1);
        chkTTO = findViewById(R.id.MTTO_Button);
        chkTO = findViewById(R.id.MTO_Button);
        MVTO_E = findViewById(R.id.MVTO_EditText);
        MVTO = findViewById(R.id.MVTO_Text);

        chkTO.setOnClickListener(this);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textViewTest);
        textView.setText(message);
        disableTO();


        // chkTO.setOnCheckedChangeListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void onClick(View v) {
        final Context context = this;
        if (v == chkTO) {
            disableTO();
        }
    }
    public void disableTO() {
        if (!chkTO.isChecked()) {
            chkTTO.setEnabled(false);
            MVTO_E.setEnabled(false);
            MVTO.setEnabled(false);
        } else {
            chkTTO.setEnabled(true);
            MVTO_E.setEnabled(true);
            MVTO.setEnabled(true);
        }
    }

}
