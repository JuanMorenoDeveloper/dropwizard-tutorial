package ve.com.proitc;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ve.com.proitc.health.TemplateHealthCheck;
import ve.com.proitc.resources.HelloWorldResource;

public class BasicApplication extends Application<BasicConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BasicApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-basic";
    }

    @Override
    public void initialize(final Bootstrap<BasicConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final BasicConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final HealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
