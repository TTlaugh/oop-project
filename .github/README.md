# Java OOP Project

#### Project structure

```
    package: classes.
                    ├── repository
                    │   ├── AcountRepository.java [v]
                    │   ├── QuestionRepository.java [x]
                    │   ├── ExamRepository.java
                    │   └── ExamRecordRepository.java
                    ├── user [v]
                    │   ├── Admin.java
                    │   ├── Professor.java
                    │   ├── Student.java
                    │   ├── UserInfo.java
                    │   ├── Account.java
                    │   └── AccountList.java
                    ├── question [x]
                    │   ├── Question.java
                    │   ├── QuestionBank.java
                    │   └── QuestionSet.java
                    ├── exam
                    │   ├── Exam.java
                    │   ├── ExamRecord.java
                    │   └── Session.java
                    ├── subject
                    │   ├── Subject.java [v]
                    │   └── SubjectList.java
                    ├── result
                    │   ├── Result.java
                    │   └── Grade.java
                    └── util
                        └── Date.java [v]

```
#### Describtion

- classes.repository: bao gom cac class thao tac truc tiep den du lieu (file)
- classes.user: gom cac class mo ta nguoi dung (Userinfo, Admin, Professor, Student) va class danh sach nguoi dung (AccountList)
- classes.question: cac class mo ta 1 cau hoi (Question), 1 bo cau hoi cho 1 bai kiem tra (QuestionSet), ngan hang cau hoi (QuestionBank)
- classes.exam: mo ta 1 bai kiem tra (Exam), ban ghi cho 1 bai kiem tra da hoan thanh (ExamRecord) va mot phien kiem tra (Session)
- classes.subject: mo ta 1 mon hoc va danh sach mon hoc
- classes.result: mo ta ket qua va diem
- classes.util: bao gom cac class chuc nang tu dinh nghia
