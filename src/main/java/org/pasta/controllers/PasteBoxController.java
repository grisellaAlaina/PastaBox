package org.pasta.controllers;

import org.pasta.api.requests.PastaBoxRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
public class PasteBoxController {
    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash) {
        return hash;
    }
    @GetMapping()
    public Collection<String> getPublicPastes() {
        return Collections.emptyList();
    }
    @PostMapping("/")
    public String addNewPasta(@RequestBody PastaBoxRequest request) {
        return request.getData();
    }
}
