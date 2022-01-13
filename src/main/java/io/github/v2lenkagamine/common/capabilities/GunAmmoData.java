package io.github.v2lenkagamine.common.capabilities;

public class GunAmmoData{

	private int ammoIn;
	
	
	public int getAmmoLeft() {
		return ammoIn;
	}
	
	public void setAmmo(int amount) {
		ammoIn = amount;
	}
	public void removeAmmo(int amount) {
		int ammoNew = ammoIn - amount;
		setAmmo(ammoNew);
	}
	public void addAmmo(int amount) {
		int ammoNew = ammoIn + amount;
		setAmmo(ammoNew);
	}
	
}
