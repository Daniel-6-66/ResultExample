public class Ticket {
    private int id;
    private double cost;
    private String departureTime;

    public Ticket(int id, double cost, String departureTime) {
        this.id = id;
        this.cost = cost;
        this.departureTime = departureTime;
    }

    public String toString(){
        return new String("Билет :"+"ID : "+id+" "+"Цена : "+cost+" "+"Время отправления "+departureTime+" ");
    }

    public int getId() {
        return id;
    }
}
