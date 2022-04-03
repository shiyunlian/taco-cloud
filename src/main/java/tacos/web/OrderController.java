package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	private static final org.slf4j.Logger log =
			org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
//	The class-level @RequestMapping specifies that any request-handling methods in this controller
//	will handle requests whose path begins with /orders. When combined with the method-level
//	@GetMapping, it specifies that the orderForm() method will handle HTTP GET requests for
//	/orders/current.
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("tacoOrder", new TacoOrder());
		//return a logical view name of orderForm
		return "orderForm";	
	}
	
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submitted: "+ order);
		return "redirect:/";
	}	

}
