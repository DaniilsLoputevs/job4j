package models.lazyinit;

import javax.persistence.*;
import java.util.Objects;

/**
 * LazyInitializationException - 2 ways how to fix this problem.
 * Reason: get access to inner object out of linked session with this inner object.
 * Way 1: use(get access) inner object before you close the session.
 * Way 2: use "join fetch" in HQL query. Example:
 * HQL = "select distinct b from Brand b join fetch b.cars"
 */
@Entity(name = "CarLazy")
@Table(name = "LIE_cars")
public class CarLazy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public CarLazy() {
    }

    public CarLazy(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CarLazy of(String name) {
        CarLazy carLazy = new CarLazy();
        carLazy.name = name;
        return carLazy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarLazy carLazy = (CarLazy) o;
        return Objects.equals(id, carLazy.id)
                && Objects.equals(name, carLazy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarLazy{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + '}';
    }
}