package io.github.v2lenkagamine.common.capabilities;

public class GunTimerData{

	private int reloadTime;
	


	public int getTimer() {
		int timer = (getTimerTicks() / 20);
		return timer;
	}
	
	public int getTimerTicks() {
		
		return reloadTime;
	}

	public void setTimer(int time) {

		reloadTime = (time * 20);
		
	}

	public void setTimerTicks(int time) {
		reloadTime = time;
		
	}

	public void timerDown() {
	int	timenew = getTimerTicks();
		timenew--;
		setTimerTicks(timenew);
		
	}

	
}
