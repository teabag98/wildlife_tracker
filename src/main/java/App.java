import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/homepage", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/homepage.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        post("/details", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rangername");
            String animal = request.queryParams("animal");
            String endangered = request.queryParams("endangered");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            Animals newAnimal = new Animals(animal, endangered, health, age);
            newAnimal.save();
            Sightings newSighting = new Sightings(location ,rangername, age,Animals.);
            newSighting.save();
            model.put("sightings", Sightings.all());
            model.put("animals",Animals.all());
            model.put("AnimalClass", Animals.class);
            model.put("template", "templates/homepage.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
