package test.annotation2.bussinessLogic;

import java.util.List;

import test.annotation2.annotation.Resource;
import test.annotation2.dataAccess.DataSaplayImpl;

public class ItemSaplayerImpl implements ItemSaplayer {

	@Resource(DataSaplayImpl.class)
	DataSaplay dataSaplay;

	@Override
	public List<Item> getItemList(){

		List<Item> itemlist = dataSaplay.getItemlist();
		return itemlist;
	}

}
