package game.languagelearning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import game.languagelearning.decorator.ButtonBlue;
import game.languagelearning.decorator.ButtonDecorator;
import game.languagelearning.decorator.ButtonGreen;
import game.languagelearning.decorator.ButtonRed;
import game.languagelearning.singleton.SingletonPoint;


public class MenuActivity extends Activity {
    private int winIndex=0;
    private int nativeLng = 0;
    private boolean[] checkedLng = new boolean[5];
    private int lngChecked=0;
    private int[] msg = {R.string.engChekedInEng, R.string.engUnChekedInEng,R.string.hebChekedInEng, R.string.hebUnChekedInEng, R.string.rusChekedInEng, R.string.rusUnChekedInEng};
    private int[] info = {R.string.noLngChekedEng, R.string.noLngChekedHeb, R.string.noLngChekedRus};
    private TextView labelName;
    private EditText fieldName;
    private TextView labelNative;
    private RadioButton nativeEng;
    private RadioButton nativeHeb;
    private RadioButton nativeRus;
    private TextView labelLearn;
    private CheckBox learnEng;
    private CheckBox learnEngToRead;
    private CheckBox learnHeb;
    private CheckBox learnHebToRead;
    private CheckBox learnRus;
    private ButtonDecorator btnStart;
    private ButtonDecorator btnSave;
    private ButtonDecorator btnLoad;
    private ButtonDecorator btnExit;
    private TextView labelLevel;
    private TextView fieldLevel;
    private TextView labelWord;
    private TextView fieldWord;
    private TextView s0;
    private TextView s1;
    private TextView s2;
    private TextView s3;
    private TextView s4;
    private TextView s5;
    private TextView s6;
    private TextView s7;
    private TextView s8;
    private TextView s9;




    public void loadMenuWin(){
        winIndex = 0;
        setContentView(R.layout.menu);
        nativeLng=0;
        checkedLng = new boolean[5];
        lngChecked=0;
        SingletonPoint.getInstance().setPoint(0);
        Library.getInstance().setLevel(1);
        loadButtons();
        loadButtonLoad();
        loadLng();
    }
    public void loadGameWin(){
        if(winIndex==0) {
            loadName();
            loadNativeLng();
            loadCheckedLng();
            winIndex = 1;
        }
        setContentView(R.layout.game);
        setGameView();
        loadButtons();
        loadButtonSave();
        MySurfaceView sv = (MySurfaceView)findViewById(R.id.gameWin);
        sv.renewRemain();
        sv.setNextButton(btnStart);
        sv.setSaveButton(btnSave);
        sv.setExitButton(btnExit);
    }
    public void loadGameLoad(){
        winIndex = 2;
        setContentView(R.layout.load);
        setLoadView();
        loadButtons();
        Library.getInstance().loadGame();
        int mem = Library.getInstance().getMemSize()-1;
        switch (mem){
            case 9:
                String name = Library.getInstance().getUserName(9);
                if (name.equals(""))    name = "Save9";
                s9.setText(name + " " + Library.getInstance().getLevel(9));
                s9.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s9.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(9);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 8:
                name = Library.getInstance().getUserName(8);
                if (name.equals(""))    name = "Save8";
                s8.setText(name + " " + Library.getInstance().getLevel(8));
                s8.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s8.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(8);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 7:
                name = Library.getInstance().getUserName(7);
                if (name.equals(""))    name = "Save7";
                s7.setText(name + " " + Library.getInstance().getLevel(7));
                s7.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s7.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(7);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 6:
                name = Library.getInstance().getUserName(6);
                if (name.equals(""))    name = "Save6";
                s6.setText(name + " " + Library.getInstance().getLevel(6));
                s6.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s6.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(6);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 5:
                name = Library.getInstance().getUserName(5);
                if (name.equals(""))    name = "Save5";
                s5.setText(name + " " + Library.getInstance().getLevel(5));
                s5.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s5.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(5);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 4:
                name = Library.getInstance().getUserName(4);
                if (name.equals(""))    name = "Save4";
                s4.setText(name + " " + Library.getInstance().getLevel(4));
                s4.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s4.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(4);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 3:
                name = Library.getInstance().getUserName(3);
                if (name.equals(""))    name = "Save3";
                s3.setText(name + " " + Library.getInstance().getLevel(3));
                s3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s3.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(3);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 2:
                name = Library.getInstance().getUserName(2);
                if (name.equals(""))    name = "Save2";
                s2.setText(name + " " + Library.getInstance().getLevel(2));
                s2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s2.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(2);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 1:
                name = Library.getInstance().getUserName(1);
                if (name.equals(""))    name = "Save1";
                s1.setText(name + " " + Library.getInstance().getLevel(1));
                s1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s1.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(1);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            case 0:
                name = Library.getInstance().getUserName(0);
                if (name.equals(""))    name = "Save0";
                s0.setText(name + " " + Library.getInstance().getLevel(0));
                s0.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetLoadColor();
                        s0.setBackgroundColor(Color.BLACK);
                        Library.getInstance().load(0);
                        btnStart.getButton().setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }


    }
    public void loadName(){
        fieldName = (EditText) findViewById(R.id.fieldName);
        SingletonPoint.getInstance().setUserName(fieldName.getText().toString());
    }
    private void loadNativeLng() {
        Library.getInstance().setNativeLng(nativeLng);
    }
    private void loadCheckedLng() {
        Library.getInstance().setCheckedLng(checkedLng);
    }
    private void loadButtons() {
        loadButtonStart();
        loadButtonExit();
    }

