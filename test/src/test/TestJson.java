package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import net.arnx.jsonic.JSON;

public class TestJson {

    private JsonUtil ju = JsonUtil.getInstance();

    private FileUtil fu = FileUtil.getInstance();

	@Test
	public void test007() {

		String intputFile = "C:/temp/data/kkk.txt";

		Map<String, Object> map = new HashMap<String, Object>();

		map = (Map<String, Object>) fu.file2Object(intputFile, map);

		List<String> lll = (List<String>) map.get("c");

		for(String sss:lll) {
			System.out.println(sss);
		}

		Map mmm = (Map) map.get("b");

		System.out.println(mmm.get("11"));
		System.out.println(mmm.get("22"));
		System.out.println(mmm.get("33"));
	}


	@Test
	public void test006() {

		Map<String, Object> map = new HashMap<String, Object>();

		List<String> ll = new ArrayList<String>();

		ll.add("aa1");
		ll.add("aあああ2");
		ll.add("a3");

		map.put("a", ll);

		Map<String, Object> map2 = new HashMap<String, Object>();

		map2.put("11", "11＿＿11331");
		map2.put("22", "2a2み2");
		map2.put("33", "3南3沢3郁男");

		map.put("b", map2);

		ll = new ArrayList<String>();

		ll.add("cいいい1");
		ll.add("cあああ2");
		ll.add("ii");

		map.put("c", ll);

		String st = ju.makeObjectToJsonString(map);

		System.out.println(st);

		String eData = StringUtil.getInstance().encodeStBase64(st);

		System.out.println(eData);

		String dData = StringUtil.getInstance().decodeStBase64(eData);

		String outputFile = "C:/temp/data/kkk.txt";

		fu.string2File(dData, outputFile);




		System.out.println(dData);

		Map<String, Object> mm = (Map<String, Object>) ju.makeJsonStringToObject(map, st);

		List<String> lll = (List<String>) mm.get("c");

		for(String sss:lll) {
			System.out.println(sss);
		}

		Map mmm = (Map) mm.get("b");

		System.out.println(mmm.get("11"));
		System.out.println(mmm.get("22"));
		System.out.println(mmm.get("33"));
	}




	@Test
	public void test005() {

		Map<String, Object> map = new HashMap<String, Object>();

		List<String> ll = new ArrayList<String>();

		ll.add("a1");
		ll.add("a2");
		ll.add("a3");

		map.put("a", ll);

		ll = new ArrayList<String>();

		ll.add("b1");
		ll.add("b2");
		ll.add("b3");

		map.put("b", ll);

		ll = new ArrayList<String>();

		ll.add("c1");
		ll.add("c2");
		ll.add("南a沢3");

		map.put("c", ll);

		String st = ju.makeObjectToJsonString(map);

		System.out.println(st);

		String eData = StringUtil.getInstance().encodeStBase64(st);

		System.out.println(eData);

		String dData = StringUtil.getInstance().decodeStBase64(eData);

		System.out.println(dData);



		Map<String, Object> mm = (Map<String, Object>) ju.makeJsonStringToObject(map, st);

		List<String> lll = (List<String>) mm.get("c");

		for(String sss:lll) {
			System.out.println(sss);
		}


	}



	@Test
	public void test003() {

		Map<String, Object> map = new HashMap<String, Object>();

		List<String> ll = new ArrayList<String>();

		ll.add("a1");
		ll.add("a2");
		ll.add("a3");

		map.put("a", ll);

		ll = new ArrayList<String>();

		ll.add("b1");
		ll.add("b2");
		ll.add("b3");

		map.put("b", ll);

		ll = new ArrayList<String>();

		ll.add("c1");
		ll.add("c2");
		ll.add("c3");

		map.put("c", ll);

		String st = null;

		try {

			st = new ObjectMapper().writeValueAsString(map);

			System.out.println(st);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Map<String, Object> mm = JSON.decode(st, Map.class);

		List<String> lll = (List<String>) mm.get("c");

		for(String sss:lll) {
			System.out.println(sss);
		}


	}


	@Test
	public void test002() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("1", "aaaaaaaa1");
		map.put("2", "aaaaaaaa2");
		map.put("3", "aaaaaaaa3");

		String st = null;

		try {

			st = new ObjectMapper().writeValueAsString(map);

			System.out.println(st);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Map mm = JSON.decode(st, Map.class);

		System.out.println();
	}
}
