package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Model.Transformer;
import com.test.aequilibrium.Persistence.TransformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
