package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Model.Transformer;
import com.test.aequilibrium.Model.TransformerResult;
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


    public TransformerResult battle(List<Integer> ids) throws Exception {
        Map<String, List<Transformer>> mapTransformers =  splitInTeams(ids);
        BattleEngine battleEngine = new BattleEngine();
        return battleEngine.battle(mapTransformers);
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
            list = list.stream().sorted((transformer1, tarTransformer2) -> tarTransformer2.getRank().compareTo(transformer1.getRank()) )
                    .collect(Collectors.toList());
            mapTransformers.put(transformer.getType(), list);
        }

        return mapTransformers;
    }

}
