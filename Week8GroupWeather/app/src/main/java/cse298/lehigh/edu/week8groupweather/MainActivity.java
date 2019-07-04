package cse298.lehigh.edu.week8groupweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    EditText editText;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        b1 = (Button)findViewById(R.id.button);

        //initilization
        preferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);






    }

    public void clicked(View v){
        //add data
        String place  = editText.getText().toString();
        preferences.edit().putString("place", place).apply();

        Toast.makeText(MainActivity.this,"Thank you for entering",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);

    }


}


