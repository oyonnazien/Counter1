package com.example.yanpat01.counter;
// package com.example.user.counter;

        import android.annotation.SuppressLint;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.res.Configuration;
        import android.graphics.drawable.Drawable;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.os.CountDownTimer;
        import android.os.Handler;
        import android.support.annotation.RequiresApi;
        import android.support.v4.content.ContextCompat;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.content.Context;
        import android.app.Activity;
        import android.graphics.Color;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.concurrent.TimeUnit;

        import static android.content.ContentValues.TAG;
        import static com.example.yanpat01.counter.R.drawable.white_button;

public class MainActivity extends Activity implements OnClickListener {

    Button btn1;
    Button btn2;
    Button btn1sqr;
    Button btn2sqr;
    Button btnSet1;
    Button btnSet2;
    Button btnReset;
    Button btnMenu;
    Button btnUndo;
    Button btnSwitch;
    ImageButton btnTO1_1;
    ImageButton btnTO1_2;
    ImageButton btnTO2_1;
    ImageButton btnTO2_2;
    TextView textTOView;
    // teams used for the display
    TypeTeam teamA = new TypeTeam();
    TypeTeam teamB = new TypeTeam();

    TypeGame ActualGame = new TypeGame();

    SharedPreferences activityPreferences;
    private static final String TAG = "MainActivity";

    // Teams used for the parameters

    // Super variable that tracks when the team switch sides; 0 is the default
    int revertTeamAand1 = 0;
    // Multiple variables for the general volleyball setup
    int MaxTO=2;
    Integer TimeOutSec = 3;
    Integer TechTimeOutSec = 5;
    Integer firstTechnicalTOValue = 8;
    Integer secondTechnicalTOValue = 16;
    Integer allowTechnicalTO = 1;
    Integer firstTechnicalTOSeen = 0;
    Integer secondTechnicalTOSeen = 0;
    Integer maxPointsSet = 25;
    Integer maxPointsLastSet = 15;
    Integer maxSets = 5;
    Integer setNow = 1;

    int toto = 0;
    int toto2 = 0;

    TextView textTeam1;
    TextView textTeam2;
    // For the delays
    private Handler mHandler = new Handler();
    public static final String EXTRA_MESSAGE = "com.example.counter.MESSAGE";
    public static final String SCORE_1 = "com.example.counter.SCORE1";
    public static final String NBTOS = "com.example.counter.NBTOS";

//    TextView scoreText;
    int counter1 = 0;
    int counter2 = 0;
    ArrayList<Integer> History = new ArrayList<Integer>();
    ArrayList<Integer> Colors = new ArrayList<Integer>();
    ArrayList<Integer> ColorsText = new ArrayList<Integer>();

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        Log.d(TAG, "onSaveInstanceState: Started.");
        savedInstanceState.putInt(EXTRA_MESSAGE, Integer.parseInt(btn2.getText().toString()));
        savedInstanceState.putInt(SCORE_1, Integer.parseInt(btn1.getText().toString()));
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: Started.");
        // Restore state members from saved instance
        toto = savedInstanceState.getInt(EXTRA_MESSAGE);
        toto2 = savedInstanceState.getInt(SCORE_1);
        btn2.setText(Integer.toString(toto));
        btn1.setText(Integer.toString(toto2));
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Main activity onRestart.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Main activity onPause.");
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        activityPreferences = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
    }
