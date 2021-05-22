package test;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import jp.co.fm.businessLogic.common.ExcelUtil;
import jp.co.fm.businessLogic.common.FileUtil;

public class TestExcelUtil {

	private ExcelUtil eu =ExcelUtil.getInstance();
    private FileUtil fu = FileUtil.getInstance();

	@Test
	public void test006() {

		String systemPath = fu.getSystemPath();

         try {
     		String path = systemPath + "workspace/test/src/test/jinji/AAAAA.xlsx";
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

}
