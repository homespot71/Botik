package Botik.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class TelegramBot extends TelegramLongPollingBot implements Switchable {
    private final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    @Autowired
    private CommandService commandService;

    @Autowired
    private MessageService telegramMessageService;

    @Value("${bot.name: }")
    private String botName;

    @Value("${bot.token: }")
    private String botToken;

    private boolean enabled = true;

    public TelegramBot() {

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().isCommand()) {
            commandService.executeCommand(update.getMessage().getText(), this);
            return;
        }
        if (!enabled) {
            return;
        }
        Message message = update.getMessage();
        Chat chat = message.getChat();
        if (chat.isGroupChat() || chat.isSuperGroupChat()) {
            String title = chat.getTitle();
            Long groupId = message.getChatId();
            String text = message.getText();
            String userName = message.getFrom().getUserName();

            telegramMessageService.addMessage(title, groupId, text, userName);
        }
    }


    @Override
    public void on() {
        enabled = true;
    }


    @Override
    public void off() {
        enabled = false;
    }
}
