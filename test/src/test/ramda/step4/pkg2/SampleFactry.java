package test.ramda.step4.pkg2;

public class SampleFactry {

	public static Sample makeSample() {

		//インターフェースをnewして内容を記述する。クラスが無くて(無名)）もメソッドを使える
		//これが無名クラス。この記述を簡素化したい要求よりラムダ式が登場する(次(step5)へ)
		return new Sample() {
			@Override
			public void execute() {
				System.out.println("InnerSample MUMEI 4444444");
			}
		};
	}
}
