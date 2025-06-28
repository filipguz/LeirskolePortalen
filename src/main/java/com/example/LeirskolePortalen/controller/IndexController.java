import com.example.LeirskolePortalen.model.Bruker;
import com.example.LeirskolePortalen.repository.BrukerRepository;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import com.example.LeirskolePortalen.security.BrukerUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    private final LeirRepository leirRepo;
    private final DeltakerRepository deltakerRepo;
    private final BrukerRepository brukerRepo;

    public IndexController(LeirRepository leirRepo, DeltakerRepository deltakerRepo, BrukerRepository brukerRepo) {
        this.leirRepo = leirRepo;
        this.deltakerRepo = deltakerRepo;
        this.brukerRepo = brukerRepo;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("leirCount", leirRepo.count());
        model.addAttribute("deltakerCount", deltakerRepo.count());

        if (userDetails instanceof BrukerUserDetails brukerDetails) {
            Bruker bruker = brukerDetails.getBruker();

            System.out.println("Bruker: " + bruker.getFornavn() + " " + bruker.getEtternavn());

            model.addAttribute("brukernavn", bruker.getFornavn() + " " + bruker.getEtternavn());
        }

        return "index";
    }


}