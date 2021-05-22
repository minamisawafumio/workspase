package jp.co.fm.businessLogic.common;

import java.io.File;
import java.io.IOException;

import org.apache.tools.zip.ZipOutputStream;

public interface ZipUtil {

	/**
	 * フォルダ圧縮処理
	 * @param path
	 * @param pathAndFileName
	 * @return
	 */
	int compres(String path, String pathAndFileName);

	/**
	 * ファイルディレクトリをZIP圧縮します
	 * @param zos
	 * @param files
	 * @throws Exception
	 */
	void encodeZip(ZipOutputStream zos, File[] files) throws Exception;

	/**
	 * 指定フォルダ下のファイルをZIPファイルにする。
	 * @param files
	 * @param pathAndZipFileName
	 */
	int folderToZip(String path, String pathAndZipFileName);

	/**
	 * ZIPファイルを解凍します。
	 *
	 * @param pathAndFileName ディレクトリ + ファイル
	 * @param outPath 出力ディレクトリ
	 * @throws IOException
	 */
	void decodeZip(String pathAndFileName, String outPath) throws IOException;

	ZipUtil zipUtil = new ZipUtilImpl();

	public static ZipUtil getInstance() {
		return zipUtil;
	}
}