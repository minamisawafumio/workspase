package test;

import org.junit.Test;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.StringUtil;

public class TestStringUtil {
	@Test
    public void testChangeCode() {

		String d1 = "%E3%81%82%E3%81%82%E3%81%82%E3%81%82";

//		d1 = StringUtil.changeCode(d1, Const.CHAR_CD_UTF8, Const.CHAR_CD_SHIFT_JIS);
//		d1 = StringUtil.changeCode(d1, Const.CHAR_CD_UTF8, Const.CHAR_CD_SJIS);
//		d1 = StringUtil.changeCode(d1, Const.CHAR_CD_ISO_8859_1, Const.CHAR_CD_SJIS);
		d1 = StringUtil.getInstance().changeCode(d1, Const.CHAR_CD_UTF8, Const.CHAR_CD_SJIS);

		System.out.println(d1);
	}

}
