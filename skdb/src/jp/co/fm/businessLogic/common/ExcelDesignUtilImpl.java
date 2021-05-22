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

import workFlow.common.WorkFlowConst;


/**
 * エクセル利用設計ユーティリティクラス
 * @author fmsof
 *
 */
public class ExcelDesignUtilImpl implements ExcelDesignUtil {

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

    @Override
    public void makeWorkbook(String fileName) {
        //新規ワークブックを作成する
        Workbook workbook = upsertWorkbook(fileName);

        try{
            File f = new File(fileName);
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
     * @param fileName
     * @return
     */
    @Override
	public Workbook upsertWorkbook(String fileName){

        int bool = FileUtil.getInstance().isExistFile(fileName);

        Workbook workbook;

        if(bool == 0){
            workbook = getWorkbook(fileName);
        }else{
            workbook = newWorkbook(fileName);
        }

        return workbook;
    }

    @Override
	public Workbook getWorkbook(String fileName){

        Workbook book = null;

        FileInputStream fi;
        try {
            fi   = new FileInputStream(fileName);
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
	public String getStringValue(Workbook workbook, Sheet sheet, Integer x, Integer y){
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

    @Override
	public Workbook newWorkbook(String fileName){
        String []array = fileName.split("\\.");

        Workbook workbook;

        if("xlsx".equals(array[array.length - 1])){
            workbook =  new XSSFWorkbook();
        }else{
            workbook =  new HSSFWorkbook();
        }
        return workbook;
    }

	@Override
	public void makeBarCorde(String message, String barCordeFileName){
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
	 * @param fileName
	 * @return
	 */
	@Override
	public Map<String, List<Integer>> getMojiretuMap(String fileName){
		Map<String, List<Integer>> rtnMap = new HashMap<>();

		Sheet sheet = getSheet(fileName, "data");

		List<Map<String, Object>> objectList = new ArrayList<>();

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
	 * シート取得
	 * @param fileName
	 * @return
	 */
	@Override
	public Sheet getSheet(String fileName, String inSheetName){
		Sheet sheet = null;

	    Workbook workbook = getWorkbook(fileName);

         for(int s = 0; s < workbook.getNumberOfSheets(); ++s){ // 全シートをなめる(※)
        	 sheet = workbook.getSheetAt(s);
        	 String sheetName = sheet.getSheetName();
        	 if (sheetName.contains(inSheetName)){
        		 return sheet;
        	 }
         }

		return sheet;
	}

	/**
	 * 線を引く情報を取得する
	 * @param sheet
	 * @return
	 */
	@Override
	public List<String> getStringList(Sheet sheet){

		PdfUtil pu = PdfUtil.getInstance();

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
        		sb.append(pu.getPdfColor(cellStyle.getTopBorderColor())    + ","); //上
        		sb.append(pu.getPdfColor(cellStyle.getBottomBorderColor()) + ","); //下
        		sb.append(pu.getPdfColor(cellStyle.getLeftBorderColor())   + ","); //左
        		sb.append(pu.getPdfColor(cellStyle.getRightBorderColor())  + ","); //右
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

	@Override
	public Integer[] getXY(Sheet sheet){
		Integer[]xy = {0, 0};
     	xy[0] = new Integer(sheet.getRow(0).getLastCellNum());
   		xy[1] = sheet.getLastRowNum() + 1;
		return xy;
	}


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
	 * セルの情報を取得する
	 * @param workbook
	 * @param sheet
	 * @param xStart
	 * @param xEnd
	 * @param y
	 * @return
	 */
	@Override
	public List<String> getCellInfoList(Workbook workbook, Sheet sheet, int xStart, int xEnd, int y){
		List<String> rtnList = new ArrayList<>();

    	for(int x = xStart; x <= xEnd; x ++ ) {
    		String st = getStringValue(workbook,  sheet, x, y);
    		rtnList.add(st);
    	}

    	wrkbookClose(workbook);
    	return rtnList;
	}

	/**
	 * ワークブックを閉じる
	 * @param workbook
	 * @return
	 */
	@Override
	public boolean wrkbookClose(Workbook workbook) {
		try {
			workbook.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, List<String>> getItemNameMap(Workbook workbook, Sheet sheet){
		Map<String,List<String>> rtnMap = new HashMap<>();

		List<Integer> keyValueIchiList = getKeyValueIchiList(workbook, sheet);

		List<String> cellInfoList = getCellInfoList(workbook, sheet, keyValueIchiList.get(0), keyValueIchiList.get(3), 1);

		List<String> keyList   = makeList(cellInfoList, keyValueIchiList.get(0) - 1, keyValueIchiList.get(1) - 1);
		List<String> valueList = makeList(cellInfoList, keyValueIchiList.get(2) - 1, keyValueIchiList.get(3) - 1);

		rtnMap.put("key", keyList);
		rtnMap.put("value", valueList);

		return rtnMap;
	}

	private List<String> makeList(List<String> list, int start, int end) {
		List<String> trnList = new ArrayList<>();

		for(int i = start; i <= end; i ++) {
			trnList.add(list.get(i));
		}

		return trnList;
	}

	/**
	 *
	 * @param pathFile
	 * @param sheetName
	 * @return list(0):キーの開始位置、list(1):キーの終了位置、list(2):値の開始位置、list(3):値の終了位置
	 */
	@Override
	public List<Integer> getKeyValueIchiList(Workbook workbook, Sheet sheet){
		List<Integer> list = new ArrayList<>();

		int x = 1;

		list.add(x); //

		while(true){
			String collor = getCellColor(workbook, sheet, x, 1);
			if(WorkFlowConst.COLOR_KEY.equals(collor)) {
				x ++;
				continue;
			}

			list.add(x - 1);
			break;
		}

		x = list.get(1) + 1;

		list.add(x);

		while(true){
			String collor = getCellColor(workbook, sheet, x, 1);
			if(WorkFlowConst.COLOR_VALUE.equals(collor)) {
				x ++;
				continue;
			}
			list.add(x - 1);
			break;
		}

    	wrkbookClose(workbook);

		return list;

	}

	/**
	 *
	 * @param pathFile
	 * @param sheetName
	 * @return
	 */
	@Override
	public Map<String, Object> getMap(Workbook workbook, Sheet sheet){

		List<Integer> list = getKeyValueIchiList(workbook, sheet);

		Map<String, Object> map = getMap(workbook, sheet, 2, list.get(0), list.get(1), list.get(2), list.get(3));

		Map<String, List<String>> mmmm = getItemNameMap(workbook, sheet);

		map.putAll(mmmm);

		return map;
	}

	/**
	 *
	 * @param workbook
	 * @param sheet
	 * @param startRow
	 * @param keyColumnS
	 * @param keyColumnE
	 * @param valueColumnS
	 * @param valueColumnE
	 * @return
	 */
	@Override
	public Map<String, Object> getMap(Workbook workbook, Sheet sheet, int startRow, int keyColumnS, int keyColumnE, int valueColumnS, int valueColumnE){
		Map<String, Object> rtnMap = new HashMap<>();

		int row = startRow;

		while (true) {
			StringBuffer keySb = new StringBuffer();

			String st;

			for(int x = keyColumnS; x <= keyColumnE; x++) {
				st = getStringValue(workbook,  sheet, x, row);
				if(st.length() > 0) {
					keySb.append(st);
					keySb.append("\t");
				}
			}

			if(keySb.length() == 0) {
				break ;
			}

			keySb.delete(keySb.length() - 1, keySb.length());

			StringBuffer valueSb = new StringBuffer();;

			for(int x = valueColumnS; x <= valueColumnE; x++) {
				valueSb.append(getStringValue(workbook,  sheet, x, row));
				valueSb.append("\t");
			}
			valueSb.delete(valueSb.length() - 1, valueSb.length());

			rtnMap.put(keySb.toString(), valueSb.toString());

			row ++;
		}

    	wrkbookClose(workbook);

		return rtnMap;
	}
}
