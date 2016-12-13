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
public class PhoneNumber {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phoneType")
    private PhoneType phoneType;

    @Basic
    @Column(name = "phoneNum")
    private String phoneNum;

    @Basic
    @Column(name = "cityCode")
    private String cityCode;

    @Basic
    @Column(name = "countryCode")
    private String countryCode;


    public enum PhoneType {
        MOBILE, FAX
    }

}
