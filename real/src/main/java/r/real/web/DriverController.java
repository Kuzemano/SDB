package r.real.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import r.real.model.Driver;
import r.real.service.DriverService;

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
    public String showAddForm(Model model) {
        return "form";
    }

    @PostMapping("/save")
    public String create(@RequestParam String name,
                         @RequestParam String teamName,
                         @RequestParam String nationality,
                         @RequestParam Integer racingNumber,
                         @RequestParam Double salary,
                         @RequestParam String salaryCurrency,
                         @RequestParam Integer championshipPoints) {
        driverService.create(name, teamName, nationality, racingNumber,
                salary, salaryCurrency, championshipPoints);
        return "redirect:/drivers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        return "form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String teamName,
                         @RequestParam String nationality,
                         @RequestParam Integer racingNumber,
                         @RequestParam Double salary,
                         @RequestParam String salaryCurrency,
                         @RequestParam Integer championshipPoints) {
        driverService.update(id, name, teamName, nationality, racingNumber,
                salary, salaryCurrency, championshipPoints);
        return "redirect:/drivers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        driverService.delete(id);
        return "redirect:/drivers";
    }

    @PostMapping("/points/{id}")
    public String addPoints(@PathVariable Long id, @RequestParam Integer points) {
        driverService.addPoints(id, points);
        return "redirect:/drivers";
    }
}