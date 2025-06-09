package com.hotels.booking.security.token.repository.entity;

import com.hotels.booking.security.token.repository.enums.TokenType;
import com.hotels.booking.security.user.repository.entity.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user_token")
public class AppUserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_app_user_token")
    @SequenceGenerator(name = "seq_app_user_token", sequenceName = "seq_app_user_token", initialValue = 1000, allocationSize = 1)
    public Long id;

    @Column(name = "token")
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    public TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    public boolean revoked;

    @Column(name = "expired")
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    public AppUser user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUserToken userToken)) return false;

        return this.getId() != null && this.getId().equals(userToken.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
