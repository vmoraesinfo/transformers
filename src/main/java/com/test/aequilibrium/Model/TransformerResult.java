package com.test.aequilibrium.Model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
                Your API should take as input a list of Transformer IDs and based on input returns:
                1. The number of battles
                2. The winning team
                3. The surviving members of the losing team
                 */
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
