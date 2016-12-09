package cz.novotm60.model.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Customer {

    private int id;
    private int customerID;
    private List<String> firstName;
    private List<String> surName;
    private CustomerStatus status;
    private List<Address> address;
    private List<Phone> phone;

    public enum CustomerStatus {
        ACTIVE, SUSPENDED, REFUND, INACTIVE
    }
}
