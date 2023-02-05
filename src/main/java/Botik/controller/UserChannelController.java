package Botik.controller;

import Botik.model.Channel;
import Botik.model.UserChannel;
import Botik.service.ChannelService;
import Botik.service.UserChannelService;
import Botik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user_channels")
public class UserChannelController {

    @Autowired
    private UserChannelService userChannelService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;


    @GetMapping
    public List<UserChannel> listAllUserChannel() {
        return userChannelService.listAllUserChannel();
    }

    @GetMapping("/{id}")
    public UserChannel getUserChannel(@PathVariable(name = "id") long id) {
        try {
            return userChannelService.getUserChannel(id);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }

    }

    @PostMapping
    public void createUserChannel(@RequestBody UserChannel userChannel) {
        try {
            userChannelService.createUserChannel(userChannel);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, exception.getLocalizedMessage());
        }
    }

    @PutMapping
    public UserChannel updateUserChannel(@RequestBody UserChannel userChannel) {
        try {
            return userChannelService.updateUserChannel(userChannel);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getLocalizedMessage());
        }

    }

    @DeleteMapping("/{id}")
    public void deleteUserChannel(@PathVariable(name = "id") long id) {
        try {
            userChannelService.deleteUserChannel(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }
    }

    @PostMapping("/{userId}/{channelId}")
    public void addSubscription(@PathVariable(name = "userId") long userId, @PathVariable(name = "channelId") long channelId) {
        try {
            userChannelService.addSubscription(userService.getUser(userId), channelService.getChannel(channelId));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }

    }

    @GetMapping("/{userId}")
    public List<Channel> findAllChannelByUser(@PathVariable(name = "userId") long userId) {
        try {
            return userChannelService.findAllChannelByUser(userService.getUser(userId));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }

    }

}
