package sec.project.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.CustomUserDetails;
import sec.project.domain.Event;
import sec.project.domain.User;
import sec.project.repository.EventRepository;
import sec.project.repository.LegacyEventRepository;
import sec.project.repository.UserRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LegacyEventRepository legacyEventRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String listEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "event-list";
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public String createEvent(@RequestParam String eventName, @RequestParam @DateTimeFormat(pattern="dd.MM.yyyy") Date eventDate, Authentication auth) {
        Event e = new Event();
        e.setEventName(eventName);
        e.setEventDate(eventDate);
        CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal();
        e.setOrganizer(cud.getDomainUserObject());
        legacyEventRepository.save(eventName, eventDate, cud.getDomainUserObject().getId());
        return "redirect:/event";
    }

    @RequestMapping(value = "/attend-event/{eventId}", method = RequestMethod.POST)
    public String attendEvent(@PathVariable Long eventId, Authentication auth) {
        Event e = eventRepository.findOne(eventId);
        CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal();
        User user = userRepository.getOne(cud.getDomainUserObject().getId());
        if (!e.getAttendees().contains(user)) {
            e.getAttendees().add(user);
            eventRepository.save(e);   
        }
        return "redirect:/event/" + eventId;
    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
    public String getEvent(@PathVariable Long eventId, Model model) {
        model.addAttribute("event", eventRepository.findByIdAndFetchAttendees(eventId));
        return "event";
    }
}