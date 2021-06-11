/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mitchell
 */
@Entity
@Table(name = "CUSTOMER_ORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c")
    , @NamedQuery(name = "CustomerOrder.findById", query = "SELECT c FROM CustomerOrder c WHERE c.id = :id")
    , @NamedQuery(name = "CustomerOrder.findByCustomerEmail", query = "SELECT c FROM CustomerOrder c WHERE c.customerEmail = :customerEmail")
    , @NamedQuery(name = "CustomerOrder.findByOrderItems", query = "SELECT c FROM CustomerOrder c WHERE c.orderItems = :orderItems")
    , @NamedQuery(name = "CustomerOrder.findByDeliveryAddress", query = "SELECT c FROM CustomerOrder c WHERE c.deliveryAddress = :deliveryAddress")
    , @NamedQuery(name = "CustomerOrder.findByDeliveryPostcode", query = "SELECT c FROM CustomerOrder c WHERE c.deliveryPostcode = :deliveryPostcode")
    , @NamedQuery(name = "CustomerOrder.findByDeliveryState", query = "SELECT c FROM CustomerOrder c WHERE c.deliveryState = :deliveryState")
    , @NamedQuery(name = "CustomerOrder.findByDeliveryCountry", query = "SELECT c FROM CustomerOrder c WHERE c.deliveryCountry = :deliveryCountry")
    , @NamedQuery(name = "CustomerOrder.findByPaymentMethod", query = "SELECT c FROM CustomerOrder c WHERE c.paymentMethod = :paymentMethod")})
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "ORDER_ITEMS")
    private String orderItems;
    @Size(max = 255)
    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;
    @Size(max = 255)
    @Column(name = "DELIVERY_POSTCODE")
    private String deliveryPostcode;
    @Size(max = 255)
    @Column(name = "DELIVERY_STATE")
    private String deliveryState;
    @Size(max = 255)
    @Column(name = "DELIVERY_COUNTRY")
    private String deliveryCountry;
    @Size(max = 255)
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    @JoinColumn(name = "CUSTOMER_EMAIL", referencedColumnName = "EMAIL")
    @ManyToOne
    private Customer customerEmail;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPostcode() {
        return deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Customer customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerOrder[ id=" + id + " ]";
    }
    
}
