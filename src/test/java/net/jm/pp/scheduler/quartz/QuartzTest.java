package net.jm.pp.scheduler.quartz;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-test.xml" })
public class QuartzTest {
	
	@Test
	public void test() {
		try {
			Thread.sleep(30000l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}

}
