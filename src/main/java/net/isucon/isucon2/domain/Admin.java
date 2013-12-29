package net.isucon.isucon2.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import net.isucon.isucon2.response.BaseResponse;

/**
 * 管理者ドメイン
 *
 * @author matsumana
 */
@RequestScoped
public class Admin extends BaseDomain {

    /**
     * 画面初期表示
     */
    public BaseResponse index() {

        List<Stock> infos = repository.findLatest();

        BaseResponse response = new BaseResponse();
        response.setTemplateName("admin");
        response.setInfos(infos);

        return response;
    }

    /**
     * データ初期化
     */
    public Response initialize() throws UnsupportedEncodingException, IOException {
        try (BufferedReader reader =
                new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("../../../../sql/initial_data.sql"), "UTF-8"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                repository.executeUpdate(line);
            }
        }

        URI uri = UriBuilder.fromUri("/admin").build();
        Response response = Response.temporaryRedirect(uri)
                .status(Response.Status.FOUND).build();

        return response;
    }

    /**
     * CSVデータ取得
     */
    public String getCsvData() {
        List<OrderRequest> orders = repository.findCsvData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder result = new StringBuilder(64 * 4096 * 10);
        for (OrderRequest order : orders) {
            result.append(order.getId()).append(",");
            result.append(order.getMemberId()).append(",");
            result.append(order.getSeatId()).append(",");
            result.append(order.getVariationId()).append(",");
            result.append(sdf.format(order.getUpdatedAt())).append("\n");
        }

        return result.toString();
    }
}
