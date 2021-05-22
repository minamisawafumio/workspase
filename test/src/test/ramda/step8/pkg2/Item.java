package test.ramda.step8.pkg2;

import java.util.function.Function;

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

	public static String save(Function<Item, String> fun) {
		Item item = new Item();

		String st = fun.apply(item);    //.accept(item);

		st = "Save!!!!! id=" + item.id + " name=" + item.name  + " description="+ item.description + " price="+ item.price;

		return st;
	}




}
