package manga.common;

import java.util.ArrayList;
import java.util.List;

import jp.co.fm.businessLogic.common.FileUtil;

public class VideoSystemCommonImpl implements VideoSystemCommon {

    @Override
	public List<String[]> makeVideoInfoList() {
    	List<String[]> rtnList = new ArrayList<>();

		String inPath = "C:/pleiades/pleiades-2019-12-java-win-64bit-jre_20200322/workspace/skdb/WebContent/resources/video/data/";

		List<String> pathFilelist = FileUtil.getInstance().getPathFileNameList(inPath);

		for(int i=0; i < pathFilelist.size(); i++) {
			String pathFileName = pathFilelist.get(i);
			pathFileName = pathFileName.replaceAll("\\\\","/");
			int lastIndex = pathFileName.lastIndexOf("/");
			int lastIndex2 = pathFileName.lastIndexOf(".");
			String path1 = pathFileName.replace(inPath, "");
			String fileName = pathFileName.substring(lastIndex + 1, lastIndex2);
			String ary[] = {path1, fileName};
			rtnList.add(ary);
		}

		return rtnList;
    }
}
