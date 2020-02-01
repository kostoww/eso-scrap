package drylo.tech.esoscrap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NuclearGeneration {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "data_time")
    private LocalDateTime time;

    @Column(name = "five")
    private int five;

    @Column(name = "six")
    private int six;

    public NuclearGeneration() {
        time = LocalDateTime.now();
    }

    public NuclearGeneration(LocalDateTime time, int five, int six) {
        this.time = time;
        this.five = five;
        this.six = six;
    }

    @Override
    public String toString() {
        return "NuclearGeneration{" +
                "id=" + id +
                ", time=" + time +
                ", five=" + five +
                ", six=" + six +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NuclearGeneration that = (NuclearGeneration) o;
        return id == that.id &&
                Objects.equals(time, that.time) &&
                five == that.five  &&
                six == that.six;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, five, six);
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

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }
}
