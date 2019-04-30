import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animals {
    public String name;
    public int id;



    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

//    public Animals (String name, String health,int id, int age){
//
//        this.id = id;
//        this.age = age;
//        this.name = name;
//        this.health = health;
//
//    }

    public void save(){

        try(Connection con = DB.sql2o.open() ){
            String sql = "INSERT INTO animals(name,health,age) VALUES(:name,:health,:age)" ;
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
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

    public static Animals find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE id= :id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
                    return animal;
        }
    }







}
