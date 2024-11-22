package com.shirtstore.entity;
// Generated Apr 20, 2024, 2:20:15 PM by Hibernate Tools 4.3.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * OrderDetail generated by hbm2java
 */
@Entity
@Table(name = "order_detail", catalog = "shirtstoredb")
@NamedQueries({
	@NamedQuery(name = "OrderDetail.listBestSelling", query = "SELECT od.shirt FROM OrderDetail od GROUP BY od.shirt.shirtId ORDER BY SUM(od.quantity) DESC"),
	@NamedQuery(name = "OrderDetail.countByShirt", query = "SELECT COUNT(*) FROM OrderDetail od WHERE od.shirt.shirtId = :shirtId AND od.id.shirtOrder.status = 'Processing'")
})
public class OrderDetail implements java.io.Serializable {
//OrderDetailId la to hop khoa chinh
	private OrderDetailId id = new OrderDetailId();
//	Class product
	private Shirt shirt;
//	Class order
	private ShirtOrder shirtOrder;
	private int quantity;
	private float subTotal;
	private String size;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id, Shirt shirt, ShirtOrder shirtOrder, int quantity, float subTotal) {
		this.id = id;
		this.shirt = shirt;
		this.shirtOrder = shirtOrder;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id", nullable = false)),
			@AttributeOverride(name = "shirtId", column = @Column(name = "product_id", nullable = false)),
			@AttributeOverride(name = "size", column = @Column(name = "size", nullable = false))})
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
	public Shirt getShirt() {
		return this.shirt;
	}

	public void setShirt(Shirt shirt) {
		this.shirt = shirt;
		this.id.setShirt(shirt);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
	public ShirtOrder getShirtOrder() {
		return this.shirtOrder;
	}

	public void setShirtOrder(ShirtOrder shirtOrder) {
		this.shirtOrder = shirtOrder;
		this.id.setShirtOrder(shirtOrder);
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "sub_total", nullable = false, precision = 12, scale = 0)
	public float getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	@Column(name = "size", nullable = false, insertable = false, updatable = false)
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
		this.id.setSize(size);
	}
}
