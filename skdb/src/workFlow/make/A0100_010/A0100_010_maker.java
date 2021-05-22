package workFlow.make.A0100_010;

import java.util.Map;

public interface A0100_010_maker {

	Map<String, Object> makeBusinessLogic();

	A0100_010_maker a0100_010_maker = new A0100_010_makerImpl();

	public static A0100_010_maker getInstance() {
		return a0100_010_maker;
	}

}