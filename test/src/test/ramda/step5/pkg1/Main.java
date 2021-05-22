package test.ramda.step5.pkg1;

import org.junit.Test;

import test.ramda.step5.pkg2.Sample;
import test.ramda.step5.pkg2.SampleFactry;

public class Main {

	@Test
	public void main() {
		Sample sample = SampleFactry.makeSample();
		sample.execute();
	}
}
