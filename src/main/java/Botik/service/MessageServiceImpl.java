package Botik.service;

import Botik.model.Message;
import Botik.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {
    private final Logger log = LoggerFactory.getLogger(MessageService.class);


    @Autowired
    private MessageRepository telegramMessageRepository;

    @Override
    public List<Message> findAllMessage() {
        return telegramMessageRepository.findAll();
    }


    @Override
    public Message getMessage(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Incorrect id");
        }
        return telegramMessageRepository.findById(id).orElse(null);
    }


    @Override
    public void addMessage(String groupTitle, long groupId, String text, String userName) {
        Message telegramMessage = new Message(groupTitle, groupId, text, userName);
        telegramMessageRepository.save(telegramMessage);
    }


    @Override
    public void deleteMessage(long id) {
        telegramMessageRepository.deleteById(id);
    }

    @Override
    public List<Message> listAllNewMessages() {
        return telegramMessageRepository.findAllNewMessages();
    }


    @Override
    public List<Message> getMessagesAndMarkThemOld() {
        List<Message> messages = telegramMessageRepository.findAllNewMessages();
        if (!messages.isEmpty()) {
            List<Long> idsList = messages.stream().map(Message::getId).toList();
            telegramMessageRepository.changeIsNewToFalse(idsList);
        }
        return messages;
    }
}
