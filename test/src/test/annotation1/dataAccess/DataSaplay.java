package test.annotation1.dataAccess;

import java.util.List;

import test.annotation1.bussinessLogic.Item;



public interface DataSaplay {

	public static DataSaplay getInsrance() {
		return new DataSaplayImpl();
	}


	public List<Item> getItemlist();
}