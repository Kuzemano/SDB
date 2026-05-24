package r.real.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import r.real.model.DriverId;
import r.real.model.valueObjects.*;
import r.real.model.Driver;
import r.real.service.DriverService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public String listDrivers(@RequestParam(required = false) String nameSearch,
                              @RequestParam(required = false) String teamSearch,
                              Model model) {
        List<Driver> drivers;
        if (nameSearch != null && !nameSearch.isEmpty()) {
            drivers = driverService.findByName(nameSearch);
        } else if (teamSearch != null && !teamSearch.isEmpty()) {
            drivers = driverService.findByTeam(teamSearch);
        } else {
            drivers = driverService.listAll();
        }
        model.addAttribute("drivers", drivers);
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "form";
    }

    @PostMapping("/save")
    public String create(@RequestParam String name,
                         @RequestParam String teamName,
                         @RequestParam String nationality,
                         @RequestParam Integer racingNumber,
                         @RequestParam BigDecimal salary,
                         @RequestParam Currency salaryCurrency,
                         @RequestParam Integer championshipPoints) {
        driverService.create(name, teamName, nationality, racingNumber, salary, salaryCurrency, championshipPoints);
        return "redirect:/drivers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("driver", driverService.findById(new DriverId(id)));
        return "form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id,
                         @RequestParam String name,
                         @RequestParam String teamName,
                         @RequestParam String nationality,
                         @RequestParam Integer racingNumber,
                         @RequestParam BigDecimal salary,
                         @RequestParam Currency salaryCurrency,
                         @RequestParam Integer championshipPoints) {
        driverService.update(new DriverId(id), name, teamName, nationality, racingNumber, salary, salaryCurrency, championshipPoints);
        return "redirect:/drivers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        driverService.delete(new DriverId(id));
        return "redirect:/drivers";
    }

    @PostMapping("/points/{id}")
    public String addPoints(@PathVariable String id, @RequestParam Integer points) {
        driverService.addPoints(new DriverId(id), points);
        return "redirect:/drivers";
    }
}