    public void loadButtonStart() {
        btnStart = new ButtonBlue((Button) findViewById(R.id.btnStart));
        btnStart.getButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (lngChecked > 0){
                    loadGameWin();
                }else if (winIndex==2) {
                    Library.getInstance().getMemento();
                    loadGameWin();
                }else{
                    Toast.makeText(getApplicationContext(), info[nativeLng/2],  Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void loadButtonSave(){
        btnSave = new ButtonGreen((Button)findViewById(R.id.btnSave));
        btnSave.getButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Library.getInstance().saveGame();
                Toast.makeText(getApplicationContext(),"Saved!", Toast.LENGTH_SHORT).show();
                btnSave.getButton().setEnabled(false);
            }
        });
    }
    public void loadButtonLoad(){
        btnLoad = new ButtonGreen((Button) findViewById(R.id.btnLoad));
        btnLoad.getButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    loadGameLoad();
                    Toast.makeText(getApplicationContext(), "Choose Game To Load!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadButtonExit(){
        //button exit
        btnExit = new ButtonRed((Button) findViewById(R.id.btnExit));
        btnExit.getButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(MenuActivity.this)
                        .setTitle("Exit!")
                        .setMessage("Are you sure?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (winIndex) {
                                    case 0:
                                        finish();
                                        break;
                                    case 1:
                                    case 2:
                                        winIndex=0;
                                        loadMenuWin();
                                        break;
                               }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void loadLng() {
        loadEnglish();
        loadHebrew();
        loadRussian();
    }

    public void loadNativeEng(){
        msg = new int[]{
                R.string.engChekedInEng, R.string.engUnChekedInEng,
                R.string.hebChekedInEng, R.string.hebUnChekedInEng,
                R.string.rusChekedInEng, R.string.rusUnChekedInEng  };

        labelName = (TextView)findViewById(R.id.labelName);
        labelName.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        labelName.setText(R.string.labelNameEng);

        fieldName = (EditText) findViewById(R.id.fieldName);
        fieldName.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        fieldName.setHint(R.string.fieldNameEng);

        labelNative = (TextView)findViewById(R.id.labelNative);
        labelNative.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        labelNative.setText(R.string.labelNativeEng);

        nativeEng = (RadioButton)findViewById(R.id.nativeEng);
        nativeEng.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        nativeEng.setText(R.string.engInEng);

        nativeHeb = (RadioButton)findViewById(R.id.nativeHeb);
        nativeHeb.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        nativeHeb.setText(R.string.hebInEng);

        nativeRus = (RadioButton)findViewById(R.id.nativeRus);
        nativeRus.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        nativeRus.setText(R.string.rusInEng);

        labelLearn = (TextView)findViewById(R.id.labelLearn);
        labelLearn.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        labelLearn.setText(R.string.labelLearnEng);

        learnEng = (CheckBox) findViewById(R.id.learnEng);
        learnEng.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnEng.setText(R.string.engInEng);

        learnHeb = (CheckBox) findViewById(R.id.learnHeb);
        learnHeb.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnHeb.setText(R.string.hebInEng);

        learnRus = (CheckBox) findViewById(R.id.learnRus);
        learnRus.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnRus.setText(R.string.rusInEng);

        learnEngToRead = (CheckBox) findViewById(R.id.learnEngToRead);
        learnEngToRead.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnEngToRead.setText(R.string.ReadInEng);

        learnHebToRead = (CheckBox) findViewById(R.id.learnHebToRead);
        learnHebToRead.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnHebToRead.setText(R.string.ReadInEng);

        btnStart.getButton().setText(R.string.btnStartEng);
        btnExit.getButton().setText(R.string.btnExitEng);
        SingletonPoint.getInstance().setNameEntered(false);
    }
    public void loadNativeHeb(){
        msg = new int[]{
                R.string.engChekedInHeb, R.string.engUnChekedInHeb,
                R.string.hebChekedInHeb, R.string.hebUnChekedInHeb,
                R.string.rusChekedInHeb, R.string.rusUnChekedInHeb  };

        labelName = (TextView)findViewById(R.id.labelName);
        labelName.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        labelName.setText(R.string.labelNameHeb);

        fieldName = (EditText) findViewById(R.id.fieldName);
        fieldName.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        fieldName.setHint(R.string.fieldNameHeb);

        labelNative = (TextView)findViewById(R.id.labelNative);
        labelNative.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        labelNative.setText(R.string.labelNativeHeb);

        nativeEng = (RadioButton)findViewById(R.id.nativeEng);
        nativeEng.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        nativeEng.setText(R.string.engInHeb);

        nativeHeb = (RadioButton)findViewById(R.id.nativeHeb);
        nativeHeb.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        nativeHeb.setText(R.string.hebInHeb);

        nativeRus = (RadioButton)findViewById(R.id.nativeRus);
        nativeRus.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        nativeRus.setText(R.string.rusInHeb);

        labelLearn = (TextView)findViewById(R.id.labelLearn);
        labelLearn.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        labelLearn.setText(R.string.labelLearnHeb);

        learnEng = (CheckBox) findViewById(R.id.learnEng);
        learnEng.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        learnEng.setText(R.string.engInHeb);

        learnHeb = (CheckBox) findViewById(R.id.learnHeb);
        learnHeb.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        learnHeb.setText(R.string.hebInHeb);

        learnRus = (CheckBox) findViewById(R.id.learnRus);
        learnRus.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        learnRus.setText(R.string.rusInHeb);

        learnEngToRead = (CheckBox) findViewById(R.id.learnEngToRead);
        learnEngToRead.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        learnEngToRead.setText(R.string.ReadInHeb);

        learnHebToRead = (CheckBox) findViewById(R.id.learnHebToRead);
        learnHebToRead.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        learnHebToRead.setText(R.string.ReadInHeb);

        btnStart.getButton().setText(R.string.btnStartHeb);
        btnExit.getButton().setText(R.string.btnExitHeb);
        SingletonPoint.getInstance().setNameEntered(false);
    }
    public void loadNativeRus(){
        msg = new int[]{
                R.string.engChekedInRus, R.string.engUnChekedInRus,
                R.string.hebChekedInRus, R.string.hebUnChekedInRus,
                R.string.rusChekedInRus, R.string.rusUnChekedInRus  };

        labelName = (TextView)findViewById(R.id.labelName);
        labelName.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        labelName.setText(R.string.labelNameRus);

        fieldName = (EditText) findViewById(R.id.fieldName);
        fieldName.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        fieldName.setHint(R.string.fieldNameRus);

        labelNative = (TextView)findViewById(R.id.labelNative);
        labelNative.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        labelNative.setText(R.string.labelNativeRus);

        nativeEng = (RadioButton)findViewById(R.id.nativeEng);
        nativeEng.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        nativeEng.setText(R.string.engInRus);

        nativeHeb = (RadioButton)findViewById(R.id.nativeHeb);
        nativeHeb.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        nativeHeb.setText(R.string.hebInRus);

        nativeRus = (RadioButton)findViewById(R.id.nativeRus);
        nativeRus.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        nativeRus.setText(R.string.rusInRus);

        labelLearn = (TextView)findViewById(R.id.labelLearn);
        labelLearn.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        labelLearn.setText(R.string.labelLearnRus);

        learnEng = (CheckBox) findViewById(R.id.learnEng);
        learnEng.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnEng.setText(R.string.engInRus);

        learnHeb = (CheckBox) findViewById(R.id.learnHeb);
        learnHeb.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnHeb.setText(R.string.hebInRus);

        learnEngToRead = (CheckBox) findViewById(R.id.learnEngToRead);
        learnEngToRead.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnEngToRead.setText(R.string.ReadInRus);

        learnHebToRead = (CheckBox) findViewById(R.id.learnHebToRead);
        learnHebToRead.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnHebToRead.setText(R.string.ReadInRus);

        learnRus = (CheckBox) findViewById(R.id.learnRus);
        learnRus.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        learnRus.setText(R.string.rusInRus);

        btnStart.getButton().setText(R.string.btnStartRus);
        btnExit.getButton().setText(R.string.btnExitRus);
        SingletonPoint.getInstance().setNameEntered(false);
    }

    public void loadEnglish(){
        nativeEng = (RadioButton) findViewById(R.id.nativeEng);
        nativeEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nativeLng=0;
                loadNativeEng();
            }
        });

