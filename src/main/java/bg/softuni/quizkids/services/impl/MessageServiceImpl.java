package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.SendMessageBindingModel;
import bg.softuni.quizkids.models.entity.MessageEntity;
import bg.softuni.quizkids.repository.MessageRepository;
import bg.softuni.quizkids.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendMessageToContactWithUs(SendMessageBindingModel sendMessageBindingModel) {
        MessageEntity message = modelMapper.map(sendMessageBindingModel, MessageEntity.class);

        message.setCreatedOn(LocalDateTime.now());

        messageRepository.save(message);

        //TODO: Implement sending notification to Admins and Moderator for send message
    }
}
