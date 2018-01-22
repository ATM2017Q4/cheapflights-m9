package com.cheapflights.entities;

public class TravelInfo {
    private String origin;
    private String destination;
    private int numberOfAdults;
    private DepartureDates departureDates;
    private ReturnDates returnDates;
    private int acceptablePrice;


    public DepartureDates getDepartureDates() {
        return departureDates;
    }

    public void setDepartureDates(DepartureDates departureDates) {
        this.departureDates = departureDates;
    }

    public ReturnDates getReturnDates() {
        return returnDates;
    }

    public void setReturnDates(ReturnDates returnDates) {
        this.returnDates = returnDates;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAcceptablePrice() {
        return acceptablePrice;
    }

    public void setAcceptablePrice(int acceptablePrice) {
        this.acceptablePrice = acceptablePrice;
    }

    public static class DepartureDates {
        private int day;
        private String month;
        private int year;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class ReturnDates {
        private int day;
        private String month;
        private int year;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

}
