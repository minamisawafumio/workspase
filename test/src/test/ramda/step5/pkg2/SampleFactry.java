package test.ramda.step5.pkg2;

public class SampleFactry {

	public static Sample makeSample() {
		//これがラムダ式。ラムダ式で使えるインターフェースを関数型インターフェースという
		//メソッドを1つだけ、参照できるもの。１つなので、コンパイラーがメソッド名を類推するので省略が可能
		Sample sample = () -> {
			System.out.println("InnerSample MUMEI 55555AAA");
		};
		return sample;
	}
}
