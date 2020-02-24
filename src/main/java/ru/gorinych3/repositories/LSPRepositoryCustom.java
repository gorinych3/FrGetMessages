package ru.gorinych3.repositories;

import org.springframework.stereotype.Repository;
import ru.gorinych3.models.MessagesLSP;

import java.util.List;

@Repository
public interface LSPRepositoryCustom {

    List<MessagesLSP> getMessagesLSP();
}
