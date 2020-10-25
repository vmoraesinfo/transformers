package com.test.aequilibrium.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOBOTS")
public class Autobot extends Transformer{

    public Autobot(Transformer transformer) {
        super(transformer);
    }

    public Autobot() {

    }
}
