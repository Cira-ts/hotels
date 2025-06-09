package com.hotels.booking.security.token.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.hotels.booking.security.token.repository.entity.AppUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppUserTokenRepository extends JpaRepository<AppUserToken, Long> {
    @Query("""
            select t
            from AppUserToken t
            inner join AppUser u on t.user.id = u.id
            where u.id = :id and (t.expired = false or t.revoked = false)
            """)
    List<AppUserToken> findAllValidTokenByUser(Long id);

    Optional<AppUserToken> findByToken(String token);
}
