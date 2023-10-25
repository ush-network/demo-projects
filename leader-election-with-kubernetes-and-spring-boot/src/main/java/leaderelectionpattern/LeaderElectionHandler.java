package leaderelectionpattern;

import io.kubernetes.client.extended.leaderelection.LeaderElectionConfig;
import io.kubernetes.client.extended.leaderelection.LeaderElector;
import io.kubernetes.client.extended.leaderelection.resourcelock.EndpointsLock;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
class LeaderElectionHandler implements ApplicationListener<ApplicationReadyEvent> {
    private final LeaderElectionProperties properties;

    private LeaderElector leaderElector;
    private Thread electorThread;
    private boolean leader;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Initializing Leader Election process. [namespace='{}' endPointLockName='{}', " +
            "lockHolderIdentityName='{}', leaseDurationMs='{}', renewDeadLineMs='{}', retryPeriodMs='{}']",
            properties.getNamespace(), properties.getEndPointLockName(), properties.getLockHolderIdentityName(),
            properties.getLeaseDurationMs(), properties.getRenewDeadlineMs(), properties.getRetryPeriodMs());

        leaderElector = new LeaderElector(new LeaderElectionConfig(
            new EndpointsLock(
                properties.getNamespace(),
                properties.getEndPointLockName(),
                properties.getLockHolderIdentityName()),
            Duration.ofMillis(properties.getLeaseDurationMs()),
            Duration.ofMillis(properties.getRenewDeadlineMs()),
            Duration.ofMillis(properties.getRetryPeriodMs())));

        electorThread = new Thread(() -> leaderElector.run(() -> leader = true, () -> leader = false));
        electorThread.setName("k8s-leader-election");
        electorThread.setDaemon(true);
        electorThread.start();
    }

    @PreDestroy
    public void shutdown() {
        if (leaderElector != null) {
            var time = System.currentTimeMillis();
            log.info("processing leader election handler shutdown.");

            electorThread.interrupt();
            leaderElector.close();
            electorThread = null;

            log.info("Leader election handler shutdown completed in {} ms.", System.currentTimeMillis() - time);
        }
    }

    public boolean isLeader() {
        return leader;
    }
}
