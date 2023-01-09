package cplayer.user.app.model;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserTests {

	private User user;

	@Before
	public void setUp() {
		user = new User();

		user.setUsername("shivaagn@123");
		user.setMobile("9810316274");
		user.setImage("lets.say.sample.jpeg");
		user.setName("Shivansh");
	}

	@Test
	public void test() {
		new BeanTester().testBean(User.class);
	}
}