package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by dolplads on 09/09/16.
 */
@Entity
@NoArgsConstructor
@Data
public class Addresss {
    @Id
    @GeneratedValue
    private Long id;
    private String address;

    @JoinColumn(name = "customer_fk")
    @OneToOne(mappedBy = "addresss")
    private Customer customer;
}
