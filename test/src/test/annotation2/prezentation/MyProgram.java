package test.annotation2.prezentation;

import java.util.List;

import org.junit.Test;

import test.annotation2.bussinessLogic.Item;
import test.annotation2.bussinessLogic.ItemSaplayer;
import test.annotation2.bussinessLogic.ItemSaplayerImpl;
import test.annotation2.core.Container;

public class MyProgram {
	@Test
	public void main() {

		//コンテナ―を作り、DI使って、DataSaplayImplとItemSaplayerImplの主従関係を逆転させた
		Container container = new Container();

		ItemSaplayer itemSaplayer = (ItemSaplayer) container.getInsrance(ItemSaplayerImpl.class);

		List<Item> itemList =  itemSaplayer.getItemList();

		for(Item item : itemList) {
			System.out.println("id=" + item.getId() + " Name=" + item.getName() + "  Price=" + item.getPrice());
		}

	}
}
