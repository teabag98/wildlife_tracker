import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public  class Animals {
    private String endangered;
    private String animal;
    private String health;
    private String age;
    private int id;

    public Animals(String animal, String endangered, String health, String age) {
        this.animal = animal;
        this.endangered = endangered;
        this.health = health;
        this.age = age;
    }

    public String getEndangered(){
        return endangered;
    }

    public String getAnimal(){
        return animal;
    }
    public String getHealth(){
        return health;
    }
    public String age(){
        return age;
    }
    public int getId(){
        return id;
    }


    public void save() {

        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(name,health,age) VALUES(:name,:health,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();

        }

    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Animals.class);
        }
    }

    public static Animals find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id= :id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }

}






