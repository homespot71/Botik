package Botik.service;


import Botik.model.Channel;
import Botik.model.User;
import Botik.model.UserChannel;
import Botik.repository.UserChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class UserChannelServiceImpl implements UserChannelService {
    private final Logger log = LoggerFactory.getLogger(UserChannelServiceImpl.class);

    @Autowired
    private UserChannelRepository userChannelRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private UserService userService;


    @Override
    public List<UserChannel> listAllUserChannel() {
        return userChannelRepository.findAll();
    }


    @Override
    public UserChannel getUserChannel(long id) {
        if (userChannelRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("please enter correct id");
        }
        return userChannelRepository.findById(id).orElse(null);
    }


    @Override
    public UserChannel createUserChannel(UserChannel userChannel) {
        if (userChannel.getId() <= 0 || userChannel.getUserId() <= 0 || userChannel.getChannelId() <= 0) {
            throw new NoSuchElementException("Please enter correct info");
        }
        return userChannelRepository.save(userChannel);
    }


    @Override
    public UserChannel updateUserChannel(UserChannel userChannel) {
        if (userChannelRepository.findById(userChannel.getId()).isEmpty() || userChannelRepository.findById(userChannel.getChannelId()).isEmpty()) {
            throw new IllegalArgumentException("Wrong Id");
        }
        long id = userChannel.getId();
        UserChannel entity = userChannelRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setChannelId(entity.getChannelId());
            entity.setUserId(entity.getUserId());
            userChannelRepository.save(entity);
        }
        return entity;
    }


    @Override
    public void deleteUserChannel(long id) {
        if (userChannelRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Sorrt, not exist");
        }
        userChannelRepository.delete(getUserChannel(id));
    }


    @Override
    public void addSubscription(User user, Channel channel) {
        if (userChannelRepository.findById(user.getId()).isEmpty() || userChannelRepository.findById(channel.getId()).isEmpty()) {
            throw new IllegalArgumentException("Please try again");
        }
        userChannelRepository.save(new UserChannel(user.getId(), channel.getId()));
    }


    @Override
    public List<Channel> findAllChannelByUser(User user) {
        if (userChannelRepository.findById(user.getId()).isEmpty()) {
            throw new IllegalArgumentException("Please enter correct id");
        }
        List<Long> channelIds = userChannelRepository.findByUserId(user.getId()).stream()
                .map(UserChannel::getChannelId).collect(Collectors.toList());
        return channelService.listAllChannelById(channelIds);
    }


    @Override
    public List<UserChannel> findAllUserChannelsByChannelIdFromIdsList(List<Long> channelIds) {
        return userChannelRepository.findAllUserChannelsByChannelIdFromIdsList(channelIds);
    }

}
