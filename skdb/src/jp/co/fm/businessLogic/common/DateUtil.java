package jp.co.fm.businessLogic.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public interface DateUtil {

	/**
	 * 00時以降の時間変更
	 * (例:030505 → 270505)
	 * @param hhmmss
	 * @return
	 */
	public String changeHhMmssForBeforDay(String hhmmss);

	/**
	 * コンピュータ日付を取得する
	 * @return
	 */
	String getComDateTime(Integer i);

	/**
	 * システム日付を取得する
	 * @return
	 */
	Date getSystemDate();

	/**
	 * Timestamp 変換
	 *
	 * @param str
	 * @return
	 */
	Timestamp getTimestamp(String str);

	/**
	 * 日付フォーマット変換
	 * @param yyyymmdd
	 * @return
	 */
	String changeFormatDate(String yyyymmdd);

	/**
	 * 指定日からＮか月後の日付を取得する
	 * @param inDate    YYYYMMDD
	 * @param addMonth  月数
	 * @return
	 */
	String getAddMonthDate(String inDate, Integer addMonth);

	/**
	 * 指定日数後の日付
	 * 指定日からＮ日後の日付を取得する
	 * 例 addDay("2004/02/29", 15)
	 * @param date
	 * @param addDate
	 * @return YyyyMMddHHmmssSSS
	 */
	String getInDesignateDays(String inYyyyMMddHHmmssSSS, Integer addDate);

	/**
	 * 経過日数計算
	 * @param startYyyyMmDd
	 * @param endYyyyMmDd
	 * @return
	 */
	Long getIntervalDayCount(String startYyyyMmDd, String endYyyyMmDd);

	/**
	 * 経過日数計算
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Long getIntervalDayCount(Date startDate, Date endDate);

	Long difference(Date startDate, Date endDate);

	/**
	* 経過ミリ秒数を取得する
	* @param startYyyyMMddHHmmssSSS
	* @param endYyyyMMddHHmmssSSS
	* @return
	*/
	Long difference(String startYyyyMMddHHmmssSSS, String endYyyyMMddHHmmssSSS);

	/**
	 * 経過時間取得
	 * @param startYyyyMMddHHmmssSSS
	 * @param endYyyyMMddHHmmssSSS
	 * @return 経過時間の配列(時、分、秒、ミリ秒)
	 */
	Integer[] differenceArray(String startYyyyMMddHHmmssSSS, String endYyyyMMddHHmmssSSS);

	/**
	 * 経過時間を文字列で返す
	 * @param startYyyyMMddHHmmssSSS
	 * @param endYyyyMMddHHmmssSSS
	 * @return
	 */
	String getDifferenceHHMMDDSSS(String startYyyyMMddHHmmssSSS, String endYyyyMMddHHmmssSSS);

	/**
	 * 日付文字列指定でDate型を生成
	 * @param yyyyMMddHHmmssSSS
	 * @return
	 */
	Date getDate(String yyyyMMddHHmmssSSS);

	/**
	 * 指定日を数値配列に変換する
	 * @param yyyymmdd
	 * @return 0:年 1:月 2:日
	 */
	Integer[] getIntDay(String yyyymmdd);

	/**
	 * 指定日のカレンダーを取得する
	 * @param _year
	 * @param _month
	 * @param _day
	 * @return
	 */
	Calendar getWeekCalendar(Integer _year, Integer _month, Integer _day);

	/**
	 * 指定日の曜日を取得する
	 * @param _year
	 * @param _month
	 * @param _day
	 * @return 1:日 2:月 3:火 4:水 5:木 6:金 7:土
	 */
	Integer getWeekInteger(Integer _year, Integer _month, Integer _day);

	/**
	 * カレンダー
	 * @return
	 */
	String getYyyyMMddHHmmssSSS(Calendar calendar);

	/**
	 *
	 * @param date
	 * @return
	 */
	String getYyyyMMddHHmmssSSS(Date date);

	/**
	 *
	 * @param date
	 * @return
	 */
	String getYyyyMMdd(Date date);

	/**
	* 戻り値のフォーマット YYYYMMDD
	*/
	String getDayFormat(Integer _year, Integer _month, Integer _day);

	/**
	* 戻り値のフォーマット yyyyMMddHHmmssSSS
	*/
	String getDayFormat(Integer _Ye, Integer _Ma, Integer _Da, Integer _Ho, Integer _Mi, Integer _Se, Integer _Ms);

	/**
	 * 時分秒変換
	 * @param _ho
	 * @param _mi
	 * @param _se
	 * @return
	 */
	String getTimeFormat(Integer _ho, Integer _mi, Integer _se);

	String getTimeFormat(Integer _ho, Integer _mi, Integer _se, Integer _ms);

	/**
	 * 時分変換
	 * @param _ho
	 * @param _mi
	 * @return
	 */
	String getTimeFormat(Integer _ho, Integer _mi);

	/**
	* ミリ秒
	*/
	String getMillisecondFormat(Integer intMillisecond);

	/**
	 * 指定日付(YYYYMMDD)の妥当性チェック
	 * @param inputDate
	 * @return
	 */
	Boolean checkDate(String yyyymmdd);

	/**
	 * 指定月の最終日を取得する
	 * @param yearMonth
	 * @return
	 */
	Integer getMonthEndDay(String yearMonth);

	/**
	 * 現在の曜日を返します
	 * @return	現在の曜日
	 */
	String getDayOfTheWeek(Calendar cal);

	/**
	 * 現在の曜日を返します
	 * @param yyyymmdd
	 * @return
	 */
	String getDayOfTheWeek(String yyyymmdd);

	/**
	 * java.util.Date から java.sql.Dateに変換する
	 * @param date
	 * @return
	 */
	java.sql.Date changeDate(Date date);

	/**
	 * 経過時間時間チェック
	 * （現在時が指定した時間を超えた場合、例外を発生する）
	 * @param startTime
	 * @param passSecond
	 * @throws Exception
	 */
	void checkTime(String startTime, Integer passSecond) throws Exception;

	/**
	 * コンピュータ日付を取得する
	 * @return
	 */
	Timestamp getComTimestamp();

	/**
	 * コンピュータ日付を取得する
	 * @return
	 */
	Calendar getComCalendar();

	/**
	 * コンピュータ日付を取得する
	 * @return
	 */
	Date getComDate();

	/**
	 * コンピュータ日付を取得する
	 * @return
	 */
	String getComDateSt();

	/**
	 * コンピュータ日付を取得する(フォーマット)
	 * フォーマットは、getSystemDateTime の内容を参照
	 * @return
	 */
	String getComDateTime(String format);

	/**
	 * 文字列からにDate変換
	 * @param yyyyMMddHHmmssSSS
	 * @return
	 */
	Date stToDate(String yyyyMMddHHmmssSSS);

	/**
	 * Dateから文字列に変換
	 * @param date
	 * @return
	 */
	String dateToSt(Date date);

	/**
	 * DateからTimestampに変換
	 * @param date
	 * @return
	 */
	Timestamp dateToTimestamp(Date date);

	/**
	 * DateからCalendarに変換
	 * @param date
	 * @return
	 */
	Calendar dateToCalendar(Date date);

	/**
	 * タイムスタンプからDateに変換
	 * @param timestamp
	 * @return
	 */
	Date timestampToDate(Timestamp timestamp);

	Calendar timestampToCalendar(Timestamp timestamp);

	/**
	 * 文字列からタイムスタンプに変換
	 * @param yyyyMMddHHmmssSSS
	 * @return
	 */
	Timestamp stToTimestamp(String yyyyMMddHHmmssSSS);

	/**
	 * タイムスタンプから文字列に変換
	 * @param timestamp
	 * @return
	 */
	String timestampToSt(Timestamp timestamp);

	/**
	 * カレンダーから文字列に変換
	 * @param calendar
	 * @return
	 */
	String calendarToSt(Calendar calendar);

	/**
	 * 文字列からカレンダーに変換
	 * @param str
	 * @return
	 */
	Calendar stToCalendar(String str);

	/**
	 * カレンダーからDateに変換
	 * @param calendar
	 * @return
	 */
	Date calendarToDate(Calendar calendar);

	/**
	 * カレンダーからTimestampに変換
	 * @param calendar
	 * @return
	 */
	Timestamp calendarToTimestamp(Calendar calendar);

	/**
	 * 日付加算処理
	 * カレンダーインスタンスに指定時間を加算して、インスタンスを返す
	 * @param cal
	 * @param yyyy
	 * @param mm
	 * @param dd
	 * @param hh
	 * @param mi
	 * @param se
	 * @param ms
	 * @return
	 */
	Calendar addCalendar(Calendar cal, Integer yy, Integer mm, Integer dd, Integer hh, Integer mi,
			Integer se, Integer ms);

	/**
	 * Dateからjava.sql.Date変換処理
	 * @param date
	 * @return
	 */
	java.sql.Date dateToSqlDate(Date date);

	/**
	 * カレンダーからjava.sql.Dateに変換する
	 * @param date
	 * @return
	 */
	java.sql.Date calendarToSqlDate(Calendar calendar);

	/**
	 * sqlDateをカレンダーに変換する
	 * @param date
	 * @return
	 */
	Calendar sqlDateToCalendar(java.sql.Date date);

	/**
	 * sqlDateを文字列に変換する
	 * @param date
	 * @return
	 */
	String sqlDateToSt(java.sql.Date date);

	/**
	 * 指定年月の最終日を取得する。
	 * @param year
	 * @param month
	 * @return
	 */
	Integer getLastDay(Integer year, Integer month);

	/**
	 * 曜日取得
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	Integer getWeek(Integer year, Integer month, Integer day);

	DateUtil dateUtil = new DateUtilImpl();

	public static DateUtil getInstance() {
		return dateUtil;
	}
}