*/
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null)
        {
            Log.d(TAG, "Main activity onCreate savedInstanceState is not null.");
            // Retrieve activity instance state data.
            //String email = savedInstanceState.getString(USER_INPUTTED_EMAIL);
            // Set the original email data in EditText view component.
            //emailInputBoxEditText.setText(email);
        }else
        {
            Log.d(TAG, "Main activity onCreate savedInstanceState is null.");
        }
        ActualGame.initiateAsie();

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Started.");
        // Check whether we're recreating a previously destroyed instance
        setContentView(R.layout.activity_main);
        // Probably initialize members with default values for a new instance
        btn1 = (Button) findViewById(R.id.score1Button);
        btn2 = (Button) findViewById(R.id.score2Button);
        btn1sqr = (Button) findViewById(R.id.square1Button);
        btn2sqr = (Button) findViewById(R.id.square2Button);
        btnSet1 = (Button) findViewById(R.id.set1Button);
        btnSet2 = (Button) findViewById(R.id.set2Button);
        btnReset = (Button) findViewById(R.id.resetButton);
        btnUndo = (Button) findViewById(R.id.undoButton);
        btnSwitch = (Button) findViewById(R.id.switchButton);
        btnMenu = (Button) findViewById(R.id.menuButton);
        textTeam1 = (TextView) findViewById(R.id.team1Title);
        textTeam2 = (TextView) findViewById(R.id.team2Title);
        btnTO1_1 = (ImageButton) findViewById(R.id.TO1_1Button);
        btnTO1_2 = (ImageButton) findViewById(R.id.TO1_2Button);
        btnTO2_1 = (ImageButton) findViewById(R.id.TO2_1Button);
        btnTO2_2 = (ImageButton) findViewById(R.id.TO2_2Button);
        textTOView = (TextView) findViewById(R.id.TOView);
        //scoreText = (TextView)findViewById(R.id.editText);
        //textTitle = (TextView)findViewById(R.id.myTextTitle);
        Colors.add(R.color.colorDarkBlue);
        Colors.add(R.color.colorOrange);
        Colors.add(R.color.colorRed);
        Colors.add(R.color.colorWhite);
        Colors.add(R.color.colorYellow);
        Colors.add(R.color.colorBlack);
        Colors.add(R.color.colorGrey);
        Colors.add(R.color.colorGreen);
        Colors.add(R.color.colorLightBlue);
        ColorsText.add(R.color.colorDarkBlueText);
        ColorsText.add(R.color.colorOrangeText);
        ColorsText.add(R.color.colorRedText);
        ColorsText.add(R.color.colorWhiteText);
        ColorsText.add(R.color.colorYellowText);
        ColorsText.add(R.color.colorBlackText);
        ColorsText.add(R.color.colorGreyText);
        ColorsText.add(R.color.colorGreenText);
        ColorsText.add(R.color.colorLightBlueText);

//—set on click listeners on the buttons—–
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        btn1sqr.setOnClickListener(this);
        btn2sqr.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        textTeam1.setOnClickListener(this);
        textTeam2.setOnClickListener(this);
        btnTO1_1.setOnClickListener(this);
        btnTO1_2.setOnClickListener(this);
        btnTO2_1.setOnClickListener(this);
        btnTO2_2.setOnClickListener(this);
        btnSwitch.setOnClickListener(this);

