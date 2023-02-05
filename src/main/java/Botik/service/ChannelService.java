package Botik.service;

import Botik.model.Channel;

import java.util.List;

public interface ChannelService {

    List<Channel> listAllChannel();

    List<Channel> listAllChannelById(List<Long> listId);


    Channel getChannel(long id);


    default Channel createChannel(Channel channel) {
        return null;
    }

    Channel updateChannel(Channel channel);


    void deleteChannel(long id);


    List<Long> findAllIdsByChannelIdFromUniqueChannelIdsList(List<Long> channelIds);
}
