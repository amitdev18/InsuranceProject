package protecons.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import protecons.insurance.entity.UserToken;

import java.util.Optional;

public interface UserTokenRepository
        extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findByToken(String token);

    Optional<UserToken> findByTokenAndRevokedFalse(
            String token
    );
}
