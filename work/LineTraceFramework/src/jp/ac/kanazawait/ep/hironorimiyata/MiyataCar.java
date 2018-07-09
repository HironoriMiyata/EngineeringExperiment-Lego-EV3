package jp.ac.kanazawait.ep.hironorimiyata;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstCar;
import jp.ac.kanazawait.ep.mmotoki.navigator.SimpleLeftEdgeTracer;
import jp.ac.kanazawait.ep.mmotoki.util.CheckerColorSensor;
import jp.ac.kanazawait.ep.mmotoki.util.EV3Logger;
import lejos.hardware.Button;
import lejos.robotics.Color;

public class MiyataCar extends AbstCar {
	public MiyataCar() {
		this.checkerColorSensor = CheckerColorSensor.getInstance();
		this.driver = new MiyataDriver("B", "C");
		this.navigator = new SimpleLeftEdgeTracer();
		this.logger = EV3Logger.getInstance();
		this.logger.setChecker(checkerColorSensor);
		this.logger.setDriver(driver);
	}
	

	@Override
	public void run() {
		this.start();
		boolean sel = false;
		boolean ch =true;
		boolean colorFirst = false;
		boolean colorSecond = false;
		boolean ch1 = true;
		while(!Button.ESCAPE.isDown() && this.checkerColorSensor.getColorId() != Color.RED) {
			if(this.checkerColorSensor.getColorId() == Color.GREEN) {
				colorFirst = true;
			} else {
				colorFirst = false;
			}
			if(sel) {
				this.navigator = new RightEdgeTracer();
				ch = false;
			} else {
				this.navigator = new SimpleLeftEdgeTracer();
				ch = true;
			}
			this.navigator.decision(this.checkerColorSensor, this.driver);
			
			if(this.checkerColorSensor.getColorId() == Color.GREEN) {
				colorSecond = true;
			}else {
				colorSecond = false;
				
			}
			
			if(colorFirst == colorSecond) {
				if(colorFirst== true&&ch1 == true) {
					sel = ch;
					ch1 = false;
				}
				
			} else if(colorFirst== false) {
				ch1=true;
			}
		}
		this.stop();
	}

	public static void main(String[] args) {
		MiyataCar car = new MiyataCar();
		car.run();
	}

	

}
