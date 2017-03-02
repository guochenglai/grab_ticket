package net.gcl.ticket.model.enums;

/**
 * Created by guochenglai on 1/26/17.
 */
public enum  CardType {
    IDENTITY("二代身份证", "1"), GAPASSPORT("港澳通行证", "C"), TPASSPORT("台湾通行证", "G"), PASSPORT("护照", "B");

    private final String label;

    private final String value;

    CardType(String label, String value) {
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
