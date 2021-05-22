package test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
//import org.bson.Document;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtilMongo;
import jp.co.fm.businessLogic.common.ExcelUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.service.FT001F01;
import jp.co.fm.businessLogic.service.StartupServlet;
import jp.co.fm.presentation.form.FT001F01Form;

public class Test_MockServlet {

	private ExcelUtil eu =ExcelUtil.getInstance();

    private FileUtil fu = FileUtil.getInstance();

    private DateUtil dt = DateUtil.getInstance();

	@Test
	public void test006() {

		String path = FileUtil.getInstance().getTargetPath("workspace");
 		String pathFile = path + "/test/src/test/jinji/AAAAA.xlsx";

         try {
    		Workbook workbook =  eu.getWorkbook(pathFile);


    		Map<String, Map<String, Object>> mapA = eu.makeMap(workbook);


    		System.out.println(eu.getData(mapA, "test01", "0005","値5"));
    		System.out.println(eu.getData(mapA, "test01", "0006","値6"));

    		System.out.println(eu.getData(mapA, "test02", "0005","値5"));
    		System.out.println(eu.getData(mapA, "test02", "0006","値6"));


    		System.out.println(eu.getData(mapA, "test03", "0005","値5"));
    		System.out.println(eu.getData(mapA, "test03", "0006","値6"));

    		System.out.println(eu.getData(mapA, "test04", "0005","値5"));
    		System.out.println(eu.getData(mapA, "test04", "0006","値6"));


       		System.out.println(eu.getData(mapA, "test16", "0265","値5"));

       		System.out.println("------------------------------");
       		String sheetName = "test16";

       		for(int y = 2; y <= 10; y++) {
       			System.out.print(eu.getKey(mapA, sheetName, y) + " ");
           		for(int x = 2; x <= 10; x++) {
           			System.out.print(eu.getData(mapA, sheetName, y, x) + " ");
           		}
           		System.out.println();
       		}


			workbook.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
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

            MongoCollection coll = db.getCollection("documents");

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




	@Test
	public void test001() {

		String systemPath = FileUtil.getInstance().getSystemPath();

        try {

        	MockServletContext mockServletContext = new MockServletContext("classpath:", new FileSystemResourceLoader());

        	StartupServlet startupServlet = new StartupServlet();

        	startupServlet.makeSystemInfo(systemPath);

        	// モッククラスのインスタンス作成
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        MockHttpServletResponse response = new MockHttpServletResponse();

	        FT001F01 fT001F01 = new FT001F01();

	        FT001F01Form fT001F01Form = new FT001F01Form();
	        fT001F01Form.setId("aaaaa");
	        fT001F01Form.setPassword("12345a+");

	        fT001F01.init(fT001F01Form);

	        HttpSession httpSession = request.getSession();

	        httpSession.setAttribute("fT001F01Form", fT001F01Form);

	        // パラメータをセットするなどテストに必要な準備を行う
	        request.setParameter("name", "value");

	        Model model = new ExtendedModelMap();

	        String kkkk = fT001F01.goto083(model, request);

	        System.out.println(kkkk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
