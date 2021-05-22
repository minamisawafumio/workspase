package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

public class TestLamda {


	@Test
	public void test_Predicate() {

		//外部から無名クラス内の変数の値を取得する事はできない。(隠蔽）
		//無名クラス内からは、外部の変数の値を取ることができるが、外部の変数を変更できない(static変数を除く）
		//関連性が外 → 中の一方通行である為、無名関数内でエラーが発生しても
		//影響範囲を特定（内部のみ）に限定できる。


		//引数=Map
		Predicate<Map<String, String>> hoge = (hogeMap) -> {

			Map<String, String> mm = new HashMap<>();

			String uyuyu = (String) hogeMap.get("1");

			Boolean bool = false;

			if(uyuyu.equals("123")) {
				bool = true;
			}

			return bool;
		};

		Map<String, String> map = new HashMap<>();

		map.put("1", "1234");

		Boolean result = hoge.test(map);
		System.out.println(result);
	}



	@Test
	public void test_Function2() {

		//外部から無名クラス内の変数の値を取得する事はできない。(隠蔽）
		//無名クラス内からは、外部の変数の値を取ることができるが、外部の変数を変更できない(static変数を除く）
		//関連性が外 → 中の一方通行である為、無名関数内でエラーが発生しても
		//影響範囲を特定（内部のみ）に限定できる。

		String jKey = "minamisawaKey";
		String jVal = "minamisawaVal";

		//入力=Map 戻り値=Map
		Function<Map<String, Object>, Map<String, String>> hoge = (hogeMap) -> {

			Map<String, String> mm = new HashMap<>();

			String uyuyu = (String) hogeMap.get("");

			mm.put(jKey, jVal);

			return mm;
		};

		Map<String, Object> mm2 = new HashMap<>();

		Map<String, String> result = hoge.apply(mm2);
		System.out.println(result.get(jKey));
	}

	@Test
	public void test_Function() {

		String jjjjj = "minamisawa";

		Function<Integer, String> hoge = (i) -> {
			return jjjjj + "*aaaaaadddddd"+ i;
		};

		String result = hoge.apply(10);
		System.out.println(result);
	}

	interface Calculator {
		int calc(int a, int b);
	}

	@Test
	public void testCalculator() {
		Calculator c = (a, b) -> a + b;

		int aaa = c.calc(10, 20);

		System.out.println(aaa);
	}

	@Test
	public void streamTest001() {

		List<Integer> integerList = Arrays.asList(
		        new Integer(998),
		        new Integer( 999),
		        new Integer( 1000),
		        new Integer( 1001),
		        new Integer( 1002)
		);

		//リストの要素が1000以上のものをストリングのリストで返す
		List<String> result = integerList.stream()
		        .filter(hoge -> hoge.intValue() > 1000)
		        .map(hoge -> hoge.toString())
		        .collect(Collectors.toList());

		for(String st:  result) {
			System.out.println(st);
		}
	}
}
