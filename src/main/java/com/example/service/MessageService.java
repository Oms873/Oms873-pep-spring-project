package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public boolean isUserExists(Integer accountId) {
        return accountRepository.existsById(accountId);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public int deleteMessage(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1; // Indicates one row was deleted
        }
        return 0; // Indicates no rows were deleted
    }

    public int updateMessageText(Integer messageId, String newMessageText) {
        if (messageRepository.existsById(messageId)) {
            Message message = messageRepository.findById(messageId).orElseThrow();
            message.setMessageText(newMessageText);
            messageRepository.save(message);
            return 1; // One row updated
        }
        return 0; // No rows updated
    }

    public List<Message> getMessagesByUser(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
