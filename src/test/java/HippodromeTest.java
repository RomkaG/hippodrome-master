import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullHorseException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void emptyHorseException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorse(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i =1; i<30; i++){
            horses.add(new Horse(" " + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i<50; i++){
            horses.add(mock(Horse.class));
        }
       new Hippodrome(horses).move();

        for (Horse horse: horses){
            verify(horse).move();
        }
    }

    @Test
    public  void getWinner(){
        Horse horse1 = new Horse("horse1", 2, 30);
        Horse horse2 = new Horse("horse2", 3, 10);
        Horse horse3 = new Horse("horse3", 1, 25);
        Horse horse4 = new Horse("horse4", 5, 15);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        assertSame(horse1, hippodrome.getWinner());
    }
}
