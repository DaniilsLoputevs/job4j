package ru.job4j.design.tdd.cinema;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class CinemaTest {
    private Ticket ticketStub;
    private Session sessionStub;

    @Test
    public void buyTest() {
        Account account = new AccountStub();
        Cinema cinema = new CinemaStub();
        Calendar date = Calendar.getInstance();

        date.set(2020, 10, 10, 23, 00);
        Ticket ticketNew = cinema.buy(account, 1, 1, date);

        assertEquals(ticketStub, ticketNew);
    }

    @Test
    public void findTest() {
        Cinema cinema = new CinemaStub();
        cinema.add(new SessionStub());
        List<Session> sessions = cinema.find(session -> true);

        assertEquals(Arrays.asList(sessionStub), sessions);
    }

    @Test
    public void addTest() {
        Cinema cinema = new CinemaStub();
        cinema.add(new SessionStub());
        assertEquals(1, cinema.sessionCount());
    }

    private class AccountStub implements Account {

    }

    private class TicketStub implements Ticket {

    }

    private class SessionStub implements Session {

    }

    private class CinemaStub implements Cinema {
        @Override
        public List<Session> find(Predicate<Session> filter) {
            sessionStub = new SessionStub();
            return List.of(sessionStub);
        }

        @Override
        public Ticket buy(Account account, int row, int column, Calendar date) {
            ticketStub = new TicketStub();
            return ticketStub;
        }

        @Override
        public void add(Session session) {

        }

        @Override
        public int sessionCount() {
            return 1;
        }
    }


}