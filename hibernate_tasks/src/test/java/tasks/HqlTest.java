package tasks;

import hbm.v2.HbmProvider;
import models.hql.Candidate;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HqlTest {
    private final CandidateStore store = new CandidateStore();


    @Before
    public void init() {
        store.save(new Candidate(0, "Alex", 5, "80k/sec"));
        store.save(new Candidate(0, "Anton", 1, "5k/sec"));
        store.save(new Candidate(0, "Hincu", 7, "100k/sec"));
    }

    @After
    public void truncate() {
        store.truncate();
    }

//    @Test
    @Ignore
    public void truncateManual() {
        store.truncate();
    }

    @Test
    public void addAndGetAll() {
        assertEquals(3, store.getAll().size());
    }

    @Test
    public void getById() {
        assertEquals("Alex", store.getById(1).getName());
    }

    @Test
    public void getByName() {
        assertEquals("Hincu", store.getByName("Hincu").getName());
    }

    @Test
    public void delete() {
        System.out.println("init size: " + store.getAll().size());
        store.delete( store.getById(2));
        assertEquals(2, store.getAll().size());
    }

    @Test
    public void update() {
        var updId = store.getByName("Hincu").getId();
        var hincuPro = new Candidate(updId, "HincuPro", 777, "777k/sec");
        store.update(hincuPro);
        assertEquals("777k/sec", store.getById(3).getSalary());
    }

    @Test
    public void updateParams() {
        var temp = store.getById(1);
        temp.setName("AlexNEW");
        temp.setSalary("82k/sec");
        store.update(temp);
        assertEquals("82k/sec", store.getById(1).getSalary());
    }


    /**
     * If you want update some parameters,
     * use getBy(id) then use setters and use normal update.
     */
    static class CandidateStore {
        private final HbmProvider<Candidate> provide = new HbmProvider<>("Candidate");

        public void save(Candidate candidate) {
            provide.save(candidate);
        }

        public List<Candidate> getAll() {
            return provide.getAll();
        }

        public Candidate getById(int id) {
            return provide.getBy("id", id);
        }

        public Candidate getByName(String name) {
            return provide.getBy("name", name);
        }

        public void delete(Candidate candidate) {
            provide.delete(candidate);
        }
        public void delete(int id) {
            provide.delete(id);
        }

        public void update(Candidate candidate) {
            provide.update(candidate);
        }

        void truncate() {
            provide.sessionWork(session -> session.createSQLQuery(
                    "truncate table candidates restart identity").executeUpdate());
        }

    }
}
