package net.isucon.isucon2.repository;

import java.math.BigInteger;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.isucon.isucon2.domain.Artist;
import net.isucon.isucon2.domain.Ticket;
import net.isucon.isucon2.domain.OrderRequest;
import net.isucon.isucon2.domain.Stock;
import net.isucon.isucon2.domain.Variation;

/**
 * リポジトリ
 *
 * @author matsumana
 */
@RequestScoped
public class IsuconRepository {

    @PersistenceContext
    EntityManager em;

    /**
     * 最近購入されたチケット取得
     */
    public List<Stock> findLatest() {
        Query q = em.createNamedQuery("Stock.findLatest").setMaxResults(10);
        return q.getResultList();
    }

    /**
     * アーティスト取得
     */
    public Artist findArtistById(int id) {
        Query q = em.createNamedQuery("Artist.findById");
        q.setParameter("id", id);
        return (Artist) q.getSingleResult();
    }

    /**
     * アーティスト全件取得
     */
    public List<Artist> findAllArtists() {
        Query q = em.createNamedQuery("Artist.findAll");
        return q.getResultList();
    }

    /**
     * チケット取得
     */
    public List<Ticket> findTicketsByArtistId(int artistId) {
        Query q = em.createNamedQuery("Ticket.findByArtistId");
        q.setParameter("artistId", artistId);
        return q.getResultList();
    }

    /**
     * 残りチケット数取得
     */
    public int countTickets(int ticketId) {
        Query q = em.createNamedQuery("Ticket.countStockByTicketId");
        q.setParameter("ticketId", ticketId);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * チケット取得
     */
    public Ticket findTicketByIdWithName(int ticketId) {
        Query q = em.createNamedQuery("Ticket.findByIdWithName");
        q.setParameter("id", ticketId);
        return (Ticket) q.getSingleResult();
    }

    /**
     * バリエーション取得
     */
    public List<Variation> findVariationsByTicketId(int ticketId) {
        Query q = em.createNamedQuery("Variation.findByTicketId");
        q.setParameter("ticketId", ticketId);
        return q.getResultList();
    }

    /**
     * 残りチケット取得
     */
    public List<Stock> findStocksByVariationId(int variationId) {
        Query q = em.createNamedQuery("Stock.findByVariationId");
        q.setParameter("variationId", variationId);
        return q.getResultList();
    }

    /**
     * 残りチケット数取得
     */
    public int countStocksByVariationId(int variationId) {
        Query q = em.createNamedQuery("Stock.countByVariationId");
        q.setParameter("variationId", variationId);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * 購入依頼保存
     */
    public void persistOrderRequest(OrderRequest orderRequest) {
        em.persist(orderRequest);
    }

    /**
     * LAST_INSERT_ID取得
     */
    public int getLastInsertId() {
        Query q = em.createNativeQuery("SELECT LAST_INSERT_ID()");
        return ((BigInteger) q.getSingleResult()).intValue();
    }

    /**
     * チケット購入時の在庫更新
     */
    public int updateStock(int orderId, int variationId) {
        Query q = em.createNamedQuery("Stock.updateOrderId");
        q.setParameter(1, orderId);
        q.setParameter(2, variationId);
        return q.executeUpdate();
    }

    /**
     * 在庫取得
     */
    public Stock findStockByOrderId(int orderId) {
        Query q = em.createNamedQuery("Stock.findByOrderId").setMaxResults(1);
        q.setParameter("orderId", orderId);
        return (Stock) q.getSingleResult();
    }

    /**
     * CSVデータ取得
     */
    public List<OrderRequest> findCsvData() {
        Query q = em.createNamedQuery("OrderRequest.findCsvData");
        return q.getResultList();
    }

    /**
     * 更新SQL実行
     */
    public int executeUpdate(String sql) {
        Query q = em.createNativeQuery(sql);
        return q.executeUpdate();
    }
}
