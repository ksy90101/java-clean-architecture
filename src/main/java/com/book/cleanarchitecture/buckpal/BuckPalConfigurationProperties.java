package com.book.cleanarchitecture.buckpal;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "buckpal")
public class BuckPalConfigurationProperties {

    private Long transferThreshold = Long.MAX_VALUE;

    public Long getTransferThreshold() {
        return transferThreshold;
    }

    public void setTransferThreshold(Long transferThreshold) {
        this.transferThreshold = transferThreshold;
    }
}
