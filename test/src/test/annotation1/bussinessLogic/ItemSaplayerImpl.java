package test.annotation1.bussinessLogic;

import java.util.List;

import test.annotation1.dataAccess.DataSaplay;

public class ItemSaplayerImpl implements ItemSaplayer {

	DataSaplay dataSaplay = DataSaplay.getInsrance();

	@Override
	public List<Item> getItemList(){

		List<Item> itemlist = dataSaplay.getItemlist();
		return itemlist;
	}
}
