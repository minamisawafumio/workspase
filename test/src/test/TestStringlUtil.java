package test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import jp.co.fm.businessLogic.common.StringUtil;

public class TestStringlUtil {
	@Test
	public void test_isHalfNumeric() {
		String moji = "45";
		System.out.println(StringUtil.getInstance().isHalfNumeric(moji));

	}

	@Test
	public void test_changeUnicode2UTF_8() {





		String moji = "aaaaa,aa%u3042%u3042%u3042a,a%u304B%u304B%u304Baa,aa%u3068%u3068%u3068aa";

		moji = moji.replaceAll("%", "\\");


//		String moji = "aaaaa,aa%u3042%u3042%u3042a,a%u304B%u304B%u304Baa,aa%u3068%u3068%u3068aa";
		//		String moji = "%u3042%u3042%u3042a,a%u304B%u304B%u304Baa,aa%u3068%u3068%u3068aa";


		try {

			byte[] moji2 = moji.getBytes("UTF-8");

//			byte moji2[] =  moji.getBytes(StandardCharsets.UTF_8.toString());
			String result = new String(moji2, "UTF-8");
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

	private static String convertToOiginal(String unicode)
	{
	    String[] codeStrs = unicode.split("\\\\u");
	    int[] codePoints = new int[codeStrs.length - 1]; // 最初が空文字なのでそれを抜かす
	    for (int i = 0; i < codePoints.length; i++) {
	        codePoints[i] = Integer.parseInt(codeStrs[i + 1], 16);
	    }
	    String encodedText = new String(codePoints, 0, codePoints.length);
	    return encodedText;
	}

	//
}
