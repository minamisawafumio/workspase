package test.ramda.step3.pkg2;


//インナークラスで、同一パッケージから、Sampleを継承したexecuteメソッドを隠す事ができる。
//ただ、この記述だと、わざわざクラスを作るほどの事でもないので、匿名(無名)クラスが登場する。
//(次(step4)で無名クラスを説明する)
public class SampleFactry {

	public static Sample makeSample() {
		return new SampleFactry().new InnerSample();
	}

	private class InnerSample implements Sample {
		@Override
		public void execute() {
			System.out.println("InnerSample");
		}
	}
}
