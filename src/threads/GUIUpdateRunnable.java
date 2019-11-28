package threads;

import controller.LoginController;

public class GUIUpdateRunnable extends Thread {
	
	private LoginController lc;
	private String time;
	
	public GUIUpdateRunnable(LoginController lc, String time){
		this.lc = lc;
		this.time = time;
	}
	
	@Override
	public void run(){
		lc.updateTime(time);
	}
}
