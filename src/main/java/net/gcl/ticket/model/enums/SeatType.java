package net.gcl.ticket.model.enums;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by guochenglai on 1/26/17.
 */
public enum  SeatType {
    ONE_SEAT("一等座", "M",false),
    TWO_SEAT("二等座", "O",false),
    HARD_SLEEPER("硬卧", "3",false),
    SOFT_SLEEPER("软卧", "4",false),
    HARD_SEAT("硬座", "1",false),
    BUSS_SEAT("商务座", "9",false),
    VAG_SLEEPER("高级软卧","6",false),
    NONE_SEAT("无座", "-1",false);

    private StringProperty label;
    private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
    private  StringProperty value;

    SeatType(String label, String value,boolean checked) {
        this.label = new SimpleStringProperty(label);
        this.value = new SimpleStringProperty(value);
        this.checked = new SimpleBooleanProperty(checked);
    }

    public static SeatType valueOfSeat(String value) {
        for (SeatType seatType : SeatType.values()) {
            if (seatType.getValue().equals(value)) {
                return seatType;
            }
        }
        return null;
    }

    public boolean getChecked() {
        return checked.get();
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public String getLabel() {
        return label.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    @Override
    public String toString() {
        return label.get();
    }
}
