package jp.co.fm.businessLogic.common;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class CalcUtilImpl implements CalcUtil {
	/**
	 * @param calc
	 * @return
	 */
    @Override
	public double calc(String calc) {
    	try {
    		ScriptEngineManager factory = new ScriptEngineManager();
    		ScriptEngine engine = factory.getEngineByName("JavaScript");
    		return (Double)engine.eval(calc);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
    }

    /**
     * @param calc
     * @param keyMap
     * @return
     */
    @Override
	public double calc(String calc, Map<String, String> keyMap) {
    	String gggg = StringUtil.getInstance().changeBindMoji(calc, keyMap);

    	return calc(gggg);
    }

}
