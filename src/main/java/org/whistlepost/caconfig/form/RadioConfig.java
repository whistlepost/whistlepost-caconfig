package org.whistlepost.caconfig.form;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(name = "radio")
public @interface RadioConfig {

    String[] values();
}
