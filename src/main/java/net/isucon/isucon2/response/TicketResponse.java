package net.isucon.isucon2.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.isucon.isucon2.domain.Ticket;
import net.isucon.isucon2.domain.Variation;

/**
 * チケットレスポンス情報
 *
 * @author matsumana
 */
@Setter
@Getter
public class TicketResponse extends BaseResponse {

    private Ticket ticket;
    private List<Variation> variations;
}
