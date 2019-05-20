package cn.gxkjdx.mapper.test;

import java.sql.SQLException;

import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {

	@Test
	public void dataSource(){
		ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("classpath:spring-*.xml");
		DataSource bean = context.getBean(DataSource.class);
		try {
			System.out.println(bean.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
