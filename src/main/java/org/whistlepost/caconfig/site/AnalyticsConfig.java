package org.whistlepost.caconfig.site;

import org.apache.sling.caconfig.annotation.Configuration;

/**
 * Various analytics tools provide the ability to track user engagement
 * on websites. With this site configuration feature you can select
 * your preferred analytics provider that is then easily embeddable across
 * your entire site.
 */
@Configuration(name = "analytics")
public @interface AnalyticsConfig {

    String provider();

    String trackingId();
}
