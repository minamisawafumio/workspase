package jp.co.fm.businessLogic.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class DateUtilImpl implements DateUtil {
	/**
	 * 00時以降の時間変更
	 * (例:030505 → 270505)
	 * @param hhmmss
	 * @return
	 */
	@Override
	public String changeHhMmssForBeforDay(String hhmmss) {
		Integer hh = Integer.parseInt(hhmmss.substring(0, 2)) + 24;
		Integer mm = Integer.parseInt(hhmmss.substring(2, 4));
		Integer dd = Integer.parseInt(hhmmss.substring(4, 6));
		return getDayFormat(hh, mm, dd);
	}

	/**
	 * コンピュータ日付を取得する
	 * @return
	 */
	@Override
	public String getComDateTime(Integer i) {

		switch(i){
			case 0:	return getComDateTime("{0,date,yyyy-MM-dd HH:mm:ss.SSS}");
			case 1:	return getComDateTime("{0,date,yyyyMMdd}");
			case 2: return getComDateTime("{0,date,yyyyMMddHH}");				//YYYYMMDDHH
			case 3: return getComDateTime("{0,date,yyyyMMddHHmm}");				//YYYYMMDDHHMi
			case 4: return getComDateTime("{0,date,yyyyMMddHHmmss}");			//YYYYMMDDHHMiSS
			case 5: return getComDateTime("{0,date,yyyyMMddHHmmssSSS}");		//YYYYMMDDHHMiSSSSS
			default:return getComDateTime("{0,date,yyyy-MM-dd HH:mm:ss.SSS}");
		}
	}

	/**
	 * システム日付を取得する
	 * @return
	 */
	@Override
	public Date getSystemDate() {
		Date date = Calendar.getInstance().getTime();
		return date;
	}

	/**
	 * Timestamp 変換
	 *
	 * @param str
	 * @return
	 */
	@Override
	public Timestamp getTimestamp(String str) {
		Date date = getDate(str);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * 日付フォーマット変換
	 * @param yyyymmdd
	 * @return
	 */
	@Override
	public String changeFormatDate(String yyyymmdd){
		StringBuilder sb = new StringBuilder();
		sb.append(yyyymmdd.substring(0, 4));
		sb.append("年");
		sb.append(yyyymmdd.substring(4, 6));
		sb.append("月");
		sb.append(yyyymmdd.substring(6, 8));
		sb.append("日");
		return sb.toString();
	}

	/**
	 * 指定日からＮか月後の日付を取得する
	 * @param inDate    YYYYMMDD
	 * @param addMonth  月数
	 * @return
	 */
	@Override
	public String getAddMonthDate(String inDate, Integer addMonth) {

		Date date = getDate(inDate);

	   	Calendar cal = dateToCalendar(date);

	   	Integer addYera   = 0;
	   	Integer addDate   = 0;
	   	Integer addHour   = 0;
	   	Integer addMinute = 0;
	   	Integer addSecond = 0;
	   	Integer addMillisecond = 0;

	   	Calendar calendar = addDay(cal, addYera, addMonth, addDate, addHour, addMinute, addSecond, addMillisecond);

	   	return getYyyyMMddHHmmssSSS(calendar);
	}

	/**
	 * 指定日数後の日付
	 * 指定日からＮ日後の日付を取得する
	 * 例 addDay("2004/02/29", 15)
	 * @param date
	 * @param addDate
	 * @return YyyyMMddHHmmssSSS
	 */
	@Override
	public String getInDesignateDays(String inYyyyMMddHHmmssSSS, Integer addDate){

		Date date = getDate(inYyyyMMddHHmmssSSS);

	   	Calendar cal = stToCalendar(inYyyyMMddHHmmssSSS);

	   	Integer addYera   = 0;
	   	Integer addMonth  = 0;
	   	Integer addHour   = 0;
	   	Integer addMinute = 0;
	   	Integer addSecond = 0;
	   	Integer addMillisecond = 0;

	   Calendar calendar = addDay(cal, addYera, addMonth, addDate, addHour, addMinute, addSecond, addMillisecond);

	   return getYyyyMMddHHmmssSSS(calendar);
	}

	/**
	 * 経過日数計算
	 * @param startYyyyMmDd
	 * @param endYyyyMmDd
	 * @return
	 */
	@Override
	public Long getIntervalDayCount(String startYyyyMmDd ,String endYyyyMmDd) {

		Date startDate = stToDate(startYyyyMmDd);

		Date endDate   = stToDate(endYyyyMmDd);

		return getIntervalDayCount(startDate, endDate);
	}

	/**
	 * 経過日数計算
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public Long getIntervalDayCount(Date startDate, Date endDate){

		Long timeDiff = difference(startDate, endDate);

	    // 差分の日数を算出します。
		Long dayDiff = timeDiff / (1000 * 60 * 60 * 24 );

		return dayDiff;
	}

	@Override
	public Long difference(Date startDate, Date endDate){
		long startLong = startDate.getTime();
		long endLong   = endDate.getTime();
		return endLong - startLong;
	}

	/**
	* 経過ミリ秒数を取得する
	* @param startYyyyMMddHHmmssSSS
	* @param endYyyyMMddHHmmssSSS
	* @return
	*/
	@Override
	public Long difference(String startYyyyMMddHHmmssSSS, String endYyyyMMddHHmmssSSS){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		try {
			Date startDate = dateFormat.parse(startYyyyMMddHHmmssSSS);
			Date endDate   = dateFormat.parse(endYyyyMMddHHmmssSSS);

		    return difference(startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 経過時間取得
	 * @param startYyyyMMddHHmmssSSS
	 * @param endYyyyMMddHHmmssSSS
	 * @return 経過時間の配列(時、分、秒、ミリ秒)
	 */
	@Override
	public Integer[] differenceArray(String startYyyyMMddHHmmssSSS, String endYyyyMMddHHmmssSSS){
		Long diff   = difference(startYyyyMMddHHmmssSSS, endYyyyMMddHHmmssSSS);
		Long hour   = diff   / (1000 * 60 * 60);
		Long amari  = diff   - (1000 * 60 * 60 * hour);
		Long min    = amari  / (1000 * 60);
		Long amari2 = amari  - (1000 * 60 * min);
		Long sec    = amari2 / (1000);
		Long amari3 = amari2 - (1000 * sec);

		Integer trnInt[] = {hour.intValue(), min.intValue(), sec.intValue(), amari3.intValue()};
		return trnInt;
	}

	/**
	 * 経過時間を文字列で返す
	 * @param startYyyyMMddHHmmssSSS
	 * @param endYyyyMMddHHmmssSSS
	 * @return
	 */
	@Override
	public String getDifferenceHHMMDDSSS(String startYyyyMMddHHmmssSSS, String endYyyyMMddHHmmssSSS){
		Integer intDay[] = differenceArray(startYyyyMMddHHmmssSSS, endYyyyMMddHHmmssSSS);
		return intDay[0] + "時間 " + intDay[1] + "分 " + intDay[2] + "秒 " + intDay[3] +"ミリ秒";
	}

	/**
	 * 日付文字列指定でDate型を生成
	 * @param yyyyMMddHHmmssSSS
	 * @return
	 */
    @Override
	public Date getDate(String yyyyMMddHHmmssSSS) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        try {
        	Date utilDate = dateFormat.parse(yyyyMMddHHmmssSSS);
        	return utilDate;
        } catch ( ParseException e ) {
            return null;
        }
    }

	/**
	 * 指定日を数値配列に変換する
	 * @param yyyymmdd
	 * @return 0:年 1:月 2:日
	 */
	@Override
	public Integer[] getIntDay(String yyyymmdd) {

		String stringYear  = yyyymmdd.substring(0, 4);
		String stringMonth = yyyymmdd.substring(4, 6);
		String stringDay   = yyyymmdd.substring(6, 8);

		Integer []rtnInt = {Integer.parseInt(stringYear),
				            Integer.parseInt(stringMonth),
				            Integer.parseInt(stringDay)};
		return rtnInt;
	}

	/**
	 * 指定日のカレンダーを取得する
	 * @param _year
	 * @param _month
	 * @param _day
	 * @return
	 */
	@Override
	public Calendar getWeekCalendar(Integer _year, Integer _month, Integer _day) {
		String day = getDayFormat(_year, _month, _day);

		Date date = getDate(day);

		Calendar cal = dateToCalendar(date);

		return cal;
	}

	/**
	 * 指定日の曜日を取得する
	 * @param _year
	 * @param _month
	 * @param _day
	 * @return 1:日 2:月 3:火 4:水 5:木 6:金 7:土
	 */
	@Override
	public Integer getWeekInteger(Integer _year, Integer _month, Integer _day) {
		Calendar cal = getWeekCalendar(_year, _month, _day);
		Integer week = cal.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	/**
	 * カレンダー
	 * @return
	 */
	@Override
	public String getYyyyMMddHHmmssSSS(Calendar calendar) {

		Integer ye = calendar.get(Calendar.YEAR);
		Integer mo = calendar.get(Calendar.MONTH) + 1;
		Integer da = calendar.get(Calendar.DAY_OF_MONTH);
		Integer ho = calendar.get(Calendar.HOUR_OF_DAY);
		Integer mi = calendar.get(Calendar.MINUTE);
		Integer se = calendar.get(Calendar.SECOND);
		Integer ms = calendar.get(Calendar.MILLISECOND);

		String stDate = getDayFormat(ye, mo, da, ho, mi, se, ms);

		return stDate;
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	@Override
	public String getYyyyMMddHHmmssSSS(Date date) {
		Calendar calendar = dateToCalendar(date);
		String day = getYyyyMMddHHmmssSSS(calendar);
		return day;
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	@Override
	public String getYyyyMMdd(Date date) {
		String yyyyMMddHHmmssSSS = getYyyyMMddHHmmssSSS(date);
		String yyyyMMdd = yyyyMMddHHmmssSSS.substring(0, 8);
		return yyyyMMdd;
	}

	/**
	* 戻り値のフォーマット YYYYMMDD
	*/
	@Override
	public String getDayFormat(Integer _year, Integer _month, Integer _day) {
        StringBuffer rtn = new StringBuffer(Integer.toString(_year));

        if (_month < 10){
        	rtn.append("0" + Integer.toString(_month));
		}else{
			rtn.append(Integer.toString(_month));
		}

        if (_day < 10){
        	rtn.append("0" + Integer.toString(_day));
		}else{
			rtn.append(Integer.toString(_day));
		}
        return rtn.toString();
	}

	/**
	* 戻り値のフォーマット yyyyMMddHHmmssSSS
	*/
	@Override
	public String getDayFormat(Integer _Ye, Integer _Ma, Integer _Da,Integer _Ho, Integer _Mi, Integer _Se, Integer _Ms) {
		StringBuffer aa = new StringBuffer(getDayFormat(_Ye, _Ma, _Da));

		aa.append(getTimeFormat(_Ho, _Mi, _Se,  _Ms));

		return aa.toString();
	}

	/**
	 * 時分秒変換
	 * @param _ho
	 * @param _mi
	 * @param _se
	 * @return
	 */
	@Override
	public String getTimeFormat(Integer _ho,Integer _mi,Integer _se) {
        String strSdayHHMM = getTimeFormat(_ho, _mi);

        if (_se < 10){
        	strSdayHHMM = strSdayHHMM + "0" + Integer.toString(_se);
		}else{
			strSdayHHMM = strSdayHHMM + Integer.toString(_se);
		}

        return strSdayHHMM;
	}

	@Override
	public String getTimeFormat(Integer _ho,Integer _mi,Integer _se, Integer _ms) {
		StringBuffer tt = new StringBuffer(getTimeFormat(_ho, _mi, _se));

		if (_ms < 10){
			tt.append("00" + Integer.toString(_ms));
		}else if (_ms < 100){
			tt.append("0"  + Integer.toString(_ms));
		} else {
			tt.append(Integer.toString(_ms));
		}
		return tt.toString();
	}

	/**
	 * 時分変換
	 * @param _ho
	 * @param _mi
	 * @return
	 */
	@Override
	public String getTimeFormat(Integer _ho,Integer _mi) {
		StringBuffer rtn = new StringBuffer();
        if (_ho < 10){
			rtn.append("0" + Integer.toString(_ho));
		}else{
			rtn.append(Integer.toString(_ho));
		}

        if (_mi < 10){
        	rtn.append("0" + Integer.toString(_mi));
		}else{
			rtn.append(Integer.toString(_mi));
		}

        return rtn.toString();
	}

	/**
	* ミリ秒
	*/
	@Override
	public String getMillisecondFormat(Integer intMillisecond){

		String strMillisecond;

	    if (intMillisecond < 10){
			strMillisecond = "00" + Integer.toString(intMillisecond);
		}else if (intMillisecond < 100){
			strMillisecond = "0"  + Integer.toString(intMillisecond);
		}else{
			strMillisecond =        Integer.toString(intMillisecond);
		}

		return strMillisecond;
	}

	/**
	 * 指定日付(YYYYMMDD)の妥当性チェック
	 * @param inputDate
	 * @return
	 */
	@Override
	public Boolean checkDate(String yyyymmdd){
		// 文字列長が8でない場合
		if (yyyymmdd.length() != 8) {
			return false;
		}

		Integer intValue = Integer.parseInt(yyyymmdd);
		//数値以外の文字がある場合
		if (intValue==null){
			return false;
		}

		Integer[]intDay =  getIntDay(yyyymmdd);

		Boolean rtnBool = true;

		Calendar cal = new GregorianCalendar();
		cal.setLenient(false);
		// 0:年, 1:月, 2:日
		cal.set(intDay[0], intDay[1] - 1, intDay[2]);

		try {
			cal.getTime();
		} catch (IllegalArgumentException iae) {
			rtnBool = false;
		}

		return rtnBool;
	}

	/**
	 * 指定月の最終日を取得する
	 * @param yearMonth
	 * @return
	 */
	@Override
	public Integer getMonthEndDay(String yearMonth){
		//指定月の一日
		String firstDay  = yearMonth + "01";
		//一ヵ月後の年月日
		String nextMonthYyyyMmDd = getAddMonthDate(firstDay, 1);
		//月の最終日
		String monthEndDay = getInDesignateDays(nextMonthYyyyMmDd, - 1);
		return Integer.parseInt(monthEndDay.substring(6, 8));
	}

	/**
	 * 指定カレンダーインスタンスに日時を加算して、インスタンスを返す
	 * @param cal
	 * @param addYera
	 * @param addMonth
	 * @param addDate
	 * @param addHour
	 * @param addMinute
	 * @param addSecond
	 * @param addMillisecond
	 * @return
	 */
	private Calendar addDay(Calendar cal, Integer addYera,Integer addMonth,Integer addDate,
			Integer addHour,Integer addMinute,Integer addSecond, Integer addMillisecond) {
		cal.add(Calendar.YEAR, addYera);
		cal.add(Calendar.MONTH, addMonth);
		cal.add(Calendar.DATE, addDate);
		cal.add(Calendar.HOUR_OF_DAY, addHour);
		cal.add(Calendar.MINUTE, addMinute);
		cal.add(Calendar.SECOND, addSecond);
		cal.add(Calendar.MILLISECOND, addMillisecond);
		return cal;
	}

	/**
	 *
	 * @param strDate
	 * @param str
	 * @param position
	 * @param addStr
	 * @param len
	 * @return
	 */
	private String fillString(String strDate, String str, String position, String addStr, Integer len){
		if (str.length() > len) {
			throw new IllegalArgumentException("引数の文字列["+ strDate +"]は日付文字列に変換できません");
		}
		return fillString(str, position, len,addStr);
	}

	/**
	 *
	 * @param str
	 * @param position
	 * @param len
	 * @param addStr
	 * @return
	 */
	private String fillString(String str, String position, Integer len, String addStr) {
	    if (addStr == null || addStr.length() == 0) {
	        throw new IllegalArgumentException
	            ("挿入する文字列の値が不正です。addStr="+addStr);
	    }
	    if (str == null) {
	        str = "";
	    }
	    StringBuffer buffer = new StringBuffer(str);
	    while (len > buffer.length()) {
	        if (position.equalsIgnoreCase("l")) {
	        	Integer sum = buffer.length() + addStr.length();
	            if (sum > len) {
	                addStr = addStr.substring
	                    (0,addStr.length() - (sum - len));
	                buffer.insert(0, addStr);
	            }else{
	                buffer.insert(0, addStr);
	            }
	        } else {
	            buffer.append(addStr);
	        }
	    }
	    if (buffer.length() == len) {
	        return buffer.toString();
	    }
	    return buffer.toString().substring(0, len);
	}

	/**
	 * 現在の曜日を返します
	 * @return	現在の曜日
	 */
	@Override
	public String getDayOfTheWeek(Calendar cal) {
	    switch (cal.get(Calendar.DAY_OF_WEEK)) {
	        case Calendar.SUNDAY:   return "日";
	        case Calendar.MONDAY:   return "月";
	        case Calendar.TUESDAY:  return "火";
	        case Calendar.WEDNESDAY:return "水";
	        case Calendar.THURSDAY: return "木";
	        case Calendar.FRIDAY:   return "金";
	        case Calendar.SATURDAY: return "土";
	    }
	    throw new IllegalStateException();
	}

	/**
	 * 現在の曜日を返します
	 * @param yyyymmdd
	 * @return
	 */
	@Override
	public String getDayOfTheWeek(String yyyymmdd) {
		Integer[]day = getIntDay(yyyymmdd);
		Calendar cal = getWeekCalendar(day[0], day[1], day[2]);
		String date  = getDayOfTheWeek(cal);
		return date;
	}

	/**
	 * java.util.Date から java.sql.Dateに変換する
	 * @param date
	 * @return
	 */
	@Override
	public java.sql.Date changeDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date d2 = new java.sql.Date(cal.getTimeInMillis());
		return d2;
	}

	/**
	 * 経過時間時間チェック
	 * （現在時が指定した時間を超えた場合、例外を発生する）
	 * @param startTime
	 * @param passSecond
	 * @throws Exception
	 */
	@Override
	public void checkTime(String startTime, Integer passSecond) throws Exception {
		//現在時取得
		String endTime = getComDateTime(5);

		Long passTime  = difference(startTime, endTime) / 1000;

		//ブランチ作成に消費する時間を超えた場合、例外を発生する
		if(passTime > passSecond){
			throw new Exception();
		}
	}

    /**
     * コンピュータ日付を取得する
     * @return
     */
	@Override
	public Timestamp getComTimestamp(){
		return stToTimestamp(getComDateSt());
	}

    /**
     * コンピュータ日付を取得する
     * @return
     */
	@Override
	public Calendar getComCalendar(){
		return stToCalendar(getComDateSt());
	}

    /**
     * コンピュータ日付を取得する
     * @return
     */
	@Override
	public Date getComDate(){
		return stToDate(getComDateSt());
	}

    /**
     * コンピュータ日付を取得する
     * @return
     */
    @Override
	public String getComDateSt() {
    	return getComDateTime("{0, date,yyyyMMddHHmmssSSS}");
    }

    /**
     * コンピュータ日付を取得する(フォーマット)
     * フォーマットは、getSystemDateTime の内容を参照
     * @return
     */
    @Override
	public String getComDateTime(String format) {
        MessageFormat mf = new MessageFormat(format);
        Object[] objs = {Calendar.getInstance().getTime()};
        String result = mf.format(objs);
        return result;
    }

    /**
     * 文字列からにDate変換
     * @param yyyyMMddHHmmssSSS
     * @return
     */
    @Override
	public Date stToDate(String yyyyMMddHHmmssSSS) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        try {
            Date utilDate = dateFormat.parse(yyyyMMddHHmmssSSS);
            return utilDate;
        } catch ( ParseException e ) {
            return null;
        }
    }

    /**
     * Dateから文字列に変換
     * @param date
     * @return
     */
    @Override
	public String dateToSt(Date date) {
    	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	return df.format(date);
    }

    /**
     * DateからTimestampに変換
     * @param date
     * @return
     */
    @Override
	public Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * DateからCalendarに変換
     * @param date
     * @return
     */
	@Override
	public Calendar dateToCalendar(Date date){
		return stToCalendar(dateToSt(date));
    }

	/**
	 * タイムスタンプからDateに変換
	 * @param timestamp
	 * @return
	 */
    @Override
	public Date timestampToDate(Timestamp timestamp) {
    	Date date = new java.util.Date(timestamp.getTime());
    	return date;
    }

    @Override
	public Calendar timestampToCalendar(Timestamp timestamp) {
    	return dateToCalendar(timestampToDate(timestamp));
    }

    /**
     * 文字列からタイムスタンプに変換
     * @param yyyyMMddHHmmssSSS
     * @return
     */
    @Override
	public Timestamp stToTimestamp(String yyyyMMddHHmmssSSS) {
    	return dateToTimestamp(stToDate(yyyyMMddHHmmssSSS));
    }

    /**
     * タイムスタンプから文字列に変換
     * @param timestamp
     * @return
     */
	@Override
	public String timestampToSt(Timestamp timestamp) {
		return dateToSt(timestampToDate(timestamp));
	}

    /**
     * カレンダーから文字列に変換
     * @param calendar
     * @return
     */
	@Override
	public String calendarToSt(Calendar calendar) {
    	StringBuffer aa = new StringBuffer();

        aa.append(String.format("%04d", calendar.get(Calendar.YEAR)));
        aa.append(String.format("%02d", calendar.get(Calendar.MONTH) + 1));
        aa.append(String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
        aa.append(String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)));
        aa.append(String.format("%02d", calendar.get(Calendar.MINUTE)));
        aa.append(String.format("%02d", calendar.get(Calendar.SECOND)));
        aa.append(String.format("%03d", calendar.get(Calendar.MILLISECOND)));

        return aa.toString();
	}

	/**
	 * 文字列からカレンダーに変換
	 * @param str
	 * @return
	 */
	@Override
	public Calendar stToCalendar(String str) {
    	Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.valueOf(str.substring( 0,  4)));
        cal.set(Calendar.MONTH, Integer.valueOf(str.substring( 4,  6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(str.substring( 6,  8)));
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(str.substring( 8, 10)));
        cal.set(Calendar.MINUTE, Integer.valueOf(str.substring(10, 12)));
        cal.set(Calendar.SECOND, Integer.valueOf(str.substring(12, 14)));
        cal.set(Calendar.MILLISECOND, Integer.valueOf(str.substring(14, 17)));

    	return cal;
    }

	/**
	 * カレンダーからDateに変換
	 * @param calendar
	 * @return
	 */
	@Override
	public Date calendarToDate(Calendar calendar) {
    	Date date = calendar.getTime();
    	return date;
    }

	/**
	 * カレンダーからTimestampに変換
	 * @param calendar
	 * @return
	 */
	@Override
	public Timestamp calendarToTimestamp(Calendar calendar) {
    	Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
    	return timestamp;
    }

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
    @Override
	public Calendar addCalendar(Calendar cal, Integer yy,Integer mm,Integer dd, Integer hh,Integer mi,
    		                            Integer se, Integer ms) {
        cal.add(Calendar.YEAR, yy);
        cal.add(Calendar.MONTH, mm);
        cal.add(Calendar.DATE, dd);
        cal.add(Calendar.HOUR, hh);
        cal.add(Calendar.MINUTE, mi);
        cal.add(Calendar.SECOND, se);
        cal.add(Calendar.MILLISECOND, ms);
        return cal;
    }

    /**
     * Dateからjava.sql.Date変換処理
     * @param date
     * @return
     */
	@Override
	public java.sql.Date dateToSqlDate(Date date){
		return calendarToSqlDate(dateToCalendar(date));
    }

	/**
     * カレンダーからjava.sql.Dateに変換する
     * @param date
     * @return
     */
    @Override
	public java.sql.Date calendarToSqlDate(Calendar calendar){
    	java.sql.Date now = new java.sql.Date(calendar.getTimeInMillis());
        return now;
    }

    /**
     * sqlDateをカレンダーに変換する
     * @param date
     * @return
     */
	@Override
	public Calendar sqlDateToCalendar(java.sql.Date date){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * sqlDateを文字列に変換する
	 * @param date
	 * @return
	 */
	@Override
	public String sqlDateToSt(java.sql.Date date){
    	return calendarToSt(sqlDateToCalendar(date));
	}

	/**
	 * 指定年月の最終日を取得する。
	 * @param year
	 * @param month
	 * @return
	 */
	@Override
	public Integer getLastDay(Integer year, Integer month){
    	StringBuffer aa = new StringBuffer();

        aa.append(String.format("%04d", year));
        aa.append(String.format("%02d", month));
        // 月に1日目を設定
        aa.append(String.format("%02d", 1));
        aa.append("000000000");
        // 指定日のカレンダーを取得する
		Calendar cal = stToCalendar(aa.toString());

		// 指定月（の１日目）に１か月後を取得する
		cal = addCalendar(cal, 0, 1,  0, 0, 0, 0, 0);
		// １日前を取得する
		cal = addCalendar(cal, 0, 0, -1, 0, 0, 0, 0);
		String st = calendarToSt(cal);

		// 日付文字列を整数にして返す
		return Integer.parseInt(st.substring(6, 8));
	}


	/**
	 * 曜日取得
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@Override
	public Integer getWeek(Integer year,Integer month, Integer day) {
		if(month == 1 || month == 2){
			year = year - 1;
			month = month + 13;
		}else{
			month = month + 1;
		}

		int aa = (int)(year * 365.25);

		int bb = (int)(month * 30.6);

		int cc = year / 400;

		int dd = year / 100;

		int ee = aa + bb + cc + day - dd - 429;

		int x1 = ee / 7;

		int ff = x1 * 7;

		int gg = ee - ff;

	    return gg;
	}
}
