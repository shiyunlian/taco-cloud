package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller identifies HomeController class as a component for component scanning
//Spring's component scanning automatically discovers it and creates an instance of HomeController as a bean
//in the Spring application context
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}

}
