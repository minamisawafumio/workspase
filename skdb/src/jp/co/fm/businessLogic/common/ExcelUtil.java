package jp.co.fm.businessLogic.common;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelUtil {

	/**
	 *
	 * @param f
	 * @param workbook
	 * @param executeList
	 */
	void updateExcelBook(File f, Workbook workbook, List<String> executeList);

	/**
	 *
	 * @param pathFile
	 */
	void makeWorkbook(String pathFile);

	/**
	 * Workbookを取得する（無ければ新規に作成）
	 * @param pathFile
	 * @return
	 */
	Workbook upsertWorkbook(String pathFile);

	/**
	 *
	 * @param pathFile
	 * @return
	 */
	Workbook getWorkbook(String pathFile);

	/**
	 * セルの内容を取得する。 内容が空か、文字列以外の場合は "" を返す。
	 * @param workbook
	 * @param sheetName
	 * @param x
	 * @param y
	 * @return
	 */
	String getCellValue(Workbook workbook, Sheet sheet, Integer x, Integer y);

	/**
	 *
	 * @param pathFile
	 * @return
	 */
	Workbook newWorkbook(String pathFile);

	/**
	 * バーコード作成
	 * @param message
	 * @param barCordeFileName
	 */
	void makeBarcode(String message, String barCordeFileName);

	/**
	 *
	 * @param sheet
	 * @return
	 */
	Map<String, List<Integer>> getMojiretuMap(Sheet sheet);

	/**
	 * ブックの全シートを取得する
	 * @param workbook
	 * @return
	 */
	Map<String, Sheet> getSheetMap(Workbook workbook);

	/**
	 * シート取得
	 * @param pathFile
	 * @return
	 */
	Sheet getSheet(Workbook workbook, String inSheetName);

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

	/**
	 *
	 * @param sheet
	 * @return
	 */
	Integer[] getXY(Sheet sheet);

	/**
	 *
	 * @param workbook
	 * @param sheet
	 * @param x
	 * @param y
	 * @return
	 */
	String getCellColor(Workbook workbook, Sheet sheet, int x, int y);

	/**
	 * ワークブックを閉じる
	 * @param workbook
	 * @return
	 */
	boolean workbookClose(Workbook workbook);

	/**
	 * キー部取得
	 * @param iMap
	 * @param sheetName
	 * @param y
	 * @return
	 */
	String getKey(Map<String, Map<String, Object>> iMap, String sheetName, int y);

	/**
	 *
	 * @param iMap
	 * @param sheetName
	 * @param y
	 * @param x
	 * @return
	 */
	String getData(Map<String, Map<String, Object>> iMap, String sheetName, int y, int x);

	/**
	 *
	 * @param iMap
	 * @param sheetName
	 * @param key1
	 * @param ichi
	 * @return
	 */
	String getData(Map<String, Map<String, Object>> iMap, String sheetName, String key1, int x);

	/**
	 *
	 * @param iMap
	 * @param sheetName
	 * @param key1
	 * @param key2
	 * @return
	 */
	String getData(Map<String, Map<String, Object>> iMap, String sheetName, String key1, String key2);

	/**
	 *
	 * @param iMap
	 * @param y
	 * @param x
	 * @return
	 */
	String getData(Map<String, Object> iMap, int y, int x);

	/**
	 *
	 * @param iMap
	 * @param key1
	 * @param ichi
	 * @return
	 */
	String getData(Map<String, Object> iMap, String key1, int x);

	/**
	 *
	 * @param iMap
	 * @param key1
	 * @param key2
	 * @return
	 */
	String getData(Map<String, Object> iMap, String key1, String key2);

	/**
	 *
	 * @param workbook
	 * @return
	 */
	Map<String, Map<String, Object>> makeMap(Workbook workbook);

	/**
	 * ブックデータアクセス用Map作成
	 * @param workbook
	 * @param sheet
	 * @return
	 */
	//-----------------------------------------------------------------
	Map<String, Object> makeMap(Workbook workbook, Sheet sheet);

	/**
	 * シート名を取得する
	 * @param workbook
	 * @return
	 */
	String getSheetName(Workbook workbook);

	ExcelUtil excelUtil = new ExcelUtilImpl();

	public static ExcelUtil getInstance() {
		return excelUtil;
	}
}