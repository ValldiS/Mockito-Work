package netology.i18n;

import netology.entity.Country;
import netology.entity.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {




    @Test
    void correct_Rus_message() {
        String expected = "Добро пожаловать";
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        LocalizationService localizationService = new LocalizationServiceImpl();


        Assertions.assertEquals(localizationService.locale(location.getCountry()),expected);
    }

    @Test
    void correct_Eng_message() {
        String expected = "Welcome";
        Location location = new Location("New York", Country.USA, null, 0);
        LocalizationService localizationService = new LocalizationServiceImpl();

        Assertions.assertEquals(expected,localizationService.locale(location.getCountry()));

    }

}