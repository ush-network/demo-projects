package pipeandfilterarchitecture.persistence;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
class DemoDataInitializer {
    private final EntityManager entityManager;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void importDemoData() {
        var time = System.currentTimeMillis();
        AtomicLong counter = new AtomicLong(0L);

        Currency.getAvailableCurrencies().stream()
            .map(currency -> CurrencyDefinitionEntity.builder()
                .uid(UUID.randomUUID())
                .code(currency.getCurrencyCode())
                .symbol(currency.getSymbol())
                .name(currency.getDisplayName(Locale.US))
                .build())
            .peek(c -> counter.getAndIncrement())
            .forEach(entityManager::persist);

        log.info("imported {} demo currency definitions in {} ms.", counter.get(), System.currentTimeMillis()-time);
    }
}