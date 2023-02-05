package Botik.service;

import Botik.model.Message;

import java.util.List;


public interface MessageService {

    List<Message> findAllMessage();


    Message getMessage(long id);


    void addMessage(String groupTitle, long groupId, String text, String userName);

    void deleteMessage(long id);


    List<Message> listAllNewMessages();


    List<Message> getMessagesAndMarkThemOld();

}
