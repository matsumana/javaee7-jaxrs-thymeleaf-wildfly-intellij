package net.isucon.isucon2.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 購入レスポンス情報
 *
 * @author matsumana
 */
@Setter
@Getter
public class BuyResponse extends BaseResponse {

    private String memberId;
    private int variationId;
    private String seatId;
}
