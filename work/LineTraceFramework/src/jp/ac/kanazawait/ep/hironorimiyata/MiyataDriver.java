package jp.ac.kanazawait.ep.hironorimiyata;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstMotorDriver;

public class MiyataDriver extends AbstMotorDriver {

	final int[] speed = {0, 150, 500, 600, 700};

	final int high = 4;
	final int normal = 3;
	final int low = 1;

	/**
	 *
	 * @param portLeft	左モーターを接続したポート（"A"～"D"の４つのいずれか）
	 * @param portRight	右モーターを接続したポート（"A"～"D"の４つのいずれか）
	 */
	public MiyataDriver(String portLeft, String portRight) {
		this.setMotor(portLeft, portRight);
	}


	@Override
	public void goStraight() {
		this.setSpeed(this.speed[normal]);
	}

	@Override
	public void turnLeft() {
		this.setSpeed(this.speed[low], this.speed[normal]);
	}

	@Override
	public void turnRight() {
		this.setSpeed(this.speed[normal], this.speed[low]);
	}

	@Override
	public void turnLeftSharply() {
		this.setSpeed(this.speed[low], this.speed[high]);
	}

	@Override
	public void turnRightSharply() {
		this.setSpeed(this.speed[high], this.speed[low]);
	}

	@Override
	public void turnLeftGently() {
		// スピードを0にすると，forwardなしにはモーターは再稼働しない
		this.setSpeed(this.speed[0], this.speed[low]);
	}

	@Override
	public void turnRightGently() {
		// スピードを0にすると，forwardなしにはモーターは再稼働しない
		this.setSpeed(this.speed[low], this.speed[0]);
	}


	@Override
	public void increaseSpeed() {
		// 現在の回転速度がspeedのどれに対応するか
		int speedRankLeft = this.getSpeedRank(this.getSpeedLeft());
		int speedRankRight = this.getSpeedRank(this.getSpeedRight());

		if(speedRankLeft < this.speed.length - 1) {
			speedRankLeft++;
		}
		if(speedRankRight < this.speed.length - 1) {
			speedRankRight++;
		}
		this.setSpeed(this.speed[speedRankLeft], this.speed[speedRankRight]);
	}


	@Override
	public void decreaseSpeed() {
		// 現在の回転速度がspeedのどれに対応するか
		int speedRankLeft = this.getSpeedRank(this.getSpeedLeft());
		int speedRankRight = this.getSpeedRank(this.getSpeedRight());
		if(speedRankLeft > 0) {
			speedRankLeft--;
		}
		if(speedRankRight > 0) {
			speedRankRight--;
		}
		this.setSpeed(this.speed[speedRankLeft], this.speed[speedRankRight]);
	}

	/**
	 * 与えた速度speedがこのクラスで設定してある速度表の何番目になっているかを求める
	 * @param speed	速度
	 * @return	このクラスで設定してある速度表でspeedが対応する速度のランク（下から何番目か．ただし，0～表の長さ-1）
	 */
	private int getSpeedRank(int speed) {
		for(int i = 0; i < this.speed.length; i++) {
			if(speed <= this.speed[i])
				return i;
		}
		return this.speed.length-1;
	}

}
