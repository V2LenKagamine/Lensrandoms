package io.github.v2lenkagamine.common.capabilities.gunTimer;

public class GunTimerDefault implements IGunTimer{

	private int reloadTime;
	

	@Override
	public int getTimer() {
		int timer = (getTimerTicks() / 20);
		return timer;
	}

	@Override
	public int getTimerTicks() {
		
		return reloadTime;
	}

	@Override
	public void setTimer(int time) {

		reloadTime = (time * 20);
		
	}

	@Override
	public void setTimerTicks(int time) {
		reloadTime = time;
		
	}

	@Override
	public void timerDown() {
	int	timenew = getTimerTicks();
		timenew--;
		setTimerTicks(timenew);
		
	}


}
