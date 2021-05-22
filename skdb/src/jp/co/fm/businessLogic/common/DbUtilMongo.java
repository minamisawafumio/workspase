package jp.co.fm.businessLogic.common;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public interface DbUtilMongo {
	List<Object> getDataList(MongoCollection coll, BasicDBObject query);

	public DbUtilMongo dum = new DbUtilMongoImpl();

	public static DbUtilMongo getInstance() {
		return dum;
	}
}
