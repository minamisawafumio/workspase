package jp.co.fm.businessLogic.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

public interface StringUtil {

	/**
	 * 文字列変換(テーブル項目名の変換に使用)    Unicode から UTF-8
	 * キャメル文字列をアンダーバー("_")繋ぎ文字列に変換する
	 * @param inStr
	 * @return
	 */
	String changeCamelItem2UnderItem(String inStr);

	/**
	 * 文字列変換(テーブル項目名の変換に使用)
	 * "_"の次の文字を"_"を取って大文字に変換する
	 * @param inStr
	 * @return
	 */
	String changeKoumokuName(String inStr);

	/**
	 * 文字列変換
	 * 文字列内の "?" 文字を順次、左から、配列の内容で置換する
	 * @param inputMoji
	 * @param keyList
	 * @return
	 */
	String bindMoji(String inputMoji, Object[] objectArray);

	/**
	 * 文字列変換
	 * 文字列内の "?" 文字を順次、左から、リストの内容で置換する
	 * @param inputMoji
	 * @param keyList
	 * @return
	 */
	String changeBindMoji(String inMoji, List<Object> objectList);

	/**
	 * 指定文字列に記号が何文字含まれるか
	 *
	 * @param inMojiretu
	 * @return
	 */
	Integer countExistKigou(String inMojiretu);

	/**
	 * 指定文字列に半角英字（小文字）が何文字含まれるか
	 *
	 * @param inMojiretu
	 * @return
	 */
	Integer countExistHankakuEijiL(String inMojiretu);

	/**
	 * 指定文字列に半角英字（大文字）が何文字含まれるか
	 *
	 * @param inMojiretu
	 * @return
	 */
	Integer countExistHankakuEijiB(String inMojiretu);

	/**
	 * 指定文字列に数字が何文字含まれるか
	 *
	 * @param inMojiretu
	 * @return
	 */
	Integer countExistSuuji(String inMojiretu);

	/**
	 * 指定文字列に文字配列中の文字が何文字含まれるか
	 *
	 * @param inMojiretu
	 * @return
	 */
	Integer countExistMoji(String inMojiretu, String[] data);

	FileInputStream string2FileInputStream(String string);

	/**
	 *
	 * @param in
	 * @return
	 */
	String fileInputStream2String(FileInputStream in);

	/**
	 *
	 * @param is
	 * @return
	 */
	String fileInputStream2String2(FileInputStream is);

	/**
	 * 文字列をインプットストリームに変換する
	 * @param input
	 * @return
	 */
	InputStream string2InputStream(String input);

	/**
	 *
	 * @param input
	 * @return
	 */
	String getPrintPwd(String input);

	/**
	 * 指定文字を指定回数分繰り返して返す
	 * @param ch
	 * @param repeatTime
	 * @return
	 */
	String repeatChar(Character ch, Integer repeatTime);

	/**
	 * 文字列をハッシュ化して返す
	 * @param input
	 * @return
	 */
	String encryption(String input, String code);

	/**
	 * メールアドレスチェック
	 * @param input
	 * @return
	 */
	Boolean isMailAddress(String input);

	/**
	 *
	 * @param input
	 * @return
	 */
	Boolean isEmpty(String input);

	/**
	 * 半角英数字チェック
	 * @param input
	 * @return
	 */
	Boolean isNumeric(String input);

	/**
	 * 半角数値チェック
	 * @param str
	 * @return
	 */
	boolean isHalfNumeric(String str);

	/**
	 *
	 * @param c
	 * @return
	 */
	String getQuote(Class c);

	/*
	 * 文字列を暗号化する(全角文字は不可)
	 */
	String angouka(String inputdata1, String inputdata2);

	/**
	   * 文字列を復元化する(全角文字は不可)
	   */
	String hukugouka(String sss, String key);

	/**
	 * 文字列より、最初のトークン以前の内容を返す
	 * @param moji
	 * @return
	 */
	String getFirstIndexOfString(String moji, String token);

	/**
	 * 文字列より、最後のトークン以降の内容を返す
	 * @param str
	 * @param token
	 * @return
	 */
	String getLastIndexOfString(String str, String token);

	/**
	 * 入力文字がnullの場合、空白 を返す
	 * @param moji
	 * @return
	 */
	String getSpase(String moji);

	/**
	 * 入力値がnullの場合、空白 を返す
	 * @param moji
	 * @return
	 */
	String getSpase(Integer moji);

