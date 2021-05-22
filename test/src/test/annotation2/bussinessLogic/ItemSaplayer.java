package test.annotation2.bussinessLogic;

import java.util.List;

public interface ItemSaplayer {

	public static ItemSaplayer getInstance() {
		return new ItemSaplayerImpl();

	}

	List<Item> getItemList();
}