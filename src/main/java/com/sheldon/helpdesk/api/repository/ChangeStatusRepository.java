package com.sheldon.helpdesk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheldon.helpdesk.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends JpaRepository<ChangeStatus, Long> {

}
