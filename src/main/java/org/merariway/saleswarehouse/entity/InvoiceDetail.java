package org.merariway.saleswarehouse.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int itemQuantity;

    private BigDecimal unitPrice;

    @OneToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id") // Se mapea mediante una clave foranea
    private  Invoice invoiceId;

    @OneToMany(mappedBy = "id")
    private Set<Product> productId;

    public InvoiceDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Set<Product> getProductId() {
        return productId;
    }

    public void setProductId(Set<Product> productId) {
        this.productId = productId;
    }
}
