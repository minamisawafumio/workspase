package jp.co.fm.businessLogic.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.TreeMap;

import javax.mail.MessagingException;

import org.apache.poi.ss.formula.functions.T;

public interface FileUtil {

	/**
	 * byte配列をファイルに出力する
	 * @param b
	 * @param outputFile
	 */
	int byte2File(byte[] byteData, String outputFile);

	/**
	 * ファイル名変更
	 * @param oldFilePathAndName
	 * @param newFilePathAndName
	 * @return
	 */
	String changeFileName(String oldFilePathAndName, String newFilePathAndName);

	/**
	 *
	 * @param is
	 * @return
	 */
	byte[] file2Byte(BufferedInputStream is);

	byte[] file2Byte(DataInputStream is);

	/**
	 * ファイルをコピーする
	 * @param path1
	 * @param fileName1
	 * @param path2
	 * @param fileName2
	 * @return
	 */
	int copyFile(String path1, String fileName1, String path2, String fileName2);

	/**
	 * フルパスのファイルをコピーする
	 * @param pathFile1
	 * @param pathFile2
	 * @return
	 */
	int copyFile(String pathFile1, String pathFile2);

	/**
	 * パス内のファイルをコピーする
	 * @param path1
	 * @param path2
	 * @return
	 */
	int copyPath(String path1, String path2);

	/**
	 * 画像ファイルの幅、高さを配列で返す
	 * @param pathAndfileName
	 * @return
	 */
	Integer[] getFileWidthHeight(String pathAndfileName);

	/**
	 * ファイル削除
	 * @param pathAndfileName
	 * @return
	 */
	boolean delete(String pathAndfileName);

	/**
	 * ファイル削除
	 * @param path
	 * @param fileName
	 * @return
	 */
	boolean delete(String path, String fileName);

	/**
	 * 指定ディレクトリ内のファイルを削除する
	 * @param path
	 * @return
	 */
	boolean deleteFiles(String path);

	/**
	 * ファイルをbyte配列で返す
	 * @param file
	 * @return
	 */
	byte[] file2Byte(String file);

	/**
	 * ファイルをオブジェクトに変換する
	 * @param intputFile
	 * @return
	 */
	Object file2Object(String intputFile, Object object);

	/**
	 * ファイル（encodeBase64画像ファイルなど）を文字列にして返す。
	 * (画像ファイルなどのファイルを文字列に変換する)
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	String file2string(String fileName);

	String file2string2(String fileName) throws FileNotFoundException;

	String file2string3(String fileName);

	/**
	 * ファイルコピー処理
	 * @param inPathAndfileName   コピー元
	 * @param outPathAndfileName  コピー先
	 * @return
	 */
	int fileCopy(String inPathAndfileName, String outPathAndfileName);

	/**
	 * ファイル出力
	 * @param charCose 文字コード "SJIS"、"UTF-8"
	 * @param lineCode 改行コード LF="\n"、CRLF="\r\n"
	 * @param list 出力文字列リスト
	 * @param pathFileName
	 * @return
	 */
	int fileWrite(String charCose, String lineCode, List<String> list, String pathFileName);

	/**
	 * ファイルの内容をListに出力する
	 * @param pathFile
	 * @return
	 */
	List<String> fileRead(String pathFile);

	/**
	 * ファイルの内容をListに出力する
	 * @param path
	 * @param fileName
	 * @return
	 */
	List<String> fileRead(String path, String fileName);

	/**
	 * ファイルアップロード処理
	 * @param filePath アップロードフォルダ名
	 * @param ff
	 * @return
	 */
	int fileUpload(String filePath, InputStream inputStream, String fileName);

	/**
	 * 文字列(画像を文字列化)のファイルをオブジェクトファイルに変換して出力する。
	 * （例：文字列ファイルを画像ファイルに変換して出力する。）
	 * @param gazouString
	 * @param objeFile
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	int gazouString2objFile(String gazouString, String objeFile);

	/**
	 * 文字列(画像を文字列化)のファイルをオブジェクトのファイルに変換して出力する。
	 * （例：文字列ファイルを画像ファイルに変換して出力する。）
	 * @param gazouFile
	 * @param objeFile
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	int gazuFile2objFile(String gazouFile, String objeFile);

	/**
	 * 絶対パスを取得する
	 * @return
	 */
	String getAbsolutePath();

	/**
	 * 指定フォルダ名までの絶対パスを取得する
	 * @param folderName
	 * @return
	 */
	String getTargetPath(String folderName);

