package service;

import model.Addresss;
import model.Customer;
import model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dolplads on 09/09/16.
 */
public class CustomerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("H2DB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void persistCustomer() throws Exception {
        EntityTransaction t = entityManager.getTransaction();

        Customer customer = new Customer();
        customer.setName("thomas");

        List<Item> items = new ArrayList<>();
        Item one = new Item();
        one.setItemName("itemName");
        Item two = new Item();
        two.setItemName("itemName2");
        items.add(one);
        items.add(two);
        customer.setItems(items);

        List<Addresss> addressses = new ArrayList<>();
        Addresss addresss = new Addresss();
        Addresss addresss2 = new Addresss();
        addresss.setAddress("A");
        addresss2.setAddress("B");

        addressses.add(addresss);
        addressses.add(addresss2);
        customer.setAddresss(addresss);
        addresss.setCustomer(customer);

        t.begin();
        entityManager.persist(customer);
        t.commit();
        entityManager.clear();
        customer.setName("pelle");
        t.begin();
        entityManager.merge(customer);
        t.commit();
        entityManager.clear();


        Addresss addresss1 = entityManager.find(Addresss.class, addresss.getId());

        System.out.println(addresss1.getCustomer().getName());
    }
}