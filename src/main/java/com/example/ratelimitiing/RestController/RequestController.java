package RestController;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class RequestController {

    // The rate limiter is for the entire API so we could use the default constructor to initialize this.

    private final Bucket bucket;
    Long tokens = 10l;


    public RequestController() {
        // Maintains the speed of the token regeneration.
        Refill refill = Refill.greedy(tokens, Duration.ofMinutes(1));
        // Sets the buckets with the refill amount. Classic gives us fine-tuning capabilities.
        Bandwidth limit = Bandwidth.classic(tokens, refill);
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    // Greet user for maximum of 10 times in a 1-minute period.
    @RequestMapping("/greeting")
    public ResponseEntity sayHello(@RequestParam String name) {
        return new ResponseEntity("Hello" + name, HttpStatus.OK);
    }

}
