import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    public void nullNameException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMessage(){
        try {
            new Horse(null, 1, 1);
            fail();
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "\t\t", "\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void negativeSpeedException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("testName", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistanceException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("testName", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName(){
        Horse horse = new Horse("nameHorse", 1, 1);
        assertEquals("nameHorse", horse.getName());
    }

    @Test
    public void getSpeed(){
        Horse horse = new Horse("nameHorse", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistance(){
        Horse horse = new Horse("nameHorse", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    public void zeroDistance(){
        Horse horse = new Horse("nameHorse", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveUseRandomDouble(){
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("nameHorse", 1, 1).move();
            mockedStatic.verify(()->Horse.getRandomDouble(0.2, 0.9));
        }
    }

}
