package test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.*;
import org.junit.runner.RunWith;

import java.io.*;
import java.net.URI;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

@Ignore
@RunWith(Arquillian.class)
public class BoundaryIT {

    @Deployment(testable = false)
    public static WebArchive deployment() {
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/pomx-test.war"));
    }

    @ArquillianResource private URI baseUri;

    @Test
    public void shouldGetHi() throws Exception {
        String response = read(baseUri);

        assertThat(response).isEqualTo("hi");
    }

    private static String read(URI uri) throws IOException {
        try (InputStream stream = uri.toURL().openConnection().getInputStream()) {
            return new Scanner(stream).useDelimiter("\\Z").next();
        }
    }
}
