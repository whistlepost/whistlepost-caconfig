package org.whistelpost.caconfig.form;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.sling.junit5.SlingContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.whistelpost.caconfig.AbstractConfigTest;
import org.whistlepost.caconfig.form.InputConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SlingContextExtension.class)
public class InputConfigTest extends AbstractConfigTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();

        context.load().json("/SLING-INF/content/wp.json", "/conf/wp");
        context.load().json("/SLING-INF/content/wp/form.json", "/conf/wp/form");
        context.load().json("/SLING-INF/content/wp/form/textfield/email.json", "/conf/wp/form/textfield/email");
        context.load().json("/SLING-INF/content/wp/form/textfield/name.json", "/conf/wp/form/textfield/name");
        context.load().json("/SLING-INF/content/wp/form/textfield/url.json", "/conf/wp/form/textfield/url");
        context.load().json("/SLING-INF/content/wp/form/temporal/time.json", "/conf/wp/form/temporal/time");
        context.load().json("/SLING-INF/content/wp/form/temporal/datetime-local.json", "/conf/wp/form/temporal/datetime-local");

        context.create().resource("/content/example/form/email", "sling:configRef", "/conf/wp/form/textfield/email");
        context.create().resource("/content/example/form/name", "sling:configRef", "/conf/wp/form/textfield/name");
        context.create().resource("/content/example/form/url", "sling:configRef", "/conf/wp/form/textfield/url");
        context.create().resource("/content/example/form/time", "sling:configRef", "/conf/wp/form/temporal/time");
        context.create().resource("/content/example/form/datetime-local", "sling:configRef", "/conf/wp/form/temporal/datetime-local");
    }

    @Test
    public void testConfigEmailValidation() {
        context.currentResource("/content/example/form/email");

        Resource resource = context.request().getResource();
        InputConfig config = resource.adaptTo(ConfigurationBuilder.class).as(InputConfig.class);
        assertNotNull(config);
        assertEquals("email", config.type());
    }

    @Test
    public void testConfigNameValidation() {
        context.currentResource("/content/example/form/name");

        Resource resource = context.request().getResource();
        InputConfig config = resource.adaptTo(ConfigurationBuilder.class).as(InputConfig.class);
        assertNotNull(config);
        assertEquals("text", config.type());
        assertEquals("[\\p{L} -]+", config.pattern());
    }

    @Test
    public void testConfigUrlValidation() {
        context.currentResource("/content/example/form/url");

        Resource resource = context.request().getResource();
        InputConfig config = resource.adaptTo(ConfigurationBuilder.class).as(InputConfig.class);
        assertNotNull(config);
        assertEquals("url", config.type());
    }

    @Test
    public void testConfigTimeValidation() {
        context.currentResource("/content/example/form/time");

        Resource resource = context.request().getResource();
        InputConfig config = resource.adaptTo(ConfigurationBuilder.class).as(InputConfig.class);
        assertNotNull(config);
        assertEquals("time", config.type());
    }

    @Test
    public void testConfigDatetimeLocalValidation() {
        context.currentResource("/content/example/form/datetime-local");

        Resource resource = context.request().getResource();
        InputConfig config = resource.adaptTo(ConfigurationBuilder.class).as(InputConfig.class);
        assertNotNull(config);
        assertEquals("datetime-local", config.type());
    }
}
