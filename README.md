# API English Learning Server

## Overview
This is a server-side API for an English learning application, built using Java and the Spring Framework.

### Key Technologies
- **Java**: The primary language used for building the API.
- **Spring Framework**:
- **Spring Boot**: For easy configuration and setup of the application.
- **Spring Security**: To handle authentication and authorization.
- **Spring MVC**: For building RESTful APIs.

### Additional Features
- **Cloudinary**: Used to store and manage images.
- **MySQL**: Relational database for data persistence.

## API Endpoints

### Auth APIs
- **Login**
    - `POST` [http://localhost:8080/api/v1/auth/authenticate](http://localhost:8080/api/v1/auth/authenticate)

  **Description**

    - The Login API allows users to authenticate by providing their email and password. Upon successful login, the API returns a JWT (JSON Web Token) that can be used for further authentication in protected routes.


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
       ```json
       {
         "email": "tranb@gmail.com",
         "password": "123456"
       }
       ```

- **Sign Up**
    - `POST` [http://localhost:8080/api/v1/auth/register](http://localhost:8080/api/v1/auth/register)

- **Description**:

- The Sign Up API allows users to create a new account by providing their personal information such as name, email, password, phone number, avatar, and role.


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:

        - **Example 1 (with role)**:
       ```json
       {
         "fullName": "vinh",
         "email": "vinh@gmail.com",
         "password": "123456",
         "phone": "0123456789",
         "avatar": "23cb8130-f55a-4bb6-b35f-49e0f8c038b2_Ahri_-29-scaled",
         "role": "TEACHER"
       }
       ```

        - **Example 2 (without role, default role is USER)**:
       ```json
       {
         "fullName": "vinh",
         "email": "vinh@gmail.com",
         "password": "123456",
         "phone": "0123456789",
         "avatar": "23cb8130-f55a-4bb6-b35f-49e0f8c038b2_Ahri_-29-scaled"
       }
       ```

  In Example 1, the `role` is explicitly set to `TEACHER`.  
  In Example 2, if the `role` is not provided in the request, it will default to `USER`.


- **Logout**
    - `POST` [http://localhost:8080/api/v1/auth/logout](http://localhost:8080/api/v1/auth/logout)

    - **Description**:
    - The Logout API allows users to log out from the application by invalidating their JWT access token. The user must provide the current access token in the Authorization header.


- **Example in Postman**
1. In the **Authorization** , select `Bearer Token`.
2. Enter the access token from login into the box to log out.

- **Refresh Token**
    - `POST` [http://localhost:8080/api/v1/auth/refresh-token](http://localhost:8080/api/v1/auth/refresh-token)

    - **Description**:
    - The Refresh Token API allows users to refresh their JWT access token using a valid refresh token. The refresh token must be provided in the Authorization header as a bearer token.


- **Example in Postman**
1. In the **Authorization** , select `Bearer Token`.
2. Enter the login access token into the box to refresh the token.


### User APIs
- **Get List of User Information**
    - `GET` [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/users)

- **Get User Information by ID**
    - `GET` [http://localhost:8080/api/v1/users/{id}](http://localhost:8080/api/v1/users/{id})


- **Get Current User**
    - `GET` [http://localhost:8080/api/v1/users/me](http://localhost:8080/api/v1/users/me)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |

    - **Response Example**
      ```json
      {
        "id": 1,
        "fullName": "John Doe",
        "email": "johndoe@example.com",
        "phone": "1234567890",
        "avatar": null,
        "status": 1,
        "role": "USER",
        "createDate": "2024-11-15T17:43:59.906+00:00",
        "updatedDate": "2024-11-15T17:43:59.906+00:00"
      }
      ```

### Course APIs
- **Add Course**
    - `POST` [http://localhost:8080/api/v1/courses](http://localhost:8080/api/v1/courses)


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
       ```json
        {
           "courseName": "test 3",
           "courseCode": ".",
           "maxQuantity": 40,
           "statusCourse": 1,
           "image": "https://example.com/image.png"
        }
       ```

- **Update Course**
    - `PUT` [http://localhost:8080/api/v1/courses/{id}](http://localhost:8080/api/v1/courses/{id})


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
    3. similar `post`


- **Show Course List**
    - `GET` [http://localhost:8080/api/v1/courses](http://localhost:8080/api/v1/courses)

- **Show Course by ID**
    - `GET` [http://localhost:8080/api/v1/courses/{id}](http://localhost:8080/api/v1/courses/{id})

- **Get all course by access-token**
    - `GET` [http://localhost:8080/api/v1/courses/all-course-by-user](http://localhost:8080/api/v1/courses/all-course-by-user)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |

    - **Response Example**
      ```json
        [
            {
                "courseId": 1,
                "courseName": "test 1",
                "courseCode": ".",
                "statusCourse": 1,
                "maxQuantity": 40,
                "image": "https://example.com/image.png",
                "enrollmentStatus": "ENROLLED"
            },
            {
                "courseId": 2,
                "courseName": "test 2",
                "courseCode": ".",
                "statusCourse": 1,
                "maxQuantity": 40,
                "image": "https://example.com/image.png",
                "enrollmentStatus": "ENROLLED"
            },
            {
                "courseId": 3,
                "courseName": "test 3",
                "courseCode": ".",
                "statusCourse": 1,
                "maxQuantity": 40,
                "image": "https://example.com/image.png",
                "enrollmentStatus": "ENROLLED"
            },
            {
                "courseId": 4,
                "courseName": "test 3",
                "courseCode": ".",
                "statusCourse": 1,
                "maxQuantity": 40,
                "image": "https://example.com/image.png",
                "enrollmentStatus": "ENROLLED"
            },
            {
                "courseId": 5,
                "courseName": "test 3",
                "courseCode": ".",
                "statusCourse": 1,
                "maxQuantity": 40,
                "image": "https://example.com/image.png",
                "enrollmentStatus": "NOT_ENROLLED"
            },
            {
                "courseId": 6,
                "courseName": "test 3",
                "courseCode": ".",
                "statusCourse": 1,
                "maxQuantity": 40,
                "image": "https://example.com/image.png",
                "enrollmentStatus": "NOT_ENROLLED"
            }
        ]
      ```


### Lesson APIs
- **Add Lesson**
    - `POST` [http://localhost:8080/api/v1/lessons](http://localhost:8080/api/v1/lessons)


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
       ```json
        {
        "lessonName": "Lesson 3",
        "content": "Lesson content",
        "attachments": "attachment_link",
        "level": "beginner",
        "courseId": 1
        }
       ```

- **Update Lesson**
    - `PUT` [http://localhost:8080/api/v1/lessons/{id}](http://localhost:8080/api/v1/lessons/{id})


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
    3. similar `post`


- **Delete Lesson**
    - `DELETE` [http://localhost:8080/api/v1/lessons/{lessonId}](http://localhost:8080/api/v1/lessons/{lessonId})

- **Show Lesson List**
    - `GET` [http://localhost:8080/api/v1/lessons](http://localhost:8080/api/v1/lessons)

- **Show Lesson by ID**
    - `GET` [http://localhost:8080/api/v1/lessons/{lessonId}](http://localhost:8080/api/v1/lessons/{lessonId})

- **Show Lesson by courses id**
    - `GET` [http://localhost:8080/api/v1/lessons/course/{course-Id}](http://localhost:8080/api/v1/lessons/course/{course-Id})

- **Get lesson by access-token**
    - `GET` [http://localhost:8080/api/v1/lessons/user](http://localhost:8080/api/v1/lessons/user)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |
 
    -  use Params as key : courseId and value : {id of course}

    - **Response Example**
      ```json
        [
            {
                "lessonId": 1,
                "lessonName": "Lesson 1",
                "content": "Lesson content",
                "attachments": "attachment_link",
                "level": 1,
                "courseId": 1,
                "progress": 0.0
                },
            {
                "lessonId": 4,
                "lessonName": "Lesson 3",
                "content": "Lesson content",
                "attachments": "attachment_link",
                "level": 1,
                "courseId": 2,
                "progress": 0.0
            },
            {
                "lessonId": 2,
                "lessonName": "Lesson 2",
                "content": "Lesson content",
                "attachments": "attachment_link",
                "level": 2,
                "courseId": 1,
                "progress": 0.0
            },
            {
                "lessonId": 3,
                "lessonName": "Lesson 3",
                "content": "Lesson content",
                "attachments": "attachment_link",
                "level": 3,
                "courseId": 1,
                "progress": 0.0
            }
        ]
      ```

### UserCourse APIs
- **Add UserCourse**

    - `POST` [http://localhost:8080/api/v1/user-courses/enroll](http://localhost:8080/api/v1/user-courses/enroll)
      
  **Description**

- This API allows you to start a new lesson for a user or update the progress of an existing lesson for a specific user in a particular course. It checks whether a `UserLesson` already exists for the specified `userId`, `courseId`, and `lessonId`. If it exists, it updates the progress, otherwise, it creates a new `UserLesson`.

  **Request Body**

- The body of the request must contain the following parameters:

  **Example Request**
    ```json
    {
      "courseId":2,
      "studentCode":4512,
      "role":"USER",
      "status":"1"
    }
    ```  

        - **Headers**
        -  | Key            | Value             | Description                       |
        -  |----------------|-------------------|-----------------------------------|
        -  | Authorization  | Bearer {JWT}      | JWT access token from login       |


- **Delete UserCourse**

    - `DELETE` [http://localhost:8080/api/v1/user-courses/{id}](http://localhost:8080/api/v1/user-courses/{id})

- **Show Lesson UserCourse**
    - `GET` [http://localhost:8080/api/v1/user-courses](http://localhost:8080/api/v1/user-courses)

- **Show UserCourse by ID**
    - `GET` [http://localhost:8080/api/v1/user-courses/{id}](http://localhost:8080/api/v1/user-courses/{id})


- **Get Current userCourses by access-token**
    - `GET` [http://localhost:8080/api/v1/user-courses/me](http://localhost:8080/api/v1/user-courses/me)
=======
  - `DELETE` [http://localhost:8080/api/v1/user-courses/{id}](http://localhost:8080/api/v1/user-courses/{id})

- **Show Lesson UserCourse**
  - `GET` [http://localhost:8080/api/v1/user-courses](http://localhost:8080/api/v1/user-courses)

- **Show UserCourse by ID**
  - `GET` [http://localhost:8080/api/v1/user-courses/{id}](http://localhost:8080/api/v1/user-courses/{id})


- **Get Current userCourses by access-token**
    - `GET` [http://localhost:8080/api/v1/user-courses/me](http://localhost:8080/api/v1/user-courses/me)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |

    - **Response Example**
      ```json
      [
        {
        "id": 2,
        "studentCode": 123,
        "role": "USER",
        "status": 1,
        "courseId": 1,
        "courseName": "test 1",
        "courseCode": ".",
        "statusCourse": 1,
        "maxQuantity": 40,
        "image": "https://example.com/image.png"
        },
        {
        "id": 4,
        "studentCode": 124,
        "role": "USER",
        "status": 1,
        "courseId": 2,
        "courseName": "test 2",
        "courseCode": ".",
        "statusCourse": 1,
        "maxQuantity": 40,
        "image": "https://example.com/image.png"
        }
      ]
      ```


### UserLesson APIs

**Add or Update User Lesson**
- **Method**: `POST`
- **Endpoint**: [http://localhost:8080/api/v1/user-lesson/start-or-update-lesson](http://localhost:8080/api/v1/user-lesson/start-or-update-lesson)

  **Description**

- This API allows you to start a new lesson for a user or update the progress of an existing lesson for a specific user in a particular course. It checks whether a `UserLesson` already exists for the specified `userId`, `courseId`, and `lessonId`. If it exists, it updates the progress, otherwise, it creates a new `UserLesson`.

  **Request Body**

- The body of the request must contain the following parameters:

- `courseId`: The ID of the course.
- `lessonId`: The ID of the lesson.
- `progress`: The progress of the lesson (optional, if provided it will update the progress of the lesson).

  **Example Request**
    ```json
    {
        "courseId": 2,
        "lessonId": 3,
        "progress": 50
    }
    ```  

        - **Headers**
        -  | Key            | Value             | Description                       |
        -  |----------------|-------------------|-----------------------------------|
        -  | Authorization  | Bearer {JWT}      | JWT access token from login       |

- **Delete User Lesson**

    - `DELETE` [http://localhost:8080/api/v1/user-lesson/{id}](http://localhost:8080/api/v1/user-lesson/{id})

- **Show all User Lesson**
    - `GET` [http://localhost:8080/api/v1/user-lesson](http://localhost:8080/api/v1/user-lesson)

- **Get all Current userLesson by access-token**
    - `GET` [http://localhost:8080/api/v1/user-lesson/user](http://localhost:8080/api/v1/user-lesson/user)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |

    - **Response Example**
      ```json
      [
      {
      "lessonId": 1,
      "lessonName": "Lesson 1",
      "content": "Lesson content",
      "attachments": "attachment_link",
      "level": "beginner",
      "courseId": 1,
      "progress": "0%"
      },
      {
      "lessonId": 1,
      "lessonName": "Lesson 1",
      "content": "Lesson content",
      "attachments": "attachment_link",
      "level": "beginner",
      "courseId": 1,
      "progress": "0%"
      },
      {
      "lessonId": 1,
      "lessonName": "Lesson 1",
      "content": "Lesson content",
      "attachments": "attachment_link",
      "level": "beginner",
      "courseId": 1,
      "progress": "0%"
      }
      ]
      ```

- **Get progress of current user lesson by access-token**
  - `GET` [http://localhost:8080/api/v1/user-lesson/progress](http://localhost:8080/api/v1/user-lesson/progress)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |
    
    -  use Params as key : courseId and value : {id of course}
    -  use Params as key : lessonId and value : {id of lesson}

    - **Response Example**
      ```json

      ```


### Vocabulary APIs
- **Add Vocabulary**
    - `POST` [http://localhost:8080/api/v1/vocabularies](http://localhost:8080/api/v1/vocabularies)

- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
       ```json
        {
        "word": "banana",
        "meaning": "A fruit that is typically green, and yellow.",
        "description": "An banana is a sweet fruit from the banana tree, Malus domestica.",
        "image": "http://example.com/images/banana.jpg",
        "audio": "http://example.com/audio/banana.mp3",
        "lessonId": 1
        }
       ```

- **Delete Vocabulary**
    - `DELETE` [http://localhost:8080/api/v1/vocabularies/{id}](http://localhost:8080/api/v1/vocabularies/{id})

- **Show Vocabulary**
    - `GET` [http://localhost:8080/api/v1/vocabularies](http://localhost:8080/api/v1/vocabularies)

- **Show Vocabulary by id**
    - `GET` [http://localhost:8080/api/v1/vocabularies/{id}](http://localhost:8080/api/v1/vocabularies/{id})

- **Show Vocabulary by word**
    - `GET` [http://localhost:8080/api/v1/vocabularies/word/{word}](http://localhost:8080/api/v1/vocabularies/word/{word})

- **Show Vocabulary by lesson id**
    - `GET` [http://localhost:8080/api/v1/vocabularies/lesson/{lessonId}](http://localhost:8080/api/v1/vocabularies/lesson/{lessonid})

- **Update vocabulary**
    - `PUT` [http://localhost:8080/api/v1/vocabularies/{id}](http://localhost:8080/api/v1/vocabularies/{id})


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
    3. similar `post`


### VocabularyUser APIs
- **Add VocabularyUser**
    - `POST` [http://localhost:8080/api/v1/vocabularyUser?userId={?}&vocabId={?}&progress={?}](http://localhost:8080/api/v1/vocabularyUser?userId={?}&vocabId={?}&progress={?})


- **Example in Postman**
    1. In the **Body** section, select `raw` and set the format to `JSON`.
    2. Create the JSON body like this:
       ```json
        {
        "userId": 1,
        "vocabId": 1,
        "progress": "learning"
        }
       ```


- **Delete VocabularyUser**
    - `DELETE` [http://localhost:8080/api/v1/vocabularyUser/{id}](http://localhost:8080/api/v1/vocabularyUser/{id})

- **Show VocabularyUser**
    - `GET` [http://localhost:8080/api/v1/vocabularyUser/all](http://localhost:8080/api/v1/vocabularyUser/all)

- **Show VocabularyUser by id**
    - `GET` [http://localhost:8080/api/v1/vocabularyUser/{id}](http://localhost:8080/api/v1/vocabularyUser/{id})

- **Get Current VocabularyUser**
    - `GET` [http://localhost:8080/api/v1/vocabularyUser/user](http://localhost:8080/api/v1/vocabularyUser/user)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |

    - **Response Example**
      ```json
        [
        {
        "id": 1,
        "userId": 1,
        "vocabId": 1,
        "progress": "ok",
        "word": "strawbery",
        "meaning": "A fruit that is typically white, and red."
        },
        {
        "id": 2,
        "userId": 1,
        "vocabId": 1,
        "progress": "ok",
        "word": "strawbery",
        "meaning": "A fruit that is typically white, and red."
        },
        {
        "id": 4,
        "userId": 1,
        "vocabId": 1,
        "progress": "ok",
        "word": "strawbery",
        "meaning": "A fruit that is typically white, and red."
        },
        {
        "id": 5,
        "userId": 1,
        "vocabId": 1,
        "progress": "learning",
        "word": "strawbery",
        "meaning": "A fruit that is typically white, and red."
        }
        ]
        ```


### Test APIs

- **Add Test**
  - `POST` [http://localhost:8080/api/v1/tests](http://localhost:8080/api/v1/tests)

- **Example in Postman**
  1. In the **Body** section, select `raw` and set the format to `JSON`.
  2. Create the JSON body like this:
     ```json
     {
         "testName": "Sample Test",
         "description": "This is a sample test",
         "examTime": 30,
         "type": "Quiz",
         "maxNumberOfExams": "100",
         "level": "Intermediate",
         "passingScore": "60",
         "status": 1,
         "courseId": 1
     }
     ```

- **Update Test**
  - `PUT` [http://localhost:8080/api/v1/tests/{id}](http://localhost:8080/api/v1/tests/{id})

- **Example in Postman**
  1. In the **Body** section, select `raw` and set the format to `JSON`.
  2. Create the JSON body like this:
     ```json
     {
         "testName": "Updated Test Name",
         "description": "Updated description",
         "examTime": 30,
         "type": "Final Exam",
         "maxNumberOfExams": "200",
         "level": "Advanced",
         "passingScore": "80",
         "status": 1,
         "courseId": 1
     }
     ```

- **Delete Test**
  - `DELETE` [http://localhost:8080/api/v1/tests/{testId}](http://localhost:8080/api/v1/tests/{testId})

- **Show Test List**
  - `GET` [http://localhost:8080/api/v1/tests](http://localhost:8080/api/v1/tests)

- **Show Test by ID**
  - `GET` [http://localhost:8080/api/v1/tests/{testId}](http://localhost:8080/api/v1/tests/{testId})

- **Show Test by Courses ID**
  - `GET` [http://localhost:8080/api/v1/tests/course/{courseId}](http://localhost:8080/api/v1/tests/course/{courseId})


### Question APIs

- **Add Question**
  - `POST` [http://localhost:8080/api/v1/questions](http://localhost:8080/api/v1/questions)

- **Example in Postman**
  1. In the **Body** section, select `raw` and set the format to `JSON`.
  2. Create the JSON body like this:
     ```json
     {
      "content": "What is the capital of France?",
      "attachments": null,
      "type": "single-choice",
      "score": "10",
      "testId": 2
     }
     ```

- **Update Question**
  - `PUT` [http://localhost:8080/api/v1/questions/{id}](http://localhost:8080/api/v1/questions/{id})

- **Example in Postman**
  1. In the **Body** section, select `raw` and set the format to `JSON`.
  2. Create the JSON body like this:
     ```json
     {
      "content": "What is the capital of Germany?",
      "attachments": null,
      "type": "single-choice",
      "score": "10",
      "testId": 2
     }
     ```

- **Delete Question**
  - `DELETE` [http://localhost:8080/api/v1/questions/{id}](http://localhost:8080/api/v1/questions/{id})

- **Show Test List**
  - `GET` [http://localhost:8080/api/v1/questions](http://localhost:8080/api/v1/questions)

- **Show Test by test ID**
  - `GET` [http://localhost:8080/api/v1/questions/test/{testId}](http://localhost:8080/api/v1/questions/test/{testId})


### Answer Question APIs

- **Add Answer Question**
  - `POST` [http://localhost:8080/api/v1/answer-options](http://localhost:8080/api/v1/answer-options)

- **Example in Postman**
  1. In the **Body** section, select `raw` and set the format to `JSON`.
  2. Create the JSON body like this:
     ```json
     {
      "correctAnswer": "0",
      "content": "London",
      "attachments": null,
      "id": 3
     }
     ```

- **Update Answer Question**
  - `PUT` [http://localhost:8080/api/v1/answer-options/{id}](http://localhost:8080/api/v1/answer-options/{id})

- **Example in Postman**
  1. In the **Body** section, select `raw` and set the format to `JSON`.
  2. Create the JSON body like this:
     ```json
     {
      "content": "Tokyo"
     }
     ```

- **Delete Answer Question**
  - `DELETE` [http://localhost:8080/api/v1/answer-options/{id}](http://localhost:8080/api/v1/answer-options/{id})

- **Show Test List**
  - `GET` [http://localhost:8080/api/v1/answer-options](http://localhost:8080/api/v1/answer-options)

- **Show Test by question ID**
  - `GET` [http://localhost:8080/api/v1/answer-options/question/{questionID](http://localhost:8080/api/v1/answer-options/question/{questionID})

### UserTest APIs

**Add User Test**
- **Method**: `POST`
- **Endpoint**: [http://localhost:8080/api/v1/user-test/save](http://localhost:8080/api/v1/user-test/save)

  **Request Body**

- The body of the request must contain the following parameters:


  **Example Request**
      ```json
      {
        "score":"5",
        "status":1,
        "testId":2,
        "answerAttachments":"adasdad"
      }
      ```

    ```
        - **Headers**
        -  | Key            | Value             | Description                       |
        -  |----------------|-------------------|-----------------------------------|
        -  | Authorization  | Bearer {JWT}      | JWT access token from login       |


- **Get Test and user test by access-token**
  - `GET` [http://localhost:8080/api/v1/user-test/ranked-tests](http://localhost:8080/api/v1/user-test/ranked-tests)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |
 
    -  use Params as key : courseId and value : {id of course}

    - **Response Example**
      ```json
      [
      {
        "testId": 1,
        "testName": "Sample Test",
        "description": "This is a sample test",
        "examDate": "2024-12-01",
        "examTime": 90,
        "level": "Intermediate",
        "maxNumberOfExams": "100",
        "courseId": 1,
        "type": "Quiz",
        "score": "8.5",
        "date": "2024-12-19",
        "examTimes": "16:48:59.495000",
        "status": 1
      },
      {
        "testId": 2,
        "testName": "Sample Test 2",
        "description": "This is a sample test",
        "examDate": "2024-12-01",
        "examTime": 90,
        "level": "Intermediate",
        "maxNumberOfExams": "100",
        "courseId": 1,
        "type": "Quiz",
        "score": "7.5",
        "date": "2024-12-19",
        "examTimes": "16:51:42.113000",
        "status": 1
      }
      ]
      ```

- **Get All Test and user test by access-token**
  - `GET` [http://localhost:8080/api/v1/user-test/ranked-tests-example](http://localhost:8080/api/v1/user-test/ranked-tests-example)


    - **Headers**
    -  | Key            | Value             | Description                       |
    -  |----------------|-------------------|-----------------------------------|
    -  | Authorization  | Bearer {JWT}      | JWT access token from login       |
 
    -  use Params as key : courseId and value : {id of course}

    - **Response Example**
      ```json
    [
    {
        "testId": 4,
        "examTime": 90,
        "level": "Intermediate",
        "maxNumberOfExams": "100",
        "userTestStatus": 1,
        "testName": "Sample Test 2.2",
        "type": "Quiz",
        "courseId": 2,
        "score": "5.5",
        "date": "2024-12-19",
        "examTimes": "16:33:17"
    },
    {
        "testId": 3,
        "examTime": 90,
        "level": "Intermediate",
        "maxNumberOfExams": "100",
        "userTestStatus": 1,
        "testName": "Sample Test 2.1",
        "type": "Quiz",
        "courseId": 2,
        "score": "7",
        "date": "2024-12-19",
        "examTimes": "16:33:08"
    },
    {
        "testId": 3,
        "examTime": 90,
        "level": "Intermediate",
        "maxNumberOfExams": "100",
        "userTestStatus": 1,
        "testName": "Sample Test 2.1",
        "type": "Quiz",
        "courseId": 2,
        "score": "5",
        "date": "2024-12-19",
        "examTimes": "16:32:48"
    }
    ]
      ```
---

## Installation and Setup
1. Clone the repository.
2. Set up a MySQL database and configure the database credentials in `application.properties`.
3. Configure Cloudinary settings in `application.properties` for image storage.
4. Run the application using your preferred IDE or via command line:
   ```bash
   ./mvnw spring-boot:run

![Alt text](b.png)

