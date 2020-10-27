package com.test.aequilibrium.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.aequilibrium.Model.Transformer;
import com.test.aequilibrium.Model.TransformerResult;
import com.test.aequilibrium.Persistence.TransformerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class TransformerServiceBattleTest {

    @MockBean
    private TransformerRepository transformerRepository;


    @InjectMocks
    TransformerService transformerService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void WhenTwoCompetitiorsOnlyAndDecepticonHas4MorePointsOfCourageAnd3MorePointsOfStrength_DeceptconShouldWinOneBattleOnly_NoSurvivorsInLosingTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("courage",8)
                .changeKeyValue("strength",8)
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("courage",3)
                .changeKeyValue("strength",4)
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("DECEPTICONS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }

    @Test
    public void WhenTwoCompetitiorsOnlyAndAutobotHas4MorePointsOfCourageAnd3MorePointsOfStrength_AutobotShouldWinOneBattleOnly_NoSurvivorsInLosingTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("courage",3)
                .changeKeyValue("strength",4)
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("courage",8)
                .changeKeyValue("strength",8)
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("AUTOBOTS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }


    @Test
    public void WhenTwoCompetitiorsOnlyAndDecepticonHas3MorePointsOfSkill_DeceptconShouldWinOneBattleOnly_NoSurvivorsInLosingTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("skill",9)
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("skill",5)
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("DECEPTICONS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }

    @Test
    public void WhenTwoCompetitiorsOnlyAndAutobotHas3MorePointsOfSkill_AutobotShouldWinOneBattleOnly_NoSurvivorsInLosingTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("skill",5)
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("skill",9)
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("AUTOBOTS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }


    @Test
    public void WhenTwoCompetitiorsOnlyAndTheAutobotOneIsOptimusPrime_AutobotShouldWinTheBattleOnly_NoSurvivorsInLosingTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("name","Optimus Prime")
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("AUTOBOTS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }

    @Test
    public void WhenTwoCompetitiorsOnlyAndTheDecepticonOneIsPredaking_DecepticonShouldWinTheBattleOnly_NoSurvivorsInLosingTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("name","Predaking")
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("DECEPTICONS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }

    @Test
    public void WhenTwoCompetitiorsOnlyAndTheDecepticonOneIsPredakingAndTheAutobotOneIsOptimusPrime_TheBattleEndsInTie_NoSurvivors() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("name","Predaking")
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("name","Optimus Prime")
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("TIE")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }

    @Test
    public void WhenfourCompetitiorsOnlyAndTheOneDecepticonIsPredakingAndOneAutobotIsOptimusPrime_TheBattleEndsInTie_NoSurvivors() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("name","Predaking")
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("type","A")
                .changeKeyValue("name","Optimus Prime")
                .build();
        String decpticonString2 = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",3)
                .changeKeyValue("type","D")
                .build();
        String autobotString2 = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",4)
                .changeKeyValue("type","A")
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);
        Transformer decepticon2 = mapper.readValue(decpticonString2, Transformer.class);
        Transformer autobot2 = mapper.readValue(autobotString2, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));
        Mockito.when(transformerRepository.findById(3)).thenReturn(Optional.of(decepticon2));
        Mockito.when(transformerRepository.findById(4)).thenReturn(Optional.of(autobot2));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2,3,4));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("TIE")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));

    }

    @Test
    public void WhenThreeCompetitiorsTwoAutobotsAndOneDecepticon_TheBattleEndsInDecepticonWinnsByOverallRating_NoSurvivors() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("rank","100")
                .changeKeyValue("speed","100")
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("name","Non Ranked Autobot")
                .changeKeyValue("type","A")
                .build();
        String autobotString2 = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",3)
                .changeKeyValue("name","Ranked Autobot")
                .changeKeyValue("rank","100")
                .changeKeyValue("type","A")
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);
        Transformer autobot2 = mapper.readValue(autobotString2, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));
        Mockito.when(transformerRepository.findById(3)).thenReturn(Optional.of(autobot2));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2,3));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("DECEPTICONS")),
                hasProperty("survivingLosingTeam",hasItems(equalTo("Ranked Autobot")))
        ));

    }

    @Test
    public void WhenThreeCompetitiorsTwoAutobotsAndOneDecepticon_TheBattleEndsInAutoBotWinnsByOverallRating_NoSurvivors() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String decpticonString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",1)
                .changeKeyValue("type","D")
                .changeKeyValue("rank","100")
                .build();
        String autobotString = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",2)
                .changeKeyValue("name","Non Ranked Autobot")
                .changeKeyValue("speed","100")
                .changeKeyValue("type","A")
                .build();
        String autobotString2 = new JsonBuilder()
                .createTransformerWithFakeValues()
                .changeKeyValue("id",3)
                .changeKeyValue("name","Ranked Autobot")
                .changeKeyValue("rank","100")
                .changeKeyValue("speed","80")
                .changeKeyValue("type","A")
                .build();
        Transformer decepticon = mapper.readValue(decpticonString, Transformer.class);
        Transformer autobot = mapper.readValue(autobotString, Transformer.class);
        Transformer autobot2 = mapper.readValue(autobotString2, Transformer.class);

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(decepticon));
        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.of(autobot));
        Mockito.when(transformerRepository.findById(3)).thenReturn(Optional.of(autobot2));

        TransformerResult transformerResult = transformerService.battle(Arrays.asList(1,2,3));
        assertThat(transformerResult, allOf(
                hasProperty("battles",equalTo(1)),
                hasProperty("winnerTeam",equalTo("AUTOBOTS")),
                hasProperty("survivingLosingTeam",emptyCollectionOf(String.class))
        ));
    }
}
