package net.isucon.isucon2.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import net.isucon.isucon2.domain.Artist;
import net.isucon.isucon2.infra.Template;
import net.isucon.isucon2.response.ArtistResponse;

/**
 * アーティストサービス
 *
 * @author matsumana
 */
@Path("artist")
@RequestScoped
public class ArtistResource {

    @Inject
    Artist artist;

    @GET
    @Path("{artistId}")
    @Template("base.html")
    public ArtistResponse index(@PathParam("artistId") int artistId) {

        // TODO validation

        return artist.getArtist(artistId);
    }
}
