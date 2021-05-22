package test.ramda.step7.pkg2;

import java.util.function.Consumer;

public class Item {
	private String id;
	private String name;
	private int price;
	private String description;

	public Item id(String id) {
		this.id = id;
		return this;
	}
	public Item name(String name) {
		this.name = name;
		return this;
	}
	public Item price(int price) {
		this.price = price;
		return this;
	}
	public Item description(String description) {
		this.description = description;
		return this;
	}

	public static void save(Consumer<Item> con) {
		Item item = new Item();

		con.accept(item);

		System.out.println("Save!!!!! id=" + item.id + " name=" + item.name);
	}

}
