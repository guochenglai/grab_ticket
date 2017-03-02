package net.gcl.ticket.util;

import net.gcl.ticket.model.Passenger;
import net.gcl.ticket.model.enums.SeatType;

import java.util.List;

/**
 * Created by guochenglai on 2/22/17.
 */
public class PassengerInfoWrapper {
    public static String generatePassengerTicketStr(SeatType seatType, List<Passenger> passengerList) {
        StringBuffer passengerTicketStr = new StringBuffer();
        for (Passenger passenger : passengerList) {
            passengerTicketStr.append(seatType.getValue()).append(",").append("0,1").append(",").append(passenger.getName()).append(",").append("1").append(",")
                    .append(passenger.getIdNumber()).append(",").append(passenger.getMobile()).append(",").append("N").append("_");
        }
        return passengerTicketStr.subSequence(0, passengerTicketStr.length() - 1).toString();
    }
    public static String generateOldPassengerStr(List<Passenger> passengerList) {
        StringBuffer passengerTicketStr = new StringBuffer();
        for (Passenger passenger : passengerList) {
            passengerTicketStr.append(passenger.getName()).append(",").append("1").append(",").append(passenger.getIdNumber()).append(",").append("1").append("_");

        }
        return passengerTicketStr.toString();
    }

}
