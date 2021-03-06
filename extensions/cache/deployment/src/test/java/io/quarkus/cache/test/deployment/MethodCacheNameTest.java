package io.quarkus.cache.test.deployment;

import static org.junit.jupiter.api.Assertions.fail;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.DeploymentException;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.cache.CacheName;
import io.quarkus.test.QuarkusUnitTest;

public class MethodCacheNameTest {

    @RegisterExtension
    static final QuarkusUnitTest TEST = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class).addClasses(CachedService.class))
            .setExpectedException(DeploymentException.class);

    @Test
    public void shouldNotBeInvoked() {
        fail("This method should not be invoked");
    }

    @ApplicationScoped
    static class CachedService {

        @CacheName("illegal-annotation-target")
        public Object getObject(Object key) {
            return new Object();
        }
    }
}
