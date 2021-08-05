package models.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /* Init code */

//            Student one = Student.of("Alex", 21, "Moscow");
//            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
//            Student three = Student.of("Nikita", 25, "Kaliningrad");
//
//            session.save(one);
//            session.save(two);
//            session.save(three);

            /* SELECT */
//            Query query = session.createQuery("from Student");
//            Query query = session.createQuery("from models.hql.Student");
//            for (Object st : query.list()) {
//                System.out.println(st);
//            }

            // unique result
//            Query query = session.createQuery("from Student s where s.id = 1");
//            System.out.println(query.uniqueResult());

            // use parameters for query
//            Query query = session.createQuery("from Student s where s.id = :fId");
//            query.setParameter("fId", 1);
//            System.out.println(query.uniqueResult());

            /* UPDATE */
//            Query query = session.createQuery(
//                    "update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId"
//            );
//            query.setParameter("newAge", 22);
//            query.setParameter("newCity", "London");
//            query.setParameter("fId", 1);
//            query.executeUpdate();

            // short way to write UPD
//            session.createQuery("update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId")
//                    .setParameter("newAge", 22)
//                    .setParameter("newCity", "London")
//                    .setParameter("fId", 1)
//                    .executeUpdate();

            /* DELETE */
//            session.createQuery("delete from Student where id = :fId")
//                    .setParameter("fId", 3)
//                    .executeUpdate();

            /* INSERT */
            // create new record with info from other record
//            session.createQuery("insert into Student (name, age, city) "
//                    + "select concat(s.name, 'NEW'), s.age + 5, s.city  "
//                    + "from Student s where s.id = :fId")
//                    .setParameter("fId", 1)
//                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}