import org.sql2o.*;
import java.util.List;


public class Sightings {
    private int animalid;
    private String location;
    private String rangername;
    private int id;

    public Sightings(int animalid, String location, String rangername) {
        this.animalid = animalid;
        this.location = location;
        this.rangername = rangername;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalid;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangername;
    }

    @Override
    public boolean equals(Object otherSighting) {
        if (!(otherSighting instanceof Sightings)) {
            return false;
        } else {
            Sightings newSighting = (Sightings) otherSighting;
            return this.getAnimalId() == (newSighting.getAnimalId()) && this.getLocation().equals(newSighting.getLocation()) && this.getRangerName().equals(newSighting.getRangerName());
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalid, location, rangername) VALUES (:animalid, :location, :rangername);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalid", this.animalid)
                    .addParameter("location", this.location)
                    .addParameter("rangername", this.rangername)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sightings> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sightings.class);
        }
    }

    public static Sightings find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql;
            sql = "SELECT * FROM sightings WHERE id=:id;";
            Sightings sighting;
            sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
            return sighting;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
}