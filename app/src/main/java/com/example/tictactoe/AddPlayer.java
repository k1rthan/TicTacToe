package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_player);

        final EditText playerOne = findViewById(R.id.player1EditText);
        final EditText playerTwo = findViewById(R.id.player2EditText);
        final Button GameStartBtn = findViewById(R.id.GameStart);

        GameStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getplayerOneName = playerOne.getText().toString();
                final String getplayerTwoName = playerTwo.getText().toString();

                if(getplayerOneName.isEmpty() || getplayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayer.this,"Please Enter player Names", Toast.LENGTH_SHORT).show();
                }
                else{

                    Intent intent = new Intent(AddPlayer.this, MainActivity.class);
                    intent.putExtra("playerOne", getplayerOneName);
                    intent.putExtra("playerTwo", getplayerTwoName);
                    startActivity(intent);
                }
            }
        });

    }
}