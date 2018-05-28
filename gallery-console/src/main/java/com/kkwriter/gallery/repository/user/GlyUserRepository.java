package com.kkwriter.gallery.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkwriter.gallery.entity.user.GlyUser;

/**
 * @author lisha
 */
public interface GlyUserRepository extends JpaRepository<GlyUser, Integer> {
}
