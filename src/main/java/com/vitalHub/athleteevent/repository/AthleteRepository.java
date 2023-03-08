package com.vitalHub.athleteevent.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.vitalHub.athleteevent.domain.Athlete;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete,Long> {
	
	@Query("SELECT DISTINCT  p FROM Athlete p "
			+ " LEFT JOIN EventParticipation pi ON p.id=pi.athleteId"
			+ " WHERE "
			//+ "("
				+ "("
					+ ":searchq IS NOT NULL AND "
					+ "("
						+ "(upper(p.firstName) LIKE '%' || upper(:searchq) || '%') "
						+ "OR (upper(p.lastName) LIKE '%' || upper(:searchq) || '%') "
						+ " OR (upper(p.country) LIKE '%' || upper(:searchq) || '%') "
						+ " OR (upper(p.gender) LIKE '%' || upper(:searchq) || '%') "
						+ " OR (upper(pi.eventName) LIKE '%' || upper(:searchq) || '%') "
					+ ") "
					+ " AND "
					+ "("
						+ "(:name IS NULL OR (:name IS NOT NULL AND upper(p.firstName) LIKE '%' || upper(:name)))"
						+ " OR (:name IS NOT NULL AND upper(p.lastName) LIKE '%' || upper(:name))"
						+ " AND (:country IS NULL OR (:country IS NOT NULL AND upper(p.country) LIKE '%' || upper(:country) || '%'))"
						+ " AND (:gender IS NULL OR (:gender IS NOT NULL AND upper(p.gender) LIKE '%' || upper(:gender) || '%') )"
						+ " OR (:event IS NULL OR (:event IS NOT NULL AND upper(pi.eventName) LIKE '%' || upper(:event) || '%'))"
					+ ")"
				+ ")"
				+ " OR "
				+ "("
					+ ":searchq IS NULL AND "
					//+ "("
					+ "(:name IS NULL OR (:name IS NOT NULL AND upper(p.firstName) LIKE '%' || upper(:name)))"
					+ " OR (:name IS NOT NULL AND upper(p.lastName) LIKE '%' || upper(:name))"
					+ " AND (:country IS NOT NULL AND upper(p.country) LIKE '%' || upper(:country))"
					+ " AND (:gender IS NOT NULL AND upper(p.gender) LIKE '%' || upper(:gender)) "
					+ " AND (:event IS NOT NULL AND upper(pi.eventName) LIKE '%' || upper(:event))"
					//+ ")"
				//+ ")"
			+ ")")
			
public List<Athlete> searchPerson(@Param("searchq")String searchq, 
		@Param("name")String name, 
		@Param("event")String event,
		@Param("country")String country,
		@Param("gender")String gender);
	
}
