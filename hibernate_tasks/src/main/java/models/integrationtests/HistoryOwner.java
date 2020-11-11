package models.integrationtests;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "history_owner")
public class HistoryOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public HistoryOwner() {
    }

    public HistoryOwner(int id, Driver driver, Car car) {
        this.id = id;
        this.driver = driver;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        HistoryOwner that = (HistoryOwner) object;
        return id == that.id
                && Objects.equals(driver, that.driver)
                && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, car);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HistoryOwner.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("driver=" + driver)
                .add("car=" + car)
                .toString();
    }
}
