package cn.gxkjdx.util.test;

import org.junit.Test;

import cn.gxkjdx.util.Md5Utils;

public class Md5UtilsTest {

	@Test
	public void getPWD(){
		String OldPWD="123";
		String NewPWD = Md5Utils.md5(OldPWD);
		System.out.println(NewPWD);
	}
}
