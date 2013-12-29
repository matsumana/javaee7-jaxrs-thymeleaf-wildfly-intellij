package net.isucon.isucon2.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * チケットドメイン
 *
 * @author matsumana
 */
@Entity
@Table(name = "ticket")
@NamedQueries({
    @NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
    @NamedQuery(name = "Ticket.findByIdWithName", query = "SELECT NEW net.isucon.isucon2.domain.Ticket(t.id, t.name, t.artistId, a.name) FROM Ticket t, Artist a WHERE t.artistId = a.id AND t.id = :id"),
    @NamedQuery(name = "Ticket.findByArtistId", query = "SELECT t FROM Ticket t WHERE t.artistId = :artistId ORDER BY t.id"),
    @NamedQuery(name = "Ticket.countStockByTicketId", query = "SELECT COUNT(v) FROM Variation v, Stock s WHERE s.variationId = v.id AND v.ticketId = :ticketId AND s.orderId IS NULL")})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "artist_id")
    private int artistId;
    // --- extended ----------
    @Transient
    private String artistName;
    @Transient
    private int count;

    public Ticket(Integer id, String name, int artistId, String artistName) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.artistName = artistName;
    }
}
