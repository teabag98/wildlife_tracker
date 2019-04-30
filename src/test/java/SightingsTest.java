import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class SightingsTest {
  @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void sighting_instantiatesCorrectly_true() {
        Animals testAnimal = new Animals("leopard", "sick","en","5");
        testAnimal.save();
        Sightings testSighting = new Sightings("zone1","zoneA","eric",testAnimal.getId());
        assertEquals(true, testSighting instanceof Sightings);
    }

    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        Animals testAnimal = new Animals("leopard", "sick","en","5");
        testAnimal.save();
        Sightings testSighting = new Sightings("zone1","zoneA","eric",testAnimal.getId());
        Sightings anotherSighting = new Sightings("zone1","zoneA","eric",testAnimal.getId());
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Animals testAnimal = new Animals("leopard", "sick","en","5");
        testAnimal.save();
        Sightings testSighting = new Sightings ("zone1","zoneA","eric",testAnimal.getId());
        testSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Animals testAnimal = new Animals("leopard", "sick","en","5");
        testAnimal.save();
        Sightings testSighting = new Sightings ("zone1","zoneA","eric",testAnimal.getId());
        testSighting.save();
        Animals secondTestAnimal = new Animals("leopard", "sick","en","5");
        secondTestAnimal.save();
        Sightings secondTestSighting = new Sightings ("zone1","zoneA","eric",testAnimal.getId());
        secondTestSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
        assertEquals(true, Sightings.all().get(1).equals(secondTestSighting));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Animals testAnimal = new Animals("leopard", "sick","en","5");
        testAnimal.save();
        Sightings testSighting = new Sightings ("zone1","zoneA","eric",testAnimal.getId());
        testSighting.save();
        Animals secondTestAnimal = new Animals("leopard", "sick","en","5" );
        secondTestAnimal.save();
        Sightings secondTestSighting = new Sightings ("zone1","zoneA","eric",testAnimal.getId());
        secondTestSighting.save();
        assertEquals(Sightings.find(secondTestSighting.getId()), secondTestSighting);
    }

    @Test
    public void find_returnsNullWhenNoAnimalFound_null() {
        Animals animal = new Animals("leopard", "sick","en","5");
        animal.save();
        assertTrue(Animals.find(500) == null);
    }

}
