import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingsTest {
  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
    public void sighting_instantiateorrectly_true(){
      Sightings testMonster = new Sightings("Bubbles", "ali",2,3,23/04/19);
      assertEquals(true, testMonster instanceof Sightings);
  }

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Animals testAnimal = new Animals("Deer");
        testAnimal.save();
        Sightings testSighting = new Sightings(testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
        assertEquals(true, testSighting instanceof Sightings);
    }

    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        Animals testAnimal = new Animals("Deer");
        testAnimal.save();
        Sightings testSighting = new Sightings(testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
        Sightings anotherSighting = new Sightings(testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Animals testAnimal = new Animals("Deer");
        testAnimal.save();
        Sightings testSighting = new Sightings (testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
        testSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Animals testAnimal = new Animals("Deer");
        testAnimal.save();
        Sightings testSighting = new Sightings (testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
        testSighting.save();
        Animal secondTestAnimal = new Animal("Badger");
        secondTestAnimal.save();
        Sighting secondTestSighting = new Sighting (testAnimal.getId(), "45.472428, -121.946466", "Ranger Reese");
        secondTestSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Animal testAnimal = new Animal("Deer");
        testAnimal.save();
        Sighting testSighting = new Sighting (testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
        testSighting.save();
        Animal secondTestAnimal = new Animal("Badger");
        secondTestAnimal.save();
        Sighting secondTestSighting = new Sighting (testAnimal.getId(), "45.472428, -121.946466", "Ranger Reese");
        secondTestSighting.save();
        assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
    }

    @Test
    public void find_returnsNullWhenNoAnimalFound_null() {
        assertTrue(Animal.find(999) == null);
    }

}
