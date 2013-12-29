package net.isucon.isucon2.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 在庫ドメイン
 *
 * @author matsumana
 */
@Entity
@Table(name = "stock")
@NamedQueries({
    @NamedQuery(name = "Stock.findByVariationId", query = "SELECT s FROM Stock s WHERE s.variationId = :variationId"),
    @NamedQuery(name = "Stock.findByOrderId", query = "SELECT s FROM Stock s WHERE s.orderId = :orderId"),
    @NamedQuery(name = "Stock.findLatest", query = "SELECT NEW net.isucon.isucon2.domain.Stock(s.id, s.seatId, v.name, t.name, a.name) FROM Stock s, Variation v, Ticket t, Artist a WHERE s.variationId = v.id AND v.ticketId = t.id AND t.artistId = a.id AND s.orderId IS NOT NULL ORDER BY s.orderId DESC"),
    @NamedQuery(name = "Stock.countByVariationId", query = "SELECT COUNT(s) FROM Stock s WHERE s.variationId = :variationId AND s.orderId IS NULL")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Stock.updateOrderId", query = "UPDATE stock SET order_id = ? WHERE variation_id = ? AND order_id IS NULL ORDER BY RAND() LIMIT 1")})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "variation_id")
    private int variationId;
    @Column(name = "seat_id")
    private String seatId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    // --- extended ----------
    @Transient
    private String variationName;
    @Transient
    private String ticketName;
    @Transient
    private String artistName;

    public Stock(Integer id, String seatId, String variationName,
            String ticketName, String artistName) {
        this.id = id;
        this.seatId = seatId;
        this.variationName = variationName;
        this.ticketName = ticketName;
        this.artistName = artistName;
    }
}
