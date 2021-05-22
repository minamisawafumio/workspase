package test.annotation1.dataAccess;

import java.util.ArrayList;
import java.util.List;

import test.annotation1.bussinessLogic.Item;



public class DataSaplayImpl implements DataSaplay{

	@Override
	public List<Item> getItemlist() {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(new Item(1100,"11",11000));
		itemList.add(new Item(1200,"12",12000));
		itemList.add(new Item(1300,"13",13000));
		return itemList;
	}

}
