package com.satvikdev.personal_journal_app.controller;

import com.satvikdev.personal_journal_app.model.Entry;
import com.satvikdev.personal_journal_app.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {
    @Autowired
    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public ResponseEntity<List<Entry>> getAllEntries() {
        List<Entry> entries = entryService.getAllEntries();
        return ResponseEntity.ok(entries);
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@RequestBody String content) {
        Entry createdEntry = entryService.createEntry(content); // Creates a new entry
        return ResponseEntity.ok(createdEntry); // Returns the created entry
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable Long id) {
        Entry entry = entryService.getEntryById(id);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        boolean deleted = entryService.deleteEntryById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
