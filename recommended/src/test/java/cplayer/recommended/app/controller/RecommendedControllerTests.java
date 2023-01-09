package cplayer.recommended.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import cplayer.recommended.app.model.Recommended;
import cplayer.recommended.app.service.RecommendedService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class RecommendedControllerTests {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private Recommended recommended;
    
    @MockBean
    RecommendedService recommendedService;
    
    @InjectMocks
    RecommendedController recommendedController;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recommendedController).build();
       
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
    public void addRecomSuccess() throws Exception  {
        when(recommendedService.addData(any())).thenReturn(true);
        mockMvc.perform(post("/api/recom")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recommended)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    
    
    @Test
    public void addRecomFailure() throws Exception {
        when(recommendedService.addData(any())).thenReturn(false);
        mockMvc.perform(post("/api/recom")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recommended)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
   
   
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
