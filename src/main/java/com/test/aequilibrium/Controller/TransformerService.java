package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Model.Transformer;
import com.test.aequilibrium.Persistence.TransformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransformerService {
    private TransformerRepository transformerRepository;

    @Autowired
    public TransformerService(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    public Transformer saveTransformer(Transformer transformer) {
        return transformerRepository.save(transformer);
    }

    public Transformer findTransformer(int id) throws Exception {
        return transformerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transformer not found with id: " + id));
    }

    public void deleteTransformer(int id) throws Exception {
        transformerRepository.deleteById(id);
    }

    public Iterable<Transformer> listAllTransformer() throws Exception {
        return transformerRepository.findAll();
    }


    public void battle(List<Integer> ids) throws Exception {
        Map<String, List<Transformer>> mapTransformers =  splitInTeams(ids);
        List<Transformer> playerAutobot = mapTransformers.get("A");
        List<Transformer> playerDecepticon = mapTransformers.get("D");
        Map<String, List<Transformer>> winners = new HashMap<>();
        List<Transformer> winnersAutobot = new ArrayList<>();
        List<Transformer> winnersDecepticon = new ArrayList<>();
        for(int i =0; i< playerAutobot.size(); i++){
            Transformer oponentAutobot = playerAutobot.get(i);
            Transformer oponentDecepticon = playerDecepticon.get(i);
            int diffCourage = oponentAutobot.diffCourage(oponentDecepticon);
            int diffStrength = oponentAutobot.diffStrength(oponentDecepticon);
            if(diffCourage <= -4 && diffStrength <= -3){
                winnersDecepticon.add(oponentDecepticon);
            }else if(diffCourage <= 4 && diffStrength <= 3){
                winnersAutobot.add(oponentAutobot);
            }
            int diffSkill = oponentAutobot.diffSkill(oponentDecepticon);
            if(diffSkill <= -3){
                winnersDecepticon.add(oponentDecepticon);
            }else{
                winnersAutobot.add(oponentAutobot);
            }
            int diffOverallRating = oponentAutobot.diffOverallRating(oponentDecepticon);
            if(diffOverallRating < 0 ){
                winnersDecepticon.add(oponentDecepticon);
            }else{
                winnersAutobot.add(oponentAutobot);
            }

        }

    }

    private Map<String, List<Transformer>> splitInTeams(List<Integer> ids) throws Exception {
        Map<String, List<Transformer>> mapTransformers = new HashMap<>();
        Transformer transformer;
        for(int id: ids){
            transformer = findTransformer(id);
            List<Transformer> list = mapTransformers.get(transformer.getType());
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(transformer);
            list = list.stream().sorted(Comparator.comparingInt(Transformer::getRank))
                    .collect(Collectors.toList());
            mapTransformers.put(transformer.getType(), list);
        }

        return mapTransformers;
    }

}
