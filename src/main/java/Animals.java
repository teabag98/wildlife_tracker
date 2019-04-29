import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Animals {
    private String name;
    private String health;
    private int id;
    private  int age;

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public Animals (String name, String health,int id, int age){

        this.id = id;
        this.age = age;
        this.name = name;
        this.health = health;

    }

    public void save(){

        try(Connection con = DB.sql2o.open() ){
            String sql = "INSERT INTO animals(name,health,age) VALUES(:name,:health,:age)" ;
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();

        }

    }

    public static List<Animals> all(){
        String sql = "SELECT * FROM animals";
        try(Connection con =DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Animals.class);
        }
    }





}
