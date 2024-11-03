package me.kire.re.pojogenerator.service;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.io.File;
import java.nio.file.Path;

@Service
public class CompressFileService {
    public Mono<Tuple2<File, Path>> execute(Path path) {
        return Mono.fromCallable(() -> {
            String dirToZip = path.toString();
            File fileToZip = new File(dirToZip);
            String dirToSaveZip = path.getParent().toString();
            File zipTempFile = new File(dirToSaveZip + File.separator + fileToZip.getName() + ".zip");

            if (!zipTempFile.exists()) {
                zipTempFile.getParentFile().mkdirs();
            }

            try (ZipFile zipFile = new ZipFile(zipTempFile)) {

                ZipParameters zipParameters = new ZipParameters();
                zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);

                zipFile.addFolder(fileToZip, zipParameters);
            }

            return Tuples.of(zipTempFile, path);
        });
    }
}
