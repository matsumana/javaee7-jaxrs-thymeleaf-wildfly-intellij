package net.isucon.isucon2.resource;

import net.isucon.isucon2.response.BaseResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import net.isucon.isucon2.application.IsuconApplication;
import net.isucon.isucon2.domain.Admin;
import net.isucon.isucon2.infra.Template;

/**
 * 管理者サービス
 *
 * @author matsumana
 */
@Path("admin")
@RequestScoped
public class AdminResource {

    @Inject
    Admin admin;
    @Inject
    IsuconApplication application;

    @GET
    @Template("base.html")
    public BaseResponse index() {
        return admin.index();
    }

    @POST
    public Response initialize() throws UnsupportedEncodingException, IOException {
        return application.initialize();
    }

    @GET
    @Path("orders")
    @Produces("text/csv")
    public String csv() {
        return admin.getCsvData();
    }
}
