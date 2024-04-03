package netology.geo;

import netology.entity.Country;
import netology.entity.Location;
import netology.geo.GeoServiceImpl;

import netology.i18n.LocalizationService;
import netology.i18n.LocalizationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {






    //Проверка определения локации по  ip.
    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11"})
    void correct_ip(String ip) {
        GeoService geoService = new GeoServiceImpl();

        Location expected  = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location = geoService.byIp(ip);

        Assertions.assertEquals(expected.getStreet(), location.getStreet());

    }
}