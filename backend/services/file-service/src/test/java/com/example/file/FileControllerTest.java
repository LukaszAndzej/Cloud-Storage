// package com.example.file;

// import com.example.file.model.File;
// import com.example.file.repository.FileRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-test.properties")
// public class FileControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private FileRepository fileRepository;

//     @BeforeEach
//     public void setup() {
//         fileRepository.deleteAll();
//         File file = new File();
//         file.setFileName("example.txt");
//         file.setFilePath("/files/example.txt");
//         file.setSize(2048L);
//         fileRepository.save(file);
//     }

//     @Test
//     void testGetAllFiles() throws Exception {
//         mockMvc.perform(get("/files"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].fileName").value("example.txt"));
//     }

//     @Test
//     void testUploadFile() throws Exception {
//         String json = """
//             {
//                 "fileName": "test.txt",
//                 "filePath": "/files/test.txt",
//                 "size": 1024
//             }
//         """;

//         mockMvc.perform(post("/files")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(json))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.fileName").value("test.txt"));
//     }

//     @Test
//     void testDeleteFile() throws Exception {
//         // Przygotowanie danych testowych
//         File file = new File();
//         file.setFileName("toDelete.txt");
//         file.setFilePath("/files/toDelete.txt");
//         file.setSize(512L);
//         file = fileRepository.save(file); // Zapis i odczyt z bazy

//         // Usunięcie pliku
//         mockMvc.perform(delete("/files/" + file.getId()))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("File deleted successfully."));
//     }
// }
