package test.ramda.step7.pkg1;

import java.util.function.Consumer;

import org.junit.Test;

import test.ramda.step7.pkg2.Item;


public class Main {

	/**
	 * Consumer関数インターフェース
	 */


	@Test
	public void main01() {

		Item.save( new Consumer<Item>()  {

			@Override
			public void accept(Item item) {
				item.id("id7001")
				.name("name7001")
				.price(71000)
				.description("main01 7description");
			}
		});
	}


	@Test
	public void main02() {

		Item.save(item -> {
			item.id("id7001")
			.name("name7001")
			.price(71000)
			.description("main02 7description");
		});

	}
}
