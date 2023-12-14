package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.SendMessageBindingModel;
import bg.softuni.quizkids.models.dto.MessageEntityDTO;

import java.util.List;

public interface MessageService {
    void sendMessageToContactWithUs(SendMessageBindingModel sendMessageBindingModel);

    List<MessageEntityDTO> getAllMessages();

    void readMessage(Long id);

    long countByUserIdAndIsRead();
}
