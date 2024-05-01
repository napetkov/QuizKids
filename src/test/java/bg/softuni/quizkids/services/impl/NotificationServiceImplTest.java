package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository mockNotificationRepository;
    @Mock
    private UserServiceImpl mockUserService;
    private NotificationServiceImpl notificationServiceToTest;

    @BeforeEach
    void setUp() {
        notificationServiceToTest = new NotificationServiceImpl(mockNotificationRepository, mockUserService);
    }

    @Test
    public void testCountByUserIdAndIsRead() {
        long userId = 123L;
        when(mockNotificationRepository.countByUser_IdAndAndIsReadIsFalse(userId)).thenReturn(5L);

        long count = notificationServiceToTest.countByUserIdAndIsRead(userId);

        assert count == 5L;
        verify(mockNotificationRepository, times(1))
                .countByUser_IdAndAndIsReadIsFalse(userId);
    }

}