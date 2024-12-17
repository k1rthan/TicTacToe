package com.example.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean playerOneActive;
    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button reset, playAgain;
    private int playerOneScoreCount, playerTwoScoreCount;
    private int rounds;
    // Initialize player name TextViews properly

    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView playerOneName = findViewById(R.id.text_player1);
        TextView playerTwoName = findViewById(R.id.text_player2);

        // Retrieve the player names from the Intent
        Intent intent = getIntent();
        String playerOne = intent.getStringExtra("playerOne");
        String playerTwo = intent.getStringExtra("playerTwo");

        // Set the retrieved names to the TextViews
        if (playerOne != null && playerTwo != null){
            playerOneName.setText(playerOne);
            playerTwoName.setText(playerTwo);
        }

        playerOneScore = findViewById(R.id.score_Player1);
        playerTwoScore = findViewById(R.id.score_Player2);
        playerStatus = findViewById(R.id.textStatus);
        reset = findViewById(R.id.Reset);
        playAgain = findViewById(R.id.playAgain);

        buttons[0] = findViewById(R.id.btn1);
        buttons[1] = findViewById(R.id.btn2);
        buttons[2] = findViewById(R.id.btn3);
        buttons[3] = findViewById(R.id.btn4);
        buttons[4] = findViewById(R.id.btn5);
        buttons[5] = findViewById(R.id.btn6);
        buttons[6] = findViewById(R.id.btn7);
        buttons[7] = findViewById(R.id.btn8);
        buttons[8] = findViewById(R.id.btn9);

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        rounds = 0;

        reset.setOnClickListener(v -> {
            playAgain();
            playerOneScoreCount = 0;
            playerTwoScoreCount = 0;
            updatePlayerScore();
        });

        playAgain.setOnClickListener(v -> playAgain());
    }


    @Override
    public void onClick(View view) {

        Intent intent = getIntent();
        String playerOne = intent.getStringExtra("playerOne");
        String playerTwo = intent.getStringExtra("playerTwo");

        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1)) - 1;

        if (playerOneActive) {
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#40E0D0"));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#ffbb00"));
            gameState[gameStatePointer] = 1;
        }
        rounds++;

        if (checkWinner()) {
            if (playerOneActive) {
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText(playerOne+" has won!");
            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText(playerTwo+" has won!");
            }
            disableButtons();
        } else if (rounds == 9) {
            playerStatus.setText("It's a draw!");
        } else {
            playerOneActive = !playerOneActive;
        }
    }

    private boolean checkWinner() {
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                return true;
            }
        }
        return false;
    }

    private void enableButtons() {
        for (Button button : buttons) {
            button.setEnabled(true);
        }
    }

    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
        playerStatus.setText("Game On!");
        enableButtons(); // Re-enable the buttons
    }


    private void updatePlayerScore() {
        playerOneScore.setText(String.valueOf(playerOneScoreCount));
        playerTwoScore.setText(String.valueOf(playerTwoScoreCount));
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }
}
