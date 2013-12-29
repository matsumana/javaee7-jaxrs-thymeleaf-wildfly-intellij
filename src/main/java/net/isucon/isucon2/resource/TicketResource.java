package net.isucon.isucon2.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import net.isucon.isucon2.domain.Variation;
import net.isucon.isucon2.infra.Template;
import net.isucon.isucon2.response.TicketResponse;

/**
 * チケットサービス
 *
 * @author matsumana
 */
@Path("ticket")
@RequestScoped
public class TicketResource {

    @Inject
    Variation variation;

    @GET
    @Path("{ticketId}")
    @Template("base.html")
    public TicketResponse index(@PathParam("ticketId") int ticketId) {

        // TODO validation

        return variation.getVacancies(ticketId);
    }
}
