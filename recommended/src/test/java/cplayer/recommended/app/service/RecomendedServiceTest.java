package cplayer.recommended.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cplayer.recommended.app.model.Recommended;
import cplayer.recommended.app.repository.RecommendedRepository;

public class RecomendedServiceTest {
	
	@Mock
     RecommendedRepository recommendedRepository;
	
	Recommended recommended;
	
	@InjectMocks
    RecommendedService recommendedService;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
        recommended = new Recommended();
        recommended.setPid("1234");
		recommended.setName("Sachin");
		recommended.setDateOfBirth("42 years");
		recommended.setCountry("India");
		recommended.setRole("Opener");
		recommended.setPlaceOfBirth("India, Mumbai Indians");
		recommended.setPlayerImg("sampleimage.jpeg");
	}
	@Test
    public void addRecomSuccess(){
        when(recommendedRepository.save((Recommended) any())).thenReturn(recommended);
        Recommended recomSaved = recommendedRepository.save(recommended);
        assertEquals(recommended, recomSaved);
    }
}