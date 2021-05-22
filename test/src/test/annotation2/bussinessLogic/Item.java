package test.annotation2.bussinessLogic;

public class Item {
	private int id;
	private String name;
	private int price;
	/**
	 * @param id
	 * @param name
	 * @param price
	 */
	public Item(int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}

}
