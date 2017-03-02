package net.gcl.ticket.model.enums;

/**
 * Created by guochenglai on 1/26/17.
 */
public enum TicketType {
    ADULT("成人票", "1"), CHILD("儿童票", "2"), STUDENT("学生票", "3"), SOLDIER("残军票", "4");

    private final String label;

    private final String value;

    TicketType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return label;
    }
}
