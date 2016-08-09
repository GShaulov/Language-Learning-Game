package game.languagelearning;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

import game.languagelearning.decorator.ButtonDecorator;
import game.languagelearning.factory.LevelWord;
import game.languagelearning.factory.Word;
import game.languagelearning.memento.LevelMemento;
import game.languagelearning.singleton.SingletonPoint;
import game.languagelearning.strategy.BonusPoint;
import game.languagelearning.strategy.RestartStrategy;
import game.languagelearning.strategy.SimpleStrategy;


public class MySurfaceView extends SurfaceView{
    private boolean isRunning;
    private Paint p;
    private BonusPoint bonusPoint = new BonusPoint(new SimpleStrategy());
    private int bonus;
    private int remain;
    private long lastClick;
    private Context context;
    private SurfaceHolder surfaceHolder;
    private Bitmap bg;
    private LevelWord words;
    private MyThread myThread;
    private ButtonDecorator btnNext;
    private ButtonDecorator btnSave;
    private ButtonDecorator btnExit;

    public MySurfaceView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        setMemento();
        this.words = new LevelWord(context);
        this.p = new Paint();
        words.setLevelWords();
        this.remain = Library.getInstance().getRemain();
        myThread = new MyThread(this);
        surfaceHolder = getHolder();
        this.isRunning=true;
        this.bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg3);

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) { }
                }
            }
        });
    }

    public void update() {
        words.init(getWidth(), getHeight());
        words.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        update();
        canvas.drawBitmap(bg, 0, 0, null);

        p.setTextSize(30);
        p.setColor(Color.WHITE);
        switch (Library.getInstance().getNativeLng()) {
            case 0:
                canvas.drawText("Point: " + SingletonPoint.getInstance().getPoint(), 0, 35, p);
                if (remain < 3) {
                    p.setTextSize(35);
                    p.setColor(Color.RED);
                }
                canvas.drawText("Remain: " + remain, 0, 70, p);
                break;

            case 2:
                canvas.drawText("נקודות: " + SingletonPoint.getInstance().getPoint(), getWidth() - 120, 35, p);
                if (remain < 3) {
                    p.setColor(Color.RED);
                }
                canvas.drawText("נשאר: " + remain, getWidth() - 105, 70, p);
                break;

            case 4:
                canvas.drawText("Очки: " + SingletonPoint.getInstance().getPoint(), 0, 35, p);
                if (remain < 3) {
                    p.setTextSize(35);
                    p.setColor(Color.RED);
                }
                canvas.drawText("Осталось: " + remain, 0, 70, p);
                break;
        }
        words.draw(canvas);
        if(!isRunning) {
            drawResults(canvas);
        }
    }

    public void drawResults(Canvas canvas){
        p.reset();
        p.setTextSize(30);
        p.setStrokeWidth(30);
        p.setColor(Color.rgb(0, 153, 204));
        canvas.drawRect(getWidth()/10, getWidth()/10, getWidth()*9/10, getHeight()*9/10, p);
        int resNameX=getWidth()*2/12;
        int resLvlX=getWidth()*5/12;
        int resPointX=getWidth()*8/12;
        int resY=getHeight()*2/20;
        p.setColor(Color.BLACK);
        canvas.drawText("Name: " , resNameX, resY, p);
        canvas.drawText("Level: ", resLvlX, resY, p);
        canvas.drawText("Point: ", resPointX, resY, p);

        //print user details
        p.setColor(Color.WHITE);
        ArrayList<String[]> users = SingletonPoint.getInstance().getUsers();
        for(int i=0, locY=resY+80; i<users.size(); i++,locY+=50){
            canvas.drawText(users.get(i)[0], resNameX, locY, p);
            canvas.drawText(users.get(i)[1], resLvlX, locY, p);
            canvas.drawText(users.get(i)[2], resPointX, locY, p);
        }
    }

    @Override
	public synchronized boolean onTouchEvent(MotionEvent event) {
		if (System.currentTimeMillis() - lastClick > 300) {
			lastClick = System.currentTimeMillis();
			synchronized (getHolder()) {
				if(remain>0) {
					for (int i = words.getSize() - 1; i >= 0; i--) {
						Word word = words.getWord(i);
						if (word.isCollision(event.getX(), event.getY())) {
							words.removeWord(word);
							remain--;
                            SingletonPoint.getInstance().plusPoint(1);
                            if(remain==0) {
                                setDemo();
                                if (isWinner()) {
                                    if (Library.getInstance().getLevel() < 20) {
                                        this.bonus=bonusPoint.getBonusPoint(Library.getInstance().getCheckedLng());
                                        SingletonPoint.getInstance().plusPoint(this.bonus);
                                        Toast.makeText(context, "(Bonus + " + (this.bonus) + ")", Toast.LENGTH_LONG).show();
                                        bonusPoint = new BonusPoint(new SimpleStrategy());
                                        Library.getInstance().nextLevel();
                                        btnNext.getButton().setEnabled(true);
                                        btnSave.getButton().setEnabled(true);
                                        setMemento();
                                    } else {
                                        Toast.makeText(context, "YOU WIN !!!", Toast.LENGTH_LONG).show();
                                        String[] user = {SingletonPoint.getInstance().getUserName(), ""+Library.getInstance().getLevel(), ""+SingletonPoint.getInstance().getPoint()};
                                        SingletonPoint.getInstance().addUser(user);
                                        isRunning=false;
                                    }
                                } else {
                                    Toast.makeText(context, "You Lose!", Toast.LENGTH_LONG).show();
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setMessage("You Lose!");
                                    builder.setIcon(R.drawable.logo2);
                                    builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            bonusPoint = new BonusPoint(new RestartStrategy());
                                            getMemento();
                                            init();
                                        }
                                    });
                                    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String[] user = {SingletonPoint.getInstance().getUserName(), ""+Library.getInstance().getLevel(), ""+SingletonPoint.getInstance().getPoint()};
                                            SingletonPoint.getInstance().addUser(user);
                                            isRunning=false;
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            }else
                                break;
                        }
					}
				}
			}
		}
		return true;
	}


    private void setDemo() {
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
        words.setDemo();
    }

    public void setNextButton(ButtonDecorator btnNext) {
        this.btnNext = btnNext;
    }
    public void setSaveButton(ButtonDecorator btnSave) {
        this.btnSave = btnSave;
    }
    public void setExitButton(ButtonDecorator btnExit) {
        this.btnExit = btnExit;
    }

    public boolean isWinner() {
        for (int i = 0; i < words.getSize(); i++) {
            for (int j = 0; j < 5; j++) {
                if (Library.getInstance().checkLevelWord(j, words.getWordName(i))){
                    break;
                } else if (j == 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public void renewRemain() {
        this.remain = Library.getInstance().getRemain();
    }

    private void setMemento() {
        Library.getInstance().setMemento();
    }

    private void saveGame(){
        Library.getInstance().saveGame();
    }

    private void getMemento() {
        Library.getInstance().getMemento();
    }


}