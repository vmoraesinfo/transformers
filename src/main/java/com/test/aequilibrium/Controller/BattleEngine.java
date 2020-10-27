package com.test.aequilibrium.Controller;

import com.test.aequilibrium.Helper.WinnersEnum;
import com.test.aequilibrium.Model.Transformer;
import com.test.aequilibrium.Model.TransformerResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BattleEngine {
    public static final int MAX_COURAGE = 4;
    public static final int MAX_STRENGTH = 3;
    public static final int MAX_SKILL = 3;
    private TransformerResult transformerResult;
    private List<Transformer> winnersAutobot;
    private List<Transformer> winnersDecepticon;

    public TransformerResult battle(Map<String, List<Transformer>> mapTransformers) {
        List<Transformer> playerAutobot = mapTransformers.get("A");
        List<Transformer> playerDecepticon = mapTransformers.get("D");
        winnersAutobot = new ArrayList<>();
        winnersDecepticon = new ArrayList<>();
        transformerResult = new TransformerResult();
        Transformer oponentAutobot;
        Transformer oponentDecepticon;
        for(int i =0; i< playerAutobot.size(); i++){
            if(playerDecepticon.size() == i) break;
            oponentAutobot = playerAutobot.remove(i);
            oponentDecepticon = playerDecepticon.remove(i);
            transformerResult.addBatle();
            if(hasWinnerInSuperOponent(oponentAutobot, oponentDecepticon)
               ||hasWinnerInDiffCourageAndStrength(oponentAutobot, oponentDecepticon)
               ||hasWinnerInDiffSkill(oponentAutobot, oponentDecepticon)){
                    continue;
            }else{
                hasWinnerInOverallRating(oponentAutobot, oponentDecepticon);
            }

        }

        battleResult(playerAutobot, playerDecepticon);

        return  transformerResult;
    }

    private void battleResult(List<Transformer> playerAutobot,List<Transformer> playerDecepticon) {
        if(winnersAutobot.size() > winnersDecepticon.size()){
            transformerResult.setWinnerTeam("AUTOBOTS");
            transformerResult.setSurvivingLosingTeam(playerDecepticon.parallelStream().map(Transformer::getName).collect(Collectors.toList()));
        }else  if(winnersAutobot.size() < winnersDecepticon.size()){
            transformerResult.setWinnerTeam("DECEPTICONS");
            transformerResult.setSurvivingLosingTeam(playerAutobot.parallelStream().map(Transformer::getName).collect(Collectors.toList()));
        }else{
            transformerResult.setWinnerTeam("TIE");
            transformerResult.setSurvivingLosingTeam(new ArrayList<>());
        }
    }

    private void hasWinnerInOverallRating(Transformer oponentAutobot, Transformer oponentDecepticon) {
        int diffOverallRating = oponentAutobot.diffOverallRating(oponentDecepticon);
        if(diffOverallRating < 0 ){
            winnersDecepticon.add(oponentDecepticon);
        }else{
            winnersAutobot.add(oponentAutobot);
        }
    }

    private boolean hasWinnerInDiffSkill(Transformer oponentAutobot, Transformer oponentDecepticon) {
        int diffSkill = oponentAutobot.diffSkill(oponentDecepticon);
        if(diffSkill <= -MAX_SKILL){
            winnersDecepticon.add(oponentDecepticon);
            return  true;
        }else if(diffSkill >= MAX_SKILL){
            winnersAutobot.add(oponentAutobot);
            return true;
        }
        return false;
    }

    private boolean hasWinnerInDiffCourageAndStrength(Transformer oponentAutobot, Transformer oponentDecepticon) {
        int diffCourage = oponentAutobot.diffCourage(oponentDecepticon);
        int diffStrength = oponentAutobot.diffStrength(oponentDecepticon);
        if(diffCourage <= -MAX_COURAGE && diffStrength <= -MAX_STRENGTH){
            winnersDecepticon.add(oponentDecepticon);
            return true;
        }else if(diffCourage >= MAX_COURAGE && diffStrength >= MAX_STRENGTH){
            winnersAutobot.add(oponentAutobot);
            return true;
        }
        return false;
    }

    private boolean hasWinnerInSuperOponent(Transformer oponentAutobot, Transformer oponentDecepticon) {
        boolean oponentAutobotWinner = WinnersEnum.isWinner(oponentAutobot.getName());
        boolean oponentDecepticonWinner = WinnersEnum.isWinner(oponentDecepticon.getName());
        if(!oponentAutobotWinner && oponentDecepticonWinner){
            winnersDecepticon.add(oponentDecepticon);
            return true;
        }else if(oponentAutobotWinner && !oponentDecepticonWinner){
            winnersAutobot.add(oponentAutobot);
            return true;
        }else if(oponentAutobotWinner && oponentDecepticonWinner){
            transformerResult.setWinnerTeam("TIE");
            transformerResult.setSurvivingLosingTeam(new ArrayList<>());
            return true;
        }
        return false;
    }
}
