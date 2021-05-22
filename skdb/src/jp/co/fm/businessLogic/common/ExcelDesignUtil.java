package jp.co.fm.businessLogic.common;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelDesignUtil {

	void updateExcelBook(File f, Workbook workbook, List<String> executeList);

	void makeWorkbook(String fileName);

	/**
	 * Workbookを取得する（無ければ新規に作成）
	 * @param fileName
	 * @return
	 */
	Workbook upsertWorkbook(String fileName);

	Workbook getWorkbook(String fileName);

	/**
	 * セルの内容を取得する。 内容が空か、文字列以外の場合は "" を返す。
	 * @param workbook
	 * @param sheetName
	 * @param x
	 * @param y
	 * @return
	 */
	String getStringValue(Workbook workbook, Sheet sheet, Integer x, Integer y);

	Workbook newWorkbook(String fileName);

	void makeBarCorde(String message, String barCordeFileName);

	/**
	 *
	 * @param fileName
	 * @return
	 */
	Map<String, List<Integer>> getMojiretuMap(String fileName);

	/**
	 * シート取得
	 * @param fileName
	 * @return
	 */
	Sheet getSheet(String fileName, String inSheetName);

	/**
	 * 線を引く情報を取得する
	 * @param sheet
	 * @return
	 */
	List<String> getStringList(Sheet sheet);

	/**
	 * 対角の座標（Object[0]=X、Object[1]=Y）を取得する
	 * @param cellColor
	 * @param cellBoolean
	 * @param inX
	 * @param inY
	 * @return
	 */
	List<Object> getXyList(Short[][] cellColor, Boolean[][] cellBoolean, Integer inX, Integer inY);

	Integer[] getXY(Sheet sheet);

	String getCellColor(Workbook workbook, Sheet sheet, int x, int y);

	/**
	 * セルの情報を取得する
	 * @param workbook
	 * @param sheet
	 * @param xStart
	 * @param xEnd
	 * @param y
	 * @return
	 */
	List<String> getCellInfoList(Workbook workbook, Sheet sheet, int xStart, int xEnd, int y);

	/**
	 * ワークブックを閉じる
	 * @param workbook
	 * @return
	 */
	boolean wrkbookClose(Workbook workbook);

	Map<String, List<String>> getItemNameMap(Workbook workbook, Sheet sheet);

	/**
	 *
	 * @param pathFile
	 * @param sheetName
	 * @return list(0):キーの開始位置、list(1):キーの終了位置、list(2):値の開始位置、list(3):値の終了位置
	 */
	List<Integer> getKeyValueIchiList(Workbook workbook, Sheet sheet);

	/**
	 *
	 * @param pathFile
	 * @param sheetName
	 * @return
	 */
	Map<String, Object> getMap(Workbook workbook, Sheet sheet);

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
	Map<String, Object> getMap(Workbook workbook, Sheet sheet, int startRow, int keyColumnS, int keyColumnE,
			int valueColumnS, int valueColumnE);

	ExcelDesignUtil excelDesignUtil = new ExcelDesignUtilImpl();

	public static ExcelDesignUtil getInstance() {
		return excelDesignUtil;
	}

}