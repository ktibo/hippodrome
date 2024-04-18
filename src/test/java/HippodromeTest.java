import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    void testMessageIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }
    @Test
    void testEmptyListIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
    }
    @Test
    void testMessageEmptyListIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @Test
    void getHorses() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse #"+String.valueOf(i), i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertIterableEquals(horses, hippodrome.getHorses());

    }

    @Test
    void move() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horseMock : hippodrome.getHorses()) {
            Mockito.verify(horseMock).move();
        }

    }

    @Test
    void getWinner() {

        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse #1", 1, 1.0));
        horses.add(new Horse("Horse #2", 1, 2.0));
        horses.add(new Horse("Horse #3", 1, 4.0));
        horses.add(new Horse("Horse #4", 1, 3.0));

        Horse horseMax = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horseMax, hippodrome.getWinner());

    }

}