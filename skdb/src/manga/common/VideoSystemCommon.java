package manga.common;

import java.util.List;

public interface VideoSystemCommon {

	List<String[]> makeVideoInfoList();

	VideoSystemCommon videoSystemCommon = new VideoSystemCommonImpl();

	public static VideoSystemCommon getinstance() {
		return videoSystemCommon;
	}
}