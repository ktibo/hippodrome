import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {


    @Test
    void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.0));
    }

    @Test
    void testMessageIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.0));
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "\n", "\t" })
    void testEmptyStringIllegalArgumentException(String str) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(str, 0.0));
    }
    @ParameterizedTest
    @ValueSource(strings = { "", " ", "\n", "\t" })
    void testMessageEmptyStringIllegalArgumentException(String str) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(str, 0.0));
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @Test
    void testNegativeSpeedIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("A", -1.0));
    }
    @Test
    void testMessageNegativeSpeedIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("A", -1.0));
        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }
    @Test
    void testNegativeDistanceIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("A", 1.0, -1.0));
    }
    @Test
    void testMessageNegativeDistanceIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("A", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }


    @Test
    void getName() {

        String str = "A";
        assertEquals(str, new Horse(str, 0.0).getName());

    }

    @Test
    void getSpeed() {
        int speed = 10;
        assertEquals(speed, new Horse("A", speed).getSpeed());
    }

    @Test
    void getDistance() {
        double distance = 10.0;
        assertEquals(distance, new Horse("A", 1, distance).getDistance());
    }
    @Test
    void getNoDistance() {
        assertEquals(0.0, new Horse("A", 1).getDistance());
    }

    @Test
    void moveGetRandomDouble() {
        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            new Horse("A", 1).move();
            utilities.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = { 0.2, 0.5, 0.9 })
    void move(double d) {

        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            utilities.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(d);
            double initialDistance = 10.0;
            Horse horse = new Horse("A", 1, initialDistance);
            horse.move();
            double expected = initialDistance + horse.getSpeed()*d;
            assertEquals(expected, horse.getDistance());
        }

    }

}