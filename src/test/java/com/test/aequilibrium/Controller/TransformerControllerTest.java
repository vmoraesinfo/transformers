package com.test.aequilibrium.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.aequilibrium.Model.Transformer;
import com.test.aequilibrium.Persistence.TransformerRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    private static Stream updateSingleFieldTest(){
        return Stream.of(
                Arguments.of("strength",5),
                Arguments.of("intelligence",70),
                Arguments.of("speed",99),
                Arguments.of("endurance",33),
                Arguments.of("rank",25),
                Arguments.of("courage",12),
                Arguments.of("firepower",45),
                Arguments.of("skill",29)

        );
    }

    private static Stream updateMultiFieldTest(){
        return Stream.of(
                Arguments.of(Arrays.asList("strength",
                        "intelligence","speed","endurance",
                        "rank","courage","firepower","skill")
                        ,Arrays.asList(5,70,99,33,25,12,45,29))

        );
    }
    @Test
    public void whenPostRequestToTransformerAndValidTransformer_thenCorrectResponse() throws Exception {
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .build();
        String jsonObject = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",0)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject));
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

    @ParameterizedTest
    @MethodSource("updateSingleFieldTest")
    public void whenPutRequestToTransformerAndValidTransformer_thenCorrectResponse(String field, int value) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transformer transformer = mapper.readValue(new JsonBuilder().createTransformerWithFakeValues().build(), Transformer.class);
        Mockito.when(transformerService.findTransformer(1)).thenReturn(transformer);
        String transformerJson = new JsonBuilder()
                .changeKeyValue("id",1)
                .changeKeyValue(field,value)
                .build();
        JSONObject jsonObject = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue(field,value)
                .getJson();
        mockMvc.perform(MockMvcRequestBuilders.put("/transformer/1")
                .content(transformerJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

    @ParameterizedTest
    @MethodSource("updateMultiFieldTest")
    public void whenPutRequestToTransformerAndValidTransformerWithMultiField_thenCorrectResponse(List<String> fields, List<Integer> values) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transformer transformer = mapper.readValue(new JsonBuilder().createTransformerWithFakeValues().build(), Transformer.class);
        Mockito.when(transformerService.findTransformer(1)).thenReturn(transformer);
        String transformerJson = new JsonBuilder()
                .changeKeyValue("id",1)
                .changeKeyValue(fields,values)
                .build();
        JSONObject jsonObject = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue(fields,values)
                .getJson();
        mockMvc.perform(MockMvcRequestBuilders.put("/transformer/1")
                .content(transformerJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

    @ParameterizedTest
    @MethodSource("updateSingleFieldTest")
    public void whenPutRequestToTransformerAndNoTransformerFound_thenNotFoundResponseExpected(String field, int value) throws Exception {
        Mockito.when(transformerService.findTransformer(1)).thenThrow(new IllegalArgumentException("Transformer not found with id: 1"));
        String transformerJson = new JsonBuilder()
                .changeKeyValue("id",1)
                .changeKeyValue(field,value)
                .build();
        JSONObject jsonObject = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue(field,value)
                .getJson();
        mockMvc.perform(MockMvcRequestBuilders.put("/transformer/1")
                .content(transformerJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.header().string("ETag","\"Transformer not found with id: 1\""));
    }

    @Test
    public void whenDeteRequestToTransformerAndValidTransformer_thenCorrectResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transformer transformer = mapper.readValue(new JsonBuilder().createTransformerWithFakeValues().build(), Transformer.class);
        Mockito.when(transformerService.findTransformer(1)).thenReturn(transformer);
        mockMvc.perform(MockMvcRequestBuilders.delete("/transformer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenDeteRequestToTransformerAndTransformerNotExists_thenNotFoundResponse() throws Exception {
        Mockito.when(transformerService.findTransformer(1)).thenThrow(new IllegalArgumentException("Transformer not found with id: 1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/transformer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.header().string("ETag","\"Transformer not found with id: 1\""));
    }

    @Test
    public void whenGetRequestToTransformerToGetByIdAndItExists_thenReturnOneTransformer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .getJson();
        Transformer transformer = mapper.readValue(jsonObject.toString(), Transformer.class);
        Mockito.when(transformerService.findTransformer(1)).thenReturn(transformer);

        mockMvc.perform(MockMvcRequestBuilders.get("/transformer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonObject.toString()));
    }

    @Test
    public void whenGetRequestToTransformerToGetAllTransformers_thenReturnAllTransformers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .getJson();
        jsonArray.put(jsonObject);
        JSONObject jsonObject1 = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .getJson();
        jsonArray.put(jsonObject1);
        List<Transformer> transformers = mapper.readValue(jsonArray.toString(), List.class);
        Mockito.when(transformerService.listAllTransformer()).thenReturn(transformers);

        mockMvc.perform(MockMvcRequestBuilders.get("/transformer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonArray.toString()));
    }

    @Test
    public void whenGetRequestToTransformerToGetByIdAndItNotExists_thenReturnOneTransformer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transformer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenGetRequestToTransformerToGetAllTransformersButIsEmpty_thenReturnOneTransformer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transformer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }


}
