package services;

import models.lazyinit.Brand;
import models.lazyinit.CarLazy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmManual {
    public static void main(String[] args) {
//        Author anatolij = Author.of("Anatolij");
//        Author vera = Author.of("Vera");

//        Book bookOne = Book.of("Music Magic");
//        Book bookTwo = Book.of("The Witcher 3");


//        List.of(bookOne, bookTwo).forEach(anatolij::addBook);
//        List.of(bookOne).forEach(vera::addBook);

//        HbmProvider.instOf().standardTransactionCore(session -> {
//            session.persist(anatolij);
//            session.persist(vera);
//            return null;
//        });


//        var brand = Brand.of("Mark");
//        var one = CarLazy.of("one");
//        var two = CarLazy.of("two");
//        var three = CarLazy.of("three");
//
//        brand.setCarLazies(List.of(one, two, three));
//        one.setBrand(brand);
//        two.setBrand(brand);
//        three.setBrand(brand);
//
//        HbmProvider.instOf().standardTransactionCore(session -> {
//            session.save(brand);
//            session.save(one);
//            session.save(two);
//            session.save(three);
//            return true;
//        });


        List<Brand> list = new ArrayList<>();
        List<CarLazy> carLazies = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

//            list = session.createQuery("from Brand").list();
//            for (Brand tempBrand : list) {
//                for (CarLazy car : tempBrand.getCarLazies()) {
//                    carLazies.add(car);
//                    System.out.println(car);
//                }
//            }
            list = session.createQuery(
                    "select distinct b from Brand b join fetch b.cars"
            ).list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Brand tempBrand : list) {
            for (CarLazy carLazy : tempBrand.getCarLazies()) {
                System.out.println(carLazy);
            }
        }

//        CustomLog.log("list", list);
//        CustomLog.log("carLazies", carLazies);
//        carLazies.forEach(System.out::println);
    }

}
