package test.ramda.step3.pkg2;

import org.junit.Test;

public class Main2 {

	@Test
	public void main() {
		//SampleFactryと同一パッケージだが、インナークラスのメソッドにはアクセスできない
		Sample sample = SampleFactry.makeSample();
		sample.execute();
	}
}
