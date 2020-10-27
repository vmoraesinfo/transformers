package com.test.aequilibrium.Model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TransformerResult {

    @Getter
    @Setter
    private int battles =0;
    @Getter
    @Setter
    private String winnerTeam;
    @Getter
    @Setter
    private List<String> survivingLosingTeam;


    public void addBatle(){
        battles++;
    }

}
