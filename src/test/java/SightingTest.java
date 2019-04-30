import org.junit.*;
import static org.junit.Assert.*;


public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("wolf");
        testAnimal.save();
        Sightings testSighting = new Sightings(testAnimal.getId(), "zone A", "eric");
        assertEquals(true, testSighting instanceof Sightings);
    }

    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sightings testSighting = new Sightings(testAnimal.getId(), "zone A", "ric");
        Sightings anotherSighting = new Sightings(testAnimal.getId(), "zone A", "eric rieeves");
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sightings testSighting = new Sightings (testAnimal.getId(), "zone A", "ric eric");
        testSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Animal testAnimal = new Animal("hyena");
        testAnimal.save();
        Sightings testSighting = new Sightings (testAnimal.getId(), "zone A", "ric man");
        testSighting.save();
        Animal secondTestAnimal = new Animal("koala");
        secondTestAnimal.save();
        Sightings secondTestSighting = new Sightings (testAnimal.getId(), "zone A", "ric man");
        secondTestSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
        assertEquals(true, Sightings.all().get(1).equals(secondTestSighting));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Animal testAnimal = new Animal("hyena");
        testAnimal.save();
        Sightings testSighting = new Sightings (testAnimal.getId(), "zone A", "ric man");
        testSighting.save();
        Animal secondTestAnimal = new Animal("lion");
        secondTestAnimal.save();
        Sightings secondTestSighting = new Sightings (testAnimal.getId(), "zone A", "ricman");
        secondTestSighting.save();
        assertEquals(Sightings.find(secondTestSighting.getId()), secondTestSighting);
    }


}