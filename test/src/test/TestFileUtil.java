package test;

import org.junit.Test;

import jp.co.fm.businessLogic.common.FileUtil;

public class TestFileUtil {

    private FileUtil fu = FileUtil.getInstance();

	@Test
	public void test008() {

		String hhh  =  fu.getSystemPath();

		System.out.println("幅  =" + hhh);

	}

	@Test
	public void test007() {

		String pathAndfileName = "C:/南沢/999 その他/manga_元画像/AVママ/001.jpg";
		Integer[]aaa =  fu.getFileWidthHeight(pathAndfileName);

		System.out.println("幅  =" + aaa[0]);
		System.out.println("高さ=" + aaa[1]);
	}

}


