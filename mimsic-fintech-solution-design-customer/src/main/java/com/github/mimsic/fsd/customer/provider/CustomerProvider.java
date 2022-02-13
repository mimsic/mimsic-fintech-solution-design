package com.github.mimsic.fsd.customer.provider;

import com.github.mimsic.base.ignite.provider.IgniteProvider;
import com.github.mimsic.fsd.customer.persistence.entity.Customer;
import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerProvider extends IgniteProvider<Long, Customer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProvider.class);

    public CustomerProvider(Ignite ignite, String qualifier) {

        super(ignite, qualifier, () -> {
            String cacheName = "Customer";
            if (qualifier != null) {
                cacheName += "." + qualifier;
            }
            CacheConfiguration<Long, Customer> cacheConfiguration = new CacheConfiguration<>(cacheName);
            cacheConfiguration.setBackups(1);
            return cacheConfiguration;
        });
    }

    public void clear() {
        cache().clear();
        LOGGER.info("Cache cleared");
    }
}
