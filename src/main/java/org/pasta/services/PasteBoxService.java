package org.pasta.services;

import org.pasta.api.requests.PastaBoxRequest;
import org.pasta.api.response.PasteBoxResponse;
import org.pasta.api.response.PasteBoxUrlResponse;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getFirstPublicPasteBox();
    PasteBoxUrlResponse create(PastaBoxRequest request);
}
