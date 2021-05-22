package test.annotation2.dataAccess;

import java.util.ArrayList;
import java.util.List;

import test.annotation2.bussinessLogic.DataSaplay;
import test.annotation2.bussinessLogic.Item;



public class DataSaplayImpl implements DataSaplay{

	@Override
	public List<Item> getItemlist() {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(new Item(2100,"21",21000));
		itemList.add(new Item(2200,"22",22000));
		itemList.add(new Item(2300,"23",23000));
		return itemList;
	}

}
