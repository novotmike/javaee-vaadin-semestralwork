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
public class Address {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Basic
    @Column(name = "streetName")
    private String streetName;

    @Basic
    @Column(name = "streetNum")
    private String streetNum;

    @Basic
    @Column(name = "postalCode")
    private String postalCode;

    @Basic
    @Column(name = "cityPart")
    private String cityPart;

    @Basic
    @Column(name = "city")
    private String city;

    @Basic
    @Column(name = "county")
    private String county;

    @Basic
    @Column(name = "country")
    private String country;

}
