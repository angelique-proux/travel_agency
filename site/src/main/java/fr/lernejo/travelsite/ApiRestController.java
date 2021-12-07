package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/api")
public class ApiRestController {

    public final ArrayList<JsonRegistry> jsonRegistries = new ArrayList<>();

    @PostMapping(path = "/inscription")
    public @ResponseBody Iterable<JsonRegistry> register_new_user(@RequestBody JsonRegistry client){
        this.jsonRegistries.add(client);
        return this.jsonRegistries;
    }

    @GetMapping(path = "/travels")
    public @ResponseBody
    Object get_potential_destinations(@RequestParam String userName){
        for (JsonRegistry jsonRegistry:
             this.jsonRegistries) {
            if(jsonRegistry.userName.equals(userName)) {
                ArrayList<JsonCountry> jsonCountries = new ArrayList<>();
                jsonCountries.add(new JsonCountry("France", 23));
                jsonCountries.add(new JsonCountry("Germany", 2));
                jsonCountries.add(new JsonCountry("Spain", 18));
                return jsonCountries;
            }
        }
        return new ResponseEntity<String>("The userName doesn't match.", HttpStatus.EXPECTATION_FAILED);
    }
}
