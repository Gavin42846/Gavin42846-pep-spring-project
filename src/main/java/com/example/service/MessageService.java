package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message) throws Exception {
        if(message.getMessageText() == null || message.getMessageText().isEmpty()) {
            throw new Exception("Message cannot be blank");
        }
        if(message.getMessageText().length() > 255) {
            throw new Exception("Message text cannot be longer than 255 characters");
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    public int deleteMessage(Integer messageId) {
        if(messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1;
        } else return 0;
    }
    public int updateMessage(Integer messageId, String messageText) {
        if(messageRepository.existsById(messageId)) {
            Message message = messageRepository.findById(messageId).get();
            message.setMessageText(messageText);
            messageRepository.save(message);
            return 1;
        }
        else return 0;
    }


    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
