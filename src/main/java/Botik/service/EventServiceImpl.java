package Botik.service;

import Botik.model.Event;
import Botik.model.Message;
import Botik.model.UserChannel;
import Botik.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventServiceImpl implements EventService {
    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserChannelService userChannelService;

    @Autowired
    private ChannelService channelService;

    @Override
    public List<Event> listAllEvent() {
        return eventRepository.findAll();
    }


    @Override
    public Event getEvent(long id) {
        return eventRepository.findById(id).orElse(null);
    }


    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public Event updateEvent(Event event) {
        if (eventRepository.findById(event.getId()).isEmpty()) {
            throw new IllegalArgumentException("Wrong id");
        }
        long eventId = event.getId();
        Event entity = eventRepository.findById(eventId).orElse(null);
        if (entity != null) {
            entity.setEventForChannel(entity.getEventForChannel());
            eventRepository.save(entity);
        }
        return entity;
    }


    @Override
    public void deleteEvent(long id) {
        if (eventRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Wrong id");
        }
        eventRepository.delete(getEvent(id));
    }


    @Scheduled(fixedRateString = "${my.fixed.delay}")
    @Override
    public void createNewEvent() {
        List<Message> messages = messageService.getMessagesAndMarkThemOld();
        if (messages.isEmpty()) {
            return;
        }
        List<Long> uniqueChannelIdsFromMessages = messages.stream().map(Message::getGroupId).distinct().toList();
        List<Long> channelIds = channelService.findAllIdsByChannelIdFromUniqueChannelIdsList(uniqueChannelIdsFromMessages);
        List<UserChannel> allUserChannelsByChannelIdFromIdsList = userChannelService
                .findAllUserChannelsByChannelIdFromIdsList(channelIds);
        String text = "You have a new messages";
        allUserChannelsByChannelIdFromIdsList.forEach(userChannel -> createEvent(new Event(userChannel.getUserId(), userChannel.getChannelId(), text)));
    }


    @Override
    public List<Event> getNewEventsByUser(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Please give correct ID");
        }
        List<Event> allNewEventsByUserId = eventRepository.findAllByUserId(id);
        allNewEventsByUserId.forEach(ev -> ev.setNew(false));
        return eventRepository.saveAll(allNewEventsByUserId);

    }


    @Override
    public List<Event> findAllByUserId(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Wrong ID");
        }
        return eventRepository.findAllByUserId(userId);
    }

}

