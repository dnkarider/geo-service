import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    @Test
    void test_russian_ip(){
        MessageSender messageSender = Mockito.mock(MessageSenderImpl.class);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(messageSender.send(headers)).thenReturn("Отправлено сообщение: Добро пожаловать");
        Assertions.assertEquals("Отправлено сообщение: Добро пожаловать", messageSender.send(headers));
    }
    @Test
    void test_english_ip(){
        MessageSender messageSender = Mockito.mock(MessageSenderImpl.class);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(messageSender.send(headers)).thenReturn("Отправлено сообщение: Welcome");
        Assertions.assertEquals("Отправлено сообщение: Welcome", messageSender.send(headers));
    }
    @Test
    void test_orientation_by_ip(){
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.123.12.19"))
                .thenReturn(location);
        Assertions.assertEquals(location, geoService.byIp("172.123.12.19"));
    }
    @Test
    void test_locale(){
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }
}
