package ru.gorinych3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gorinych3.models.MessagesLSP;
import java.util.Date;
import java.util.Set;

@Repository
public interface MessRepository extends JpaRepository<MessagesLSP, Long> {


    @Transactional
    @Procedure(name = "getNewMessages", procedureName = "dbo.getNewMessages")
    @Query(value = "EXECUTE getNewMessages", nativeQuery = true)
    Set<MessagesLSP> getMessagesLSP();


    @Query(value = "SELECT * from MessagesLSP m where m.status = 'N'",
            nativeQuery = true)
    Set<MessagesLSP> getAllMessages();


    @Query
    Set<MessagesLSP> findMessagesLSPSByStatus(String status);


    @Modifying
    @Query(value = "UPDATE MessagesLSP  SET status = ?, lastProcessing = ? where id = ?", nativeQuery = true)
    void updateStatusLastProcessing(String status, Date lastProcessing, long id);



}
