package net.isucon.isucon2.application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import net.isucon.isucon2.domain.Admin;
import net.isucon.isucon2.domain.OrderRequest;
import net.isucon.isucon2.response.BuyResponse;

/**
 * アプリケーション　(ファサード)
 *
 * @author matsumana
 */
@RequestScoped
public class IsuconApplication {

    @Inject
    Admin admin;
    @Inject
    OrderRequest orderRequest;

    @Transactional(rollbackOn = Exception.class)
    public Response initialize() throws UnsupportedEncodingException, IOException {
        return admin.initialize();
    }

    @Transactional
    public BuyResponse buy(int variationId, String memberId) {
        return orderRequest.buy(variationId, memberId);
    }
}
