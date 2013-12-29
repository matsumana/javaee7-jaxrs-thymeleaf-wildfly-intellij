package net.isucon.isucon2.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.isucon.isucon2.domain.Stock;

/**
 * 基底レスポンス情報
 *
 * @author matsumana
 */
@Setter
@Getter
public class BaseResponse {

    private String templateName;
    private List<Stock> infos;
}
