package projeto.herois.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('Default')")
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
