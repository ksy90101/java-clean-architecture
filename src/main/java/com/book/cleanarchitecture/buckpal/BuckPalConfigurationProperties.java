package com.book.cleanarchitecture.buckpal;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "buckpal")
public class BuckPalConfigurationProperties {

    private final Long transferThreshold = Long.MAX_VALUE;

    public Long getTransferThreshold() {
        return transferThreshold;
    }
}
