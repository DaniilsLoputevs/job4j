package tasks.manytomany;

import models.integrationtests.manytomany.Student;
import models.integrationtests.manytomany.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 21.11.2020 05:11
 * Oт Петр Арсентьев к Daniil Loputevs
 * "Нужно показать аннотацию @ManyToMany. Любой пример."
 *
 * http://java-online.ru/hibernate-entities.xhtml
 */
public class Example {
    private final SessionFactory sf = initSf();
    private Session currentSession;
//    private HistoryOwner initHistory;

    private static SessionFactory initSf() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    /* ======== PREPARE ======== */

    @Before
    public void prepareAnyTest() {
        // create rollback session
        this.currentSession = this.sf.getCurrentSession();
        this.currentSession.getTransaction().setRollbackOnly();
        this.currentSession.beginTransaction();

        // init Test Data
        University uOne = new University(0, "uOne", null);
        University uTwo = new University(1, "uTwo", null);
        List<University> universities = List.of(uOne, uTwo);

        Student sOne = new Student(0, "sOne", null);
        Student sTwo = new Student(1, "sTwo", null);
        List<Student> students = List.of(sOne, sTwo);

        uOne.setStudents(students);
        uTwo.setStudents(students);

        sOne.setStudents(universities);
        sTwo.setStudents(universities);

        this.currentSession.save(uOne);
        this.currentSession.save(uTwo);

        this.currentSession.save(sOne);
        this.currentSession.save(sTwo);
    }


    @After
    public void closeAnyTest() {
        this.currentSession.getTransaction().rollback();
        this.currentSession.close();
    }

    private List<University> getAllUniversities() {
        var hql = "select distinct mt "
                + "from models.integrationtests.manytomany.University as mt "
                + "join fetch mt.students ";
        return this.currentSession.createQuery(hql, University.class).list();
    }

    private List<Student> getAllStudents() {
        var hql = "select distinct mt "
                + "from models.integrationtests.manytomany.Student as mt "
                + "join fetch mt.universities";
        return this.currentSession.createQuery(hql, Student.class).list();
    }

    /* ======== TESTS ======== */

    @Test
    public void getAllTests() {
        assertEquals(2, getAllStudents().get(0).getUniversities().size());
        assertEquals(2, getAllUniversities().get(0).getStudents().size());
    }
}
