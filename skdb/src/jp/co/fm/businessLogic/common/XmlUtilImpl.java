package jp.co.fm.businessLogic.common;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


class XmlUtilImpl implements XmlUtil {

	/**
	 * Objectの配列をxmlファイルに出力する。
	 * (オブジェクトの配列をXMLにエンコードする）
	 * @param objArray Objectの配列
	 * @param fileName xmlファイル
	 * @throws FileNotFoundException
	 */
	@Override
	public void encodeBeanArray(Object beanArray[], String fileName) throws FileNotFoundException{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		XMLEncoder encoder = new XMLEncoder(bufferedOutputStream);
		encoder.writeObject(beanArray);
		encoder.close();
	}

	/**
	 * xmlファイルをオブジェクトの配列で取得する。
	 * (encodeBeanArrayでエンコードしたXMLをデコードする）
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	@Override
	public Object[] decodeBeanArray(String fileName) throws FileNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(fileName);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		XMLDecoder dec = new XMLDecoder(bufferedInputStream);
		Object[] rtnBeanArray = (Object[])dec.readObject();
        dec.close();
        return rtnBeanArray;
	}
}
