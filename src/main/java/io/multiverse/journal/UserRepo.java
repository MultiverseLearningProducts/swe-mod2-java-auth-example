package io.multiverse.journal;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepo extends JpaRepository<User, Integer>{}
