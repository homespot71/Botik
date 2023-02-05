package Botik.repository;

import Botik.model.UserChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {

    @Query("SELECT uc FROM UserChannel uc WHERE uc.userId = :userId")
    List<UserChannel> findByUserId(@Param("userId") long userId);


    @Query("SELECT uc FROM UserChannel uc WHERE uc.channelId IN :channelIds")
    List<UserChannel> findAllUserChannelsByChannelIdFromIdsList(@Param("channelIds") List<Long> channelIds);

}

