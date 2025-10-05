package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.UserTestConverter;
import com.example.english_learning_server.dto.RankedTestDTO;
import com.example.english_learning_server.dto.RankedTestDTO2;
import com.example.english_learning_server.dto.TestDTO;
import com.example.english_learning_server.dto.UserTestDTO;
import com.example.english_learning_server.entity.Test;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserTest;
import com.example.english_learning_server.reponsitory.TestRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.example.english_learning_server.reponsitory.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTestService {
    @Autowired
    private UserTestRepository userTestRepository;

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private TestRepository testRepository;

    public UserTestDTO createOrUpdateUserTest(UserTestDTO dto) {
        // Lấy userId từ access_token (sử dụng SecurityContextHolder)
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Integer userId = user.getId();  // Lấy userId từ User entity

        // Lấy Test từ ID
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found"));

        // Kiểm tra xem dữ liệu đã tồn tại chưa dựa trên userId và testId
        Optional<UserTest> existingUserTest = userTestRepository.findByIdAndUserId(dto.getId(), user.getId());

        UserTest userTest;
        if (existingUserTest.isPresent()) {
            // Cập nhật dữ liệu chỉ với điểm số, ngày giờ
            userTest = existingUserTest.get();
            userTest.setScore(dto.getScore());  // Cập nhật điểm số
            userTest.setExamTimes(LocalTime.now()); // Cập nhật thời gian làm bài
            userTest.setDate(LocalDate.now()); // Cập nhật ngày làm bài
        } else {
            // Thêm mới dữ liệu với ngày giờ hiện tại và điểm số từ DTO
            userTest = UserTestConverter.dtoToEntity(dto, user, test);
            userTest.setExamTimes(LocalTime.now()); // Cập nhật thời gian làm bài
            userTest.setDate(LocalDate.now()); // Cập nhật ngày làm bài
        }

        // Lưu vào cơ sở dữ liệu
        UserTest savedUserTest = userTestRepository.save(userTest);

        // Trả về DTO sau khi lưu
        return UserTestConverter.entityToDTO(savedUserTest);
    }

    public List<RankedTestDTO> getRankedTestsForUser(Integer courseId) {
        // Lấy userId từ access_token (sử dụng SecurityContextHolder)
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Integer userId = user.getId();  // Lấy userId từ User entity

        // Lấy danh sách bài kiểm tra đã xếp hạng cho user theo courseId
        List<Object[]> rankedTests = userTestRepository.getRankedTests(userId, courseId);

        // Tạo một danh sách để chứa kết quả
        List<RankedTestDTO> result = new ArrayList<>();

        for (Object[] row : rankedTests) {
            RankedTestDTO rankedTestDTO = new RankedTestDTO();

            // Gán giá trị từ các cột vào đối tượng RankedTestDTO
            rankedTestDTO.setTestId((Integer) row[0]);  // testId
            rankedTestDTO.setTestName((String) row[1]);  // testName
            rankedTestDTO.setDescription((String) row[2]);  // description
            rankedTestDTO.setExamTime((Integer) row[4]);  // examTime

            // Kiểm tra nếu row[3] (java.sql.Date) là null trước khi chuyển đổi
            if (row[3] != null) {
                rankedTestDTO.setExamDate(((Date) row[3]).toLocalDate().toString());  // Chuyển đổi java.sql.Date thành LocalDate và convert thành String
            } else {
                rankedTestDTO.setExamDate(null);  // Hoặc giá trị mặc định
            }

            rankedTestDTO.setLevel((String) row[5]);  // level
            rankedTestDTO.setMaxNumberOfExams((String) row[6]);  // maxNumberOfExams
            rankedTestDTO.setCourseId((Integer) row[9]);  // courseId
            rankedTestDTO.setType((String) row[8]);  // type

            // Chuyển đổi Long thành Integer cho status
            rankedTestDTO.setStatus(getIntegerValue(row[7]));  // status

            rankedTestDTO.setScore((String) row[10]);  // score

            // Kiểm tra nếu row[11] (java.sql.Date) là null trước khi chuyển đổi
            if (row[11] != null) {
                rankedTestDTO.setDate(((Date) row[11]).toLocalDate().toString());  // date
            } else {
                rankedTestDTO.setDate(null);  // Hoặc giá trị mặc định
            }

            // Kiểm tra nếu row[12] (String) là null trước khi chuyển đổi
            if (row[12] != null) {
                rankedTestDTO.setExamTimes((String) row[12]);  // examTimes
            } else {
                rankedTestDTO.setExamTimes(null);  // Hoặc giá trị mặc định
            }

            result.add(rankedTestDTO);  // Thêm đối tượng RankedTestDTO vào kết quả
        }

        return result;  // Trả về danh sách chứa kết quả
    }

    private Integer getIntegerValue(Object value) {
        if (value instanceof Long) {
            return ((Long) value).intValue();  // Chuyển Long thành Integer
        }
        return (Integer) value;  // Nếu là Integer thì trả về trực tiếp
    }

    public List<RankedTestDTO2> getRankedTestsForUser2(Integer courseId) {
        // Lấy userId từ access_token (sử dụng SecurityContextHolder)
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Integer userId = user.getId();  // Lấy userId từ User entity

        // Lấy các bài kiểm tra đã xếp hạng
        List<Object[]> results = userTestRepository.getRankedTests2(userId, courseId);

        // Chuyển đổi kết quả thành DTO
        return results.stream()
                .map(result -> {
                    RankedTestDTO2 dto = new RankedTestDTO2();
                    dto.setTestId((Integer) result[0]);
                    System.out.println("Value of result[0]: " + result[0]);
//                    dto.setDescription((String) result[1]);
                    dto.setExamTime(result[1] != null ? Integer.parseInt(result[1].toString()) : null);
                    System.out.println("Value of result[1]: " + result[1]);
                    dto.setLevel((String) result[2]);
                    System.out.println("Value of result[2]: " + result[2]);
                    dto.setMaxNumberOfExams((String) result[3]);
                    System.out.println("Value of result[3]: " + result[3]);
                    dto.setUserTestStatus((Integer) result[4]);
                    System.out.println("Value of result[4]: " + result[4]);
                    dto.setTestName((String) result[5]);
                    System.out.println("Value of result[5]: " + result[5]);
                    dto.setType((String) result[6]);
                    System.out.println("Value of result[6]: " + result[6]);
                    dto.setCourseId((Integer) result[7]);
                    System.out.println("Value of result[7]: " + result[7]);
                    dto.setScore((String) result[8]);
                    System.out.println("Value of result[8]: " + result[8]);
                    if (result[9] != null) {
                        dto.setDate(((Date) result[9]).toLocalDate().toString());  // date
                    } else {
                        dto.setDate(null);  // Hoặc giá trị mặc định
                    }
                    System.out.println("Value of result[9]: " + result[9]);
                    if (result[10] != null) {
                        dto.setExamTimes(((Time) result[10]).toLocalTime());
                    } else {
                        dto.setExamTimes(null); // Hoặc giá trị mặc định
                    }

                    System.out.println("Value of result[10]: " + result[10]);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
