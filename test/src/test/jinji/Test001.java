package test.jinji;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
//import org.bson.Document;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.ExcelUtil;
import jp.co.fm.businessLogic.common.FileUtil;

public class Test001 {

	private ExcelUtil eu =ExcelUtil.getInstance();

    private FileUtil fu = FileUtil.getInstance();

    private DateUtil dt = DateUtil.getInstance();

	@Test
	public void test007() {

		String path = FileUtil.getInstance().getTargetPath("workspace");
 		String pathFile =  path + "/test/src/test/jinji/人事データ.xlsx";

         try {

    		Workbook workbook =  eu.getWorkbook(pathFile);


    		Map<String, Map<String, Object>> mapA = eu.makeMap(workbook);

    		String w_date = eu.getData(mapA, "社員マスタ", "0005", "入社年月日");

    		Date inDate = dt.stToDate(w_date.replaceAll("/", "") + "000000000");

    		Date nowDate = new Date();

    		Long kankaku = dt.getIntervalDayCount(inDate, nowDate);

    		System.out.println(kankaku);

			workbook.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}


	@Test
	public void test006() {

         try {
     		String path = "C:/pleiades/pleiades-2019-12-java-win-64bit-jre_20200322/workspace/test/src/test/jinji/AAAAA.xlsx";
    		Workbook workbook =  eu.getWorkbook(path);


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
