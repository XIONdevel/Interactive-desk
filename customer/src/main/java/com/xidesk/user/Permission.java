package com.xidesk.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public enum Permission {
    ADMIN_READ,
    ADMIN_CREATE,
    ADMIN_DELETE,

    READ,
    CREATE,
    DELETE
}
