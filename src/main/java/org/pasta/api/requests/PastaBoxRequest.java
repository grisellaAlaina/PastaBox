package org.pasta.api.requests;

import lombok.Data;

@Data
public class PastaBoxRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
