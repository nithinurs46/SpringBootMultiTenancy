package dev.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
	private static final String DEFAULT_TENANT_ID = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenant = TenantContext.getCurrentTenant();
		if (tenant != null) {
			return tenant;
		} else {
			return DEFAULT_TENANT_ID;
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
