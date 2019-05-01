import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class AnimalsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_false() {
        Animal testAnimal = new Animal("lionessr");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Deer() {
        Animal testAnimal = new Animal("lion");
        assertEquals("lion", testAnimal.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        Animal firstAnimal = new Animal("lion");
        Animal anotherAnimal = new Animal("lion");
        assertTrue(firstAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_assignsIdToObjectAndSavesObjectToDatabase() {
        Animal testAnimal = new Animal("hyena");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_false() {
        Animal firstAnimal = new Animal("hyena");
        firstAnimal.save();
        Animal secondAnimal = new Animal("panther");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal("panther");
        firstAnimal.save();
        Animal secondAnimal = new Animal("panther");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void delete_deletesAnimalFromDatabase_0() {
        Animal testAnimal = new Animal("panther");
        testAnimal.save();
        testAnimal.delete();
        assertEquals(0, Animal.all().size());
    }

    public void updateName_updatesAnimalNameInDatabase_String() {
        Animal testAnimal = new Animal("panther");
        testAnimal.save();
        testAnimal.updateName("panther");
        assertEquals("panther", testAnimal.getName());
    }

}