package cz.novotm60.model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Phone {

    /*
    "phoneNumberType",
        "phoneNum",
        "cityCode",
        "countryCode"
     */
    private int id;
    private int phoneType;
    private String phoneNum;
    private String cityCode;
    private String countryCode;

}
