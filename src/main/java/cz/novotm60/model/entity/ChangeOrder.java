package cz.novotm60.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class ChangeOrder {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "requestType")
    private RequestType requestType;

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "ID", nullable = false)
    private Customer customer;

    @Basic
    @Column(name= "sent")
    private boolean sent;


    public enum RequestType {
        ACTIVE, INCHANGE, REFUNDED, SUSPENDED, DEACTIVATED
    }
}
