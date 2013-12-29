package net.isucon.isucon2.infra;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS有効化
 *
 * @see http://backpaper0.github.io/2013/05/02/jaxrs.html
 */
@ApplicationPath("rest")
public class JaxrsActivator extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public JaxrsActivator() {
        singletons.add(new ThymeleafMessageBodyWriter());
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> defaults = super.getSingletons();
        singletons.addAll(defaults);
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        classes.add(net.isucon.isucon2.resource.AdminResource.class);
        classes.add(net.isucon.isucon2.resource.ArtistResource.class);
        classes.add(net.isucon.isucon2.resource.BuyResource.class);
        classes.add(net.isucon.isucon2.resource.ListResource.class);
        classes.add(net.isucon.isucon2.resource.TicketResource.class);
        return classes;
    }
}