        learnEng = (CheckBox) findViewById(R.id.learnEng);
        learnEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(learnEng.isChecked()){
                    ++lngChecked;
                    checkedLng[0] =true;
                    Toast.makeText(getApplicationContext(),msg[0], Toast.LENGTH_SHORT).show();
                    learnEngToRead.setEnabled(true);
                }
                else{
                    --lngChecked;
                    checkedLng[0] = false;
                    Toast.makeText(getApplicationContext(), msg[1], Toast.LENGTH_SHORT).show();
                    learnEngToRead.setChecked(false);
                    learnEngToRead.setEnabled(false);
                }
            }
        });
        learnEngToRead = (CheckBox) findViewById(R.id.learnEngToRead);
        learnEngToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(learnEngToRead.isChecked()){
                    ++lngChecked;
                    checkedLng[1] =true;
                }
                else{
                    --lngChecked;
                    checkedLng[1] = false;
                }
            }
        });
    }
    public void loadHebrew(){
        nativeHeb = (RadioButton) findViewById(R.id.nativeHeb);
        nativeHeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nativeLng=2;
                loadNativeHeb();
            }
        });

        learnHeb = (CheckBox) findViewById(R.id.learnHeb);
        learnHeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(learnHeb.isChecked()){
                    ++lngChecked;
                    checkedLng[2] =true;
                    Toast.makeText(getApplicationContext(),msg[2], Toast.LENGTH_SHORT).show();
                    learnHebToRead.setEnabled(true);
                }
                else{
                    --lngChecked;
                    checkedLng[2] = false;
                    Toast.makeText(getApplicationContext(), msg[3], Toast.LENGTH_SHORT).show();
                    learnHebToRead.setChecked(false);
                    learnHebToRead.setEnabled(false);
                }
            }
        });

        learnHebToRead = (CheckBox) findViewById(R.id.learnHebToRead);
        learnHebToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(learnHebToRead.isChecked()){
                    ++lngChecked;
                    checkedLng[3] =true;
                }
                else{
                    --lngChecked;
                    checkedLng[3] = false;
                }
            }
        });
    }
    public void loadRussian(){
        nativeRus = (RadioButton) findViewById(R.id.nativeRus);
        nativeRus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nativeLng=4;
                loadNativeRus();
            }
        });

        learnRus = (CheckBox) findViewById(R.id.learnRus);
        learnRus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(learnRus.isChecked()){
                    ++lngChecked;
                    checkedLng[4] =true;
                    Toast.makeText(getApplicationContext(),msg[4], Toast.LENGTH_SHORT).show();
                }
                else{
                    --lngChecked;
                    checkedLng[4] = false;
                    Toast.makeText(getApplicationContext(), msg[5], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setGameView(){
        switch(nativeLng) {
            case 0:
                labelLevel = (TextView) findViewById(R.id.labelLevel);
                labelLevel.setText(R.string.labelLevelEng);
                fieldLevel = (TextView) findViewById(R.id.fieldLevel);
                fieldLevel.setText(""+Library.getInstance().getLevel());
                labelWord = (TextView) findViewById(R.id.labelWord);
                labelWord.setText(R.string.labelWordEng);
                fieldWord = (TextView) findViewById(R.id.fieldWord);
                fieldWord.setText(Library.getInstance().getWordInfo());
                btnStart.getButton().setText(R.string.btnNextEng);
                btnExit.getButton().setText(R.string.btnBackEng);
                break;
            case 2:
                labelLevel = (TextView) findViewById(R.id.fieldLevel);
                labelLevel.setText(R.string.labelLevelHeb);
                fieldLevel = (TextView) findViewById(R.id.labelLevel);
                fieldLevel.setText(""+Library.getInstance().getLevel());
                labelWord = (TextView) findViewById(R.id.fieldWord);
                labelWord.setText(R.string.labelWordHeb);
                fieldWord = (TextView) findViewById(R.id.labelWord);
                fieldWord.setText(Library.getInstance().getWordInfo());
                btnStart.getButton().setText(R.string.btnNextHeb);
                btnExit.getButton().setText(R.string.btnBackHeb);
                break;
            case 4:
                labelLevel = (TextView) findViewById(R.id.labelLevel);
                labelLevel.setText(R.string.labelLevelRus);
                fieldLevel = (TextView) findViewById(R.id.fieldLevel);
                fieldLevel.setText(""+Library.getInstance().getLevel());
                labelWord = (TextView) findViewById(R.id.labelWord);
                labelWord.setText(R.string.labelWordRus);
                fieldWord = (TextView) findViewById(R.id.fieldWord);
                fieldWord.setText(Library.getInstance().getWordInfo());
                btnStart.getButton().setText(R.string.btnNextRus);
                btnExit.getButton().setText(R.string.btnBackRus);
                break;
            default:
                break;
        }
    }
    public void setLoadView(){
        s0 = (TextView) findViewById(R.id.save0);
        s1 = (TextView) findViewById(R.id.save1);
        s2 = (TextView) findViewById(R.id.save2);
        s3 = (TextView) findViewById(R.id.save3);
        s4 = (TextView) findViewById(R.id.save4);
        s5 = (TextView) findViewById(R.id.save5);
        s6 = (TextView) findViewById(R.id.save6);
        s7 = (TextView) findViewById(R.id.save7);
        s8 = (TextView) findViewById(R.id.save8);
        s9 = (TextView) findViewById(R.id.save9);
    }
    public void resetLoadColor(){
        s0.setBackgroundColor(0);
        s1.setBackgroundColor(0);
        s2.setBackgroundColor(0);
        s3.setBackgroundColor(0);
        s4.setBackgroundColor(0);
        s5.setBackgroundColor(0);
        s6.setBackgroundColor(0);
        s7.setBackgroundColor(0);
        s8.setBackgroundColor(0);
        s9.setBackgroundColor(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMenuWin();
    }

    @Override
    public void onBackPressed() {
        loadMenuWin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
