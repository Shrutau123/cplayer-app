package cplayer.favourites.app.model;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class FavoutitesTests {

	private Favourites favourites;

	@Before
	public void setUp() {
		favourites = new Favourites();
		
		favourites.setPid("1234");
		favourites.setName("Sachin");
		
		favourites.setDateOfBirth("42 years");
		favourites.setCountry("India");
		favourites.setRole("Opener");
		favourites.setPlaceOfBirth("India, Mumbai Indians");
		favourites.setPlayerImg("sampleimage.jpeg");
		favourites.setUsername("shivaagn@in.ibm.com");
		favourites.setStatus(true);
	}

	@Test
	public void test() {
		new BeanTester().testBean(Favourites.class);
	}
}