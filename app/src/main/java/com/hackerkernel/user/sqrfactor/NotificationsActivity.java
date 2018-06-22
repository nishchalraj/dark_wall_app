package com.hackerkernel.user.sqrfactor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

public class NotificationsActivity extends AppCompatActivity {

    TextView text, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        text = (TextView)findViewById(R.id.text);
        text2 = (TextView)findViewById(R.id.text2);

        String s = getApplicationContext().getResources().getString(R.string.noti_string);
        text.setText(Html.fromHtml(s));
        text2.setText(Html.fromHtml(s));

    }
}
