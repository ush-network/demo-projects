package leaderelectionpattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "kubernetes.leader-election")
class LeaderElectionProperties {
    private boolean debugMode = false;
    private String namespace = "${K8S_NAMESPACE}";
    private String endPointLockName = "myapplication-leader-election";
    private String lockHolderIdentityName = "${HOSTNAME}";
    private long leaseDurationMs = 10000;
    private long renewDeadlineMs = 8000;
    private long retryPeriodMs = 2000;
}
