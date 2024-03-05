package org.pasta.controllers;

import lombok.RequiredArgsConstructor;
import org.pasta.api.requests.PastaBoxRequest;
import org.pasta.api.response.PasteBoxResponse;
import org.pasta.api.response.PasteBoxUrlResponse;
import org.pasta.services.PasteBoxService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {
    private final PasteBoxService pasteBoxService;
    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {
        return pasteBoxService.getByHash(hash);
    }
    @GetMapping("/")
    public Collection<PasteBoxResponse> getPublicPastes() {
        return pasteBoxService.getFirstPublicPasteBox();
    }
    @PostMapping("/")
    public PasteBoxUrlResponse addNewPasta(@RequestBody PastaBoxRequest request) {
        return pasteBoxService.create(request);
    }
}
