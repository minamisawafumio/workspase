package test.ramda.step1.pkg2;

import org.junit.Test;

public class Main {

	@Test
	public void main() {
		Sample sample = new SampleImple();
		//これはいけない！！　SampleImpleのメソッドは隠蔽すべきである
		sample.execute();
	}
}
