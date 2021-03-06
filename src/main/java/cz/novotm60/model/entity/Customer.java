package cz.novotm60.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
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
    private BigInteger customerID;

    @Basic
    @Column(name = "birthnum")
    private String birthNum;

    @Basic
    @Column(name = "countryoforigin")
    private String countryOfOrigin;

    @ElementCollection
    @OrderColumn(name = "firstnames")
    private List<String> firstName;

    @ElementCollection
    @OrderColumn(name = "lastnames")
    private List<String> surName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestType status;

    @OneToMany
    private List<Address> address;

    @OneToMany
    private List<PhoneNumber> phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<ChangeOrder> changeOrders;

    public enum RequestType {
        ACTIVE, INCHANGE, REFUNDED, SUSPENDED, DEACTIVATED
    }
}
