package personal.project.two_vago.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    CloudinaryImage upload(MultipartFile multipartFile) throws IOException;
    boolean delete(String publicId);
}
