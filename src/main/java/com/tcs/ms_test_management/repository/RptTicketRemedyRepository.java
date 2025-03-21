package com.tcs.ms_test_management.repository;

import com.tcs.ms_test_management.domain.model.RptTicketRemedy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RptTicketRemedyRepository extends JpaRepository<RptTicketRemedy, Long> {
    List<RptTicketRemedy> findByCategorizacionTier1AndCategorizacionTier2AndCategorizacionTier3(String categorizacionTier1, String categorizacionTier2, String categorizacionTier3);
    List<RptTicketRemedy> findByTecnologiaAndVaribleAValidar(String tecnologia, String varibleAValidar);
    @Query("SELECT r FROM RptTicketRemedy r WHERE r.tecnologia = :tecnologia AND r.varibleAValidar = :varibleAValidar AND :rango BETWEEN r.rangoInicial AND r.rangoFinal")
    List<RptTicketRemedy> findByTecnologiaAndVaribleAValidarAndRango(@Param("tecnologia") String tecnologia, @Param("varibleAValidar") String varibleAValidar, @Param("rango") Double rango); // Nuevo método
}