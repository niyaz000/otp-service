package io.channelapi.sms_service.audit;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class EntityAuditor implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.of(1L);
  }

}