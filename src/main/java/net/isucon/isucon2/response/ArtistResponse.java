package net.isucon.isucon2.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.isucon.isucon2.domain.Artist;
import net.isucon.isucon2.domain.Ticket;

/**
 * アーティストレスポンス情報
 *
 * @author matsumana
 */
@Setter
@Getter
public class ArtistResponse extends BaseResponse {

    private Artist artist;
    private List<Ticket> tickets;
}
