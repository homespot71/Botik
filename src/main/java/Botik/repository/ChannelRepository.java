package Botik.repository;

import Botik.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("SELECT ch.id FROM Channel ch WHERE ch.groupId IN :channelIds")
    List<Long> findAllIdsByChannelIdFromUniqueChannelIdsList(@Param("channelIds") List<Long> channelIds);
}
