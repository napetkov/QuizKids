package bg.softuni.quizkids.services;

import bg.softuni.quizkids.models.binding.SendMessageBindingModel;

public interface MessageService {
    void sendMessageToContactWithUs(SendMessageBindingModel sendMessageBindingModel);
}
