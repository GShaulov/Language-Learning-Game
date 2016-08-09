package game.languagelearning;

import android.graphics.Canvas;
import android.widget.TextView;

public class MyThread extends Thread {
	MySurfaceView myView;
	private boolean running = false;

	public MyThread(MySurfaceView view) {
		myView = view;
	}
	
	public void setRunning(boolean run) {
        running = run;    
	}

	@Override
	public void run() {
		float ticksPS=1000;
		long gameTime;
		long idleTime;
		while(running){
			gameTime=System.nanoTime();

			Canvas canvas = myView.getHolder().lockCanvas();
			if(canvas != null){
				synchronized (myView.getHolder()) {
					myView.draw(canvas);
				}
				myView.getHolder().unlockCanvasAndPost(canvas);
			}

			idleTime =(long)ticksPS - (System.nanoTime() - gameTime);
			try {
				if (idleTime > 0)   Thread.sleep(idleTime);
				else  Thread.sleep(0);
			} catch (Exception e) { }
			
		}
	}

}
