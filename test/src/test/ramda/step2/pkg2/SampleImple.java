package test.ramda.step2.pkg2;


//ディフォルトクラスにしてパッケージ外からクラスにアクセスできないように隠蔽する
class SampleImple implements Sample{

	@Override
	public void execute() {
		System.out.println("SampleImple");
	}

}
