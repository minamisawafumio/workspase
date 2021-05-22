package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;
//import org.bson.Document;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtilMongo;
import jp.co.fm.businessLogic.common.ExcelUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import manga.common.def.MangaSystemCommon;


public class Test_mongoDB {

	private ExcelUtil eu =ExcelUtil.getInstance();

    private FileUtil fu = FileUtil.getInstance();

    private DateUtil dt = DateUtil.getInstance();


    /**
     * 複数件削除
     */
	@Test
	public void test_mongoDB_deleteMany() {
        // MongoDBクライントを生成する
        MongoClient client = new MongoClient("localhost", 27017);
        // DBオブジェクトを取得する
        MongoDatabase db = client.getDatabase("db0003");
        // ユーザーコレクションを取得する
        MongoCollection<Document> users = db.getCollection("documents");

        // 削除条件
        users.deleteMany(Filters.eq("lastName", "yamada"));

        // クライントを閉じる
        client.close();
    }


    /**
     * 一件削除
     */
	@Test
	public void test_mongoDB_deleteOne() {
        // MongoDBクライントを生成する
        MongoClient client = new MongoClient("localhost", 27017);
        // DBオブジェクトを取得する
        MongoDatabase db = client.getDatabase("db0003");
        // ユーザーコレクションを取得する
        MongoCollection<Document> users = db.getCollection("documents");

        // 削除条件
        users.deleteOne(Filters.eq("_id", new ObjectId("600348f15a436348317d174e")));

        // クライントを閉じる
        client.close();
    }

    /**
     * 更新
     */
	@Test
	public void test_mongoDB_update() {
        // MongoDBクライントを生成する
        MongoClient client = new MongoClient("localhost", 27017);
        // DBオブジェクトを取得する
        MongoDatabase db = client.getDatabase("db0003");
        // ユーザーコレクションを取得する
        MongoCollection<Document> users = db.getCollection("documents");

        // 更新条件
        Document filter = new Document();
        filter.append("_id", new ObjectId("600348f15a436348317d174e"));

        // 更新データ
        Document updateSet = new Document();
        updateSet.append("lastName", "鈴木");
        updateSet.append("updateDate", new Date());

        // updateオブジェクト
        Document update = new Document();
        update.append("$set", updateSet);

        // 更新
        users.updateOne(filter , update);


        // クライントを閉じる
        client.close();
    }

    /**
     * 複数インサート
     */
	@Test
	public void test_mongoDB_insertMany() {
        // MongoDBクライントを生成する
        MongoClient client = new MongoClient("localhost", 27017);
        // DBオブジェクトを取得する
        MongoDatabase db = client.getDatabase("db0003");
        // ユーザーコレクションを取得する
        MongoCollection<Document> users = db.getCollection("documents");

        List<Document> userList = new ArrayList<Document>();

        Document user1 = new Document();
        user1.append("lastName", "tanaka");

        Document user2 = new Document();
        user2.append("lastName", "yamada");

        userList.add(user1);
        userList.add(user2);

        // リストをDBにインサート
        users.insertMany(userList);

        // クライントを閉じる
        client.close();
    }

    /**
     * 単数インサート
     */
	@Test
	public void test_mongoDB_insertOne() {
        // MongoDBクライントを生成する
        MongoClient client = new MongoClient("localhost", 27017);
        // DBオブジェクトを取得する
        MongoDatabase db = client.getDatabase("db0003");
        // ユーザーコレクションを取得する
        MongoCollection<Document> users = db.getCollection("documents");

        // ユーザーの属性をセット
        Document user = new Document();
        user.append("corpName", "tanaka");
        user.append("location", "ichiro");
        user.append("tel", "男性");

        // DBにインサート
        users.insertOne(user);

        // クライントを閉じる
        client.close();
    }

	@Test
	public void test_mongoDB_select2() {


		ObjectMapper mapper = new ObjectMapper();

		MangaSystemCommon msc = MangaSystemCommon.getInstance();

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

		mongoLogger.setLevel(Level.SEVERE);

       	MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase db = mongoClient.getDatabase("db0003");

        MongoCollection coll = db.getCollection("documents");

        List<Object> list = msc.get_T0000_from_mongoDB(coll, "1", "7033", "1");

        try  {

            // DBオブジェクトを取得する

            for(Object sss : list) {

            	String sss2 = (String) sss.toString();

            	System.out.println(sss2);
            	List<Student> participantJsonList = mapper.readValue(sss2, new TypeReference<List<Student>>(){});


            }

            // クライントを閉じる
            mongoClient.close();

        }catch(Exception e) {
        	e.printStackTrace();
        }
	}

