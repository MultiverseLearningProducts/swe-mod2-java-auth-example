package io.multiverse.journal;

import org.springframework.data.jpa.repository.JpaRepository;

interface JournalRepo extends JpaRepository<JournalEntry, Integer>{}
