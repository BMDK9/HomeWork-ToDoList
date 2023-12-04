# HomeWork-ToDoList API
![image](https://github.com/BMDK9/HomeWork-ToDoList/assets/144665614/7e53aea7-20af-4302-8b01-511b2bd8dac6)


포스트맨 API 문서
https://documenter.getpostman.com/view/30903657/2s9Ye8gEur

# HomeWork_ToDoList ERD
https://drawsql.app/teams/-713/diagrams/copy-of-todo
![todo](https://github.com/BMDK9/HomeWork-ToDoList/assets/144665614/e7397b75-ee27-409d-83b3-e4a97476e99b)


# 디렉토리 구조
```bash
src
├─main
│  ├─java
│  │  └─com
│  │      └─sparta
│  │          └─homework2_todolist
│  │              ├─domain
│  │              │  ├─card
│  │              │  │  ├─constant
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  ├─entity
│  │              │  │  ├─exception
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─users
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  ├─entity
│  │              │  │  ├─exception
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  └─utills
│  │              └─global
│  │                  ├─common
│  │                  ├─config
│  │                  ├─exception
│  │                  │  ├─advice
│  │                  │  └─response
│  │                  ├─jwt
│  │                  └─security
│  └─resources
│      ├─static
│      └─templates
└─test
    └─java
        └─com
            └─sparta
                └─homework2_todolist
                    └─domain
                        ├─card
                        │  ├─controller
                        │  ├─entity
                        │  └─service
                        └─users
                            ├─dto
                            ├─entity
                            └─service
