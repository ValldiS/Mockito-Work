package netology.sender;

import netology.entity.Country;
import netology.entity.Location;
import netology.geo.GeoService;
import netology.geo.GeoServiceImpl;
import netology.i18n.LocalizationService;
import netology.i18n.LocalizationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplMockTest {

    @Mock
    GeoService geoService = Mockito.mock(GeoServiceImpl.class);
    @Mock
    LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);




    //Проверка отправления сообщения на русском  если ip  начинается с 172.
    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.0.43.11", "172.1.32.13", "172.4.32.11",})
    void test_Russian_massage(String id) {

        Mockito.when(geoService.byIp(id))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, id);
        String expected =  "Добро пожаловать";
        String real = messageSender.send(headers);

        Assertions.assertEquals(real, expected);
    }

    //Проверка отправления сообщения на английском  если ip  начинается с 96.
    @ParameterizedTest
    @ValueSource(strings = {"96.44.183.149", "96.47.183.148", "96.55.999.149", "96.01.000.22"})
    void test_English_massage(String id) {

        Mockito.when(geoService.byIp(id))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, id);
        messageSender.send(headers);
        String expected = "Welcome";
        String real = messageSender.send(headers);
        Assertions.assertEquals(expected,real);
    }


}