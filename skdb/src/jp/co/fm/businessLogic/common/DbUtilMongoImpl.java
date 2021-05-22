package jp.co.fm.businessLogic.common;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DbUtilMongoImpl implements DbUtilMongo {
    @Override
	public List<Object> getDataList(MongoCollection coll, BasicDBObject query) {

    	List<Object> list = new ArrayList<Object>();
    	FindIterable find = coll.find(query);
    	MongoCursor cursor = find.iterator();
    	while (cursor.hasNext()) {
    			list.add(cursor.next());
    	}
    	cursor.close();
    	return list;
	}
}
