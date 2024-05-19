package com.xidesk.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@NoArgsConstructor
public enum Role {

    ADMIN (List.of(Permission.values())),
    USER (List.of(
            Permission.READ,
            Permission.CREATE,
            Permission.DELETE
    ));

    private List<Permission> permissions;
    private Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        permissions.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.name()));
        });
        return authorities;
    }

}
