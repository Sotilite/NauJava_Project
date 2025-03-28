package ru.alexander.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.alexander.NauJava.repository.LevelRepository;
import ru.alexander.NauJava.repository.LogEventRepository;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final LogEventRepository logEventRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, LogEventRepository logEventRepository, PlatformTransactionManager transactionManager) {
        this.levelRepository = levelRepository;
        this.logEventRepository = logEventRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteLevelByTitle(String levelTitle) {
        var status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            var logEvents = logEventRepository.findAllByLevelTittle(levelTitle);
            for (var event : logEvents) {
                logEventRepository.delete(event);
            }
            levelRepository.delete(levelRepository.findByTitle(levelTitle));
            //levelRepository.deleteByTitle(levelTitle);
            transactionManager.commit(status);
        }
        catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
