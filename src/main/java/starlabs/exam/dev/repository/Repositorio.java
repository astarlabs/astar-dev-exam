package starlabs.exam.dev.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {
	@PersistenceContext(unitName = "PU_ASTAR")
	private EntityManager manager;
}
