package jp.co.fm.businessLogic.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;

import jp.co.fm.businessLogic.system.SystemUtil;

class StringUtilImpl implements StringUtil {

    /**
     * 文字列変換(テーブル項目名の変換に使用)    Unicode から UTF-8
     * キャメル文字列をアンダーバー("_")繋ぎ文字列に変換する
     * @param inStr
     * @return
     */
    @Override
	public String changeCamelItem2UnderItem(String inStr){

        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < inStr.length(); i++){
            char item = inStr.charAt(i);
            if (('A' <= item && item <= 'Z')) {
                sb.append("_" + Character.toLowerCase(item));
            } else {
                sb.append(item);
            }
        }
        return sb.toString();
    }

	/**
	 * 文字列変換(テーブル項目名の変換に使用)
	 * "_"の次の文字を"_"を取って大文字に変換する
	 * @param inStr
	 * @return
	 */
	@Override
	public String changeKoumokuName(String inStr){
		StringBuffer sb = new StringBuffer();

		for(int i = 0; i < inStr.length(); i++){
			String moji = inStr.substring(i, i + 1);

			if("_".equals(moji)){
				i ++;
				moji = inStr.substring(i, i + 1).toUpperCase();
			}
			sb.append(moji);
		}
		return sb.toString();
	}

    /**
     * 文字列変換
     * 文字列内の "?" 文字を順次、左から、配列の内容で置換する
     * @param inputMoji
     * @param keyList
     * @return
     */
    @Override
	public String bindMoji(String inputMoji, Object []objectArray){
         List<Object> objectList = SystemUtil.getInstance().makeObjectList(objectArray);
         return changeBindMoji(inputMoji, objectList);

    }

    /**
     * 文字列変換
     * 文字列内の "?" 文字を順次、左から、リストの内容で置換する
     * @param inputMoji
     * @param keyList
     * @return
     */
    @Override
	public String changeBindMoji(String inMoji, List<Object> objectList){
        StringBuffer inputMoji = new StringBuffer(inMoji);

        Integer cnt = 0;

        for(Integer i = 0; i < inputMoji.length() ; i++){
            i = inputMoji.indexOf("?", i);
            if (i < 0){
                break;
            }
            inputMoji.deleteCharAt(i);
            inputMoji.insert(i, objectList.get(cnt));
            cnt = cnt + 1;
        }
        return inputMoji.toString();
    }

    /**
     * 指定文字列に記号が何文字含まれるか
     *
     * @param inMojiretu
     * @return
     */
    @Override
	public Integer countExistKigou(String inMojiretu){
    	String []data ={
    			"!","\"","#","$","%" ,"&","'","(",")","=",
    			"-","~" ,"^","|","\\","`","@","{","[","+",
    			";","*" ,":","]","}" ,",","<",".",">","?",
    			"/","_"
    	};

    	return countExistMoji(inMojiretu, data);
    }

    /**
     * 指定文字列に半角英字（小文字）が何文字含まれるか
     *
     * @param inMojiretu
     * @return
     */
    @Override
	public Integer countExistHankakuEijiL(String inMojiretu){
    	String []data = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
    	return countExistMoji(inMojiretu, data);
    }

    /**
     * 指定文字列に半角英字（大文字）が何文字含まれるか
     *
     * @param inMojiretu
     * @return
     */
    @Override
	public Integer countExistHankakuEijiB(String inMojiretu){
       	String []data = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,z".split(",");
    	return countExistMoji(inMojiretu, data);
    }

    /**
     * 指定文字列に数字が何文字含まれるか
     *
     * @param inMojiretu
     * @return
     */
    @Override
	public Integer countExistSuuji(String inMojiretu){
    	String []data = "1,2,3,4,5,6,7,8,9,0".split(",");
    	return countExistMoji(inMojiretu, data);
    }

    /**
     * 指定文字列に文字配列中の文字が何文字含まれるか
     *
     * @param inMojiretu
     * @return
     */
    @Override
	public Integer countExistMoji(String inMojiretu, String []data){

    	Integer cnt = 0;

    	for(String moji : data){
    		if(inMojiretu.contains(moji)){
    			cnt ++;
    		}
    	}
    	return cnt;
    }

	@Override
	public FileInputStream string2FileInputStream(String string) {

		FileInputStream fileInputStream = null;

		try {
			fileInputStream =  new FileInputStream(string);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 *
	 * @param in
	 * @return
	 */
	@Override
	public String fileInputStream2String(FileInputStream in) {
		StringBuilder sb = new StringBuilder();

		try {
            int ch;
            while ((ch = in.read()) != -1) {
                sb.append(Integer.toHexString(ch));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return sb.toString();
	}

	/**
	 *
	 * @param is
	 * @return
	 */
	@Override
	public String fileInputStream2String2(FileInputStream is) {
		StringBuilder sb = new StringBuilder();

		try {
            InputStreamReader in = new InputStreamReader(is, "UTF8");
            int ch;
            while ((ch = in.read()) != -1) {
            	sb.append(Integer.toHexString(ch));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return sb.toString();
	}

	/**
	 * 文字列をインプットストリームに変換する
	 * @param input
	 * @return
	 */
	@Override
	public InputStream string2InputStream(String input) {
		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	@Override
	public String getPrintPwd(String input){
		StringBuilder sb = new StringBuilder();

		for (int i = 0 ; i < input.length(); i++){
			sb.append("●");
		}
		return sb.toString();
	}

	/**
	 * 指定文字を指定回数分繰り返して返す
	 * @param ch
	 * @param repeatTime
	 * @return
	 */
	@Override
	public String repeatChar(Character ch, Integer repeatTime){
		StringBuilder sb = new StringBuilder();
		for(Integer i = 0; i < repeatTime; i++){
			sb.append(ch.toString());
		}
		return sb.toString();
	}

	/**
	 * 文字列をハッシュ化して返す
	 * @param input
	 * @return
	 */
	@Override
	public String encryption(String input, String code) {
		//"MD5"		MD5は虚弱性が発見され電子政府推奨暗号リスト外になった
		//"SHA-256"
		//"SHA-384"
		//"SHA-512"

		try {
			MessageDigest md = MessageDigest.getInstance(code);
			byte[] digest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, digest);
			String sha = number.toString(16);
			while(sha.length() < 40){
				sha = "0" + sha;
			}
			return sha;
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * メールアドレスチェック
	 * @param input
	 * @return
	 */
	@Override
	public Boolean isMailAddress(String input) {
		String ptnStr ="[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+";
		Pattern ptn = Pattern.compile(ptnStr);
		Matcher mc = ptn.matcher(input);
		if(mc.matches()) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	@Override
	public Boolean isEmpty(String input) {
		if (input == null) {
			return true;
		}
		if ("".equals(input)) {
			return true;
		}
		return false;
	}

	/**
	 * 半角英数字チェック
	 * @param input
	 * @return
	 */
	@Override
	public Boolean isNumeric(String input){
		if(input == null || input.isEmpty()){
			return false;
		}

		String ptnStr ="^[0-9]*$";
		Pattern ptn = Pattern.compile(ptnStr);
		Matcher mc = ptn.matcher(input);
		if(mc.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 半角数値チェック
	 * @param str
	 * @return
	 */
	@Override
	public boolean isHalfNumeric(String str) {
        return Pattern.matches("^[0-9]*$", str);
    }

	/**
	 *
	 * @param c
	 * @return
	 */
	@Override
	public String getQuote(Class c) {
		try{
			if (c.getName().equals("java.lang.String")) {
				return "'";
			}
			else {
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 文字列を暗号化する(全角文字は不可)
	 */
	@Override
	public String angouka(String inputdata1, String inputdata2) {
        SecretKeySpec sKey = new SecretKeySpec(inputdata2.getBytes(), "Blowfish");

        String encStr2 = null;
        try{
        	Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] outputdata1 = cipher.doFinal(inputdata1.getBytes());
            encStr2 = new String(outputdata1,"iso-8859-1");
        }catch(Exception e){
        	e.printStackTrace();
        }

        return encStr2;
	}

	  /**
	   * 文字列を復元化する(全角文字は不可)
	   */
	@Override
	public String hukugouka(String sss, String key) {

		String encStr2 = null;

		try{
			byte[] encrypted = sss.getBytes("iso-8859-1");
			SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = javax.crypto.Cipher.getInstance("Blowfish");
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, sksSpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			encStr2 = new String(decrypted,"iso-8859-1");
		}catch(Exception e){
			e.printStackTrace();
		}

		return encStr2;
	  }

	/**
	 * 文字列より、最初のトークン以前の内容を返す
	 * @param moji
	 * @return
	 */
	@Override
	public String getFirstIndexOfString(String moji, String token){
		int index = moji.indexOf(token);
		String userid = moji.substring(0, index);
		return userid;
	}

	/**
	 * 文字列より、最後のトークン以降の内容を返す
	 * @param str
	 * @param token
	 * @return
	 */
	@Override
	public String getLastIndexOfString(String str, String token) {
		int len = str.length();
		int ichi = str.lastIndexOf(token) + 1;
		return str.substring(ichi, len);
	}

	/**
	 * 入力文字がnullの場合、空白 を返す
	 * @param moji
	 * @return
	 */
	@Override
	public String getSpase(String moji){
		if(moji == null) {
			return "";
		}
		return moji;
	}

	/**
	 * 入力値がnullの場合、空白 を返す
	 * @param moji
	 * @return
	 */
	@Override
	public String getSpase(Integer moji){
		if(moji == null) {
			return "";
		}
		return moji.toString();
	}

	/**
	 * 文字化け対応
	 * @param str
	 * @param fromCode
	 * @param toCode
	 * @return
	 */
	@Override
	public String changeCode(String str, String fromCode, String toCode) {
		if(str == null){
			return "";
		}

		try{
		    str = new String(str.getBytes(fromCode), toCode);
		}catch(Exception e) {
		    e.printStackTrace();
		}
		return str;
	}

	/**
	 * 文字化け対応
	 * @param str
	 * @param fromCode
	 * @param toCode
	 * @return
	 */
	@Override
	public String decode(String str) {

		String exe01Text = null;

		try {
			exe01Text = new String(URLDecoder.decode(str, "iso-8859-1").getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return exe01Text;
	}

	/*
	 * 文字列をトークンで分割してListで返す
	 **/
	@Override
	public List<String> splitStringList(String str, String token) {
		List<String> l = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(str, token);

		while(st.hasMoreTokens()){
			l.add(st.nextToken());
		}
		return l;
	}

	/**
	 * 文字列をトークンで分割してListで返す (空の要素を削除しない)
	 * @param value
	 * @param token
	 * @return
	 */
	@Override
	public List<String> splitStringList2(String value, String token) {
		// 分割した文字を格納する変数
	    List<String> list = new ArrayList<>();
	    Integer i = 0;
	    Integer j = value.indexOf(token);
	    for (int h = 0; j >= 0; h++) {
	      list.add(value.substring(i, j));
	      i = j + 1;
	      j = value.indexOf(token, i);
	    }
	    list.add(value.substring(i));
	    return list;
	}

	/**
	 *
	 * 文字列をトークンで分割してMapで返す (空の要素を削除する)
	 * キーは、項目位置の順番
	 * @param str
	 * @param token
	 * @return
	 */
	@Override
	public Map<String, String> splitStringMap(String str, String token) {
		Map<String, String> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(str, token);
		int cnt = 0;
		while(st.hasMoreTokens()){
			map.put(Integer.toString(cnt), st.nextToken());
			cnt ++;
		}
		return map;
	}

	/**
	* 先頭の１文字を小文字にして返す
	*/
	@Override
	public String headLowerCase(String s){
		if(s.length()==0) return "";
		return s.substring(0, 1).toLowerCase() + s.substring(1,s.length());
	}

	/**
	* 先頭の１文字を大文字にして返す
	*/
	@Override
	public String headUpperCase(String s){
		if(s.length()==0) return "";
		return s.substring(0, 1).toUpperCase() + s.substring(1,s.length());
	}

	/**
	 * フォーマット変換
	 * format="000000" suu=123 → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	@Override
	public String changeFormat(String format, long suu){
		return changeFormat(format, suu);
	}

	/**
	 * フォーマット変換
	 * format="000000" suu=123 → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	@Override
	public String changeFormat(String format, int suu){
		DecimalFormat f = new DecimalFormat(format);
		String rtn = f.format(suu);
		return rtn;
	}

	/**
	 * フォーマット変換
	 * format="000000" suu=123 → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	@Override
	public String changeFormat(String format, double suu){
		DecimalFormat f = new DecimalFormat(format);
		String rtn = f.format(suu);
		return rtn;
	}

	/**
	 * フォーマット変換
	 * format="000000" suu="123" → "000123"
	 * @param format
	 * @param suu
	 * @return
	 */
	@Override
	public String changeFormat(String format, String suu){
		Double intSuu = Double.parseDouble(suu);
		return changeFormat(format, intSuu);
	}

	/**
	 * 文字列の頭0を取る
	 * @param str
	 * @return
	 */
	@Override
	public String trimLeftZero(String str) {
	    return str.replaceFirst("^0+", "");
	}

	/**
	 * 数値を3桁毎にカンマで区切る
	 * @param suu
	 * @return
	 */
	@Override
	public String changeFormatCamma(String suu){
		Integer intValue = Integer.valueOf(suu);
		return changeFormatCamma(intValue);
	}

	/**
	 * 数値を3桁毎にカンマで区切る
	 * @param suu
	 * @return
	 */
	@Override
	public String changeFormatCamma(Integer suu){
		return String.format("%1$,3d", suu);
	}

	/**
	 * 文字列⇒Long数値変換
	 * @param num
	 * @return
	 */
	@Override
	public Long changeLong(String number) {
		try {
			return Long.parseLong(number);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 *
	 * 全角チェック
	 * @param item
	 * @return
	 */
	@Override
	public boolean checkZenkaku(String item) {

	    byte[] bytes = null;

        try {
            bytes = item.getBytes("MS932");
        } catch (UnsupportedEncodingException e) {
            System.out.println("No Encode Character");
            e.printStackTrace();
        }

        Integer beams = item.length() * 2;

        StringBuffer sb = new StringBuffer(item);

        for (int i = 0; i < item.length(); i++) {
            if ('\n' == sb.charAt(i)) {
                beams = beams - 2;
            }
        }

        if (beams == bytes.length) {
            return true;
        } else {
            return false;
        }
    }

	/**
	 *
	 * 半角（大文字／英字）チェック
	 * @param item
	 * @return
	 */
	@Override
	public boolean checkHankaku(String item) {
    	boolean flag = true;

        for (int i = 0; item.length() > i && flag == true; i++) {

            if (('A' <= item.charAt(i) && item.charAt(i) <= 'Z')) {
            } else {
                flag = false;
            }
        }
        return flag;
	}

	/**
	 * オブジェクトを文字列に変換する
	 * @param object
	 * @return
	 */
	@Override
	public String convert(Object object)  {
		String ret = "";
		try {
			if (object instanceof String)	{
				ret = object.toString();
			} else
			if (object instanceof Boolean)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Byte)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Short)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Integer)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Long)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Float)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Double)	{
				ret = object.toString().trim();
			} else
			if (object instanceof BigDecimal)	{
				ret = object.toString().trim();
			} else
			if (object instanceof Date)	{
	        	java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyyy/MM/dd");
	            ret = date.format((Date)object);
			} else
			if (object instanceof Time)	{
	        	java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("HH:mm:ss");
	            ret = time.format((Time)object);
			} else
			if (object instanceof Timestamp)	{
//				java.text.SimpleDateFormat ts = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				ret = ts.format((Timestamp)object);
				ret = object.toString().trim();
			} else
			if (object instanceof Blob)	{
				byte[] bData = new byte[(int)((Blob)object).length()];
				bData = ((Blob)object).getBytes(0,bData.length);
				ret = new String(bData);
			} else
			if (object instanceof Clob)	{
				ret = ((Clob)object).getSubString(0,(int)((Clob)object).length());
			}
		}
		catch (Exception ex)	{
		}
		return ret;
	}

	/**
	 * 文字列変換
	 * @param inputMoji
	 * @param keyMap
	 * @return
	 */
	@Override
	public String changeBindMoji(String inputMoji, Map<String, String> keyMap){
		Iterator ite = keyMap.keySet().iterator();
		while (ite.hasNext() ) {
			String key = (String)ite.next();
			String valse = (String)keyMap.get(key);

			inputMoji = inputMoji.replaceAll(key, valse);
		}
		return inputMoji;
	}

	/**
	 *
	 * @param suu
	 * @return
	 */
	@Override
	public String getカンマ区切(Object suu){
		NumberFormat exObject1 = NumberFormat.getNumberInstance();
		return exObject1.format(suu);
	}

	/**
	 * byteデータを文字列に変換する。(改行コード(CRLF)あり)
	 *
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	@Override
	public String encodeBase64(byte[] data) throws IOException, UnsupportedEncodingException, MessagingException {
	      if (data == null) {
	         return null;
	      }

	      ByteArrayOutputStream to = new ByteArrayOutputStream();

	      try {
	         OutputStream out = MimeUtility.encode(to, "base64");
	         try {
	             out.write(data);
	         }
	         finally {
	            out.close();
	         }
	         return to.toString();
	      }
	      finally {
	         to.close();
	      }
	}

	/**
	 * byteデータを文字列に変換する。(改行コード(CRLF)無し)
	 */
	@Override
	public String encodeBase64_2(byte[] data) {
	    try (ByteArrayOutputStream encodedChunked = new ByteArrayOutputStream()) {
	        try (OutputStream os = MimeUtility.encode(encodedChunked, "base64")) {
	            os.write(data);
	            os.flush();
	        }
	        // 改行文字を除去する
	        String encodedStr = trimCRLF(encodedChunked);
	        return encodedStr;

	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Bad Encryption";
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        return "Bad Encryption";
	    }
	}

	/**
	 *
	 * @param encodedCRLF
	 * @return
	 */
	private static String trimCRLF(ByteArrayOutputStream encodedCRLF) {
	    byte inputArray[] = encodedCRLF.toByteArray();
	    byte outputArray[] = new byte[encodedCRLF.size()];

	    // CR(0x0d)、LF(0x0a)箇所を飛ばして出力用配列にコピー
	    int n = 0;
	    for (int i = 0; i < encodedCRLF.size() - 1; i++) {
	        if (inputArray[i] == 0x0d) {// CR
	            if (inputArray[i + 1] == 0x0a) {// LF
	                i++;
	                continue;
	            }
	        }
	        outputArray[n] = inputArray[i];
	        n++;
	    }
	    return new String(outputArray, 0, n);
	}

	/**
	 * 文字列の中にCRLFが含まれているかチェックする
	 * @param inStr
	 * @return
	 */
	@Override
	public boolean isExistCRLF(String inStr){

		byte[] sbyte = inStr.getBytes();

        for(int i = 0; i < sbyte.length; i++){
	        if (sbyte[i] == 0x0d) {// CR
	            if (sbyte[i + 1] == 0x0a) {// LF
	                return true;
	            }else {
	                i++;
	            }
	        }
        }
		return false;
	}

	/**
	 * 文字列の中にCRLFが含まれている場合、全て削除して返す
	 */
	@Override
	public String deleteCRLF(String inStr) {

		byte[] inputArray = inStr.getBytes();

	    byte outputArray[] = new byte[inputArray.length];

	    // CR(0x0d)、LF(0x0a)箇所を飛ばして出力用配列にコピー
	    int n = 0;
	    for (int i = 0; i < inputArray.length; i++) {
	        if (inputArray[i] == 0x0d) {// CR
	            if (inputArray[i + 1] == 0x0a) {// LF
	                i++;
	                continue;
	            }
	        }
	        outputArray[n] = inputArray[i];
	        n++;
	    }


	    return new String(outputArray, 0, n);
	}

	/**
	 * テーブルの項目名を作成する。(aaa_bbb_cc ⇒ aaaBbbCc)
	 * @param inStr
	 * @return
	 */
	@Override
	public String makeTableKoumokuName(String inStr){

		StringBuffer sb = new StringBuffer();
		boolean hiFlag = false;

		for(int i = 0; i < inStr.length(); i++){
			String moji = inStr.substring(i, i + 1);
			if("_".equals(moji)){
				hiFlag = true;
				continue;
			}
			if(true == hiFlag){
				hiFlag = false;
				moji = inStr.substring(i, i + 1).toUpperCase();
			}
			sb.append(moji);
		}
		return sb.toString();
	}

	/**
	 * 引数の文字列がnullの場合に""（長さ0の文字列）に変換します。
	 * @param inStr
	 * @return
	 */
	@Override
	public String cnvNull(String inStr){
	    if (inStr == null) {
	    	return "";
	    }
		return inStr;
	}

	/**
	 * テーブル項目のセッター名を取得する
	 * "aa_bb_cc ⇒ setAaBbCc"
	 * @param inStr
	 * @return
	 */
	@Override
	public String getSetterName(String inStr){
		inStr = "set_" + inStr;
		String koumokuName = changeKoumokuName(inStr);
		return koumokuName;
	}

	/**
	 * 文字列をにbyteデータ復元する。
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	@Override
	public byte[] decodeBase64(String data) throws IOException, UnsupportedEncodingException, MessagingException{
	   if ( data == null ) {
	      return null;
	   }
	   if ( data.length() < 1 ) {
	      return null;
	   }

	   ByteArrayInputStream from = new ByteArrayInputStream( data.getBytes() );
	   try {
	      InputStream in = MimeUtility.decode(from, "base64");
	      try {
	          ByteArrayOutputStream to = new ByteArrayOutputStream();
	          try {
	            byte[] buffer = new byte[4096];
	            Integer bytesRead;

	            while ( (bytesRead = in.read(buffer)) != -1 ) {
	               to.write( buffer, 0, bytesRead );
	            }
	            to.flush();
	             return to.toByteArray();
	          }
	          finally {
	             to.close();
	          }
	      }
	      finally {
	         in.close();
	      }
	   }
	   finally {
	      from.close();
	   }
	}

	/**
	 * デコード(Base64)
	 * @param moji
	 * @return
	 */
	@Override
	public String decodeStBase64(String moji) {
		byte[]b = Base64.getDecoder().decode(moji);
	    String de = new String(b, StandardCharsets.UTF_8);
	    return de;
	}

	/**
	 * エンコード(Base64)
	 * @param data
	 * @return
	 */
	@Override
	public String encodeStBase64(String moji) {
		byte[]bMoji = moji.getBytes(StandardCharsets.UTF_8);
		byte[]a = Base64.getEncoder().encode(bMoji);
        String en = new String(a, StandardCharsets.UTF_8);
        return en;
	}

	/**
	 * 文字列に指定数を加算して文字列で返す
	 * @param suu
	 * @param addCount
	 * @return
	 */
	@Override
	public String addNum(String suu, int addCount) {
		int intSuu = Integer.parseInt(suu);
		intSuu = intSuu + addCount;
		return Integer.toString(intSuu);
	}
}
