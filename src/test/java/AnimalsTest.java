import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class AnimalsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void all_returnsAllInstancesOfPerson_true() {
        Animals firstAnimal = new Animals("leopard", "sick",1,5);
        firstAnimal.save();
        Animals secondAnimal = new Animals("leopard", "sick",2,5);
        secondAnimal.save();
        assertEquals(true, Animals.all().get(0).equals(firstAnimal));
        assertEquals(true, Animals.all().get(1).equals(secondAnimal));
    }

    @Test
        public void find_returnsAnimalWithSameId_secondPerson() {
        Animals firstAnimal = new Animals("leopard", "sick",1,5);
        firstAnimal.save();
        Animals secondAnimal = new Animals("leopard", "sick",1,5);
        secondAnimal.save();
        assertEquals(Animals.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void save_addsAnimalToDatabase_true(){
        Animals newAnimal = new Animals("leopard", "sick",1,5);
                newAnimal.save();
                assertEquals(newAnimal.getName(), "leopard");
    }
}
