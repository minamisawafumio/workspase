package jp.co.fm.businessLogic.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.int2of5.ITF14Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

class ExcelUtilImpl implements ExcelUtil {

	/**
	 *
	 * @param f
	 * @param workbook
	 * @param executeList
	 */
	@Override
	public void updateExcelBook(File f, Workbook workbook, List<String> executeList){

        for(String datas: executeList){

            String []data = datas.split(",");

            String sheetName = data[0].trim();
            // 編集したいシート
            Sheet sheet = workbook.getSheet(sheetName);

            if(sheet == null){
                sheet = workbook.createSheet(sheetName);
            }

            String order = data[1];
            Integer x = Integer.parseInt(data[2].trim());
            Integer y = Integer.parseInt(data[3].trim());

            Row row = sheet.getRow(y);

            if(row == null){
                row = sheet.createRow(y);
            }

            Cell cell = row.getCell(x);

            if(cell == null){
                cell = row.createCell(x);
            }

            switch (order){
              case Const.UPDATE:
                    String value = data[4].trim();
                    cell.setCellValue(value);
                break;
              case Const.DELETE:
                //--------列の追加は、まだ、未実装（列追加のメソッドは容易されていないもよう。行単位に１つづつ、シフトさせて対応）-----------------------------

                if (x > 0){

                }

                if(y > 0){
                    sheet.removeRow(row);
                    // 上に1つ行をずらす
                    int lastRow = sheet.getLastRowNum();
                    if(y + 1 <= lastRow) {
                        sheet.shiftRows(y + 1, lastRow, -1);
                    }
                }
                break;
              case Const.INSERT:
                //--------列の追加は、まだ、未実装（列追加のメソッドは容易されていないもよう。行単位に１つづつ、シフトさせて対応）-----------------------------

                Integer count = Integer.parseInt(data[4].trim());
                Integer lastRowNum = sheet.getLastRowNum();
                sheet.createRow(lastRowNum + count);
                sheet.shiftRows(y, lastRowNum + 1, count);
                break;
            }
        }
        // 編集した内容の書き出し
        try {
            OutputStream os = new FileOutputStream(f);
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param pathFile
     */
    @Override
	public void makeWorkbook(String pathFile) {
        //新規ワークブックを作成する
        Workbook workbook = upsertWorkbook(pathFile);

        try{
            File f = new File(pathFile);
            FileOutputStream os = new FileOutputStream(f);
            //作成したワークブックを保存する
            workbook.write(os);
            os.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Workbookを取得する（無ければ新規に作成）
     * @param pathFile
     * @return
     */
    @Override
	public Workbook upsertWorkbook(String pathFile){

        int bool = FileUtil.getInstance().isExistFile(pathFile);

        Workbook workbook;

        if(bool == 0){
            workbook = getWorkbook(pathFile);
        }else{
            workbook = newWorkbook(pathFile);
        }

        return workbook;
    }

    /**
     *
     * @param pathFile
     * @return
     */
    @Override
	public Workbook getWorkbook(String pathFile){

        Workbook book = null;

        FileInputStream fi;
        try {
            fi   = new FileInputStream(pathFile);
            book = WorkbookFactory.create(fi);
            fi.close();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
			e.printStackTrace();
		}

        return book;
    }

    /**
     * セルの内容を取得する。 内容が空か、文字列以外の場合は "" を返す。
     * @param workbook
     * @param sheetName
     * @param x
     * @param y
     * @return
     */
    @Override
	public String getCellValue(Workbook workbook, Sheet sheet, Integer x, Integer y){
    	String rtnData = "";

        Row row = sheet.getRow(y - 1);

        Cell cell = null;

        try{
        	cell = row.getCell(x - 1);
        	rtnData = cell.getStringCellValue();
        }catch(Exception e){

        }

        return rtnData;
    }

    /**
     *
     * @param pathFile
     * @return
     */
    @Override
	public Workbook newWorkbook(String pathFile){
        String []array = pathFile.split("\\.");

        Workbook workbook;

        if("xlsx".equals(array[array.length - 1])){
            workbook =  new XSSFWorkbook();
        }else{
            workbook =  new HSSFWorkbook();
        }
        return workbook;
    }

    /**
     * バーコード作成
     * @param message
     * @param barCordeFileName
     */
	@Override
	public void makeBarcode(String message, String barCordeFileName){
	    // 生成するバーコードの情報を宣言
	    ITF14Bean barcodeBean = new ITF14Bean();

	    int dpi = 40;

	    // バーコードを生成してファイルに出力する
	    try {
		  File file = new File(barCordeFileName);
	      FileOutputStream outputStream = new FileOutputStream(file);
	      BitmapCanvasProvider canvas = new BitmapCanvasProvider(outputStream, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	      barcodeBean.generateBarcode(canvas, message);
	      canvas.finish();
	      outputStream.close();
	    }catch(IOException exception) {
	      exception.printStackTrace();
	    }
	}

	/**
	 *
	 * @param sheet
	 * @return
	 */
	@Override
	public Map<String, List<Integer>> getMojiretuMap(Sheet sheet){
		Map<String, List<Integer>> rtnMap = new HashMap<>();

		for(Row row: sheet){ // 全行をなめる
			Integer y = row.getRowNum();
			for(Cell cell: row){ // 全セルをなめる
				String moji = cell.getStringCellValue();

				//文字列が空でない かつ 文字列の左端が "$"の場合、文字列を取得する
				if(!moji.isEmpty() && "$".equals(moji.substring(0, 1))){
					Integer x = cell.getColumnIndex();
					List<Integer> xyList = new ArrayList<>();
					xyList.add(x);
					xyList.add(y);
					rtnMap.put(moji, xyList);
				}
			}
		}
		return rtnMap;
	}

	/**
	 * ブックの全シートを取得する
	 * @param workbook
	 * @return
	 */
	@Override
	public Map<String, Sheet> getSheetMap(Workbook workbook){
		Map<String, Sheet> rtnMap = new HashMap<>();

        for(int s = 0; s < workbook.getNumberOfSheets(); ++s){
        	Sheet sheet = workbook.getSheetAt(s);
        	String sheetName = sheet.getSheetName();
        	rtnMap.put(sheetName, sheet);
        }

        return rtnMap;
	}


	/**
	 * シート取得
	 * @param pathFile
	 * @return
	 */
	@Override
	public Sheet getSheet(Workbook workbook, String inSheetName){
		Map<String, Sheet> map = getSheetMap(workbook);
		return map.get(inSheetName);
	}

	/**
	 * 線を引く情報を取得する
	 * @param sheet
	 * @return
	 */
	@Override
	public List<String> getStringList(Sheet sheet){

		List<String> rtnList = new ArrayList<>();

        for(Row row: sheet){ // 全行をなめる
        	Integer y = row.getRowNum();
        	for(Cell cell: row){ // 全セルをなめる
        		CellStyle cellStyle = cell.getCellStyle();
        		StringBuilder sb = new StringBuilder();
        		Integer x = cell.getColumnIndex();
        		sb.append(x + ",");
        		sb.append(y + ",");
        		// 線の太さ
         		sb.append(cellStyle.getBorderTop()    + ","); //上
        		sb.append(cellStyle.getBorderBottom() + ","); //下
        		sb.append(cellStyle.getBorderLeft()   + ","); //左
        		sb.append(cellStyle.getBorderRight()  + ","); //右
        		// 線の色
        		sb.append(PdfUtil.getInstance().getPdfColor(cellStyle.getTopBorderColor())    + ","); //上
        		sb.append(PdfUtil.getInstance().getPdfColor(cellStyle.getBottomBorderColor()) + ","); //下
        		sb.append(PdfUtil.getInstance().getPdfColor(cellStyle.getLeftBorderColor())   + ","); //左
        		sb.append(PdfUtil.getInstance().getPdfColor(cellStyle.getRightBorderColor())  + ","); //右
        		//背景色
        		sb.append(cellStyle.getFillForegroundColor() + ",");
        		//文字
        		sb.append(cell);

        		rtnList.add(sb.toString());
        	}
        }
        return rtnList;
	}

	/**
	 * 対角の座標（Object[0]=X、Object[1]=Y）を取得する
	 * @param cellColor
	 * @param cellBoolean
	 * @param inX
	 * @param inY
	 * @return
	 */
	@Override
	public List<Object> getXyList(Short [][]cellColor, Boolean [][]cellBoolean, Integer inX, Integer inY){

		Integer maxX = cellColor.length;
		Integer maxY = cellColor[0].length;

		Short color = cellColor[inX][inY];

		while (true){
			inX ++;
			if(inX > maxX){
				break;
			}
			if(cellColor[inX][inY] != color){
				break;
			}
		}
		inX --;
		while (true){
			inY ++;
			if(inY > maxY){
				break;
			}
			if(cellColor[inX][inY] != color){
				break;
			}
		}
		inY--;

		List<Object> objectList = new ArrayList<>();
		objectList.add(inX);
		objectList.add(inY);

		return objectList;
	}

	/**
	 *
	 * @param sheet
	 * @return
	 */
	@Override
	public Integer[] getXY(Sheet sheet){
		Integer[]xy = {0, 0};
     	xy[0] = new Integer(sheet.getRow(0).getLastCellNum());
   		xy[1] = sheet.getLastRowNum() + 1;
		return xy;
	}

	/**
	 *
	 * @param workbook
	 * @param sheet
	 * @param x
	 * @param y
	 * @return
	 */
	@Override
	public String getCellColor(Workbook workbook, Sheet sheet, int x, int y) {
		String rtnString = null;

        Row row = sheet.getRow(y - 1);

        Cell cell = null;

        try{
        	cell = row.getCell(x - 1);

        	if(cell == null) {
        		return rtnString;
        	}

    		CellStyle cellStyle = cell.getCellStyle();
    		Color color = cellStyle.getFillForegroundColorColor();
    		if (color != null) {
    	    	if (color instanceof XSSFColor) {
    	    		rtnString =  ((XSSFColor)color).getARGBHex();
    	    	} else if (color instanceof HSSFColor) {
    	    		if (! (color instanceof HSSFColor.AUTOMATIC)) {
    	    			rtnString = ((HSSFColor)color).getHexString();
    	    		}
    		    }
    	    }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return rtnString;
	}

	/**
	 * ワークブックを閉じる
	 * @param workbook
	 * @return
	 */
	@Override
	public boolean workbookClose(Workbook workbook) {
		try {
			workbook.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * キー部取得
	 * @param iMap
	 * @param sheetName
	 * @param y
	 * @return
	 */
	@Override
	public String getKey(Map<String,Map<String, Object>> iMap, String sheetName, int y) {
		Map<String, Object> map  = iMap.get(sheetName);
		return (String) map.get(Integer.valueOf(y) + Const.TAB + Const.ROW);
	}

	/**
	 *
	 * @param iMap
	 * @param sheetName
	 * @param y
	 * @param x
	 * @return
	 */
	@Override
	public String getData(Map<String,Map<String, Object>> iMap, String sheetName, int y, int x) {
		Map<String, Object> map  = iMap.get(sheetName);
		return getData(map, y, x);
	}


	/**
	 *
	 * @param iMap
	 * @param sheetName
	 * @param key1
	 * @param ichi
	 * @return
	 */
	@Override
	public String getData(Map<String,Map<String, Object>> iMap, String sheetName, String key1, int x) {
		Map<String, Object> map = iMap.get(sheetName);
		return getData(map, key1, x);
	}

	/**
	 *
	 * @param iMap
	 * @param sheetName
	 * @param key1
	 * @param key2
	 * @return
	 */
	@Override
	public String getData(Map<String,Map<String, Object>> iMap, String sheetName, String key1, String key2) {
		Map<String, Object> map = iMap.get(sheetName);
		return getData(map, key1, key2);
	}

	/**
	 *
	 * @param iMap
	 * @param y
	 * @param x
	 * @return
	 */
	@Override
	public String getData(Map<String, Object> iMap, int y, int x) {
		String key1 = (String) iMap.get(Integer.valueOf(y) + Const.TAB + Const.ROW);
		return getData(iMap,  key1, x);
	}

	/**
	 *
	 * @param iMap
	 * @param key1
	 * @param ichi
	 * @return
	 */
	@Override
	public String getData(Map<String, Object> iMap, String key1, int x) {
		String data[] = ((String) iMap.get(key1)).split(Const.TAB);
		return data[x - 2];
	}

	/**
	 *
	 * @param iMap
	 * @param key1
	 * @param key2
	 * @return
	 */
	@Override
	public String getData(Map<String, Object> iMap, String key1, String key2) {
		String key = Const.HEAD + Const.TAB + key2;
		Integer ichi = (Integer) iMap.get(key);
		String data[] = ((String) iMap.get(key1)).split(Const.TAB);
		return data[ichi - 2];
	}

	/**
	 *
	 * @param workbook
	 * @return
	 */
	@Override
	public Map<String, Map<String, Object>> makeMap(Workbook workbook) {
		Map<String, Map<String, Object>> rtnMap = new HashMap<>();

		Map<String, Sheet> sheetMap = getSheetMap(workbook);

		for(String key: sheetMap.keySet()) {
			Sheet sheet = sheetMap.get(key);

			Map<String, Object> map = makeMap(workbook, sheet);

			rtnMap.put(sheet.getSheetName(), map);
		}

		return rtnMap;
	}

	/**
	 * ブックデータアクセス用Map作成
	 * @param workbook
	 * @param sheet
	 * @return
	 */
	//-----------------------------------------------------------------
	@Override
	public Map<String, Object> makeMap(Workbook workbook, Sheet sheet) {
		Map<String, Object> rtnMap = new TreeMap<>();

		String sheetName = getSheetName(workbook);

		String headKey = sheetName + Const.TAB + Const.HEAD;

	   	//HEADキー情報作成 ------------------------------------------------------------
		int x = 1;

		StringBuffer val = new StringBuffer("");

		String cel = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, x, 1);

		while (cel.length() > 0) {
    	   val.append(cel + Const.TAB);
    	   x ++;
    	   cel = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, x, 1);
		}
		val.delete(val.length()-1, val.length());

		rtnMap.put(headKey, val.toString());

		//HEADの位置情報作成 ----------------------------------------------------------
		String headArray = (String) rtnMap.get(headKey);

		String dataArray[] = headArray.split(Const.TAB);

		for(int i = 0; i <  dataArray.length; i++ ) {
			String key = Const.HEAD + Const.TAB + dataArray[i];
			rtnMap.put(key, Integer.valueOf(i + 1));
		}

		//----------------------------------------------------------------------------
       int koumokuSuu = x - 1;

       int y = 2;

       String key = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, 1, y);

       while (key.length() > 0) {
    	   StringBuffer value = new StringBuffer("");
    	   for(int x2 = 2; x2 <= koumokuSuu; x2++) {
    		   String celData = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, x2, y) + Const.TAB;
    		   value.append(celData);
    	   }
    	   value.delete(value.length()-1, value.length());
    	   rtnMap.put(key, value.toString());
    	   y ++;
    	   key = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, 1, y);
       }

       //LOW情報作成(キー=y+TAB+"R"  値=キー部------------------------------------------
       y = 2;

       key = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, 1, y);

       while (key.length() > 0) {
    	   rtnMap.put(Integer.valueOf(y) + Const.TAB + Const.ROW, key);
    	   y ++;
    	   key = ExcelDesignUtil.getInstance().getStringValue(workbook, sheet, 1, y);
       }

       return rtnMap;
	}

	/**
	 * シート名を取得する
	 * @param workbook
	 * @return
	 */
	@Override
	public String getSheetName(Workbook workbook) {
		int no =workbook.getActiveSheetIndex();
		return workbook.getSheetName(no);
	}
}
