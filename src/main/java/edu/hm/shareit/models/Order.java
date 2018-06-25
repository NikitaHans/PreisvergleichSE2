package edu.hm.shareit.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="brand"),
            @JoinColumn(name="modelName")
    })
    private Car car;
    @ManyToOne
    @JoinColumn(name="paket")
    private CarPackage paket;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CarAttribute> attributes;
    private float totalPrice;
    private String nation;
    @ManyToOne
    @JoinColumn(name="mail")
    private User user;
    private boolean verified;

    public Order(){}

    public Order(Car car, CarPackage carPackage, List<CarAttribute> attributes){
        this.car = car;
        this.paket = carPackage;
        this.nation = "de";
        this.attributes = attributes;
        this.totalPrice = 0;
        this.verified = false;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void  setCar (Car car){
        this.car = car;
    }

    public void setPaket (CarPackage paket){
        this.paket = paket;
    }

    public void setAttributes (List<CarAttribute> attributes){
        this.attributes = attributes;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public CarPackage getPaket() {
        return paket;
    }

    public List<CarAttribute> getAttributes() {
        return attributes;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getNation() {
        return nation;
    }

    public boolean isVerified() {
        return verified;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.totalPrice, totalPrice) == 0 &&
                verified == order.verified &&
                Objects.equals(id, order.id) &&
                Objects.equals(car, order.car) &&
                Objects.equals(paket, order.paket) &&
                Objects.equals(attributes, order.attributes) &&
                Objects.equals(nation, order.nation) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, car, paket, attributes, totalPrice, nation, user, verified);
    }
}
