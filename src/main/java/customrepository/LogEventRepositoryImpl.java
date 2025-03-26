package customrepository;

import entity.LogEvent;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LogEventRepositoryImpl implements LogEventRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public LogEventRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LogEvent> findLogEventsByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(LogEvent.class);
        var logEventRoot = criteriaQuery.from(LogEvent.class);
        criteriaQuery.select(logEventRoot)
                .where(criteriaBuilder.between(logEventRoot.get("timestamp"), startTime, endTime));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
