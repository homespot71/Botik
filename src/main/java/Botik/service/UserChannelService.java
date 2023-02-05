package Botik.service;

import Botik.model.Channel;
import Botik.model.User;
import Botik.model.UserChannel;

import java.util.List;


public interface UserChannelService {

    List<UserChannel> listAllUserChannel();

    UserChannel getUserChannel(long id);

    UserChannel createUserChannel(UserChannel userChannel);


    UserChannel updateUserChannel(UserChannel userChannel);


    void deleteUserChannel(long id);


    void addSubscription(User user, Channel channel);


    List<Channel> findAllChannelByUser(User user);


    List<UserChannel> findAllUserChannelsByChannelIdFromIdsList(List<Long> channelIds);

}
