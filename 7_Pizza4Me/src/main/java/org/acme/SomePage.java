package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import static java.util.Objects.requireNonNull;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/some-page")
@Produces(MediaType.TEXT_HTML)
public class SomePage {

    private final Template page;

    public SomePage(Template home) {
        this.page = requireNonNull(home, "page is required");
    }

    @GET
    public TemplateInstance get(@QueryParam("name") String name) {
        return page.data("name", name);
    }

}
