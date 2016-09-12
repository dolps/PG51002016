package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by dolplads on 09/09/16.
 */
@Entity
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "address_fk")
    @Cascade({CascadeType.PERSIST})
    Addresss addresss;

    @OrderBy(clause = "itemName desc")
    @Cascade(CascadeType.ALL)
    @OneToMany
    @JoinColumn(name = "item_fk") // instad of making a joined table add reference key
            List<Item> items;
/**
 Hibernate: insert into Addresss (address, id) values (?, ?)
 Hibernate: insert into Customer (address_fk, name, id) values (?, ?, ?) x
 Hibernate: insert into Item (itemName, id) values (?, ?) x
 Hibernate: insert into Item (itemName, id) values (?, ?) x
 Hibernate: insert into Customer_Item (Customer_id, items_id) values (?, ?)
 Hibernate: insert into Customer_Item (Customer_id, items_id) values (?, ?)

 Hibernate: insert into Addresss (address, id) values (?, ?) x
 Hibernate: insert into Customer (address_fk, name, id) values (?, ?, ?) x
 Hibernate: insert into Item (itemName, id) values (?, ?) x
 Hibernate: insert into Item (itemName, id) values (?, ?) x
 Hibernate: update Item set item_fk=? where id=?
 Hibernate: update Item set item_fk=? where id=?
 */
}
