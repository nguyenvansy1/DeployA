package com.example.becapstone1.repository;

import com.example.becapstone1.model.event.EventUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface IEventUserRepository extends JpaRepository<EventUser, Long> {

    @Query(value = "select * from event_user\n" +
            "where user_id =:id", nativeQuery = true)
    Page<EventUser> getListEventByUser(@Param("id") Long id, Pageable pageable);

    @Query(value = "select * from event_user\n" +
            "where event_id = :id", nativeQuery = true)
    Page<EventUser> getListUserByEvent(@Param("id") Long id, Pageable pageable);

    @Query(value = "select * from event_user\n" +
            "where event_id = :id", nativeQuery = true)
    List<EventUser> getListUserByEvent1(@Param("id") Long id);

    @Query(value = "SELECT * FROM event_user where event_id = :idEvent and user_id= :idUser and event_user_status = 0;", nativeQuery = true)
    EventUser getEventUserByEventAndUser(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);

    @Modifying
    @Query(value = "insert into event_user (event_time_checkin,event_user_status,event_id,user_id) values (?1,1,?2,?3)", nativeQuery = true)
    void addEventUser(String time, Long id, Long code);
}
