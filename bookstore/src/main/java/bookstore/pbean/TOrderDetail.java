package bookstore.pbean;

// Generated 2011/04/28 4:53:34 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

/**
 * TOrderDetail generated by hbm2java
 */
@Entity
@Table(name = "t_order_detail", schema = "public")
public class TOrderDetail implements java.io.Serializable {

	private int id;
	private TOrder TOrder;
	private TBook TBook;

	public TOrderDetail() {
	}

	public TOrderDetail(int id, TOrder TOrder, TBook TBook) {
		this.id = id;
		this.TOrder = TOrder;
		this.TBook = TBook;
	}

	@Id
	@SequenceGenerator(name="hibernate_sequence", sequenceName="hibernate_sequence")
	@GeneratedValue(generator="hibernate_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id_fk", nullable = false)
	public TOrder getTOrder() {
		return this.TOrder;
	}

	public void setTOrder(TOrder TOrder) {
		this.TOrder = TOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id_fk", nullable = false)
	public TBook getTBook() {
		return this.TBook;
	}

	public void setTBook(TBook TBook) {
		this.TBook = TBook;
	}

}