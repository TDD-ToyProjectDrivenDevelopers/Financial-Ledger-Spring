package gubun.financialledger.user.oauth.provider;

import lombok.RequiredArgsConstructor;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private static final String PROVIDER_NAME = "google";

    private final Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return PROVIDER_NAME;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
