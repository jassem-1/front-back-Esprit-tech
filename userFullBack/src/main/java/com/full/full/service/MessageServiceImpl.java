package com.full.full.service;

import com.full.full.chat.Message;
import com.full.full.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MessageServiceImpl implements MessageService{
    public MessageServiceImpl(MessageRepo messageRepository) {
        this.messageRepository = messageRepository;
    }

    private final MessageRepo messageRepository;
    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.orElse(null);
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