	/**
	 * 文字化け対応
	 * @param str
	 * @param fromCode
	 * @param toCode
	 * @return
	 */
	String changeCode(String str, String fromCode, String toCode);

	/**
	 * 文字化け対応
	 * @param str
	 * @param fromCode
	 * @param toCode
	 * @return
	 */
	String decode(String str);

	/*
	 * 文字列をトークンで分割してListで返す
	 **/
	List<String> splitStringList(String str, String token);

	/**
	 * 文字列をトークンで分割してListで返す (空の要素を削除しない)
	 * @param value
	 * @param token
	 * @return
	 */
	List<String> splitStringList2(String value, String token);

	/**
	 *
	 * 文字列をトークンで分割してMapで返す (空の要素を削除する)
	 * キーは、項目位置の順番
	 * @param str
	 * @param token
	 * @return
	 */
	Map<String, String> splitStringMap(String str, String token);

	/**
	* 先頭の１文字を小文字にして返す
	*/
	String headLowerCase(String s);

	/**
	* 先頭の１文字を大文字にして返す
	*/
	String headUpperCase(String s);

	/**
	 * フォーマット変換
	 * format="000000" suu=123 → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	String changeFormat(String format, long suu);

	/**
	 * フォーマット変換
	 * format="000000" suu=123 → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	String changeFormat(String format, int suu);

	/**
	 * フォーマット変換
	 * format="000000" suu=123 → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	String changeFormat(String format, double suu);

	/**
	 * フォーマット変換
	 * format="000000" suu="123" → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	String changeFormat(String format, String suu);

	/**
	 * 文字列の頭0を取る
	 * @param str
	 * @return
	 */
	String trimLeftZero(String str);

	/**
	 * 数値を3桁毎にカンマで区切る
	 * @param suu
	 * @return
	 */
	String changeFormatCamma(String suu);

	/**
	 * 数値を3桁毎にカンマで区切る
	 * @param suu
	 * @return
	 */
	String changeFormatCamma(Integer suu);

	/**
	 * 文字列⇒Long数値変換
	 * @param num
	 * @return
	 */
	Long changeLong(String number);

	/**
	 *
	 * 全角チェック
	 * @param item
	 * @return
	 */
	boolean checkZenkaku(String item);

	/**
	 *
	 * 半角（大文字／英字）チェック
	 * @param item
	 * @return
	 */
	boolean checkHankaku(String item);

	/**
	 * オブジェクトを文字列に変換する
	 * @param object
	 * @return
	 */
	String convert(Object object);

	/**
	 * 文字列変換
	 * @param inputMoji
	 * @param keyMap
	 * @return
	 */
	String changeBindMoji(String inputMoji, Map<String, String> keyMap);

	/**
	 *
	 * @param suu
	 * @return
	 */
	String getカンマ区切(Object suu);

	/**
	 * byteデータを文字列に変換する。(改行コード(CRLF)あり)
	 *
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	String encodeBase64(byte[] data) throws IOException, UnsupportedEncodingException, MessagingException;

	/**
	 * byteデータを文字列に変換する。(改行コード(CRLF)なし)
	 * @param data
	 * @return
	 */
	String encodeBase64_2(byte[] data);

	/**
	 * テーブルの項目名を作成する。(aaa_bbb_cc ⇒ aaaBbbCc)
	 * @param inStr
	 * @return
	 */
	String makeTableKoumokuName(String inStr);

	/**
	 * 引数の文字列がnullの場合に""（長さ0の文字列）に変換します。
	 * @param inStr
	 * @return
	 */
	String cnvNull(String inStr);

	/**
	 * テーブル項目のセッター名を取得する
	 * "aa_bb_cc ⇒ setAaBbCc"
	 * @param inStr
	 * @return
	 */
	String getSetterName(String inStr);

	/**
	 * 文字列をにbyteデータ復元する。
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	byte[] decodeBase64(String data) throws IOException, UnsupportedEncodingException, MessagingException;

	/**
	 * デコード(Base64)
	 * @param moji
	 * @return
	 */
	String decodeStBase64(String moji);

	/**
	 * エンコード(Base64)
	 * @param data
	 * @return
	 */
	String encodeStBase64(String moji);

	/**
	 * 文字列に指定数を加算して文字列で返す
	 * @param suu
	 * @param addCount
	 * @return
	 */
	String addNum(String suu, int addCount);

	StringUtil stringUtil = new StringUtilImpl();

	public static StringUtil getInstance() {
		return stringUtil;
	}

	boolean isExistCRLF(String inStr);

	String deleteCRLF(String inStr);
}