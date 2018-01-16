package com.example.android.waterpolo;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * saves Name of both Teams
     */

    static final String lastTeamNameA = "Name1";
    static final String lastTeamNameB = "Name2";
    /**
     * saves Score of both Teams
     */
    static final String lastScoreTeamA = "ScoreTeamA";
    static final String lastScoreTeamB = "ScoreTeamB";
    public int counterA;
    public int counterB;
    int teamAScore = 0;
    int teamBScore = 0;
    int TeamNameA = 0;
    int TeamNameB = 0;
    Dialog ThisDialog;
    TextView textViewA;
    TextView textViewB;

    /**
     * save current score
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(lastScoreTeamA, teamAScore);
        savedInstanceState.putInt(lastScoreTeamB, teamBScore);

        savedInstanceState.putInt(lastTeamNameA, TeamNameA);
        savedInstanceState.putInt(lastTeamNameB, TeamNameB);

        super.onSaveInstanceState(savedInstanceState);

    }

    /**
     * restore the score
     */

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            teamAScore = savedInstanceState.getInt(lastScoreTeamA);
            teamBScore = savedInstanceState.getInt(lastScoreTeamB);

            TeamNameA = savedInstanceState.getInt(lastTeamNameA);
            TeamNameB = savedInstanceState.getInt(lastTeamNameB);

            displayForTeamA(TeamNameA);
            displayForTeamB(TeamNameB);


            scoreForTeamA(teamAScore);
            scoreForTeamB(teamBScore);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Team A 20sec timer
     */


    public void StartTeamA(View v) {
        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewA = findViewById(R.id.timerTeamA20sec);
                textViewA.setText(String.valueOf(counterA));
                counterA++;
            }

            public void onFinish() {
                textViewA.setText("FINISH!!");
            }
        }.start();
    }

    /**
     * Team B 20sec timer
     */


    public void StartTeamB(View v) {
        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewB = findViewById(R.id.timerTeamB20sec);
                textViewB.setText(String.valueOf(counterB));
                counterB++;
            }

            public void onFinish() {
                textViewB.setText("FINISH!!");
            }
        }.start();
    }


    /**
     * dialog
     */
    /**
     * button to get dialog
     */
    public void addTeamNames(View v) {
        ThisDialog = new Dialog(MainActivity.this);
        ThisDialog.setTitle("Title");
        ThisDialog.setContentView(R.layout.dialog_team);

        final EditText TeamA = ThisDialog.findViewById(R.id.addTeamNameA);
        final EditText TeamA20sec = ThisDialog.findViewById(R.id.addTeamNameA);
        final EditText TeamB = ThisDialog.findViewById(R.id.addTeamNameB);
        final EditText TeamB20sec = ThisDialog.findViewById(R.id.addTeamNameB);

        Button saveDialog = ThisDialog.findViewById(R.id.saveDialog);


        TeamA.setEnabled(true);
        TeamA20sec.setEnabled(true);
        TeamB.setEnabled(true);
        TeamB20sec.setEnabled(true);
        saveDialog.setEnabled(true);

        ThisDialog.show();

    }

    /**
     * save's name + add name's to TextView
     */


    public void saveDialog(View v) {
        EditText addTeamA = ThisDialog.findViewById(R.id.addTeamNameA);
        EditText addTeamB = ThisDialog.findViewById(R.id.addTeamNameB);

        SharedPrefTeamA(addTeamA.getText().toString());
        SharedPrefTeamB(addTeamB.getText().toString());

        TextView TeamA = findViewById(R.id.TeamAEdit);
        TextView TeamA20sec = findViewById(R.id.TeamA20sec);
        TextView TeamB = findViewById(R.id.TeamBEdit);
        TextView TeamB20sec = findViewById(R.id.TeamB20sec);

        SharedPreferences TeamName = getApplicationContext().getSharedPreferences("NAME", 0);
        TeamA.setText(TeamName.getString("TeamA", null));
        TeamA20sec.setText(TeamName.getString("TeamA", null));
        TeamB.setText(TeamName.getString("TeamB", null));
        TeamB20sec.setText(TeamName.getString("TeamB", null));

        displayForTeamB(TeamNameB);
        displayForTeamA(TeamNameA);

        ThisDialog.cancel();


    }

    /**
     * end Dialog
     */


    /**
     * this method counts +1 to score
     */
    public void add_team_B(View v) {
        teamBScore = teamBScore + 1;
        scoreForTeamB(teamBScore);
    }

    public void add_team_A(View v) {
        teamAScore = teamAScore + 1;
        scoreForTeamA(teamAScore);
    }

    /**
     * this method counts -1 to score
     */
    public void remove_team_B(View v) {
        teamBScore = teamBScore - 1;
        scoreForTeamB(teamBScore);
    }

    public void remove_team_A(View v) {
        teamAScore = teamAScore - 1;
        scoreForTeamA(teamAScore);
    }


    /**
     * this method give's name team A
     */
    public void displayForTeamA(int TeamNameA) {
        TextView TeamA = findViewById(R.id.TeamAEdit);
        TextView TeamA20sec = findViewById(R.id.TeamA20sec);
        TeamA.setText(String.valueOf(TeamNameA));
        TeamA20sec.setText(String.valueOf(TeamNameA));
        SharedPreferences NameView = getApplicationContext().getSharedPreferences("NAME", 0);
        TeamA.setText(NameView.getString("TeamA", null));
        TeamA20sec.setText(NameView.getString("TeamA", null));


    }


    public void SharedPrefTeamA(String TeamA) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("NAME", 0);
        SharedPreferences.Editor prefEDIT = prefs.edit();
        prefEDIT.putString("TeamA", TeamA);
        prefEDIT.commit();
    }

    /**
     * this method give's score team A
     */
    public void scoreForTeamA(int score) {
        TextView scoreView = findViewById(R.id.scoreTeamA);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * TeamA.setText(String.valueOf(TeamNamA));
     * this method give's Name team b
     */
    public void displayForTeamB(int TeamNameB) {
        TextView TeamB = findViewById(R.id.TeamBEdit);
        TextView TeamB20sec = findViewById(R.id.TeamB20sec);

        TeamB.setText(String.valueOf(TeamNameB));
        TeamB20sec.setText(String.valueOf(TeamNameB));

        SharedPreferences NameView = getApplicationContext().getSharedPreferences("NAME", 0);
        TeamB.setText(NameView.getString("TeamB", null));
        TeamB20sec.setText(NameView.getString("TeamB", null));


    }

    public void SharedPrefTeamB(String TeamB) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("NAME", 0);
        SharedPreferences.Editor prefEDIT = prefs.edit();
        prefEDIT.putString("TeamB", TeamB);
        prefEDIT.commit();
    }

    /**
     * this method give's score team b
     */
    public void scoreForTeamB(int score) {
        TextView scoreView = findViewById(R.id.scoreTeamB);
        scoreView.setText(String.valueOf(score));

    }
}


