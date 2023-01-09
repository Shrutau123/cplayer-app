package cplayer.favourites.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cplayer.favourites.app.model.Favourites;
import cplayer.favourites.app.repository.FavouritesRepository;


public class FavServiceImplTest {
	
	@Mock
     FavouritesRepository favouritesRepository;
	
	Favourites favourites;
	
	@InjectMocks
    FavouritesService favouritesService;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
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
    public void addFavSuccess(){
        when(favouritesRepository.save((Favourites) any())).thenReturn(favourites);
        Favourites favSaved = favouritesRepository.save(favourites);
        assertEquals(favourites, favSaved);
    }
}