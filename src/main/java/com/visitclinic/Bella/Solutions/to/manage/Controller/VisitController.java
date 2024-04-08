package com.visitclinic.Bella.Solutions.to.manage.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.visitclinic.Bella.Solutions.to.manage.Entity.Visit;
import com.visitclinic.Bella.Solutions.to.manage.Repository.VisitRepository;

@RestController
@RequestMapping()

public class VisitController {

	@Autowired
	private VisitRepository visitRepository;

	@GetMapping("/")
	public ModelAndView redirectToHomePage() {

		ModelAndView modelAndView = new ModelAndView("visitForm");
		modelAndView.setStatus(HttpStatus.OK);
		System.out.println("ModelAndView");
		return modelAndView;
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView addVisists(@ModelAttribute("newVisit") Visit newVisit, Model model)
			throws JsonProcessingException, UnsupportedEncodingException {
		visitRepository.save(newVisit);

		ModelAndView modelAndView = new ModelAndView("visitForm");

		modelAndView.setStatus(HttpStatus.OK);
		modelAndView.addObject("message", "Visit added successfully");
		return modelAndView;
	}

	// Get all visits
	@GetMapping("/getAll")
	public ModelAndView getAllVisits() {
		ModelAndView view = new ModelAndView("visitForm");
		view.addObject("visits", visitRepository.findAll());
		view.addObject("newVisit", new Visit()); // Add this line to initialize the newVisit object for the form
		return view;
	}

	// Get a visit by ID
	@GetMapping("/visits/{id}")
	public ModelAndView getVisitById(@PathVariable Long id) {
		Optional<Visit> visit = visitRepository.findById(id);
		ModelAndView view = new ModelAndView("visitForm");
		view.addObject("visit", visit.orElse(null));
		view.addObject("newVisit", new Visit()); // Add this line to initialize the newVisit object for the form
		return view;
	}

	// Update a visit by ID
	@PostMapping("/visits/{id}")
	public String updateVisit(@PathVariable Long id, @ModelAttribute Visit updatedVisit) {
		visitRepository.findById(id).map(visit -> {
			visit.setVisitDateTime(updatedVisit.getVisitDateTime());
			visit.setPurpose(updatedVisit.getPurpose());
			return visitRepository.save(visit);
		}).orElseThrow(() -> new RuntimeException("Visit not found with id " + id));
		return "redirect:/getAll"; // Redirect to the page showing all visits after updating
	}

	// Delete a visit by ID
	@PostMapping("/visits/{id}/delete")
	public String deleteVisit(@PathVariable Long id) {
		visitRepository.deleteById(id);
		return "redirect:/getAll"; // Redirect to the page showing all visits after deletion
	}

}
