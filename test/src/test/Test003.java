package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.service.FT001F01;
import jp.co.fm.businessLogic.table.T_0160;

@RunWith(PowerMockRunner.class)
public class Test003 {

	@Mock
	DbUtil dbUtil;

	@BeforeClass
	public static void start(){
		System.out.println("start");
	}

	@AfterClass
	public static void end() {
		System.out.println("end");
    }

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@InjectMocks
    private FT001F01 fT001F01;


	@Test
	public void test1(){

		Map mmm = new HashMap();

		String st = null;

		try{
			T_0160 t_0160 = null;


		}catch(Exception e) {

		}

		System.out.println(st);
	}
}
