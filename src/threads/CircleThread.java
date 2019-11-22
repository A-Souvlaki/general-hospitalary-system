package threads;

import controller.MenuAdminController;


public class CircleThread extends Thread{
	
	private MenuAdminController mac;
	
	public CircleThread(MenuAdminController mac) {
		this.mac = mac;
	}
	
	public void run() {
		while(true) {
			mac.moveBlueCircle();
			try {
				sleep(8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}