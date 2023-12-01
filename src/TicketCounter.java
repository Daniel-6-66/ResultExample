import java.util.ArrayList;
import java.util.List;

public class TicketCounter {
    private String name;
    private String address;
    private String contactInformation;

    private ArrayList<Ticket> already_buy;

    public TicketCounter(String name, String address, String contactInformation) {
        this.name = name;
        this.address = address;
        this.contactInformation = contactInformation;
        this.already_buy = new ArrayList<>();
    }

    public ArrayList<Ticket> getAlready_buy() {
        return already_buy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

}
