package me.kire.re.pojogenerator.application.service;

import lombok.RequiredArgsConstructor;
import me.kire.re.pojogenerator.application.client.ClientPojoGenerator;
import me.kire.re.pojogenerator.application.dto.in.DtoInGeneratePojoPost;
import me.kire.re.pojogenerator.domain.factory.FileFactory;
import me.kire.re.pojogenerator.domain.port.in.CompressFilePort;
import me.kire.re.pojogenerator.domain.port.in.CreateTempDirectoryPort;
import me.kire.re.pojogenerator.domain.port.in.DeleteDirectoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
@RequiredArgsConstructor
public class PojoGeneratorService {
    private final GenerateCodeService generateCodeService;
    private final CompressFilePort compressFilePort;
    private final CreateTempDirectoryPort createTempDirectoryPort;
    private final DeleteDirectoryPort deleteDirectoryPort;
    private final FileFactory fileFactory;

    public Mono<File> generate(DtoInGeneratePojoPost request) {
        return this.createTempDirectoryPort.execute()
                .flatMap(path ->
                        this.generateCodeService.execute(this.fileFactory, request, path))
                .flatMap(ClientPojoGenerator::write)
                .flatMap(this.compressFilePort::execute)
                .flatMap(tuple -> this.deleteDirectoryPort.execute(tuple.getT2(), tuple.getT1()));
    }
}
