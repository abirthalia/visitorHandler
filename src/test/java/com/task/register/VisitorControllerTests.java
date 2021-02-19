package com.task.register;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.task.register.visitor.Visitor;
import com.task.register.visitor.VisitorController;
import com.task.register.visitor.VisitorService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VisitorController.class)
public class VisitorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VisitorService visitorService;




    @Test
    public void testListVisitors() throws Exception{
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(new Visitor(004,
                "Amit B",
                101,
                LocalDate.of(2021, Month.JANUARY,26),
                LocalTime.of(11,00),
                "Entering"));
        visitorList.add(new Visitor(005,
                "Kumar G",
                101,
                LocalDate.of(2021,Month.JANUARY,29),
                LocalTime.of(12,00),
                "Entering"));

        Mockito.when(visitorService.getAllVisitorList()).thenReturn(visitorList);

        String URI = "/visitors";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(visitorList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

    }

    @Test
    public void testCreateVisitors() throws Exception{
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(new Visitor(004,
                "Amit B",
                101,
                LocalDate.of(2021, Month.JANUARY,26),
                LocalTime.of(11,00),
                "Entering"));
        visitorList.add(new Visitor(005,
                "Kumar G",
                101,
                LocalDate.of(2021,Month.JANUARY,29),
                LocalTime.of(12,00),
                "Entering"));

        String inputInJson = this.mapToJson(visitorList);

        String URI = "/visitors";
        Mockito.when(visitorService.addVisitor(Mockito.any(Visitor.class))).thenReturn(visitorList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());

        /*String inputInJson = this.mapToJson(visitorList);

        Mockito.when(visitorService.addVisitor(Mockito.any(Visitor.class))).thenReturn(visitorList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/visitors")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        assertEquals("http://localhost/visitors",response.getHeader(HttpHeaders.LOCATION));*/

    }



    private String mapToJson(Object object) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);

    }



}
