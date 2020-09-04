package com.github.perscholas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.perscholas.models.MyModel;
import com.github.perscholas.repositories.MyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


/**
 * @author leon on 8/30/18.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private MyRepository repository;

    @Test
    public void testShow() throws Exception {
        Long givenId = 1L;
        MyModel myModel = new MyModel();
        BDDMockito
                .given(repository.findById(givenId))
                .willReturn(Optional.of(myModel));
        String expectedContent = new ObjectMapper().writeValueAsString(myModel);
        this.mvc.perform(MockMvcRequestBuilders
                .get("/" + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testCreate() throws Exception {
        MyModel myModel = new MyModel();
        BDDMockito
                .given(repository.save(myModel))
                .willReturn(myModel);

        String expectedContent = new ObjectMapper().writeValueAsString(myModel);
        this.mvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }
}
