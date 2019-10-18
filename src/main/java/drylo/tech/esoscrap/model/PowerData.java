package drylo.tech.esoscrap.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PowerData {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "npp")
    private Integer nuclearPowerPlant;

    @Column(name = "tpp")
    private Integer thermalPowerPlant;

    @Column(name = "dhp")
    private Integer districtHeatingPlant ;

    @Column(name = "fpp")
    private Integer fabricPowerPlant;

    @Column(name = "wpp")
    private Integer waterPowerPlant;

    @Column(name = "small_wpp")
    private Integer smallWaterPowerPlant;

    @Column(name = "wind_pp")
    private Integer windPowerPlant;

    @Column(name = "photo_pp")
    private Integer photovoltaicPowerPlant;

    @Column(name = "bio_pp")
    private Integer bioPowerPlant;

    @Column(name = "export")
    private Integer exportPower;

    public PowerData() {
    }

    public PowerData(LocalDateTime time, Integer export, Integer... data) {
        this.time = time;
        this.nuclearPowerPlant = data[0];
        this.thermalPowerPlant = data[1];
        this.districtHeatingPlant = data[2];
        this.fabricPowerPlant = data[3];
        this.waterPowerPlant = data[4];
        this.smallWaterPowerPlant = data[5];
        this.windPowerPlant = data[6];
        this.photovoltaicPowerPlant = data[7];
        this.bioPowerPlant = data[8];
        this.exportPower = export;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerData that = (PowerData) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(nuclearPowerPlant, that.nuclearPowerPlant) &&
                Objects.equals(thermalPowerPlant, that.thermalPowerPlant) &&
                Objects.equals(districtHeatingPlant, that.districtHeatingPlant) &&
                Objects.equals(fabricPowerPlant, that.fabricPowerPlant) &&
                Objects.equals(waterPowerPlant, that.waterPowerPlant) &&
                Objects.equals(smallWaterPowerPlant, that.smallWaterPowerPlant) &&
                Objects.equals(windPowerPlant, that.windPowerPlant) &&
                Objects.equals(photovoltaicPowerPlant, that.photovoltaicPowerPlant) &&
                Objects.equals(bioPowerPlant, that.bioPowerPlant) &&
                Objects.equals(exportPower, that.exportPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, nuclearPowerPlant, thermalPowerPlant, districtHeatingPlant, fabricPowerPlant, waterPowerPlant, smallWaterPowerPlant, windPowerPlant, photovoltaicPowerPlant, bioPowerPlant, exportPower);
    }

    @Override
    public String toString() {
        String NL = "\n";
        return "PowerGeneration{" +
                "time=" + time + NL +
                ", nuclearPowerPlant=" + nuclearPowerPlant + NL +
                ", thermalPowerPlant=" + thermalPowerPlant + NL +
                ", districtHeatingPlant=" + districtHeatingPlant + NL +
                ", fabricPowerPlant=" + fabricPowerPlant + NL +
                ", waterPowerPlant=" + waterPowerPlant + NL +
                ", smallWaterPowerPlant=" + smallWaterPowerPlant + NL +
                ", windPowerPlant=" + windPowerPlant + NL +
                ", photovoltaicPowerPlant=" + photovoltaicPowerPlant + NL +
                ", bioPowerPlant=" + bioPowerPlant + NL +
                ", exportPower=" + exportPower + NL +
                '}';
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getNuclearPowerPlant() {
        return nuclearPowerPlant;
    }

    public void setNuclearPowerPlant(Integer nuclearPowerPlant) {
        this.nuclearPowerPlant = nuclearPowerPlant;
    }

    public Integer getThermalPowerPlant() {
        return thermalPowerPlant;
    }

    public void setThermalPowerPlant(Integer thermalPowerPlant) {
        this.thermalPowerPlant = thermalPowerPlant;
    }

    public Integer getDistrictHeatingPlant() {
        return districtHeatingPlant;
    }

    public void setDistrictHeatingPlant(Integer districtHeatingPlant) {
        this.districtHeatingPlant = districtHeatingPlant;
    }

    public Integer getFabricPowerPlant() {
        return fabricPowerPlant;
    }

    public void setFabricPowerPlant(Integer fabricPowerPlant) {
        this.fabricPowerPlant = fabricPowerPlant;
    }

    public Integer getWaterPowerPlant() {
        return waterPowerPlant;
    }

    public void setWaterPowerPlant(Integer waterPowerPlant) {
        this.waterPowerPlant = waterPowerPlant;
    }

    public Integer getSmallWaterPowerPlant() {
        return smallWaterPowerPlant;
    }

    public void setSmallWaterPowerPlant(Integer smallWaterPowerPlant) {
        this.smallWaterPowerPlant = smallWaterPowerPlant;
    }

    public Integer getWindPowerPlant() {
        return windPowerPlant;
    }

    public void setWindPowerPlant(Integer windPowerPlant) {
        this.windPowerPlant = windPowerPlant;
    }

    public Integer getPhotovoltaicPowerPlant() {
        return photovoltaicPowerPlant;
    }

    public void setPhotovoltaicPowerPlant(Integer photovoltaicPowerPlant) {
        this.photovoltaicPowerPlant = photovoltaicPowerPlant;
    }

    public Integer getBioPowerPlant() {
        return bioPowerPlant;
    }

    public void setBioPowerPlant(Integer bioPowerPlant) {
        this.bioPowerPlant = bioPowerPlant;
    }

    public Integer getExportPower() {
        return exportPower;
    }

    public void setExportPower(Integer exportPower) {
        this.exportPower = exportPower;
    }
}
