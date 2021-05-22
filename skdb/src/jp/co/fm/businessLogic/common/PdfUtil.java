package jp.co.fm.businessLogic.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.poi.ss.usermodel.Sheet;

import com.util.bean.pdf.PdfDataBean;

public interface PdfUtil {

	String COLOR_WHITE = "255255255"; //白
	String COLOR_YELLOW = "255255000"; //黄色
	String COLOR_PERPLE = "255000255"; //赤紫
	String COLOR_RED = "255000000"; //赤
	String COLOR_LIGHT_BLUE = "255255"; //水色
	String COLOR_YELLOW_GREEN = "255000"; //黄緑
	String COLOR_BLUE = "255"; //青
	String COLOR_BLACK = "0"; //黒
	String EXCEL_COLOR = "10:255000000," //赤
			+ "13:255255000," //黄色
			+ "17:255000," //黄緑
			+ "30:255"; //青
	Double PAGE_WIDTH = 610d;
	Double PAGE_HIGHT = 790d;

	PdfDataBean makePdfDataBean();

	/**
	 *
	 * @param pdfPath
	 * @param pdfDataBean
	 * @return
	 */
	boolean pdfPrint(String pdfPath, PdfDataBean pdfDataBean);

	/**
	 *
	 * @param pDPageContentStream
	 * @param pDDocument
	 * @param pdfImageFileBeanList
	 * @throws IOException
	 */
	void printImage(PDPageContentStream pDPageContentStream, PDDocument pDDocument,
			List<Map<String, Object>> pdfImageFileBeanList);

	/**
	 *
	 * @param pDPageContentStream
	 * @param maxX
	 * @param maxY
	 * @param pdfFillBoxBeanList
	 * @throws IOException
	 */
	void printFillLine(PDPageContentStream pDPageContentStream, Integer maxX, Integer maxY,
			List<Map<String, Object>> pdfFillBoxBeanList) throws IOException;

	/**
	 * 文字に色を付ける
	 * 数値文字列９ケタを３ケタづつ区切る。例 "255255255"⇒ 255 255 255
	 * @param pDPageContentStream
	 * @param moji
	 * @throws IOException
	 */
	void setNonStrokingColor(PDPageContentStream pDPageContentStream, String moji) throws IOException;

	Double getPointX(Integer bunkatuX, Integer x);

	Double getPointY(Integer bunkatuY, Integer y);

	/**
	 * ラインを引く
	 * @param contents
	 * @param objectList
	 * @throws IOException
	 */
	void printLine(PDPageContentStream pDPageContentStream, Integer maxX, Integer maxY,
			List<Map<String, Object>> objectList) throws IOException;

	/**
	 * PDFページ作成
	 * @param mojiDataList
	 * @param lineDataList
	 * @param maxX
	 * @param maxY
	 * @throws IOException
	 */
	void printPage(PdfDataBean pdfDataBean) throws IOException;

	/**
	 * カラーコード変換
	 *
	 * @param excelColor
	 * @return
	 */
	String getPdfColor(Short excelColor);

	List<Map<String, Object>> getObjectArrayList(Sheet sheet);

	/**
	 * 横線（上）描画情報編集
	 * @param data
	 * @return
	 */
	List<Map<String, Object>> getLineDataX(String data);

	/**
	 *
	 * @param fileName
	 * @return
	 */
	PdfDataBean decodeExcel(Sheet sheet);

	/**
	 *
	 * @param mojiretuMap
	 * @param key
	 * @param moji
	 * @return
	 */
	Map<String, Object> getObjArray(Map<String, List<Integer>> mojiretuMap, String key, Integer mojiSize, String color,
			String moji);

	/**
	 *
	 * @param sheet
	 * @param maxX
	 * @param maxY
	 * @return
	 */
	List<Map<String, Object>> getColorMap(Sheet sheet, Integer maxX, Integer maxY);

	/**
	 *
	 * @param fileName
	 * @return
	 */
	List<Map<String, Object>> getExcelMojiList(Sheet sheet);

	/**
	 * 色変換
	 * @param pdfFillBoxMapList
	 * @throws IOException
	 */
	void changeColor(List<Map<String, Object>> pdfFillBoxMapList);

	/**
	 * オブジェクト配列をXMLファイルに出力する
	 * @param fileName
	 * @param lineDataList
	 * @param excelMojiList
	 * @param maxX
	 * @param maxY
	 */
	void encodeXml(String fileName, PdfDataBean pdfDataBean);

	/**
	 *
	 * @param fileName
	 * @param jsonPdfDataBean
	 */
	void encodeJson(String fileName, PdfDataBean jsonPdfDataBean);

	/**
	 * XMLファイルをオブジェクト配列に復元する
	 * @param fileName
	 * @return
	 */
	PdfDataBean decodeXml(String fileName);

	/**
	 *
	 * @param fileName
	 * @return
	 */
	PdfDataBean decodeJson(String fileName);

	/**
	 *
	 * @param o1
	 * @param o2
	 * @param o3
	 * @param o4
	 * @param color
	 * @return
	 */
	Map<String, Object> setColor(Object o1, Object o2, Object o3, Object o4, Object color);

	/**
	 *
	 * @param o1
	 * @param o2
	 * @param barCordeFileName
	 * @return
	 */
	Map<String, Object> setImage(Integer o1, Integer o2, String barCordeFileName);

	Integer[] getXY(Sheet sheet);

	/**
	 * 対角の座標（Object[0]=X、Object[1]=Y）を取得する
	 * @param cellColor
	 * @param cellBoolean
	 * @param inX
	 * @param inY
	 * @return
	 */
	List<Object> getXyList(Short[][] cellColor, Boolean[][] cellBoolean, Integer inX, Integer inY);

	void pdfToJpeg(String pathFilePdf, String pathFileJpeg, int page);

	void rasterize(InputStream in, OutputStream out, int page);

	/**
	 * PDFファイルのページ数を返す
	 * @param pathFilePdf
	 * @return
	 */
	int getPage(String pathFilePdf);

	/**
	 * PDFファイルのページ数を返す
	 * @param in
	 * @return
	 */
	int getPage(InputStream in);

	/**
	 *
	 * @param map
	 * @param key
	 * @return
	 */
	Integer changeInteger(Map map, String key);

	PdfUtil pdfUtil = new PdfUtilImpl();

	public static PdfUtil getInstance() {
		return pdfUtil;
	}
}