package leaderelectionpattern;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
class HouseKeepingJob {
    private final LeaderElectionHandler leaderElectionHandler;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void executeScheduledCode() {
        if (leaderElectionHandler.isLeader()) {
            log.info("execute scheduled code only on leader instance.");
        }
    }
}
