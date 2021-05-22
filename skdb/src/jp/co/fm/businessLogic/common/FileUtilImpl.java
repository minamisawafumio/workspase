package jp.co.fm.businessLogic.common;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;

class FileUtilImpl implements FileUtil {

	/**
	 * byte配列をファイルに出力する
	 * @param b
	 * @param outputFile
	 */
	@Override
	public int byte2File(byte[] byteData, String outputFile) {
		int rtnCd =0;

		try {
			InputStream inputStream = new ByteArrayInputStream(byteData);
			inputStream2File(inputStream, outputFile);
			inputStream.close();
		} catch (IOException e) {
			rtnCd = 1;
			e.printStackTrace();
		}

		return rtnCd;
	}

	/**
	 * ファイル名変更
	 * @param oldFilePathAndName
	 * @param newFilePathAndName
	 * @return
	 */
	@Override
	public String changeFileName(String oldFilePathAndName, String newFilePathAndName){
		 //変更前ファイル名
	      File fileA = new File(oldFilePathAndName);

	      //変更後のファイル名
	      File fileB = new File(newFilePathAndName);

	      if(fileA.renameTo(fileB)){
	         return newFilePathAndName;
	      }else{
	         return null;
	      }
	}

	/**
	 *
	 * @param is
	 * @return
	 */
	@Override
	public byte[] file2Byte(BufferedInputStream is){
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		BufferedOutputStream os = new BufferedOutputStream(b);
		int c;
		try {
			while ((c = is.read()) != -1) {
				os.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b.toByteArray();
	}

	@Override
	public byte[] file2Byte(DataInputStream is){
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		BufferedOutputStream os = new BufferedOutputStream(b);
		int c;
		try {
			while ((c = is.read()) != -1) {
				os.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b.toByteArray();
	}

	/**
	 * ファイルをコピーする
	 * @param path1
	 * @param fileName1
	 * @param path2
	 * @param fileName2
	 * @return
	 */
	@Override
	public int copyFile(String path1, String fileName1, String path2, String fileName2) {
		return copyFile(path1 + "/" + fileName1, path2 + "/" + fileName2);
	}

	/**
	 * フルパスのファイルをコピーする
	 * @param pathFile1
	 * @param pathFile2
	 * @return
	 */
	@Override
	public int copyFile(String pathFile1, String pathFile2) {
		int rtnCd = 0;

		try{
			FileChannel ic = new FileInputStream(pathFile1).getChannel();
			FileChannel oc = new FileOutputStream(pathFile2).getChannel();
			oc.transferFrom(ic, 0, ic.size());
			ic.close();
			oc.close();
		}catch(IOException e){
			rtnCd = 1;
			e.printStackTrace();
		}

		return rtnCd;
	}

	/**
	 * パス内のファイルをコピーする
	 * @param path1
	 * @param path2
	 * @return
	 */
	@Override
	public int copyPath(String path1, String path2) {
		int rtnCd = 0;

		List<String> fileList = getFileOnlyNameList(path1);

		for(String fileName: fileList){
			rtnCd = copyFile(path1, fileName, path2, fileName);

			if(rtnCd != 0){
				return rtnCd;
			}
		}

		return rtnCd;
	}

	/**
	 * 画像ファイルの幅、高さを配列で返す
	 * @param pathAndfileName
	 * @return
	 */
	@Override
	public Integer[] getFileWidthHeight(String pathAndfileName) {
		File file = new File(pathAndfileName);

		int width = 0;
		int height = 0;

		try {
			BufferedImage img = ImageIO.read(file);
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer widthHeight[] = {width, height};
		return widthHeight;
	}

	/**
	 * ファイル削除
	 * @param pathAndfileName
	 * @return
	 */
	@Override
	public boolean delete(String pathAndfileName) {
        File file = new File(pathAndfileName);
        return file.delete();
    }

	/**
	 * ファイル削除
	 * @param path
	 * @param fileName
	 * @return
	 */
	@Override
	public boolean delete(String path, String fileName) {
	    File file = new File(path + "/" + fileName);
	    return file.delete();
	}

	/**
	 * 指定ディレクトリ内のファイルを削除する
	 * @param path
	 * @return
	 */
	@Override
	public boolean deleteFiles(String path) {
		List<String> fileList = getFileOnlyNameList(path);

		for(int i = 0; i < fileList.size(); i++){
			String fileName = fileList.get(i);
			delete(path, fileName);
		}
		return true;
	}

	/**
	 * ファイルをbyte配列で返す
	 * @param file
	 * @return
	 */
	@Override
	public byte[] file2Byte(String file){
		try {
			BufferedInputStream bis = new BufferedInputStream( new FileInputStream(file) );
			return file2Byte(bis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ファイルをオブジェクトに変換する
	 * @param intputFile
	 * @return
	 */
	@Override
	public Object file2Object(String intputFile, Object object) {

		String st = null;

		try {
			st = file2string2(intputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return JsonUtil.getInstance().makeJsonStringToObject(object, st);
	}

	/**
	 * ファイル（encodeBase64画像ファイルなど）を文字列にして返す。
	 * (画像ファイルなどのファイルを文字列に変換する)
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Override
	public String file2string(String fileName) {

		String ddd = null;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			//BufferedInputStream bis = new BufferedInputStream(fis); //BufferedInputStream 非推奨になったのでやめた
			DataInputStream bis = new DataInputStream(fis);

			byte[] ss = file2Byte(bis);
			ddd = StringUtil.getInstance().encodeBase64_2(ss);
			bis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ddd;
	}

	@Override
	public String file2string2(String fileName) throws FileNotFoundException{
		String result = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] ss = file2Byte(bis);
			result = new String(ss, "UTF-8");
			bis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

    /**
     * ファイルコピー処理
     * @param inPathAndfileName   コピー元
     * @param outPathAndfileName  コピー先
     * @return
     */
    @Override
	public int fileCopy(String inPathAndfileName, String outPathAndfileName){

        byte[] buff = new byte[1024];    //配列の定義
        FileInputStream infile = null;    // ファイル“入力装置”
        FileOutputStream outfile=null;    // ファイル“出力装置”
        try{
            infile = new FileInputStream(inPathAndfileName) ;
            outfile = new FileOutputStream(outPathAndfileName) ;
        }
        catch(FileNotFoundException e){
            return 1;
        }

        // ファイルの終了まで,以下のループを繰り返します
        try {
            while (true) {
                int n = infile.read(buff);    // 入力ファイルからの読み込み
                if(n<0) break;                // 入力ファイルの終わりに達した場合ループを終了
                else outfile.write(buff, 0, n);    // 出力ファイルへの書き出し
            }
            infile.close() ;
            outfile.close() ;
        } catch(IOException e) {
            return 1;
        }
        return 0;
    }

	/**
	 * ファイル出力
	 * @param charCose 文字コード "SJIS"、"UTF-8"
	 * @param lineCode 改行コード LF="\n"、CRLF="\r\n"
	 * @param list 出力文字列リスト
	 * @param pathFileName
	 * @return
	 */
	@Override
	public int fileWrite(String charCose, String lineCode, List<String> list, String pathFileName) {

		try {
			FileOutputStream fos = new FileOutputStream(pathFileName);

			OutputStreamWriter osw = new OutputStreamWriter(fos, charCose);

			BufferedWriter bw = new BufferedWriter(osw);

			for(String data: list){
				bw.write(data + lineCode);
			}
			bw.close();

			osw.close();

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 1;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return 2;
		} catch (IOException e) {
			e.printStackTrace();
			return 3;
		}
		return 0;
	}

	/**
	 * ファイルの内容をListに出力する
	 * @param pathFile
	 * @return
	 */
	@Override
	public List<String> fileRead(String pathFile){
		List<String> l = new ArrayList<>();

		try{
		    BufferedReader br = new BufferedReader(new FileReader(pathFile));
		    String line;
		    while((line = br.readLine()) != null){
		    	l.add(line);
		    }
		    br.close();
		    return l;
		}catch(Exception e) {
		    return null;
		}
	}

	/**
	 * ファイルの内容をListに出力する
	 * @param path
	 * @param fileName
	 * @return
	 */
	@Override
	public List<String> fileRead(String path, String fileName){
        return fileRead(path + "/" + fileName);
	}

	/**
	 * ファイルアップロード処理
	 * @param filePath アップロードフォルダ名
	 * @param ff
	 * @return
	 */
	@Override
	public int fileUpload(String filePath, InputStream inputStream, String fileName){

		BufferedInputStream  bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(inputStream);
			//格納ファイルパス
			bos = new BufferedOutputStream(new FileOutputStream(filePath + "/" + fileName));
			//1byte読んで1byte書き込む
			int b = 0;
			while((b = bis.read()) != -1){
				bos.write(b);
			}
		} catch (IOException e) {
			return 1;
		} finally {
			if (bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					return 1;
				}
			}
			if (bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					return 1;
				}
			}
		}

		return 0;
	}

	/**
	 * 文字列(画像を文字列化)のファイルをオブジェクトファイルに変換して出力する。
	 * （例：文字列ファイルを画像ファイルに変換して出力する。）
	 * @param gazouString
	 * @param objeFile
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Override
	public int gazouString2objFile(String gazouString, String objeFile) {
		int rtnCd = 0;

		try {
			byte[]fff = StringUtil.getInstance().decodeBase64(gazouString);
			rtnCd = byte2File(fff, objeFile);
		}catch(Exception e) {
			rtnCd = 1;
			e.printStackTrace();
		}
		return rtnCd;
	}

	/**
	 * 文字列(画像を文字列化)のファイルをオブジェクトのファイルに変換して出力する。
	 * （例：文字列ファイルを画像ファイルに変換して出力する。）
	 * @param gazouFile
	 * @param objeFile
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Override
	public int gazuFile2objFile(String gazouFile, String objeFile) {

		int rtnCd = 0;

		try {
			BufferedInputStream bis = new BufferedInputStream( new FileInputStream(gazouFile));
			byte[]byteData = file2Byte(bis);
			String stdata = new String(byteData, "UTF-8");
			gazouString2objFile(stdata, objeFile);
		}catch(Exception e) {
			rtnCd = 1;
			e.printStackTrace();
		}
		return rtnCd;
	}

	/**
	 * 絶対パスを取得する
	 * @return
	 */
	@Override
	public String getAbsolutePath(){
		return new File("").getAbsolutePath();
	}

	/**
	 * 指定フォルダ名までの絶対パスを取得する
	 * @return
	 */
	@Override
	public String getTargetPath(String folderName) {
		int folderNameLength = folderName.length();
		String path = getAbsolutePath();
		path = path.replaceAll("\\\\", "/");
		int ichi = path.lastIndexOf(folderName);
		path = path.substring(0, ichi + folderNameLength);
		return path;
	}

	/**
	 *
	 * @param inClass
	 * @return
	 */
	@Override
	public String getClassDir(Class inClass) {
		String ward = "workspace";
		int wardLength = ward.length();
		String simpleName = inClass.getSimpleName() + ".class";
	   	String userDir = inClass.getResource(simpleName).toString();
	   	int workspacePoint = userDir.indexOf(ward);
	   	int slashPoint = userDir.indexOf("/", workspacePoint + wardLength + 1);
	   	String rtnString = userDir.substring(0, slashPoint);
	   	rtnString = rtnString.replaceAll("file:/", "");
	   	//file:/
	   	return rtnString;
	}

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含む)をListで返す
	 * @param inPath
	 * @return
	 */
	@Override
	public List<String> getPathFileNameList(String inPath) {
		List<String> list = getPathList(inPath);

		List<String> pathFilelist = new ArrayList<>();

		for(String pathName: list) {
			List<String> fileList = getFileOnlyNameList(pathName);

			StringBuffer sb;

			for(String fileName: fileList) {
				sb = new StringBuffer();
				sb.append(pathName);
				sb.append("\\");
				sb.append(fileName);
				pathFilelist.add(sb.toString());
			}
		}

		return pathFilelist;
	}


	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含まない)をListで返す
	 * @param path
	 * @return
	 */
	@Override
	public List<String> getFileOnlyNameList(String path) {

		List<String> fileList = new ArrayList<>();

	    File dir = new File(path);
	    File[] files = dir.listFiles();
	    for (int i = 0; i < files.length; i++) {
	        File file = files[i];

	        if(file.isFile()){
		        String fileName = file.getName();
		        fileList.add(fileName);
	        }
	    }

		return fileList;
	}

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含まない(拡張子も含まない))をListで返す
	 * @param path
	 * @return
	 */
	@Override
	public List<String> getFileOnlyNameListNoExtension(String path) {

		List<String> fileList = new ArrayList<>();

	    File dir = new File(path);
	    File[] files = dir.listFiles();
	    for (int i = 0; i < files.length; i++) {
	        File file = files[i];

	        if(file.isFile()){
		        String fileName = file.getName();
		        String array[] = fileName.split(".");
		        fileList.add(array[0]);
	        }
	    }

		return fileList;
	}

	/**
	 * 指定ディレクトリ内のファイルの名称(パスを含まない(拡張子も含まない))をTreeSetで返す
	 * @param path
	 * @return
	 */
	@Override
	public TreeMap<String, Integer> getFileOnlyNameTreeMapNoExtension(String path) {

		TreeMap<String, Integer> fileMap = new TreeMap<String, Integer>();

	    File dir = new File(path);
	    File[] files = dir.listFiles();
	    for (int i = 0; i < files.length; i++) {
	        File file = files[i];

	        if(file.isFile()){
		        String fileName = file.getName();
		        String []array = fileName.split(Pattern.quote("."));
		        fileMap.put(array[0], i + 1);
	        }
	    }

		return fileMap;
	}

	/**
	 * 指定ディレクトリ内の指定文字列を含むファイルの名称をListで返す
	 * (指定文字列は 大文字、小文字の区別なし)
	 * @param path
	 * @param kakucyousi
	 * @return
	 */
	@Override
	public List<String> getFileSiteimojiIgnoreCaseList(String path, String siteimoji) {

		int siteimojiLength = siteimoji.length();

		List<String> fileList = new ArrayList<>();

	    File dir = new File(path);
	    File[] files = dir.listFiles();
	    for (int i = 0; i < files.length; i++) {
	        File file = files[i];

	        if(file.isFile()){
		        String fileName = file.getName();

		        String kakucyousi = fileName.substring(fileName.length() - siteimojiLength, fileName.length());

		        if(kakucyousi.compareToIgnoreCase(siteimoji)==0){
			        fileList.add(fileName);
		        }
	        }
	    }

		return fileList;
	}

	/**
	 * 指定ディレクトリ内の指定文字列を含むファイルの名称をListで返す
	 * (指定文字列は 大文字、小文字の区別をしている)
	 * @param path
	 * @param kakucyousi
	 * @return
	 */
	@Override
	public List<String> getFileSiteimojiList(String path, String siteimoji) {

		List<String> fileList = new ArrayList<>();

	    File dir = new File(path);

	    File[] files = dir.listFiles();

	    for (int i = 0; i < files.length; i++) {
	        File file = files[i];

	        if(file.isFile()){
		        String fileName = file.getName();

		        Integer ichi = fileName.lastIndexOf(siteimoji);

		        if(ichi >= 0){
			        fileList.add(fileName);
		        }
	        }
	    }

		return fileList;
	}

	/**
	 * ファイルサイズを取得する
	 * @param pathAndfileName
	 * @return
	 */
	@Override
	public long getFileSize(String pathAndfileName) {
		File file = new File(pathAndfileName);
		return file.length();
	}

	/**
	 * ファイルの最終更新日を返す(YYYYMMDD)
	 * @param path
	 * @param fileName
	 * @return
	 */
	@Override
	public String getLastModifiedYyyyMmDd(String path, String fileName) {
	    File file = new File(path + "/" + fileName);
	    Date lastModifiedDate = new Date(file.lastModified());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastModifiedDate);
	    String day = DateUtil.getInstance().getYyyyMMddHHmmssSSS(calendar);
	    return day;
	}

	/**
	 * 指定ディレクトリ内のファイルの名称をListで返す（頭にファイルパスを付ける）
	 * @param path
	 * @return
	 */
	@Override
	public List<String> getPathFileList(String path) {
		List<String> fileList = getFileOnlyNameList(path);

		List<String> pathFileList = new ArrayList<>();

		for(String file: fileList){
			pathFileList.add(path + "/" + file);
		}

		return pathFileList;
	}

	/**
	 * ファイル名から拡張子を返します。
	 * @param fileName ファイル名
	 * @return ファイルの拡張子
	 */
	@Override
	public String getSuffix(String fileName) {
	    if (fileName == null)
	        return null;
	    int point = fileName.lastIndexOf(".");
	    if (point != -1) {
	        return fileName.substring(point + 1);
	    }
	    return fileName;
	}

	/**
	 * InputStreamをbyte配列に変換する
	 * @param is
	 * @return
	 */
	@Override
	public byte[] inputStream2Bytes(InputStream is) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		OutputStream os = new BufferedOutputStream(b);
		int c;
		try {
			while ((c = is.read()) != -1) {
				os.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b.toByteArray();
	}

	/**
	 * ユーザディレクトリを返す
	 * @return
	 */
	@Override
	public String getUserDir() {
    	String userDir = System.getProperty("user.dir");
    	userDir = userDir.replace("\\","/");
    	return userDir;
	}

	@Override
	public void inputStream2File(InputStream inputStream, String outputFile) {
		File outputImageFile = new File(outputFile);

		OutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(outputImageFile));
			int c;
			while ((c = inputStream.read()) != -1){
				os.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ディレクトリ存在チェック
	 * @param path
	 * @return
	 */
	@Override
	public Boolean isDirectory(String path) {
		File dir = new File(path);
		return dir.isDirectory();
	}

    @Override
	public int isExistFile(String pathFileName) {
        int rtnCd = 1;

        File file = new File(pathFileName);

        if(file.exists() && file.isFile()){
        	rtnCd = 0;
        }

        return rtnCd;
    }

    /**
     * ファイル存在チェック
     * @param fileName
     * @return
     */
    @Override
	public int isExistFile(String path, String fileName) {
        return isExistFile(path + "/" + fileName);
    }

	/**
	 * リストの内容を指定ファイルに出力する
	 * @param list
	 * @param outFileName
	 * @return
	 */
	@Override
	public int listWrite(List<String> list, String path, String fileName) {
		try{
			//ファイル名のインスタンス化
			File outputFile = new File(path + "/" + fileName);

			//入出力ストリームのインスタンス化
			FileWriter out = new FileWriter(outputFile);

			for(int i=0;i < list.size() ; i ++){
				out.write(list.get(i));
				out.write("\n");
			}

			out.close();
			return 0;
		}catch(IOException err){
			return 1;
		}
	}

    /**
     * ディレクトリを作成する(親ディレクトリも含めてまとめて作成)
     * @param dirName
     * @return
     */
    @Override
	public int mkDirs(String dirName){
        File file = new File(dirName);

        if(file.mkdirs()){
            return 0; //作成
        }else{
            return 1; //既に存在するので未作成
        }
    }

	@Override
	public int mkFile(String pathfileName){
		int rtnCd = 0;

		File file = new File(pathfileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			rtnCd = 1;
			e.printStackTrace();
		}
		return rtnCd;
	}

	/**
	 * ファイル作成する
	 * @param dirName
	 * @return
	 */
	@Override
	public int mkFile(String path, String fileName){
		return  mkFile(path + "/" + fileName);
	}

	/**
	 * ファイルを移動する
	 * @param path1
	 * @param fileName1
	 * @param path2
	 * @param fileName2
	 * @return
	 */
	@Override
	public boolean moveFile(String path1, String fileName1, String path2, String fileName2) {
		File file1 = new File(path1 + "/" + fileName1);	// 移動元
		File file2 = new File(path2 + "/" + fileName2);	// 移動先
		boolean ret = file1.renameTo(file2);
		return ret;
	}

	/**
	 * オブジェクトをファイルに出力する
	 * @param obj
	 * @param outputFile
	 * @return
	 */
	@Override
	public int object2File(Object obj ,String outputFile) {

		String jsonSt = JsonUtil.getInstance().makeObjectToJsonString(obj);

		int rtnCd = string2File(jsonSt, outputFile);

		return rtnCd;
	}

	/**
	 * オブジェクトファイルを文字列ファイルに変換して出力する。
	 * （例：画像ファイルを文字列に変換してファイルに出力する。）
	 * @param objeFile
	 * @param stringFile
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Override
	public int objFile2stringFile(String objeFile, String stringFile) {

		String ddd = file2string(objeFile);

		return string2File(ddd, stringFile);
	}

	@Override
	public Boolean output(File file, OutputStream os) {
		Boolean rtnBool = true;

		byte buffer[] = new byte[4096];
        try (FileInputStream fis = new FileInputStream(file)) {
            int size;
            while ((size = fis.read(buffer)) != -1) {
                os.write(buffer, 0, size);
            }
        } catch (IOException e) {
			e.printStackTrace();
			rtnBool = false;
		}

        return rtnBool;
    }

    /**
     * 対象のファイルオブジェクトの削除を行う.<BR>
     * ディレクトリの場合は再帰処理を行い、削除する。
     *
     * @param file ファイルオブジェクト
     * @throws Exception
     */
    @Override
	public int recursiveDeleteFile(File file) {
        // 存在しない場合は処理終了
        if (!file.exists()) {
            return 1;
        }
        // 対象がディレクトリの場合は再帰処理
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                recursiveDeleteFile(child);
            }
        }
        // 対象がファイルもしくは配下が空のディレクトリの場合は削除する
        file.delete();

        return 0;
    }

    /**
     * 対象のファイルオブジェクトの削除を行う.<BR>
     * @param pathFile
     * @return
     */
    @Override
	public int recursiveDeleteFile(String pathFile) {
    	File file = new File(pathFile);
    	return recursiveDeleteFile(file);
    }

	/**
	 * 文字列をファイルに出力する
	 * @param b
	 * @param outputFile
	 * @throws UnsupportedEncodingException
	 * @return
	 */
	@Override
	public int string2File(String data, String outputFile) {
		int rtnCd = 0;
		try {
			byte[] byteData = data.getBytes("UTF-8");
			byte2File(byteData, outputFile);
		} catch (UnsupportedEncodingException e) {
			rtnCd = 1;
			e.printStackTrace();
		}
		return rtnCd;
	}

	/**
	 * 文字列リストをファイルに出力する
	 * @param dataList
	 * @param outputFile
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public void stringListToFile(List<String> dataList, PrintWriter pw) {
		// ファイルへの書き込み
		for(String data: dataList){
	    	  StringBuffer str = new StringBuffer();
	    	  str.append(data);
	    	  str.append(System.getProperty("line.separator"));
	    	  pw.write(str.toString());
		}
	}

	/**
	 * 文字列リストをファイルに出力する
	 * @param dataList
	 * @param outputFile
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public int stringListToFile(List<String> dataList, String _outputFile) {

		int rtnCd = 0;

		try {
				    // ファイルオブジェクトの生成
		    File outputFile = new File(_outputFile);

		    // 出力ストリームの生成（文字コード指定）
		    FileOutputStream fo = new FileOutputStream(outputFile);
		    OutputStreamWriter ow = new OutputStreamWriter(fo, "UTF-8");
		    PrintWriter pw = new PrintWriter(ow);

		    stringListToFile(dataList, pw);

		    pw.close();
		    ow.close();
		    fo.close();

		} catch(Exception e) {
			rtnCd = 1;
			e.printStackTrace();
		}

	    return rtnCd;
	}

	/**
	 * パスリスト取得
	 * 指定パスは以下のフォルダ名称を再帰的に取得してリストで返す
	 * @param inPath
	 * @return
	 */
	@Override
	public List<String> getPathList(String inPath) {
		List<String> rtnList = new ArrayList<>();

		rtnList = dumpPath(rtnList, new File(inPath));

		return rtnList;
	}

	/**
	 * パスリスト取得
	 * @param list
	 * @param file
	 * @return
	 */
	private List<String> dumpPath(List<String> list,File file){

        // ファイル一覧取得
        File[] files = file.listFiles();

        if(files == null){
            return list;
        }

        for (File tmpFile : files) {
        	// ディレクトリの場合
            if(tmpFile.isDirectory()){
                // 再帰呼び出し
                list.add(tmpFile.getPath());
                dumpPath(list, tmpFile);
            }
        }

        return list;
    }

	@Override
	public String file2string3(String fileName) {
		StringBuffer sb = new StringBuffer();

		List<String> list = getPathFileList(fileName);

		for(String data : list) {
			sb.append(data);
			sb.append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}

	@Override
	public String getSystemPath() {

		String targetPath = new File(".").getAbsoluteFile().getParent();

		String pathName = null;

		if (targetPath.indexOf("eclipse") < 0) {
			pathName = StringUtil.getInstance().getFirstIndexOfString(targetPath, "workspace");
		}else {
			pathName = StringUtil.getInstance().getFirstIndexOfString(targetPath, "eclipse");
		}

		return pathName;
	}
}
