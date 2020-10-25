package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Persistence.TransformerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
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

    @Test
    public void whenPostRequestToTransformerAndValidTransformer_thenCorrectResponse() throws Exception{
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

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingStrength_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("strength")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"strength cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingStrengthAndIntelligence_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("strength")
                .removeAnElementFromJson("intelligence")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"intelligence cannot be null\",\"strength cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingIntelligence_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("intelligence")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"intelligence cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingSpeed_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("speed")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"speed cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingEndurance_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("endurance")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"endurance cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingRank_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("rank")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"rank cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingCourage_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("courage")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"courage cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingFirePower_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("firepower")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"firepower cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNoValidTransformerMissingSkill_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .removeAnElementFromJson("skill")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"skill cannot be null\"]}"));
    }

    @Test
    public void whenPostRequestToTransformerAndNotValidTransformerSkillLowerThan1_thenExpectBadRequest() throws Exception{
        String transformer = new JsonBuilder()
                .createTransformerWithFakeValues()
                .addOrChangeKeyValue("skill",-1)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/transformer")
                .content(transformer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"BAD_REQUEST\",\"messages\":[\"skill must be equal or greater than 1\"]}"));
    }
}
