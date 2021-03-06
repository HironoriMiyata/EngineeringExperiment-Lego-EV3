package jp.ac.kanazawait.ep.mmotoki.test;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstMotorDriver;
import jp.ac.kanazawait.ep.mmotoki.driver.SimpleDriver;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class SimpleDriverTest {

	public static void main(String[] args) {
		AbstMotorDriver driver = new SimpleDriver("B", "C");
		LCD.drawString("Press any button", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		LCD.drawString("Press any button", 0, 0);
		driver.goStraight();
		Delay.msDelay(100);
		LCD.drawString("speedL=" + driver.getSpeedLeft(), 0, 1);
		LCD.drawString("speedR=" + driver.getSpeedRight(), 0, 2);
		Button.waitForAnyPress();
		driver.turnLeft();;
		Delay.msDelay(100);
		LCD.drawString("speedL=" + driver.getSpeedLeft(), 0, 1);
		LCD.drawString("speedR=" + driver.getSpeedRight(), 0, 2);
		Button.waitForAnyPress();
		driver.turnLeftGently();;
		Delay.msDelay(100);
		LCD.drawString("speedL=" + driver.getSpeedLeft(), 0, 1);
		LCD.drawString("speedR=" + driver.getSpeedRight(), 0, 2);
		Button.waitForAnyPress();
	}

}
