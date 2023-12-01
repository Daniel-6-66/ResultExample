import java.util.ArrayList;

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

    public static int generateUniqueTicketId(ArrayList<Ticket> alreadyBuy) {
        int id;
        do {
            id = (int) (Math.random() * 1000); // Генерация случайного числа для id билета
        } while (isIdAlreadyUsed(alreadyBuy, id)); // Проверка на уникальность id
        return id;
    }
    public static boolean isIdAlreadyUsed(ArrayList<Ticket> alreadyBuy, int id) {
        for (Ticket ticket : alreadyBuy) {
            if (ticket.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
