package com.full.full.service;

import com.full.full.chat.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    Message getMessageById(Long id);
    Message saveMessage(Message message);
    void deleteMessage(Long id);
}
