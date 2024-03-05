package org.pasta.services;

import lombok.RequiredArgsConstructor;
import org.pasta.api.requests.PastaBoxRequest;
import org.pasta.api.requests.PublicStatus;
import org.pasta.api.response.PasteBoxResponse;
import org.pasta.api.response.PasteBoxUrlResponse;
import org.pasta.repository.PasteBoxEntity;
import org.pasta.repository.PasteBoxRepositoryMap;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasteBoxServiceImpl implements PasteBoxService{

    private final String host = "http://test.com";
    private final int publicListSize = 10;

    private final PasteBoxRepositoryMap repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);
    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
        return new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBox() {
        List<PasteBoxEntity> Pasteslist = repository.getListOfPublicAndAlive(publicListSize);
        return Pasteslist.stream().map(pasteBoxEntity ->
            new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteBoxUrlResponse create(PastaBoxRequest request) {
        int hash = generateId();
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setHash(Integer.toHexString(hash));
        pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteBoxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pasteBoxEntity);
        return new PasteBoxUrlResponse(host + "/" + pasteBoxEntity.getHash());

    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
