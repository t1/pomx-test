package test;

import javax.ws.rs.*;

@Path("/")
public class Boundary {
    @GET public String get() { return "hi"; }
}
