package ru.job4j.rest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.rest.RestApplication;
import ru.job4j.rest.models.Person;
import ru.job4j.rest.services.PersonService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RestApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {
    private static final String CONTROLLER_URL = "/person/";
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PersonService personService;
    
    
    @Test
    void findAllTest() throws Exception {
        when(personService.findAll()).thenReturn(List.of(
                new Person(1, "first", "firstP"),
                new Person(2, "second", "secondP"),
                new Person(3, "third", "thirdP")
        ));
        
        String rsl = this.mockMvc.perform(get(CONTROLLER_URL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = '['
                + "{\"id\":1,\"login\":\"first\",\"password\":\"firstP\"},"
                + "{\"id\":2,\"login\":\"second\",\"password\":\"secondP\"},"
                + "{\"id\":3,\"login\":\"third\",\"password\":\"thirdP\"}"
                + ']';
        
        System.out.println(rsl);
        assertEquals(rsl, expected);
    }
    
    @Test
    void findByIdTest() throws Exception {
        when(personService.findById(1)).thenReturn(Optional.of(new Person(1, "first", "firstP")));
        
        String rsl = this.mockMvc.perform(get(CONTROLLER_URL + '1'))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"id\":1,\"login\":\"first\",\"password\":\"firstP\"}";
        
        System.out.println(rsl);
        assertEquals(rsl, expected);
    }
    
    @Test
    void createTest() throws Exception {
        when(personService.save(any(Person.class))).thenReturn(new Person(1, "first", "firstP"));
        
        final String requestBodyJson
                = "{\n"
                + "  \"id\": 0,\n"
                + "  \"login\": \"first\",\n"
                + "  \"password\": \"firstP\"\n"
                + "}";
        
        String rsl = this.mockMvc.perform(post(CONTROLLER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        String expected = "{\"id\":1,\"login\":\"first\",\"password\":\"firstP\"}";
        
        System.out.println(rsl);
        assertEquals(expected, rsl);
    }
    
    @Test
    void updateTest() throws Exception {
        final String requestBodyJson
                = "{\n"
                + "  \"id\": 0,\n"
                + "  \"login\": \"first\",\n"
                + "  \"password\": \"firstP\"\n"
                + "}";
        
        this.mockMvc.perform(put(CONTROLLER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andExpect(status().isOk());
    }
    
    @Test
    void deleteTest() throws Exception {
        this.mockMvc.perform(delete(CONTROLLER_URL + '1'))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
}