package footballTest;

public class Player {

	/**	所属チーム名  */
	private String teamName;

	private String name;

	private Integer age;

	/**	現在移動している向き(最大359(度))  */
	private Integer direction;

	/**	現在移動しているスピード  */
	private Integer speed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
}
