package it.gssi.edelta.migrationservice.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edelta.example.migration.personlist.migrator.PersonsModelMigrator;
import it.gssi.edelta.migrationservice.configuration.MigrationConfigProperties;

@RestController
@RequestMapping("/api/v1/migrationservice")
public class MigrationController {

	private static final Logger logger = LoggerFactory.getLogger(MigrationController.class);

	private MigrationConfigProperties properties;

	public MigrationController(MigrationConfigProperties properties) {
		this.properties = properties;
	}

	@PostMapping("/")
	public ResponseEntity<byte[]> modelmigration(@RequestParam MultipartFile[] modelFiles) {
		try {
			Path newFolder = createFolderWithInputModels(modelFiles);

			PersonsModelMigrator personsModelMigrator = new PersonsModelMigrator();
			Collection<Resource> result = personsModelMigrator.execute(newFolder.toString());
			logger.info("Migrated models: " + result.stream().map(Resource::getURI).toList());

			byte[] zipBytes = createZipFileResponse(result);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/zip"));
			headers.setContentDispositionFormData("attachment", "migratedmodels.zip");
			headers.setContentLength(zipBytes.length);
			return new ResponseEntity<>(zipBytes, headers, org.springframework.http.HttpStatus.OK);
		} catch (Exception e) {
			throw new MigrationException(e);
		}
	}

	private Path createFolderWithInputModels(MultipartFile[] modelFiles) throws IOException {
		Path newSubfolder = Paths.get(properties.getModelfolder(), System.currentTimeMillis() + "");
		Path dir = Files.createDirectories(newSubfolder);
		logger.info("folder containing input models: " + dir.toString());
		for (int i = 0; i < modelFiles.length; i++) {
			Files.write(Path.of(dir.toString(), modelFiles[i].getOriginalFilename()), modelFiles[i].getBytes());
		}
		return dir;
	}

	private byte[] createZipFileResponse(Collection<Resource> result) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);

		for (Resource resource : result) {
			logger.debug("output model: " + resource.getURI().toString());
			String filename = resource.getURI().toFileString();
			Path filePath = Paths.get(filename);
			if (Files.exists(filePath) && Files.isReadable(filePath)) {
				ZipEntry zipEntry = new ZipEntry(resource.getURI().lastSegment());
				zos.putNextEntry(zipEntry);
				Files.copy(filePath, zos);
				zos.closeEntry();
			} else {
				logger.error("File not found or not readable: " + filename);
			}
		}
		zos.close();

		return baos.toByteArray();
	}

}
