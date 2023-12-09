package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.binding.SendMessageBindingModel;
import bg.softuni.quizkids.models.entity.MessageEntity;
import bg.softuni.quizkids.repository.MessageRepository;
import bg.softuni.quizkids.services.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class MessageServiceImplTest {
    @Autowired
    private MessageService serviceToTest;
    @Autowired
    private MessageRepository mockMessageRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        serviceToTest = new MessageServiceImpl(mockMessageRepository, modelMapper);
    }

    @Test
    void testSendingMessageToContactWith() {
        SendMessageBindingModel newMessageBindingModel = creatTestMessageBindingModel();

        serviceToTest.sendMessageToContactWithUs(newMessageBindingModel);

        List<MessageEntity> massages = mockMessageRepository.findAll();

        Assertions.assertTrue(massages.stream().map(
                MessageEntity::getName).toList().contains(newMessageBindingModel.getName()),
                "The name is not mapped properly!");
        Assertions.assertTrue(massages.stream().map(
                MessageEntity::getText).toList().contains(newMessageBindingModel.getText()),
                "The text message is not mapped properly!");
        Assertions.assertTrue(massages.stream().map(
                MessageEntity::getEmail).toList().contains(newMessageBindingModel.getEmail()),
                "The email is not mapped properly!");
        Assertions.assertTrue(massages.stream().map(
                MessageEntity::getPhoneNumber).toList().contains(newMessageBindingModel.getPhoneNumber()),
                "The phone number is not mapped properly!");


    }

    private SendMessageBindingModel creatTestMessageBindingModel() {
        SendMessageBindingModel newMessage = new SendMessageBindingModel();
        newMessage.setName("name");
        newMessage.setEmail("test@example.com");
        newMessage.setText("test text text");
        newMessage.setPhoneNumber("+359 888 888 888");


        return newMessage;
    }


}