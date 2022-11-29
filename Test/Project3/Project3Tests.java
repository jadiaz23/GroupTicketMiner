package Project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Project3Tests {
    Customer customer;
    EventFactory eventFactory = new EventFactory();
    Venue venue;
    Event event;
    Ticket ticket;


    @BeforeEach
    void constructObjects() {
        // Customer object for tests
        customer = new Customer("1", "Ronnie", "Coleman", "lightweight", "yeahbuddy!", "888888.88", "False", "1");

        // Event object for tests
        venue = new Auditorium("1", "Zappos Theater", "Auditorium", "7000", "7000",
                "900000", "5", "10", "15", "20", "45", "5");
        event = eventFactory.createEvent("1", "Special", "Olympia", "12/15/2022", "5:00", 1000.00, venue);

        // Ticket object for tests (vip $5000)
        ticket = new Ticket(123, event.name, event.venue.name, event.date, event.time, event.type, event.vipPrice);
    }

    //Customer class test

    @Test
    void buyTicket() {
        assert (customer.buyTicket(ticket));
        assert (customer.getMoney() == 883888.88); // 888888.88 - 5000.0
    }

    // Event class tests

    @Test
    void totalRevenue() {
        event.sellTicket(ticket);
        event.venue.BuyVip();
        assert (event.totalRevenue() == 5000.0);
    }

    @Test
    void profit() {
        event.sellTicket(ticket);
        event.venue.BuyVip();
        assert (event.profit() == -895000.0); // 5000.0 - 900000
    }

    @Test
    void sellTicket() {
        event.sellTicket(ticket);
        assert (event.tickets.size() == 1);
        assert (venue.capacity == 6999);
    }

    // Ticket class test

    @Test
    void applySalesTax() {
        ticket.applySalesTax();
        assert (ticket.cost == 5412.5);
    }
}
