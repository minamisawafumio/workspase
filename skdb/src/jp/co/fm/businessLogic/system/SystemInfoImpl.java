package jp.co.fm.businessLogic.system;

import java.util.HashMap;
import java.util.Map;

class SystemInfoImpl implements SystemInfo {

	private Map<String ,Object> commonMap;

	public SystemInfoImpl(){
		commonMap = new HashMap<>();
	}

	@Override
	public Object getValue(String key) {
		return commonMap.get(key);
	}

	@Override
	public Object putValue(String key, Object obj) {
		return commonMap.put(key, obj);
	}
}
