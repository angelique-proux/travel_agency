package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/api")
public class ApiRestController {

    public final PredictionEngineClient predictionEngineClient = new Launcher().predictionEngineClient();
    public final ApiService apiService = new ApiService(this.predictionEngineClient);

    @PostMapping(path = "/inscription")
    public @ResponseBody Iterable<JsonRegistry> register_new_user(@RequestBody JsonRegistry client){
        return this.apiService.register_user(client);
    }

    @GetMapping(path = "/travels")
    public @ResponseBody
    Object get_potential_destinations(@RequestParam String userName){
        return this.apiService.get_destinations(userName);
    }
}
