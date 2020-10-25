package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Model.Autobot;
import com.test.aequilibrium.Model.Decepticon;
import com.test.aequilibrium.Model.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transformer")
public class TransformerController {

    private TransformerService transformerService;

    @Autowired
    public TransformerController(TransformerService transformerService) {
        this.transformerService = transformerService;
    }

    @PostMapping
    @ResponseBody
    public Transformer create(@Valid @RequestBody Transformer transformer) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        transformer = getSubTypeTransformer(transformer);
        transformerService.saveTransformer(transformer);
        return transformer;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Transformer update(@RequestBody Transformer transformer, @NotNull @PathVariable("id") int id) throws Exception {


        Transformer transformerFromDB = transformerService.findTransformer(id);
        transformerFromDB = getSubTypeTransformer(transformerFromDB);
        transformer.setId(id);
        transformerFromDB.merge(transformer);
        transformerService.saveTransformer(transformer);
        return transformer;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@NotNull @PathVariable("id") int id) throws Exception {
        transformerService.findTransformer(id);
        transformerService.deleteTransformer(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity listAll() throws Exception {

        return ResponseEntity.ok(transformerService.listAllTransformer());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Transformer findById(@NotNull @PathVariable("id") int id) throws Exception {

        return transformerService.findTransformer(id);
    }

    @PostMapping("/battle")
    @ResponseBody
    public ResponseEntity battle(@NotNull @Valid @RequestBody List<Integer> ids) throws Exception {



        return ResponseEntity.ok().build();
    }

    private Transformer getSubTypeTransformer(Transformer transformer){
        switch (transformer.getType()){
            case "D" : transformer = new Decepticon(transformer); break;
            case "A" : transformer = new Autobot(transformer);break;
            default: transformer= null;break;
        };
        return transformer;

    }

}