// change font size of the text
//       textTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
// Need to initiate the TeamA and TeamB
        teamA.setEqName("Team A");
        teamA.setBgColor(0);
        teamB.setEqName("Team B");
        teamB.setBgColor(1);
        btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(teamA.getBgColor())));
        btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(teamB.getBgColor())));
        textTeam1.setTextColor(getResources().getColor(ColorsText.get(teamA.getBgColor())));
        textTeam2.setTextColor(getResources().getColor(ColorsText.get(teamB.getBgColor())));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        final Context context = this;
        btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
        btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));

        int isAWin = 0;
        int maxPointsSetNow = maxPointsSet;
        if (setNow == maxSets) {
            maxPointsSetNow = maxPointsLastSet;
        }
        if (v == btn1) {
            if (revertTeamAand1==1) {
                teamB.incScore();
                btn1.setText(Integer.toString(teamB.getScore()));
            } else {
                teamA.incScore();
                btn1.setText(Integer.toString(teamA.getScore()));
            }
            boolean errorAdd = History.add(1);
            counter1++;
//            scoreText.setText(Integer.toString(counter1));
            // Context context = this;
            btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
            btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
            // btn2.setBackgroundColor(Color.WHITE);

            isAWin = testWin(teamA, teamB, maxPointsSetNow, revertTeamAand1, History);

        }
        if (v == btn2) {
            if (revertTeamAand1==0) {
                teamB.incScore();
                btn2.setText(Integer.toString(teamB.getScore()));
            } else {
                teamA.incScore();
                btn2.setText(Integer.toString(teamA.getScore()));
            }
            boolean errorAdd = History.add(2);
//            scoreText.setText(Integer.toString(counter1));
            counter2++;
//            scoreText.setText(Integer.toString(counter2));
            // Context context = this;
            btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
            btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
            isAWin = testWin(teamA, teamB, maxPointsSetNow, revertTeamAand1, History);
        }
        if (v == btn1sqr) {
            // Show the dialog for the color choice
            final Integer[] lastColorBt1 = new Integer[1];
            final Integer[] lastColorTextBt1 = new Integer[1];
            lastColorBt1[0] = teamA.getBgColor();
            lastColorTextBt1[0] = teamA.getTxtColor();
            if (revertTeamAand1==1) {
                lastColorBt1[0] = teamB.getBgColor();
                lastColorTextBt1[0] = teamB.getTxtColor();
            }
            final Integer[] nextColorBt1 = new Integer[1];
            // final Context context = this;
            final Dialog dialogcolor = new Dialog(context);
            dialogcolor.setContentView(R.layout.colorchoice);
            Button Color0 = (Button)dialogcolor.findViewById(R.id.button0);
            Button Color1 = (Button)dialogcolor.findViewById(R.id.button1);
            Button Color2 = (Button)dialogcolor.findViewById(R.id.button2);
            Button Color3 = (Button)dialogcolor.findViewById(R.id.button3);
            Button Color4 = (Button)dialogcolor.findViewById(R.id.button4);
            Button Color5 = (Button)dialogcolor.findViewById(R.id.button5);
            Button Color6 = (Button)dialogcolor.findViewById(R.id.button6);
            Button Color7 = (Button)dialogcolor.findViewById(R.id.button7);
            Button Color8 = (Button)dialogcolor.findViewById(R.id.button8);
            Button ColorOK = (Button)dialogcolor.findViewById(R.id.buttonColorOK);
            Button ColorCancel = (Button)dialogcolor.findViewById(R.id.buttonColorCancel);
            Color0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 0;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 1;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 2;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 3;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 4;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 5;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 6;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 7;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 8;
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam1.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            ColorOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Need to write the last color in the team color and close the popup
                    if (revertTeamAand1==0) {
                        teamA.setBgColor(nextColorBt1[0]);
                    } else {
                        teamB.setBgColor(nextColorBt1[0]);
                    }
                    dialogcolor.dismiss();
                }
            });
            ColorCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Need to revert any change in the trials
                    if (revertTeamAand1==0) {
                        teamA.setBgColor(lastColorBt1[0]);
                    } else {
                        teamB.setBgColor(lastColorBt1[0]);
                    }
                    btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(lastColorBt1[0])));
                    dialogcolor.dismiss();
                }
            });
            dialogcolor.show();
        }

        if (v == btn2sqr) {
            // Show the dialog for the color choice
            final Integer[] lastColorBt1 = new Integer[1];
            final Integer[] lastColorTextBt1 = new Integer[1];
            lastColorTextBt1[0] = teamA.getTxtColor();
            lastColorBt1[0] = teamA.getBgColor();
            if (revertTeamAand1==0) {
                lastColorBt1[0] = teamB.getBgColor();
                lastColorTextBt1[0] = teamB.getTxtColor();
            }
            final Integer[] nextColorBt1 = new Integer[1];
            // final Context context = this;
            final Dialog dialogcolor = new Dialog(context);
            dialogcolor.setContentView(R.layout.colorchoice);
            Button Color0 = (Button)dialogcolor.findViewById(R.id.button0);
            Button Color1 = (Button)dialogcolor.findViewById(R.id.button1);
            Button Color2 = (Button)dialogcolor.findViewById(R.id.button2);
            Button Color3 = (Button)dialogcolor.findViewById(R.id.button3);
            Button Color4 = (Button)dialogcolor.findViewById(R.id.button4);
            Button Color5 = (Button)dialogcolor.findViewById(R.id.button5);
            Button Color6 = (Button)dialogcolor.findViewById(R.id.button6);
            Button Color7 = (Button)dialogcolor.findViewById(R.id.button7);
            Button Color8 = (Button)dialogcolor.findViewById(R.id.button8);
            Button ColorOK = (Button)dialogcolor.findViewById(R.id.buttonColorOK);
            Button ColorCancel = (Button)dialogcolor.findViewById(R.id.buttonColorCancel);
            Color0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 0;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 1;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 2;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 3;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 4;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 5;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 6;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 7;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            Color8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempCol = 8;
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(tempCol)));
                    textTeam2.setTextColor(getResources().getColor(ColorsText.get(tempCol)));
                    nextColorBt1[0]=tempCol;
                }
            });
            ColorOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Need to write the last color in the team color and close the popup
                    if (revertTeamAand1==1) {
                        teamA.setBgColor(nextColorBt1[0]);
                        teamA.setTxtColor(nextColorBt1[0]);
                    } else {
                        teamB.setBgColor(nextColorBt1[0]);
                        teamB.setTxtColor(nextColorBt1[0]);
                    }
                    dialogcolor.dismiss();
                }
            });
            ColorCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Need to revert any change in the trials
                    if (revertTeamAand1==0) {
                        teamA.setBgColor(lastColorBt1[0]);
                        teamA.setTxtColor(lastColorBt1[0]);
                    } else {
                        teamB.setBgColor(lastColorBt1[0]);
                        teamB.setTxtColor(lastColorBt1[0]);
                    }
                    btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(lastColorBt1[0])));
                    dialogcolor.dismiss();
                }
            });
            dialogcolor.show();
        }

        if (v == btnMenu) {
            // Need to open the menu
            String message = btn1.getText().toString();
            Intent intent = new Intent(MainActivity.this, menu2counter1.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            // extras.putSerializable("tag",mObject)
            // intent.putExtra(NBTOS, ActualGame);
            startActivity(intent);
        }

        if (v == btnReset) {
            // final Context context = this;
            final Dialog dialog = new Dialog(context);

            // final Context context = this;
            new AlertDialog.Builder(context)
                    .setTitle(R.string.resetT)
                    .setMessage(R.string.resetQ)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // continue with delete

                            counter1 = 0;
                            counter2 = 0;
                            History.clear();
                            teamA.resetfull();
                            teamB.resetfull();
                            revertTeamAand1 = 0;
                            btn1.setText(Integer.toString(counter1));
                            btn2.setText(Integer.toString(counter2));
                            teamA.setEqName("Team A");
                            teamB.setEqName("Team B");
                            teamA.setBgColor(0);
                            teamB.setBgColor(1);
                            teamA.setSetWon(0);
                            teamB.setSetWon(0);
                            // scoreText.setBackgroundColor(Color.RED);
                            btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
                            btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
                            btnTO1_1.setImageResource(R.mipmap.time_out);
                            btnTO1_2.setImageResource(R.mipmap.time_out);
                            btnTO2_1.setImageResource(R.mipmap.time_out);
                            btnTO2_2.setImageResource(R.mipmap.time_out);
                            firstTechnicalTOSeen=0;
                            secondTechnicalTOSeen=0;
                            displayBothTeam(teamA,teamB,revertTeamAand1);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // do nothing
                            if (History != null && !History.isEmpty()) {
                                if (History.get(History.size()-1)==1) {
                                    btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
                                } else {
                                    btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
                                }
                            }
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        if (v == btnUndo) {
            // Undo: Need to remove one element, need to manage the switch sides

/*
            final Context context = this;
            final Dialog dialog = new Dialog(context);
            new AlertDialog.Builder(context)
                    .setTitle("Undo debug")
                    .setMessage("List of previous commands "+History)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
*/
            if (History != null && !History.isEmpty()) {
                Integer item = History.get(History.size()-1);
                History.remove(History.size()-1);

                if (item==0) {
                    // switch sides
                    revertTeamAand1 = 1-revertTeamAand1;
                    // Need to redisplay score, time outs
                    // displayBothTeam(teamA, teamB, revertTeamAand1);
                } else {
                    if (item==1) {

                        if (revertTeamAand1==0) {
                            teamA.decScore();
                        } else {
                            teamB.decScore();
                        }
                    }
                    if (item==2) {
                        if (revertTeamAand1==1) {
                            teamA.decScore();
                        } else {
                            teamB.decScore();
                        }
                    }
                }
                displayBothTeam(teamA, teamB, revertTeamAand1);
                int i = 1;
                while ((History.size()-i)>=0) {
                    // retrieves last team scoring
                    Integer lastColor = History.size()-i;
                    if (History.get(lastColor)==0) {
                        i++;
                    } else {
                        // Context context = this;
                        if (((History.get(lastColor) + i) % 2) == 0)  {
                            btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
                        } else {
                            btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
                        }
                        break;
                    }
                }
            }
        }
        if (v == textTeam1) {
            // rename the Team A
            // final Context context = this;
            final Dialog dialog;
            final EditText editText;
            dialog = new AlertDialog.Builder(this).create();
            editText = new EditText(this);
            // editText.setText(textTeam1.getText());
            String teamRen;
            if (revertTeamAand1==1) {
                teamRen = "B";
            } else {
                teamRen = "A";
            }

            String totoU= (String) getResources().getText(R.string.teamu);
            String totoQ= (String) getResources().getText(R.string.renameQ);
            String titleTeam = String.format("%s %s",totoU, teamRen);
            String msgTeam = String.format("%s %s",totoQ, teamRen);
            new AlertDialog.Builder(context)
                    .setTitle(titleTeam)
                    .setMessage(msgTeam+"\n")
                    .setView(editText)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // writes the text in the text
                            String teamName = editText.getText().toString();
                            textTeam1.setText(teamName);
                            if (revertTeamAand1==1) {
                                teamB.setEqName(teamName);
                            } else {
                                teamA.setEqName(teamName);
                            }
                        }
                    })
                    //.editText.setText(textView.getText())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            // dialog.setContentView(editText);
        }

        if (v == textTeam2) {
            // rename the Team B
            // final Context context = this;
            final Dialog dialog;
            final EditText editText;
            dialog = new AlertDialog.Builder(this).create();
            editText = new EditText(this);
            // editText.setText(textTeam1.getText());
            String teamRen;
            if (revertTeamAand1==0) {
                teamRen = "B";
            } else {
                teamRen = "A";
            }
            String totoU= (String) getResources().getText(R.string.teamu);
            String totoQ= (String) getResources().getText(R.string.renameQ);
            String titleTeam = String.format("%s %s",totoU, teamRen);
            String msgTeam = String.format("%s %s",totoQ, teamRen);
            new AlertDialog.Builder(context)
                    .setTitle(titleTeam)
                    .setMessage(msgTeam+"\n")
                    .setView(editText)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // writes the text in the text
                            String teamName = editText.getText().toString();
                            textTeam2.setText(teamName);
                            if (revertTeamAand1==0) {
                                teamB.setEqName(teamName);
                            } else {
                                teamA.setEqName(teamName);
                            }
                        }
                    })
                    //.editText.setText(textView.getText())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            // dialog.setContentView(editText);
        }
        if (v == btnTO1_1) {
            // TimeOut on left side
            if (revertTeamAand1==1) {
                runsTO(teamB,btnTO1_1,btnTO1_2);
            }
            if (revertTeamAand1==0) {
                runsTO(teamA,btnTO1_1,btnTO1_2);
            }
        }
        if (v == btnTO1_2) {
            // TimeOut on left side
            if (revertTeamAand1==1) {
                runsTO(teamB,btnTO1_1,btnTO1_2);
            }
            if (revertTeamAand1==0) {
                runsTO(teamA,btnTO1_1,btnTO1_2);
            }
        }
        if (v == btnTO2_1) {
            // TimeOut on left side
            if (revertTeamAand1==0) {
                runsTO(teamB,btnTO2_1,btnTO2_2);
            }
            if (revertTeamAand1==1) {
                runsTO(teamA,btnTO2_1,btnTO2_2);
            }
        }
        if (v == btnTO2_2) {
            // TimeOut on left side
            if (revertTeamAand1==0) {
                runsTO(teamB,btnTO2_1,btnTO2_2);
            }
            if (revertTeamAand1==1) {
                runsTO(teamA,btnTO2_1,btnTO2_2);
            }
        }
        if (v == btnSwitch) {
            // We switch teams sides
            revertTeamAand1 = 1-revertTeamAand1;
            // Need to redisplay score, time outs
            displayBothTeam(teamA, teamB, revertTeamAand1);
            boolean errorAdd = History.add(0);
            // Context context = this;
            if (findLastPoint(History, revertTeamAand1)==1) {
                btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
                btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
            } else if (findLastPoint(History, revertTeamAand1)==2) {
                btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.scorenumber));
                btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
            } else {
                btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
                btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
            }
        }

    }

    public int testWin(TypeTeam team1, TypeTeam team2, int MaxSet, int revert, ArrayList Hist) {
        // Returns 1 when there is a win for team1, 2 for team2 0 otherwise
        int score1 = team1.getScore();
        int score2 = team2.getScore();
        if (score1 >= MaxSet) {
            if (score1 - score2 >= 2) {
                btn1.setBackgroundColor(Color.BLUE);
                if (revert ==1) {
                    btn2.setBackgroundColor(Color.BLUE);
                } else {
                    btn1.setBackgroundColor(Color.BLUE);
                }
                scoreEvolution(Hist);
                firstTechnicalTOSeen=0;
                secondTechnicalTOSeen=0;
                return 1;
            }
        }
        if (score2 >= MaxSet) {
            if (score2 - score1 >= 2) {
                if (revert !=1) {
                    btn2.setBackgroundColor(Color.BLUE);
                } else {
                    btn1.setBackgroundColor(Color.BLUE);
                }
                scoreEvolution(Hist);
                firstTechnicalTOSeen=0;
                secondTechnicalTOSeen=0;

                return 2;
            }
        }
        if ((((score1==firstTechnicalTOValue) || (score2==firstTechnicalTOValue)) && (firstTechnicalTOSeen==0)) && (allowTechnicalTO==1)) {
            // First time we see the technical time out
            // Need to launch the counter
            reverseTimer(TechTimeOutSec,textTOView);
            firstTechnicalTOSeen=1;
        }
        if ((((score1==secondTechnicalTOValue) || (score2==secondTechnicalTOValue)) && (secondTechnicalTOSeen==0)) && (allowTechnicalTO==1)) {
            // First time we see the technical time out
            // Need to launch the counter
            reverseTimer(TechTimeOutSec,textTOView);
            secondTechnicalTOSeen=1;
        }
        return 0;
    }

    public void changeTOColor(ImageButton button) {
        // This changes the color of the TO after a certain time
        button.setImageResource(R.mipmap.time_out_red);
    }

    public int scoreEvolution(ArrayList<Integer> H) {
        // Returns The evolution of the score when needed
        Integer lastV = H.get(1);
        Integer tempCount;
        Integer lastCounter1 =0;
        Integer lastCounter2 =0;
        String evolution = "";
/*
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
*/
        final Context context = this;
        final Dialog dialog = new Dialog(context);

        new AlertDialog.Builder(context)
                .setTitle(R.string.wonSetT)
                // .setMessage("Here is how the score has evolved \n"+evolution)
                .setPositiveButton(R.string.newSet, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // continue with delete
                        counter1 = 0;
                        counter2 = 0;
                        if (((revertTeamAand1==1) && (History.get(History.size()-1)==1)) || ((revertTeamAand1==0) && (History.get(History.size()-1)==2))) {
                            teamB.wonSet();
                            teamA.reset();
                        } else {
                            teamA.wonSet();
                            teamB.reset();
                        }
                        History.clear();
                        revertTeamAand1 = 1 - revertTeamAand1;
                        displayBothTeam(teamA, teamB, revertTeamAand1);
                        btn1.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));
                        btn2.setBackground(ContextCompat.getDrawable(context,R.drawable.white_button));

                    }
                })
                .setNegativeButton(R.string.resetButton, new DialogInterface.OnClickListener() {
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
    }
    public void reverseTimer(int Seconds, final TextView tv) {

        new CountDownTimer(Seconds * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                btn1.setClickable(false);
                btn2.setClickable(false);
                btn1sqr.setClickable(false);
                btn2sqr.setClickable(false);
                btnReset.setClickable(false);
                btnSwitch.setClickable(false);
                btnTO1_1.setClickable(false);
                btnTO1_2.setClickable(false);
                btnTO2_1.setClickable(false);
                btnTO2_2.setClickable(false);
                int seconds = (int) (millisUntilFinished / 1000);
                //int hours = seconds / (60 * 60);
                //int tempMint = (seconds - (hours * 60 * 60));
                int tempMint = (seconds);
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);
                String toto= (String) getResources().getText(R.string.timeOutT);
                String toBeDisplayedTimer = String.format("%s ",toto) + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds);
                tv.setText(toBeDisplayedTimer);
            }
            public void onFinish() {
                tv.setText("");
                btn1.setClickable(true);
                btn2.setClickable(true);
                btn1sqr.setClickable(true);
                btn2sqr.setClickable(true);
                btnReset.setClickable(true);
                btnSwitch.setClickable(true);
                btnTO1_1.setClickable(true);
                btnTO1_2.setClickable(true);
                btnTO2_1.setClickable(true);
                btnTO2_2.setClickable(true);
            }
        }.start();

    }
    public void displayOneTeam(TypeTeam team, int side) {
        // Will provide data to the right side 1, or 2
        if (side == 1) {
            btn1.setText(Integer.toString(team.getScore()));
            textTeam1.setText(team.getEqName());
            textTeam1.setTextColor(getResources().getColor(ColorsText.get(team.getBgColor())));
            btnSet1.setText(Integer.toString(team.getSetWon()));
            btn1sqr.setBackgroundColor(getResources().getColor(Colors.get(team.getBgColor())));

            // Next will be the display of sets, and time-outs
            if (team.getTimeOutTaken()==2) {
                btnTO1_1.setImageResource(R.mipmap.time_out_red);
                btnTO1_2.setImageResource(R.mipmap.time_out_red);
            } else if (team.getTimeOutTaken()==1) {
                btnTO1_1.setImageResource(R.mipmap.time_out_red);
                btnTO1_2.setImageResource(R.mipmap.time_out);
            } else if (team.getTimeOutTaken()==0) {
                btnTO1_1.setImageResource(R.mipmap.time_out);
                btnTO1_2.setImageResource(R.mipmap.time_out);
            }
        }
        if (side == 2) {
            btn2.setText(Integer.toString(team.getScore()));
            textTeam2.setText(team.getEqName());
            textTeam2.setTextColor(getResources().getColor(ColorsText.get(team.getBgColor())));
            btnSet2.setText(Integer.toString(team.getSetWon()));
            // Next will be the display of sets, and time-outs
            btn2sqr.setBackgroundColor(getResources().getColor(Colors.get(team.getBgColor())));
            if (team.getTimeOutTaken()==2) {
                btnTO2_1.setImageResource(R.mipmap.time_out_red);
                btnTO2_2.setImageResource(R.mipmap.time_out_red);
            } else if (team.getTimeOutTaken()==1) {
                btnTO2_1.setImageResource(R.mipmap.time_out_red);
                btnTO2_2.setImageResource(R.mipmap.time_out);
            } else if (team.getTimeOutTaken()==0) {
                btnTO2_1.setImageResource(R.mipmap.time_out);
                btnTO2_2.setImageResource(R.mipmap.time_out);
            }
        }
    }
    public void displayBothTeam(TypeTeam team1, TypeTeam team2, int switching) {
        // Will provide data to the right side
        displayOneTeam(team1, 1+switching);
        displayOneTeam(team2, 2-switching);
    }
    public int findLastPoint(ArrayList history, int revert) {
        // This function will provide the side of the last scorer, 1 on the left, 2 on the right
        int i=1;
        int temp_revert = revert;
        int temp_revert2;
        int result = 0;
        Integer lastColor = 0;
        while ((History.size()-i)>=0) {
            // retrieves last team scoring
            lastColor = History.size()-i;
            if (History.get(lastColor)==0) {
                i++;
                temp_revert = 1 - temp_revert;
            } else {
                if (((History.get(lastColor) == 1) && (temp_revert!=revert)) || ((History.get(lastColor) == 2) && (temp_revert==revert))) {
                    //btn2.setBackgroundColor(Color.GREEN);
                    result = 2;
                }
                if (((History.get(lastColor) == 1) && (temp_revert==revert)) || ((History.get(lastColor) == 2) && (temp_revert!=revert))) {
                    //btn1.setBackgroundColor(Color.GREEN);
                    result = 1;
                }
                break;
            }
        }

/*
        final Context context = this;
        final Dialog dialog = new Dialog(context);
        new AlertDialog.Builder(context)
                .setTitle("findLastPoint debug")
                .setMessage("List of previous commands "+history+" result= "+result+" revert ="+revert+" temp_revert ="+temp_revert + " lastColor:" + lastColor+" revertTeamAand1:"+revertTeamAand1)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

*/
        return result;
    }
    public int getCount( ArrayList<Integer> listT, int elem ) {
        int result =0;
        for (Integer elt: listT) {
            if( elt == elem ) {
                result++;
            }
        }
        return result;
    }
    public void runsTO(TypeTeam team, final ImageButton TO1, final ImageButton TO2) {
        // Function that will launch and count the time out
        // Need to check how many timeouts have been taken before
        if (team.getTimeOutTaken()==0) {
            // first time out on the side
            TO1.setImageResource(R.mipmap.time_out_orange);
            reverseTimer(TimeOutSec,textTOView);
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    changeTOColor(TO1);
                }
            }, TimeOutSec *1000);
        }
        if (team.getTimeOutTaken()==1) {
            // first time out on the side
            TO2.setImageResource(R.mipmap.time_out_orange);
            reverseTimer(TimeOutSec,textTOView);
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    changeTOColor(TO2);
                }
            }, TimeOutSec *1000);
        }
        team.incTimeOut();
    }
//    public void setSilentMode(Button btn1) {
//        btn1.
//    }

    public void undoTimeOut(ArrayList<Integer> History, Integer isTOlive, Integer revert,
                            final TextView tv) {
        // This function is able to correct the timeout when undo is done
        // This will stop the countdown, and/or report the right amount
        // of timeouts for a team or in a set
        if (isTOlive!=0) {
            // Need to stop the countdown, 1 for left team, 2 for the right and 3 for a score
            tv.setText("");
        }
    }
    /** Called when the user taps the Send button */
    public void sendMessage(Button bbButton) {
        Intent intent = new Intent(this, menu2counter1.class);
        Button editText = (Button) bbButton;
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, 20);
    }
}