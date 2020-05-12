package org.acme;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class ExampleResourceTest extends CamelTestSupport {
    @Override protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override public void configure() throws Exception {
                from("direct:sampleInput")
                    .log("Received Message is ${body} and Headers are ${headers}")
                    .to("mock:output");
            }
        };
    }

    @Test
    void name() throws InterruptedException {
        String expected = "Hello";

        MockEndpoint mock = getMockEndpoint("mock:output");
        mock.expectedBodiesReceived(expected);
        String input = "Hello";
        template.sendBody("direct:sampleInput", input);
        assertMockEndpointsSatisfied();
    }
}