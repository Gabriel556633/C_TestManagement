package com.tcs.ms_test_management.repository;


import com.tcs.ms_test_management.domain.model.RptReplicaClientesFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RptClientsFinalRepository extends JpaRepository<RptReplicaClientesFinal, String> {
    Optional<RptReplicaClientesFinal> findByNumeroServicio(String numeroServicio);
    // Métodos personalizados de consulta si es necesario
}
