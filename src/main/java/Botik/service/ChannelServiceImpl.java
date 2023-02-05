package Botik.service;

import Botik.model.Channel;
import Botik.repository.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChannelServiceImpl implements ChannelService {
    private final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);
    @Autowired
    private ChannelRepository channelRepository;


    @Override
    public List<Channel> listAllChannel() {
        return channelRepository.findAll();
    }


    @Override
    public List<Channel> listAllChannelById(List<Long> listId) {
        return channelRepository.findAllById(listId);
    }


    @Override
    public Channel getChannel(long id) {
        if (channelRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Wrong id");
        }
        return channelRepository.findById(id).orElse(null);
    }


    @Override
    public Channel createChannel(Channel channel) {
        if (channel.getGroupId() == 0 || channel.getName().isEmpty()) {
            throw new IllegalArgumentException("Please enter correct info");
        }
        return channelRepository.save(channel);
    }


    @Override
    public Channel updateChannel(Channel channel) {
        if (channel.getId() <= 0 || channelRepository.findById(channel.getId()).isEmpty() || channel.getName().isEmpty() || channelRepository.findById(channel.getGroupId()).isEmpty()) {
            throw new IllegalArgumentException("Wrong ID");
        }
        long channelId = channel.getId();
        Channel entity = channelRepository.findById(channelId).orElse(null);
        if (entity != null) {
            entity.setName(entity.getName());
            entity.setGroupId(entity.getGroupId());
            channelRepository.save(entity);
        }
        return entity;
    }


    @Override
    public void deleteChannel(long id) {
        if (channelRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Wrong" + id);
        }
        channelRepository.delete(getChannel(id));
    }


    @Override
    public List<Long> findAllIdsByChannelIdFromUniqueChannelIdsList(List<Long> channelIds) {
        return channelRepository.findAllIdsByChannelIdFromUniqueChannelIdsList(channelIds);
    }
}
