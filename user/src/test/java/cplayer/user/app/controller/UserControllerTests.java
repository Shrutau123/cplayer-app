package cplayer.user.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import cplayer.user.app.model.User;
import cplayer.user.app.service.UserService;

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
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private User user;
    @MockBean
    UserService userService;
    @InjectMocks
    UserController userController;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setUsername("shivaagn@123");
        user.setMobile("9810316274");
        user.setImage("lets.say.sample.jpeg");
        user.setName("Shivansh");
    }
    
    
    @Test
    public void registerUserSuccess() throws Exception  {
        when(userService.addUser(any())).thenReturn(true);
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    
    
    @Test
    public void registerUserFailure() throws Exception {
        when(userService.addUser(any())).thenReturn(false);
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
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
