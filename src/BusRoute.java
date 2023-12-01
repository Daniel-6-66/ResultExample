public class BusRoute {
    private String routeName;
    private String departurePoint;
    private String arrivalPoint;
    private Schedule schedule;

    public BusRoute(String routeName, String departurePoint, String arrivalPoint, Schedule schedule) {
        this.routeName = routeName;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.schedule = schedule;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public String toString(){
        return new String(routeName+" "+schedule);
    }
}
