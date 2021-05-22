package jp.co.fm.businessLogic.system;

public interface SystemInfo {

	Object getValue(String key);

	Object putValue(String key, Object obj);

	SystemInfo systemInfo = new SystemInfoImpl();

	public static SystemInfo getInstance() {
		return systemInfo;
	}
}