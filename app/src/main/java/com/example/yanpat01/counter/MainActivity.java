package com.example.yanpat01.counter;
// package com.example.user.counter;

        import android.annotation.SuppressLint;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.content.Context;
        import android.app.Activity;
        import android.graphics.Color;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    TextView textTeam1;
    TextView textTeam2;
//    TextView scoreText;
    int counter1 = 0;
    int counter2 = 0;
    ArrayList<Integer> History = new ArrayList<Integer>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.addButton);
        btn2 = (Button) findViewById(R.id.substractButton);
        btn3 = (Button) findViewById(R.id.resetButton);
        btn4 = (Button) findViewById(R.id.undoButton);
        textTeam1 = (TextView) findViewById(R.id.team1Title);
        textTeam2 = (TextView) findViewById(R.id.team2Title);
        //scoreText = (TextView)findViewById(R.id.editText);
        //textTitle = (TextView)findViewById(R.id.myTextTitle);

//—set on click listeners on the buttons—–
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        textTeam1.setOnClickListener(this);
        textTeam2.setOnClickListener(this);

// change font size of the text
//        textTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
    }

    @Override
    public void onClick(View v) {
        btn1.setBackgroundColor(Color.WHITE);
        btn2.setBackgroundColor(Color.WHITE);

        int isAWin = 0;
        if (v == btn1) {
            counter1++;
//            scoreText.setText(Integer.toString(counter1));
            btn1.setText(Integer.toString(counter1));
            btn1.setBackgroundColor(Color.GREEN);
            btn2.setBackgroundColor(Color.WHITE);
            boolean errorAdd = History.add(1);
        }
        if (v == btn2) {
            counter2++;
//            scoreText.setText(Integer.toString(counter2));
            btn2.setText(Integer.toString(counter2));
            btn2.setBackgroundColor(Color.GREEN);
            btn1.setBackgroundColor(Color.WHITE);
            boolean errorAdd = History.add(2);
        }

        if (v == btn3) {
            final Context context = this;
            final Dialog dialog = new Dialog(context);

            new AlertDialog.Builder(context)
                    .setTitle("Confirm reset")
                    .setMessage("Are you sure you want to reset? ")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // continue with delete
                            counter1 = 0;
                            counter2 = 0;
                            textTeam1.setText("Team A");
                            textTeam1.setText("Team B");
                            History.clear();

                            btn1.setText(Integer.toString(counter1));
                            btn2.setText(Integer.toString(counter2));

                            // scoreText.setBackgroundColor(Color.RED);
                            btn1.setBackgroundColor(Color.WHITE);
                            btn2.setBackgroundColor(Color.WHITE);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // do nothing
                            if (History != null && !History.isEmpty()) {
                                if (History.get(History.size()-1)==1) {
                                    btn1.setBackgroundColor(Color.GREEN);
                                } else {
                                    btn2.setBackgroundColor(Color.GREEN);
                                }
                            }
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        if (v == btn4) {
            // Undo: Need to remove one element

            if (History != null && !History.isEmpty()) {
                Integer item = History.get(History.size()-1);
                History.remove(History.size()-1);

                if (item==1) {
                    counter1--;
                    btn1.setText(Integer.toString(counter1));
                } else {
                    counter2--;
                    btn2.setText(Integer.toString(counter2));
                }
                if (History.size()>=1) {
                    // retrieves last team scoring
                    Integer lastColor = History.size()-1;
                    if (History.get(lastColor)==1) {
                        btn1.setBackgroundColor(Color.GREEN);
                    } else {
                        btn2.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        }
        if (v == textTeam1) {
            // rename the Team A
            final Context context = this;
            final Dialog dialog;
            final EditText editText;
            dialog = new AlertDialog.Builder(this).create();
            editText = new EditText(this);
            // editText.setText(textTeam1.getText());

            new AlertDialog.Builder(context)
                    .setTitle("Rename TeamA")
                    .setMessage("What's the new name of TeamA \n")
                    .setView(editText)
                    .setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // writes the text in the text
                            textTeam1.setText(editText.getText());
                        }
                    })
                    //.editText.setText(textView.getText())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            // dialog.setContentView(editText);
        }
        if (v == textTeam2) {
            // rename the Team B
            final Context context = this;
            final Dialog dialog;
            final EditText editText;
            dialog = new AlertDialog.Builder(this).create();
            editText = new EditText(this);
            // editText.setText(textTeam1.getText());

            new AlertDialog.Builder(context)
                    .setTitle("Rename TeamB")
                    .setMessage("What's the new name of TeamB \n")
                    .setView(editText)
                    .setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // writes the text in the text
                            textTeam2.setText(editText.getText());
                        }
                    })
                    //.editText.setText(textView.getText())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            // dialog.setContentView(editText);
        }
        isAWin = testWin(counter1, counter2);
        if (isAWin !=0) {
            scoreEvolution(History);
        }
    }

    public int testWin(int score1, int score2) {
        // Returns 1 when there is a win for team1, 2 for team2 0 otherwise
        if (score1 >= 25) {
            if (score1 - score2 >= 2) {
                btn1.setBackgroundColor(Color.BLUE);
                return 1;
            }
        }
        if (score2 >= 25) {
            if (score2 - score1 >= 2) {
                btn2.setBackgroundColor(Color.BLUE);
                return 2;
            }
        }
        return 0;
    }
    public int scoreEvolution(ArrayList<Integer> H) {
        // Returns The evolution of the score when needed
        Integer lastV = H.get(1);
        Integer tempCount;
        Integer lastCounter1 =0;
        Integer lastCounter2 =0;
        String evolution = "";
        for (int i=0; i<=H.size()-1; i++) {
            if (H.get(i)==1) {
                lastCounter1++;
                if (i==0) {
                    evolution += lastCounter1 + "     \n";
                }
            } else {
                lastCounter2++;
                if (i==0) {
                    evolution += "       " + lastCounter2 + "\n";
                }
            }
            if ((lastV!=H.get(i)) || (i==H.size()-1)){
                if ((H.get(i)==1) && (i!=H.size()-1) || (H.get(i)!=1) && (i==H.size()-1)) {
                    evolution+="        |\n"+lastCounter1+"     "+lastCounter2+"\n";
                } else {
                    evolution+="|     \n"+lastCounter1+"     "+lastCounter2+"\n";
                }
                lastV=H.get(i);
            }
        }
        final Context context = this;
        final Dialog dialog = new Dialog(context);

        new AlertDialog.Builder(context)
                .setTitle("Score history")
                .setMessage("Here is how the score has evolved \n"+evolution)
                .setPositiveButton("Reset ?", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // continue with delete
                        counter1 = 0;
                        counter2 = 0;
                        textTeam1.setText("Team A");
                        textTeam1.setText("Team B");
                        History.clear();
                        btn1.setText(Integer.toString(counter1));
                        btn2.setText(Integer.toString(counter2));
                        // scoreText.setBackgroundColor(Color.RED);
                        btn1.setBackgroundColor(Color.WHITE);
                        btn2.setBackgroundColor(Color.WHITE);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        return 0;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }}
