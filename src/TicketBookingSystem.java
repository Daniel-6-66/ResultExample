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
            frame.setSize(550, 600);
            frame.setResizable(false);
            frame.add(new TicketBookingPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class TicketBookingPanel extends JPanel {

    private JComboBox<String> routeComboBox;
    private ArrayList<BusRoute> list_routes = new ArrayList<>();
    private JSpinner seatSpinner;
    private DefaultListModel<Ticket> ticketListModel;

    public TicketBookingPanel() {
        setLayout(new BorderLayout());

        TicketCounter ticketCounter = new TicketCounter("Ticket Counter", "Address", "Contact Information");
        Schedule schedule = new Schedule("10:00 AM", "Stop1, Stop2, Stop3", "No delays", 25);
        BusRoute busRoute = new BusRoute("Liski -> Voronej", "Departure Point", "Arrival Point", schedule);
        Schedule schedule1 = new Schedule("04:15 PM", "Stop1, Stop2, Stop3", "No delays", 40);
        BusRoute busRoute1 = new BusRoute("Moscow -> Voronej", "Departure Point", "Arrival Point", schedule1);

        list_routes.add(busRoute);
        list_routes.add(busRoute1);
        routeComboBox = new JComboBox<>();
        for (BusRoute route : list_routes) {
            routeComboBox.addItem(route.toString());
        }
        routeComboBox.setToolTipText("Select a bus route"); // Подсказка для панели выбора маршрута

        seatSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
        seatSpinner.setToolTipText("Select the number of seats"); // Подсказка для панели выбора количества мест

        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.setToolTipText("Click here to buy a ticket");
        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusRoute selectedRoute = list_routes.get(routeComboBox.getSelectedIndex());
                Schedule selectedSchedule = selectedRoute.getSchedule();

                int numberOfSeats = (int) seatSpinner.getValue();
                int availableSeats = selectedSchedule.getCount_of_seats();

                if (availableSeats >= numberOfSeats) {
                    for (int i = 0; i < numberOfSeats; i++) {
                        int uniqueId = Ticket.generateUniqueTicketId(ticketCounter.getAlready_buy());
                        String departureTime = selectedSchedule.getDepartureTime();
                        Ticket ticket = new Ticket(uniqueId, 20.0, departureTime);
                        ticketListModel.addElement(ticket);
                        ticketCounter.getAlready_buy().add(ticket);
                    }
                    selectedSchedule.setCount_of_seats(selectedSchedule.getCount_of_seats() - numberOfSeats);
                } else {
                    JOptionPane.showMessageDialog(TicketBookingPanel.this,
                            "Not enough seats available for this route!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ticketListModel = new DefaultListModel<>();
        JList<Ticket> ticketList = new JList<>(ticketListModel);
        JScrollPane ticketScrollPane = new JScrollPane(ticketList);

        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel routeLabel = new JLabel("Bus Route:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        controlPanel.add(routeLabel, gbc);

        JPanel routePanel = new JPanel();
        routePanel.add(routeComboBox);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(routePanel, gbc);

        JLabel seatsLabel = new JLabel("Select Number of Seats:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        controlPanel.add(seatsLabel, gbc);

        JPanel seatsPanel = new JPanel();
        seatsPanel.add(seatSpinner);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(seatsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        controlPanel.add(buyTicketButton, gbc);

        add(controlPanel, BorderLayout.NORTH);
        add(ticketScrollPane, BorderLayout.CENTER);
    }
}
