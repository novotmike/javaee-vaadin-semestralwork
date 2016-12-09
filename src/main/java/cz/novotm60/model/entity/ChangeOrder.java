package cz.novotm60.model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChangeOrder {

    private int id;
    private RequestType requestType;
    private Customer customer;


    public enum RequestType {
        NEW, CHANGE, REFUND, SUSPEND, DELETE
    }
}
