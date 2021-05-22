package test.annotation2.bussinessLogic;

import java.util.List;

import test.annotation2.dataAccess.DataSaplayImpl;



public interface DataSaplay {

	public static DataSaplay getInsrance() {
		return new DataSaplayImpl();
	}


	public List<Item> getItemlist();
}