package test.ramda.step8.pkg1;

import java.util.function.Function;

import org.junit.Test;

import test.ramda.step8.pkg2.Item;


public class Main {

	/**
	 * Functiopn関数インターフェース
	 */

	@Test
	public void main01() {


		/** 無名クラスの記述 */
		String st = Item.save( new Function<Item, String>() {

			@Override
			public String apply(Item t) {
				t.id("id")
				.price(100)
				.name("name")
				.description("description");

				return null;
			}

		});

		System.out.println("Function main01 st="+ st);

	}

	/** ラムダ式の記述 01*/
	@Test
	public void main02() {

		String st = Item.save((Function<Item, String>) item  -> {
			item.id("id7001")
			.name("name7001")
			.price(71000)
			.description("main02 7description");
			return "";

		});

		System.out.println("Function main02 st="+ st);
	}

	/** ラムダ式の記述 */
	@Test
	public void main03() {

		String id = "ididididid77777777";
		String hhhhhhhh = "aaa minamisawa aaaaaaa";


		String st = Item.save((item)->  {
			item.id(id)
			.name(hhhhhhhh)
			.price(71000)
			.description("main03----- 7description");
			return "";

		});

		System.out.println("Function main03 st="+ st);
	}

}
