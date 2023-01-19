package com.hkblog.api.repository;

import com.hkblog.api.domain.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
