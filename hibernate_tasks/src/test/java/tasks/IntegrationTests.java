package tasks;

import models.integrationtests.Car;
import models.integrationtests.Driver;
import models.integrationtests.Engine;
import models.integrationtests.HistoryOwner;
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

public class IntegrationTests {
    private final SessionFactory sf = initSf();
    private Session currentSession;
    private HistoryOwner initHistory;

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
        Engine engine = new Engine(0, "engineName");
        Car car = new Car(0, "carName", engine);
        Driver driver = new Driver(0, "driverName");

        HistoryOwner historyOwner = new HistoryOwner(0, driver, car);

        this.currentSession.save(historyOwner);
        this.initHistory = historyOwner;
    }


    @After
    public void closeAnyTest() {
        this.currentSession.getTransaction().rollback();
        this.currentSession.close();
    }

    private List<HistoryOwner> getAll() {
        var hql = "select distinct mt "
                + "from HistoryOwner as mt "
                + "join fetch mt.driver "
                + "join fetch mt.car";
        return (List<HistoryOwner>) this.currentSession.createQuery(hql).list();
    }

    /* ======== TESTS ======== */

    @Test
    public void addTest() {
        assertEquals(this.initHistory.getCar().getName(),
                getAll().get(0).getCar().getName()
        );
    }

    @Test
    public void updTest() {
        var initDriver = this.initHistory.getDriver();

        Driver newDriver = new Driver(0, "newDriverName");
        this.initHistory.setDriver(newDriver);
        this.currentSession.update(this.initHistory);

        assertEquals(newDriver.getName(),
                getAll().get(0).getDriver().getName()
        );
        this.initHistory.setDriver(initDriver);
    }

    @Test
    public void delTest() {
        this.currentSession.delete(this.initHistory);

        assertEquals(0,
                getAll().size()
        );
    }

}
