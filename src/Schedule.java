public class Schedule {
    private String departureTime;
    private String stops;
    private String timeDelays;

    private int count_of_seats;

    public Schedule(String departureTime, String stops, String timeDelays, int count_of_seats) {
        this.departureTime = departureTime;
        this.stops = stops;
        this.timeDelays = timeDelays;
        this.count_of_seats = count_of_seats;
    }

    public int getCount_of_seats() {
        return count_of_seats;
    }

    public void setCount_of_seats(int count_of_seats) {
        this.count_of_seats = count_of_seats;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getTimeDelays() {
        return timeDelays;
    }

    public void setTimeDelays(String timeDelays) {
        this.timeDelays = timeDelays;
    }

    public String toString(){
        return new String("Время отправления"+departureTime);
    }

}
