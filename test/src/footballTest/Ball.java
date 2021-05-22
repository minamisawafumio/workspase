package footballTest;

public class Ball {

	public Ball() {
		direction = 0;
		speed     = 0;
	}

	/**	現在移動している向き(最大359(度))  */
	private Integer direction;

	/**	現在移動しているスピード  */
	private Integer speed;

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
}
