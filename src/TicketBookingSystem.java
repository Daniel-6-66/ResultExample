import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicketBookingSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ticket Booking System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new TicketBookingPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class TicketBookingPanel extends JPanel {

    private JComboBox<String> routeComboBox;
    private ArrayList<BusRoute> list_routes = new ArrayList<>();
    private JSpinner seatSpinner;
    private JTextField ticketTextField;
    private DefaultListModel<Ticket> ticketListModel;

    public TicketBookingPanel() {
        setLayout(new BorderLayout());

        TicketCounter ticketCounter = new TicketCounter("Ticket Counter", "Address", "Contact Information");
        Schedule schedule = new Schedule("10:00 AM", "Stop1, Stop2, Stop3", "No delays" , 25);
        BusRoute busRoute = new BusRoute("Liski -> Voronej", "Departure Point", "Arrival Point", schedule);
        Schedule schedule1 = new Schedule("04:15 PM", "Stop1, Stop2, Stop3", "No delays" , 40);
        BusRoute busRoute1 = new BusRoute("Moscow -> Voronej", "Departure Point", "Arrival Point", schedule1);





        list_routes.add(busRoute);
        list_routes.add(busRoute1);
        routeComboBox = new JComboBox<>();
        routeComboBox.addItem(busRoute.toString());
        routeComboBox.addItem(busRoute1.toString());




        seatSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));

        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusRoute selectedRoute = list_routes.get(routeComboBox.getSelectedIndex());
                Schedule selectedSchedule = selectedRoute.getSchedule();

                int numberOfSeats = (int) seatSpinner.getValue();
                int availableSeats = selectedSchedule.getCount_of_seats(); // Получаем количество доступных мест

                if (availableSeats >= numberOfSeats) {
                    for (int i = 0; i < numberOfSeats; i++) {
                        int uniqueId = generateUniqueTicketId(ticketCounter.getAlready_buy());
                        String departureTime = selectedSchedule.getDepartureTime();
                        Ticket ticket = new Ticket(uniqueId, 20.0, departureTime);
                        ticketListModel.addElement(ticket);
                        ticketCounter.getAlready_buy().add(ticket); // Добавляем билет в список already_buy
                    }
                    selectedSchedule.setCount_of_seats(selectedSchedule.getCount_of_seats() - numberOfSeats); // Уменьшаем количество доступных мест
                } else {
                    JOptionPane.showMessageDialog(TicketBookingPanel.this,
                            "Not enough seats available for this route!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        ticketListModel = new DefaultListModel<>();
        JList<Ticket> ticketList = new JList<>(ticketListModel);
        JList<String> ticketListInf = new JList<>();
        for (Ticket t: ticketList.getSelectedValuesList()){
            ticketListInf.add(ticketList);
        }
        JScrollPane ticketScrollPane = new JScrollPane(ticketList);


        JPanel controlPanel = new JPanel(new GridLayout(3, 2));
        controlPanel.add(new JLabel("Select Route:"));
        controlPanel.add(routeComboBox);
        controlPanel.add(new JLabel("Select Number of Seats:"));
        controlPanel.add(seatSpinner);
        controlPanel.add(new JLabel());
        controlPanel.add(buyTicketButton);

        add(controlPanel, BorderLayout.NORTH);
        add(ticketScrollPane, BorderLayout.CENTER);
    }

    private int generateUniqueTicketId(ArrayList<Ticket> alreadyBuy) {
        int id;
        do {
            id = (int) (Math.random() * 1000); // Генерация случайного числа для id билета
        } while (isIdAlreadyUsed(alreadyBuy, id)); // Проверка на уникальность id
        return id;
    }
    private boolean isIdAlreadyUsed(ArrayList<Ticket> alreadyBuy, int id) {
        for (Ticket ticket : alreadyBuy) {
            if (ticket.getId() == id) {
                return true;
            }
        }
        return false;
    }
}


