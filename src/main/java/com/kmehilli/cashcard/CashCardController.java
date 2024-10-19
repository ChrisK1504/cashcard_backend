package com.kmehilli.cashcard;

import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/cashcards")
class CashCardController {
	private final CashCardRepository cashCardRepository;

	private CashCardController(CashCardRepository cashCardRepository) {
		this.cashCardRepository = cashCardRepository;
	}

	@GetMapping("/{id}")
	private ResponseEntity<CashCard> findById(@PathVariable Long id) {
		Optional<CashCard> cashCardOptional = cashCardRepository.findById(id);
		if (cashCardOptional.isPresent()) {
			return ResponseEntity.ok(cashCardOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping()
	private ResponseEntity<Iterable<CashCard>> findAll(Pageable pageable) {
		Page<CashCard> page = cashCardRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
		return ResponseEntity.ok(page.getContent());
	}
	
	@PostMapping()
	private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb) {
		CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
		URI locationOfNewCashCard = ucb.path("/cashcards/{id}").buildAndExpand(savedCashCard.id()).toUri();
		
		return ResponseEntity.created(locationOfNewCashCard).build();
	}
	
	
	
}