package net.isucon.isucon2.resource;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import net.isucon.isucon2.domain.Artist;
import net.isucon.isucon2.infra.Template;
import net.isucon.isucon2.response.ListResponse;

/**
 * 一覧サービス
 *
 * @author matsumana
 */
@Path("list")
@RequestScoped
public class ListResource {

    @Inject
    Artist artist;

    @GET
    @Template("base.html")
    public ListResponse index() {
        return artist.getArtists();
    }
}
