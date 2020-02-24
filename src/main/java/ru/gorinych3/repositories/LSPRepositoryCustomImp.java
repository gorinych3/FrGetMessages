package ru.gorinych3.repositories;

import org.springframework.stereotype.Repository;
import ru.gorinych3.models.MessagesLSP;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class LSPRepositoryCustomImp implements LSPRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MessagesLSP> getMessagesLSP() {
        Query query = entityManager.createNamedQuery("getNewMessages");
        return  query.getResultList();
    }
}
