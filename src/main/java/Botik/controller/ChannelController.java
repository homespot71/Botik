package Botik.controller;

import Botik.model.Channel;
import Botik.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping
    public List<Channel> listAllChannel() {
        return channelService.listAllChannel();
    }

    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable(name = "id") long id) {
        try {
            return channelService.getChannel(id);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

    @PostMapping
    public void createChannel(@RequestBody Channel channel) {
        try {
            channelService.createChannel(channel);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

    @PutMapping
    public Channel updateChannel(@RequestBody Channel channel) {
        try {
            return channelService.updateChannel(channel);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable(name = "id") long id) {
        try {
            channelService.deleteChannel(id);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }
    }

}
