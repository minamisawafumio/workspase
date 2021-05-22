package test.ramda.step6.pkg1;

import org.junit.Test;

import test.ramda.step6.pkg2.MyProcess;

public class Main {

	@Test
	public void main() {
		MyProcess myProcess = new MyProcess();
		myProcess.process(() -> {
			System.out.println(" step6 aaaaa22222aaaaaaafadf");
		});
	}
}
