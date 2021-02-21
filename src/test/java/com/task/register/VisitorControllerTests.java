package com.task.register;

import org.junit.Test;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
    public void testGetAllVisitorList() throws Exception{
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
    public void testAddVisitor() throws Exception{

        Visitor visitor1 = new Visitor(004,
                "Amit B",
                101,
                LocalDate.of(2021, Month.JANUARY,26),
                LocalTime.of(11,00),
                "Entering");


        String inputInJson = this.mapToJson(visitor1);

        String URI = "/visitors";
        Mockito.when(visitorService.addVisitor(Mockito.any(Visitor.class))).thenReturn(visitor1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    public void testGetVisitorByDate() throws Exception{
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(new Visitor(004,
                "Amit B",
                101,
                LocalDate.of(2021, Month.JANUARY,29),
                LocalTime.of(11,00),
                "Entering"));
        visitorList.add(new Visitor(005,
                "Kumar G",
                101,
                LocalDate.of(2021,Month.JANUARY,29),
                LocalTime.of(12,00),
                "Entering"));

        Mockito.when(visitorService.getVisitorByDate(Mockito.any(LocalDate.class))).thenReturn(visitorList);

        String URI = "/visitors/2021-01-29";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(visitorList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

    }



    @Test
    public void testCheckVisitor() throws Exception{
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


        Mockito.when(visitorService.checkVisitor(Mockito.anyInt())).thenReturn("Visitor already Exists");

        String URI = "/visitors/check/004";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals(content, "Visitor already Exists");

    }

    @Test
    public void testUpdateVisitor() throws Exception {

        Visitor visitor1 = new Visitor(001,
                "Amit B",
                101,
                LocalDate.of(2021, Month.JANUARY, 26),
                LocalTime.of(11, 00),
                "Entering");


        String inputInJson = this.mapToJson(visitor1);

        String URI = "/visitors/updateStatus/001";

        Mockito.when(visitorService.updateVisitor(Mockito.anyInt(), Mockito.any(Visitor.class))).thenReturn(visitor1);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URI)
                .contentType(MediaType.APPLICATION_JSON).content(inputInJson))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

    }


    private String mapToJson(Object object) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);

    }



}