	class Student {

	    String _id;
	    String key1;
	    String key2;
	    String key3;
	    String value;


	    //getters and setters
	}

	@Test
	public void test_mongoDB_select() {

		DbUtilMongo dum = DbUtilMongo.getInstance();

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

		mongoLogger.setLevel(Level.SEVERE);

        try  {

           	MongoClient mongoClient = new MongoClient("localhost", 27017);

            // DBオブジェクトを取得する
            MongoDatabase db = mongoClient.getDatabase("db0003");

            MongoCollection<Document> coll = db.getCollection("documents");

            BasicDBObject query = new BasicDBObject();
            query.put("corpName", "333");

            List<Object> list = dum.getDataList(coll, query);

            for(Object sss : list) {
            	System.out.println(sss);
            }

            // クライントを閉じる
            mongoClient.close();

        }catch(Exception e) {
        	e.printStackTrace();
        }
	}

	@Test
	public void test00221() {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

		mongoLogger.setLevel(Level.SEVERE);

        try  {

           	MongoClient mongoClient = new MongoClient("localhost", 27017);

            // DBオブジェクトを取得する
            MongoDatabase db = mongoClient.getDatabase("db0003");


            MongoCollection coll = db.getCollection("documents");


            BasicDBObject query = new BasicDBObject();
            query.put("corpName", "333");
            FindIterable find = coll.find(query);
            MongoCursor cursor = find.iterator();
            try {
	            while (cursor.hasNext()) {
	            	System.out.println(cursor.next().toString());
	            }
            } finally {
            	cursor.close();
            }
            long count = coll.count(query);
            System.out.println(count + "件");

            // クライントを閉じる
            mongoClient.close();

        }catch(Exception e) {
        	e.printStackTrace();
        }
	}




//	@Test
//	public void test001() {
//
//        try {
//
//        	MockServletContext mockServletContext =
//        			new MockServletContext("classpath:", new FileSystemResourceLoader());
//
//        	String webInfRealPath = "C:/pleiades/pleiades-4.8.0_JSP/2019_04_07_1/workspace/skdb/WebContent/WEB-INF";
//
//        	StartupServlet startupServlet = new StartupServlet();
//
//        	startupServlet.makeSystemInfo(webInfRealPath);
//
//        	// モッククラスのインスタンス作成
//	        MockHttpServletRequest request = new MockHttpServletRequest();
//	        MockHttpServletResponse response = new MockHttpServletResponse();
//
//	        FT001F01 fT001F01 = new FT001F01();
//
//	        FT001F01Form fT001F01Form = new FT001F01Form();
//	        fT001F01Form.setId("aaaaa");
//	        fT001F01Form.setPassword("12345a+");
//
//	        fT001F01.init(fT001F01Form);
//
//	        HttpSession httpSession = request.getSession();
//
//	        httpSession.setAttribute("fT001F01Form", fT001F01Form);
//
//	        // パラメータをセットするなどテストに必要な準備を行う
//	        request.setParameter("name", "value");
//
//	        Model model = new ExtendedModelMap();
//
//	        String kkkk = fT001F01.goto083(model, request);
//
//	        System.out.println(kkkk);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

	@Test
	public void test005() {


		String pathFile = "C:/南沢/999 その他/manga/Oびっちにマジ恋！/005.jpg";
		String value = fu.file2string(pathFile);

		System.out.println(value);



	}


	private String aaaaa(Integer iii){
		if(iii == 1){
		    return "1";
		}else if(iii == 2){
		    return "2";
		}else{
		    return "3";
		}
	}

/**
 *
 * @param iii　あああ
 * @param jjj　いいいｊｊｊ
 * @return
 */
	private String bbb(Integer iii, String jjj){
		if(iii == 1){
			if(jjj.equals("1")){
			    return "1";
			}else{
			    return "2";
			}
		}else if(iii == 2){
			if(jjj.equals("1")){
			    return "3";
			}else{
			    return "4";
			}
		}else{
			if(jjj.equals("1")){
			    return "5";
			}else{
			    return "6";
			}
		}
	}
}
