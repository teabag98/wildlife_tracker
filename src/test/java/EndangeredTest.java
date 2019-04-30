import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EndangeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Endangered_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered("wolf", "sick", "old");
        assertEquals(true, testEndangered instanceof Endangered);
    }

    @Test
    public void getHealth_returnsHealthAttribute_true() {
        Endangered testEndangered = new Endangered("wolf", "sick", "old");
        assertEquals("Healthy", testEndangered.getHealth());
    }

    @Test
    public void save_assignsIdAndSavesObjectToDatabase() {
        Endangered testEndangered = new Endangered("wolf", "sick", "old");
        testEndangered.save();
        Endangered savedEndangered = Endangered.all().get(0);
        assertEquals(testEndangered.getId(), savedEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangered_true() {
        Endangered firstEndangered = new Endangered("wolf", "sick", "old");
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("wolf", "sick", "old");
        secondEndangered.save();
        assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
        assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Endangered firstEndangered = new Endangered("wolf", "sick", "old");
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("wolf", "sick", "old");
        secondEndangered.save();
        assertEquals(Endangered.find(secondEndangered.getId()), secondEndangered);
    }

    @Test
    public void update_updatesHealthAttribute_true() {
        Endangered testEndangered = new Endangered("wolf", "sick", "old");
        testEndangered.save();
        testEndangered.updateHealth("ill");
        assertEquals("ill", Endangered.find(testEndangered.getId()).getHealth());
    }

    @Test
    public void update_updatesAgeAttribute_true() {
        Endangered testEndangered = new Endangered("wolf", "sick", "old");
        testEndangered.save();
        testEndangered.updateAge("old");
        assertEquals("old", Endangered.find(testEndangered.getId()).getAge());
    }

}