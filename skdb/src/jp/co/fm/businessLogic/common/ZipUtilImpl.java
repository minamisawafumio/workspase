package jp.co.fm.businessLogic.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

class ZipUtilImpl implements ZipUtil {

	/**
	 * フォルダ圧縮処理
	 * @param path
	 * @param pathAndFileName
	 * @return
	 */
	@Override
	public int compres(String path, String pathAndFileName) {
		File file = new File(path);

		File []files = file.listFiles();

		File zipFile = new File(pathAndFileName);

			FileOutputStream fileOutputStream;

			try {
				fileOutputStream = new FileOutputStream(zipFile);

				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

				ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

				zipOutputStream.setLevel(5);

				zipOutputStream.setEncoding("Shift_JIS");

				byte[]buf = new byte[1024];

				InputStream bufferedInputStream = null;

				for(File file2 : files){
					String name = file2.getName();
					ZipEntry zipEntry = new ZipEntry(name);
					zipOutputStream.putNextEntry(zipEntry);
					FileInputStream fileInputStream = new FileInputStream(file2);
					bufferedInputStream = new BufferedInputStream(fileInputStream);

					Integer len = 0;

					while((len = bufferedInputStream.read(buf))!= -1){
						zipOutputStream.write(buf, 0, len);
					}
					IOUtils.closeQuietly(bufferedInputStream);
				}

				zipOutputStream.close();

				bufferedOutputStream.close();

				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return -2;
			}
			return 0;
	}


	/**
	 * ファイルディレクトリをZIP圧縮します
	 * @param zos
	 * @param files
	 * @throws Exception
	 */
	@Override
	public void encodeZip(ZipOutputStream zos, File[] files) throws Exception {
		byte[] buf = new byte[1024];

		for (File f : files) {
			if (f.isDirectory()) {
				encodeZip(zos, f.listFiles());
			} else {
				ZipEntry ze = new ZipEntry(f.getPath().replace('\\', '/'));
				zos.putNextEntry(ze);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				for (;;) {
					int len = is.read(buf);
					if (len < 0) break;
					zos.write(buf, 0, len);
				}
				is.close();
			}
		}
	}

    /**
     * 指定フォルダ下のファイルをZIPファイルにする。
     * @param files
     * @param pathAndZipFileName
     */
    @Override
	public int folderToZip(String path, String pathAndZipFileName) {

    	int rtnCd = 0;

        File dir = new File(path);

        File[] files = dir.listFiles();

    	File zipFile = new File(pathAndZipFileName);

    	try {
        	FileOutputStream fi = new FileOutputStream(zipFile);

        	BufferedOutputStream bf = new BufferedOutputStream(fi);

        	ZipOutputStream zos = new ZipOutputStream(bf);

        	zos.setEncoding("MS932");

            byte[] buf = new byte[1024];

            InputStream is = null;

            for (File file : files) {
                ZipEntry entry = new ZipEntry(file.getName());
                zos.putNextEntry(entry);
                is = new BufferedInputStream(new FileInputStream(file));
                int len = 0;
                while ((len = is.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
            }

           	is.close();

            zos.close();

            bf.close();

            fi.close();
        } catch (IOException e) {
            e.printStackTrace();
            rtnCd = 1;
        } finally {

        }

    	return rtnCd;
    }



	/**
	 * ZIPファイルを解凍します。
	 *
	 * @param pathAndFileName ディレクトリ + ファイル
	 * @param outPath 出力ディレクトリ
	 * @throws IOException
	 */
	@Override
	public void decodeZip(String pathAndFileName, String outPath) throws IOException {
		ZipFile zipFile = new ZipFile(pathAndFileName, "MS932");
		Enumeration<?> enum1 = zipFile.getEntries();

		while (enum1.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) enum1.nextElement();

			if (entry.isDirectory()) {
				new File(entry.getName()).mkdirs();
			}else{
				File parent = new File(outPath + "/" + entry.getName()).getParentFile();

				if (parent != null) {
					parent.mkdirs();
				}
				FileOutputStream out = new FileOutputStream(outPath + "/" + entry.getName());
				InputStream in = zipFile.getInputStream(entry);
				byte[] buf = new byte[1024];
				int size = 0;
				while ((size = in.read(buf)) != -1) {
					out.write(buf, 0, size);
				}
				out.close();
				in.close();
			}
		}
		zipFile.close();
	}
}
