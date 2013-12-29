package net.isucon.isucon2.domain;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
import net.isucon.isucon2.response.TicketResponse;

/**
 * チケットバリエーションドメイン
 *
 * @author matsumana
 */
@Entity
@RequestScoped
@Table(name = "variation")
@NamedQueries({
    @NamedQuery(name = "Variation.findByTicketId", query = "SELECT v FROM Variation v WHERE v.ticketId = :ticketId ORDER BY v.id")})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Variation extends BaseDomain {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "ticket_id")
    private int ticketId;
    // --- extended ----------
    @Transient
    private List<String> stocks;
    @Transient
    private int vacancy;

    /**
     * 空席状況取得
     */
    public TicketResponse getVacancies(int ticketId) {
        Ticket ticket = repository.findTicketByIdWithName(ticketId);
        if (ticket == null) {
            // TODO
            throw new IllegalArgumentException("Ticket not found. id=" + ticketId);
        }

        List<Variation> variations = repository.findVariationsByTicketId(ticketId);
        for (Variation variation : variations) {
            List<String> s = new ArrayList<>(4096);
            List<Stock> resultList = repository.findStocksByVariationId(variation.getId());
            for (Stock stock : resultList) {
                String seatId = stock.getSeatId();
                String cssClass = (stock.getOrderId() == null) ? "available" : "unavailable";
                s.add(seatId + ":" + cssClass);
            }

            variation.setStocks(s);
            variation.setVacancy(repository.countStocksByVariationId(variation.getId()));
        }

        List<Stock> infos = repository.findLatest();

        TicketResponse response = new TicketResponse();
        response.setTemplateName("ticket");
        response.setTicket(ticket);
        response.setVariations(variations);
        response.setInfos(infos);

        return response;
    }
}