	/**
	 *
	 * @param inClass
	 * @return
	 */
	String getClassDir(Class<T> inClass);

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含む)をListで返す
	 * @param inPath
	 * @return
	 */
	List<String> getPathFileNameList(String inPath);

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含まない)をListで返す
	 * @param path
	 * @return
	 */
	List<String> getFileOnlyNameList(String path);

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含まない(拡張子も含まない))をListで返す
	 * @param path
	 * @return
	 */
	List<String> getFileOnlyNameListNoExtension(String path);

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含まない(拡張子も含まない))をTreeSetで返す
	 * @param path
	 * @return
	 */
	TreeMap<String, Integer> getFileOnlyNameTreeMapNoExtension(String path);

	/**
	 * 指定ディレクトリ内の指定文字列を含むファイルの名称をListで返す
	 * (指定文字列は 大文字、小文字の区別なし)
	 * @param path
	 * @param kakucyousi
	 * @return
	 */
	List<String> getFileSiteimojiIgnoreCaseList(String path, String siteimoji);

	/**
	 * 指定ディレクトリ内の指定文字列を含むファイルの名称をListで返す
	 * (指定文字列は 大文字、小文字の区別をしている)
	 * @param path
	 * @param kakucyousi
	 * @return
	 */
	List<String> getFileSiteimojiList(String path, String siteimoji);

	/**
	 * ファイルサイズを取得する
	 * @param pathAndfileName
	 * @return
	 */
	long getFileSize(String pathAndfileName);

	/**
	 * ファイルの最終更新日を返す(YYYYMMDD)
	 * @param path
	 * @param fileName
	 * @return
	 */
	String getLastModifiedYyyyMmDd(String path, String fileName);

	/**
	 * 指定ディレクトリ内のファイルの名称をListで返す（頭にファイルパスを付ける）
	 * @param path
	 * @return
	 */
	List<String> getPathFileList(String path);

	/**
	 * ファイル名から拡張子を返します。
	 * @param fileName ファイル名
	 * @return ファイルの拡張子
	 */
	String getSuffix(String fileName);

	/**
	 * InputStreamをbyte配列に変換する
	 * @param is
	 * @return
	 */
	byte[] inputStream2Bytes(InputStream is);

	/**
	 * ユーザディレクトリを返す
	 * @return
	 */
	String getUserDir();

	void inputStream2File(InputStream inputStream, String outputFile);

	/**
	 * ディレクトリ存在チェック
	 * @param path
	 * @return
	 */
	Boolean isDirectory(String path);

	int isExistFile(String pathFileName);

	/**
	 * ファイル存在チェック
	 * @param fileName
	 * @return
	 */
	int isExistFile(String path, String fileName);

	/**
	 * リストの内容を指定ファイルに出力する
	 * @param list
	 * @param outFileName
	 * @return
	 */
	int listWrite(List<String> list, String path, String fileName);

	/**
	 * ディレクトリを作成する(親ディレクトリも含めてまとめて作成)
	 * @param dirName
	 * @return
	 */
	int mkDirs(String dirName);

	int mkFile(String pathfileName);

	/**
	 * ファイル作成する
	 * @param dirName
	 * @return
	 */
	int mkFile(String path, String fileName);

	/**
	 * ファイルを移動する
	 * @param path1
	 * @param fileName1
	 * @param path2
	 * @param fileName2
	 * @return
	 */
	boolean moveFile(String path1, String fileName1, String path2, String fileName2);

	/**
	 * オブジェクトをファイルに出力する
	 * @param obj
	 * @param outputFile
	 * @return
	 */
	int object2File(Object obj, String outputFile);

	/**
	 * オブジェクトファイルを文字列ファイルに変換して出力する。
	 * （例：画像ファイルを文字列に変換してファイルに出力する。）
	 * @param objeFile
	 * @param stringFile
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	int objFile2stringFile(String objeFile, String stringFile);

	Boolean output(File file, OutputStream os);

	/**
	 * 対象のファイルオブジェクトの削除を行う.<BR>
	 * ディレクトリの場合は再帰処理を行い、削除する。
	 *
	 * @param file ファイルオブジェクト
	 * @throws Exception
	 */
	int recursiveDeleteFile(File file);

	/**
	 * 対象のファイルオブジェクトの削除を行う.<BR>
	 * @param pathFile
	 * @return
	 */
	int recursiveDeleteFile(String pathFile);

	/**
	 * 文字列をファイルに出力する
	 * @param b
	 * @param outputFile
	 * @throws UnsupportedEncodingException
	 * @return
	 */
	int string2File(String data, String outputFile);

	/**
	 * 文字列リストをファイルに出力する
	 * @param dataList
	 * @param outputFile
	 * @throws UnsupportedEncodingException
	 */
	void stringListToFile(List<String> dataList, PrintWriter pw);

	/**
	 * 文字列リストをファイルに出力する
	 * @param dataList
	 * @param outputFile
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	int stringListToFile(List<String> dataList, String _outputFile);

	/**
	 * パスリスト取得
	 * 指定パスは以下のフォルダ名称を再帰的に取得してリストで返す
	 * @param inPath
	 * @return
	 */
	List<String> getPathList(String inPath);

	FileUtil fileUtil = new FileUtilImpl();

	public static FileUtil getInstance() {
		return fileUtil;
	}

	String getSystemPath();

}