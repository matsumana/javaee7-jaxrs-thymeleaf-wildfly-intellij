package net.isucon.isucon2.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.isucon.isucon2.domain.Artist;

/**
 * 一覧レスポンス情報
 *
 * @author matsumana
 */
@Setter
@Getter
public class ListResponse extends BaseResponse {

    private List<Artist> artists;
}
