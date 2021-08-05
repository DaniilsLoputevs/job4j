package models.lazyinit;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LazyInitializationException - 2 ways how to fix this problem.
 * Reason: get access to inner object out of linked session with this inner object.
 * Way 1: use(get access) inner object before you close the session.
 * Way 2: use "join fetch" in HQL query. Example:
 * HQL = "select distinct b from Brand b join fetch b.carLazies"
 */
@Entity
@Table(name = "LIE_brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "brand"
//            , cascade = CascadeType.ALL, orphanRemoval = true
    )
    private List<CarLazy> carLazies = new ArrayList<>();

    public Brand() {
    }

    public Brand(int id, String name, List<CarLazy> carLazies) {
        this.id = id;
        this.name = name;
        this.carLazies = carLazies;
    }

    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.name = name;
        return brand;
    }

    public void addCar(CarLazy carLazy) {
        this.carLazies.add(carLazy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id)
                && Objects.equals(name, brand.name);
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

    public List<CarLazy> getCarLazies() {
        return carLazies;
    }

    public void setCarLazies(List<CarLazy> carLazies) {
        this.carLazies = carLazies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Brand{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", carLazies=" + carLazies
                + '}';
    }
}