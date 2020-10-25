package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Persistence.TransformerRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@WebMvcTest
@AutoConfigureMockMvc
public class TransformerControllerTest {
    @MockBean
    private TransformerRepository transformerRepository;

    @MockBean
    private TransformerService transformerService;

    @Autowired
    TransformerController transformerController;

    @Autowired
    private MockMvc mockMvc;

    private static Stream multiFieldTest(){
        return Stream.of(
                Arguments.of(Arrays.asList("strength")),
                Arguments.of(Arrays.asList("strength","intelligence","speed")),
                Arguments.of(Arrays.asList("strength","intelligence","speed","endurance")),
                Arguments.of(Arrays.asList("strength","intelligence","speed","endurance","rank","courage", "firepower")),
                Arguments.of(Arrays.asList("strength","intelligence","speed","endurance","rank","courage", "firepower","skill"))
        );
    }

    private static Stream singleFieldsTest(){
        List<String> fildsToTest = Arrays.asList("strength","intelligence","speed","endurance","rank","courage", "firepower","skill");
        return fildsToTest.stream();
    }
    @Test
    public void whenPostRequestToTransformerAndValidTransformer_thenCorrectResponse() throws Exception {
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"strength\":20,\"intelligence\":15,\"speed\":45,\"endurance\":87,\"rank\":100,\"courage\":1,\"firepower\":31,\"skill\":66,\"overallRating\":198}"));
    }


    @ParameterizedTest
    @MethodSource("multiFieldTest")
    public void whenPostRequestToTransformerAndMultipleFieldsMissing_thenShouldRetunBadRequest(List<String> fields) throws Exception {
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeMultipleElementsFromJson(fields)
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","BAD_REQUEST");
        JSONArray jsonArray = new JSONArray();
        for(String field: fields)
            jsonArray.put(field + " cannot be null");
        jsonObject.put("messages", jsonArray);
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

    @ParameterizedTest
    @MethodSource("singleFieldsTest")
    public void whenPostRequestToTransformerAndSngleFieldMissing_thenShouldRetunBadRequest(String field) throws Exception {
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson(field)
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","BAD_REQUEST");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(field + " cannot be null");
        jsonObject.put("messages", jsonArray);
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

    @ParameterizedTest
    @MethodSource("singleFieldsTest")
    public void whenPostRequestToTransformerAndHaveAFieldValueLowerThan1_thenShouldRetunBadRequest(String field) throws Exception {
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue(field,-1)
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","BAD_REQUEST");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(field + " must be equal or greater than 1");
        jsonObject.put("messages", jsonArray);
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

    @ParameterizedTest
    @MethodSource("singleFieldsTest")
    public void whenPostRequestToTransformerAndHaveAFieldValueGreaterThan100_thenShouldRetunBadRequest(String field) throws Exception {
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue(field,101)
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","BAD_REQUEST");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(field + " must be equal or less than 100");
        jsonObject.put("messages", jsonArray);
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

}
