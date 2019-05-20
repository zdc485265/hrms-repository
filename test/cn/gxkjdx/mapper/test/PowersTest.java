package cn.gxkjdx.mapper.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gxkjdx.mapper.PowerMapper;

public class PowersTest {

	@Test
	public void getPowers(){
		ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("classpath:spring-*.xml");
		PowerMapper bean = context.getBean(PowerMapper.class);
		List<Map<String, Object>> powers = bean.findByIds("1");
		System.out.println(powers);
	}
}
