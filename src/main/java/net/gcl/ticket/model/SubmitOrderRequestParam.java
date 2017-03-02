package net.gcl.ticket.model;

import net.gcl.ticket.model.enums.SeatType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guochenglai on 2/21/17.
 */
public class SubmitOrderRequestParam {
    private static List<TrainInfo> trainInfoList = new ArrayList<>();
    private static List<Passenger> passengerIdList = new ArrayList<>();
    private static List<SeatType> seatTypeList = new ArrayList<>();

    public static void addSeatType(String seatValue) {
        SeatType seatType = SeatType.valueOfSeat(seatValue);
        seatTypeList.add(seatType);

    }

    public static void removeSeatType(String seatValue){
        SeatType seatType = SeatType.valueOfSeat(seatValue);
        if (seatTypeList.contains(seatType)) {
            seatTypeList.remove(seatType);
        }
    }


    public static void addPassenger(Passenger passenger) {
        passengerIdList.add(passenger);
    }

    public static void removePassenger(Passenger passenger) {
        if (passengerIdList.contains(passenger)) {
            passengerIdList.remove(passenger);
        }
    }

    public static void addTrainInfo(TrainInfo trainInfo) {
        trainInfoList.add(trainInfo);
    }

    public static void removeTrainInfo(TrainInfo trainInfo) {
        if (trainInfoList.contains(trainInfo)) {
            trainInfoList.remove(trainInfo);
        }
    }

    public static List<TrainInfo> getTrainInfoList() {
        return trainInfoList;
    }

    public static void setTrainInfoList(List<TrainInfo> trainInfoList) {
        SubmitOrderRequestParam.trainInfoList = trainInfoList;
    }

    public static List<Passenger> getPassengerIdList() {
        return passengerIdList;
    }

    public static void setPassengerIdList(List<Passenger> passengerIdList) {
        SubmitOrderRequestParam.passengerIdList = passengerIdList;
    }

    public static List<SeatType> getSeatTypeList() {
        return seatTypeList;
    }

    public static void setSeatTypeList(List<SeatType> seatTypeList) {
        SubmitOrderRequestParam.seatTypeList = seatTypeList;
    }
}
