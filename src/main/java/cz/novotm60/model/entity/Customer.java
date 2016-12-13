package cz.novotm60.model.entity;

import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Customer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Basic
    @Column(name = "customerID")
    private int customerID;

    @ElementCollection
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> firstName;

    @ElementCollection
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> surName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    @OneToMany
    private List<Address> address;

    @OneToMany
    private List<PhoneNumber> phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<ChangeOrder> changeOrders;

    public enum CustomerStatus {
        ACTIVE, SUSPENDED, REFUND, INACTIVE
    }
}
