package test.annotation1.prezentation;

import java.util.List;

import org.junit.Test;

import test.annotation1.bussinessLogic.Item;
import test.annotation1.bussinessLogic.ItemSaplayer;

public class MyProgram {
	@Test
	public void main() {
		ItemSaplayer itemSaplayer = ItemSaplayer.getInstance();

		List<Item> itemList =  itemSaplayer.getItemList();

		for(Item item : itemList) {
			System.out.println("id=" + item.getId() + " Name=" + item.getName() + "  Price=" + item.getPrice());
		}

	}
}
