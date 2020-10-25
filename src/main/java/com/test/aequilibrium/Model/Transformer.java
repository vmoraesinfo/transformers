package com.test.aequilibrium.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

@Entity
@Table(name = "TRANSFORMERS")
public class Transformer {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    private int id;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer strength;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer intelligence;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer speed;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer endurance;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer rank;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer courage;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer firepower;

    @Setter
    @Getter
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotNull(message = "cannot be null")
    private Integer skill;

    public int getOverallRating(){
        return strength + intelligence + speed + endurance + firepower;
    }

    public Transformer merge(Transformer transformer){
        if(transformer == null){
            transformer = this;
        }else {
            if (transformer.getStrength() == null || transformer.getStrength() <= 0) transformer.setStrength(this.getStrength());
            if (transformer.getIntelligence() == null || transformer.getIntelligence() <= 0) transformer.setIntelligence(this.getIntelligence());
            if (transformer.getSpeed() == null || transformer.getSpeed() <= 0) transformer.setSpeed(this.getSpeed());
            if (transformer.getEndurance() == null || transformer.getEndurance() <= 0) transformer.setEndurance(this.getEndurance());
            if (transformer.getRank() == null || transformer.getRank() <= 0) transformer.setRank(this.getRank());
            if (transformer.getCourage() == null || transformer.getCourage() <= 0) transformer.setCourage(this.getCourage());
            if (transformer.getFirepower() == null || transformer.getFirepower() <= 0) transformer.setFirepower(this.getFirepower());
            if (transformer.getSkill() == null || transformer.getSkill() <= 0) transformer.setSkill(this.getSkill());
        }

        return transformer;
    }
}
