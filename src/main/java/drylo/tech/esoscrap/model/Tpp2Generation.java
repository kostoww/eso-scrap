package drylo.tech.esoscrap.model;

import drylo.tech.esoscrap.utils.Tpp2Identifier;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Entity(name = "tpp2_generation")
public class Tpp2Generation {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "data_time")
    private LocalDateTime time;

    private int unitOne;
    private int unitTwo;
    private int unitThree;
    private int unitFour;
    private int unitFive;
    private int unitSix;
    private int unitSeven;
    private int unitEight;

    private double desulphOneTwo;
    private double desulphThreeFour;
    private double desulphFiveSix;
    private double desulphSeven;
    private double desulphEight;

    public Tpp2Generation() {
    }

    public Tpp2Generation(LocalDateTime time, Map<Tpp2Identifier, Integer> powerGeneration, Map<Tpp2Identifier, Double> desulphValues) {
        this.time = time;
        this.unitOne = powerGeneration.get(Tpp2Identifier.UNIT_1_POWER);
        this.unitTwo = powerGeneration.get(Tpp2Identifier.UNIT_2_POWER);
        this.unitThree = powerGeneration.get(Tpp2Identifier.UNIT_3_POWER);
        this.unitFour = powerGeneration.get(Tpp2Identifier.UNIT_4_POWER);
        this.unitFive = powerGeneration.get(Tpp2Identifier.UNIT_5_POWER);
        this.unitSix = powerGeneration.get(Tpp2Identifier.UNIT_6_POWER);
        this.unitSeven = powerGeneration.get(Tpp2Identifier.UNIT_7_POWER);
        this.unitEight = powerGeneration.get(Tpp2Identifier.UNIT_8_POWER);
        this.desulphOneTwo = desulphValues.get(Tpp2Identifier.DESULPH_12);
        this.desulphThreeFour = desulphValues.get(Tpp2Identifier.DESULPH_34);
        this.desulphFiveSix = desulphValues.get(Tpp2Identifier.DESULPH_56);
        this.desulphSeven = desulphValues.get(Tpp2Identifier.DESULPH_7);
        this.desulphEight = desulphValues.get(Tpp2Identifier.DESULPH_8);
    }

    @Override
    public String toString() {
        return "Tpp2Generation{" +
                "id=" + id +
                ", time=" + time +
                ", unitOne='" + unitOne + '\'' +
                ", unitTwo='" + unitTwo + '\'' +
                ", unitThree='" + unitThree + '\'' +
                ", unitFour='" + unitFour + '\'' +
                ", unitFive='" + unitFive + '\'' +
                ", unitSix='" + unitSix + '\'' +
                ", unitSeven='" + unitSeven + '\'' +
                ", unitEight='" + unitEight + '\'' +
                ", desulphOneTwo='" + desulphOneTwo + '\'' +
                ", desulphThreeFour='" + desulphThreeFour + '\'' +
                ", desulphFiveSix='" + desulphFiveSix + '\'' +
                ", desulphSeven='" + desulphSeven + '\'' +
                ", desulphEight='" + desulphEight + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tpp2Generation that = (Tpp2Generation) o;
        return id == that.id &&
                unitOne == that.unitOne &&
                unitTwo == that.unitTwo &&
                unitThree == that.unitThree &&
                unitFour == that.unitFour &&
                unitFive == that.unitFive &&
                unitSix == that.unitSix &&
                unitSeven == that.unitSeven &&
                unitEight == that.unitEight &&
                Double.compare(that.desulphOneTwo, desulphOneTwo) == 0 &&
                Double.compare(that.desulphThreeFour, desulphThreeFour) == 0 &&
                Double.compare(that.desulphFiveSix, desulphFiveSix) == 0 &&
                Double.compare(that.desulphSeven, desulphSeven) == 0 &&
                Double.compare(that.desulphEight, desulphEight) == 0 &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, unitOne, unitTwo, unitThree, unitFour, unitFive, unitSix, unitSeven, unitEight, desulphOneTwo, desulphThreeFour, desulphFiveSix, desulphSeven, desulphEight);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getUnitOne() {
        return unitOne;
    }

    public void setUnitOne(int unitOne) {
        this.unitOne = unitOne;
    }

    public int getUnitTwo() {
        return unitTwo;
    }

    public void setUnitTwo(int unitTwo) {
        this.unitTwo = unitTwo;
    }

    public int getUnitThree() {
        return unitThree;
    }

    public void setUnitThree(int unitThree) {
        this.unitThree = unitThree;
    }

    public int getUnitFour() {
        return unitFour;
    }

    public void setUnitFour(int unitFour) {
        this.unitFour = unitFour;
    }

    public int getUnitFive() {
        return unitFive;
    }

    public void setUnitFive(int unitFive) {
        this.unitFive = unitFive;
    }

    public int getUnitSix() {
        return unitSix;
    }

    public void setUnitSix(int unitSix) {
        this.unitSix = unitSix;
    }

    public int getUnitSeven() {
        return unitSeven;
    }

    public void setUnitSeven(int unitSeven) {
        this.unitSeven = unitSeven;
    }

    public int getUnitEight() {
        return unitEight;
    }

    public void setUnitEight(int unitEight) {
        this.unitEight = unitEight;
    }

    public double getDesulphOneTwo() {
        return desulphOneTwo;
    }

    public void setDesulphOneTwo(double desulphOneTwo) {
        this.desulphOneTwo = desulphOneTwo;
    }

    public double getDesulphThreeFour() {
        return desulphThreeFour;
    }

    public void setDesulphThreeFour(double desulphThreeFour) {
        this.desulphThreeFour = desulphThreeFour;
    }

    public double getDesulphFiveSix() {
        return desulphFiveSix;
    }

    public void setDesulphFiveSix(double desulphFiveSix) {
        this.desulphFiveSix = desulphFiveSix;
    }

    public double getDesulphSeven() {
        return desulphSeven;
    }

    public void setDesulphSeven(double desulphSeven) {
        this.desulphSeven = desulphSeven;
    }

    public double getDesulphEight() {
        return desulphEight;
    }

    public void setDesulphEight(double desulphEight) {
        this.desulphEight = desulphEight;
    }
}
