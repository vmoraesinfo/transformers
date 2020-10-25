package com.test.aequilibrium.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DECEPTICONS")
public class Decepticon extends Transformer {

    public Decepticon(){

    }

    public Decepticon(Transformer transformer) {
        super(transformer);
    }
}
