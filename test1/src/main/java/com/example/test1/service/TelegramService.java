package com.example.test1.service;

import com.example.test1.config.BotConfig;
import com.example.test1.model.User;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.util.List;

@Component
public class TelegramService extends TelegramLongPollingBot {
    final BotConfig config;
    private final UserService userService;



    public TelegramService(BotConfig config, UserService userService) {
        this.config = config;
        this.userService = userService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String textMessage = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            switch (textMessage) {
                case ("/start"):
                    try {
                        startCommandReceived(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                        sendMessage(chatId,"Sorry, unknown command");



            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken(){
        return config.getToken();
    }




    private void startCommandReceived(Long chatId) throws TelegramApiException {

            List<User> answer = userService.findAll();
            for(User userInfo: answer){
                String message = "ФИО: " + userInfo.getFIO()+"\n"+
                        "Email: " + userInfo.getEmail() + "\n"+
                        "Phone Number: " + userInfo.getPhoneNumber() + "\n"+
                        "Date: " + userInfo.getDateTime();
                sendMessage(chatId, message);


            }

    }
    public void sendUsersByOne(User user){
        Long chatId = 489800742L;
        String message = "Добавлен новый полльзователь: \n"+
                "ФИО: " + user.getFIO()+"\n"+
                "Email: " + user.getEmail() + "\n"+
                "Phone Number: " + user.getPhoneNumber() + "\n"+
                "Date: " + user.getDateTime();
        sendMessage(chatId, message);
    }

    private void sendMessage(Long chatId, String message)  {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try{
        execute(sendMessage);}
        catch (TelegramApiException e){}
    }
}
