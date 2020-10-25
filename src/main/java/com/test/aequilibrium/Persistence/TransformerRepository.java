package com.test.aequilibrium.Persistence;

import com.test.aequilibrium.Model.Transformer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerRepository extends CrudRepository<Transformer, Integer> {
}
