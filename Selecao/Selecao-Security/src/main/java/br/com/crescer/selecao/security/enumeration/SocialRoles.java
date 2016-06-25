package br.com.crescer.selecao.security.enumeration;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Murillo
 */
public enum SocialRoles implements GrantedAuthority {

    ROLE_USER;

    @Override
    public String getAuthority() {
        return toString();
    }

    public static List<SocialRoles> valuesToList() {
        return Arrays.asList(SocialRoles.values());
    }

}
