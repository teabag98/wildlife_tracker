import org.sql2o.*;

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


    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animal(animal, endangered, health, age) VALUES(:animal, :endangered, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal", this.animal)
                    .addParameter("endangered", this.endangered)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<Animals> all() {
        String sql = "select * from animal";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }


    public static String find(int id) {
        String sql = "select animal from animal where id = :id;";
        try(Connection con = DB.sql2o.open()) {
            String name = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(String.class);
            return name;
        }
    }

    public static String getAnimalEndangered(int id) {
        String sql = "select endangered from animal where id = :id;";
        try(Connection con = DB.sql2o.open()) {
            String endangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(String.class);
            return endangered;
        }
    }

    public static String getAnimalHealth(int id) {
        String sql = "select health from animal where id = :id;";
        try(Connection con = DB.sql2o.open()) {
            String health = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(String.class);
            return health;
        }
    }

    public static String getAnimalAge(int id) {
        String sql = "select age from animal where id = :id;";
        try(Connection con = DB.sql2o.open()) {
            String age = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(String.class);
            return age;
        }
    }

}






