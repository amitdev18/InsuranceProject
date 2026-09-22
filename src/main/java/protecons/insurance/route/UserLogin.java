package protecons.insurance.route;

import org.apache.camel.builder.RouteBuilder;

public class UserLogin extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:login")
                .id("user-login");

    }
}
