# HomeWork-ToDoList API
![TodoList Api](https://github.com/BMDK9/HomeWork-ToDoList/assets/144665614/7f9f551d-2022-48f1-ae62-89055284f428)

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
