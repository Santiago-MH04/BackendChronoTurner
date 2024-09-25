classDiagram
direction BT
class Category {
  + Category(String, String, String, String, List~Task~) 
  + Category() 
  - String description
  - String name
  - String colour
  - List~Task~ taskList
  - String id
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
  + equals(Object) boolean
  + builder() CategoryBuilder
   String name
   String description
   String id
   String colour
   List~Task~ taskList
}
class CategoryBuilder {
  ~ CategoryBuilder() 
  + id(String) CategoryBuilder
  + name(String) CategoryBuilder
  + description(String) CategoryBuilder
  + colour(String) CategoryBuilder
  + taskList(List~Task~) CategoryBuilder
  + build() Category
  + toString() String
}
class CategoryService {
<<Interface>>

}
class CategoryServiceImpl {
  + CategoryServiceImpl() 
  + delete(Category) void
  + deleteById(String) void
  + save(Category) Category
  + findAll() List~Category~
}
class DeleteService~Entity, ID~ {
<<Interface>>
  + deleteById(ID) void
  + delete(Entity) void
}
class FindAllService~Entity~ {
<<Interface>>
  + findAll() List~Entity~
}
class FindByEmailService~Entity, Email~ {
<<Interface>>
  + findByEmail(Email) Optional~Entity~
}
class FindByIdService~Entity, ID~ {
<<Interface>>
  + findById(ID) Optional~Entity~
}
class FindByNameService~Entity, ID~ {
<<Interface>>
  + findByNameContaining(ID) Entity
}
class Goal {
  + Goal() 
  + Goal(String, String, String, LocalDateTime, LocalDateTime, GoalStatus, List~Task~, User) 
  - String name
  - List~Task~ taskList
  - User user
  - String description
  - String id
  - LocalDateTime startDate
  - LocalDateTime endDate
  - GoalStatus status
  + toString() String
  # canEqual(Object) boolean
  + hashCode() int
  + equals(Object) boolean
  + builder() GoalBuilder
  + GoalCreation() void
   String name
   String description
   LocalDateTime endDate
   List~Task~ taskList
   LocalDateTime startDate
   String id
   User user
   GoalStatus status
}
class GoalBuilder {
  ~ GoalBuilder() 
  + status(GoalStatus) GoalBuilder
  + id(String) GoalBuilder
  + name(String) GoalBuilder
  + description(String) GoalBuilder
  + startDate(LocalDateTime) GoalBuilder
  + endDate(LocalDateTime) GoalBuilder
  + taskList(List~Task~) GoalBuilder
  + user(User) GoalBuilder
  + build() Goal
  + toString() String
}
class GoalService {
<<Interface>>

}
class GoalServiceImpl {
  + GoalServiceImpl() 
  + findByNameContaining(String) Goal
  + deleteById(String) void
  + delete(Goal) void
  + findAll() List~Goal~
  + findById(String) Optional~Goal~
  + save(Goal) Goal
}
class Priority {
  + Priority(String, Name, String, List~Task~) 
  + Priority() 
  - String id
  - Name name
  - List~Task~ taskList
  - String description
  + builder() PriorityBuilder
  # canEqual(Object) boolean
  + toString() String
  + hashCode() int
  + equals(Object) boolean
   Name name
   String description
   String id
   List~Task~ taskList
}
class PriorityBuilder {
  ~ PriorityBuilder() 
  + id(String) PriorityBuilder
  + name(Name) PriorityBuilder
  + description(String) PriorityBuilder
  + taskList(List~Task~) PriorityBuilder
  + build() Priority
  + toString() String
}
class PriorityService {
<<Interface>>

}
class PriorityServiceImpl {
  + PriorityServiceImpl() 
  + delete(Priority) void
  + save(Priority) Priority
  + findAll() List~Priority~
  + deleteById(String) void
}
class Role {
  + Role(Integer, String, List~User~, LocalDateTime, LocalDateTime) 
  + Role() 
  - List~User~ users
  - LocalDateTime createdDate
  - String name
  - Integer id
  - LocalDateTime lastModifiedDate
  + builder() RoleBuilder
   String name
   List~User~ users
   Integer id
   LocalDateTime createdDate
   LocalDateTime lastModifiedDate
}
class RoleBuilder {
  ~ RoleBuilder() 
  + id(Integer) RoleBuilder
  + name(String) RoleBuilder
  + users(List~User~) RoleBuilder
  + createdDate(LocalDateTime) RoleBuilder
  + lastModifiedDate(LocalDateTime) RoleBuilder
  + build() Role
  + toString() String
}
class SaveService~Entity~ {
<<Interface>>
  + save(Entity) Entity
}
class Task {
  + Task(String, String, String, LocalDateTime, TaskStatus, SubtaskStatus, Priority, Goal, Category, User) 
  + Task() 
  - TaskStatus taskStatus
  - User user
  - String name
  - Priority priority
  - Goal goal
  - Category category
  - String id
  - LocalDateTime expirationDate
  - String description
  - SubtaskStatus subtaskStatus
  + builder() TaskBuilder
  + hashCode() int
  + equals(Object) boolean
  # canEqual(Object) boolean
  + toString() String
   String name
   String description
   Goal goal
   TaskStatus taskStatus
   Category category
   LocalDateTime expirationDate
   Priority priority
   SubtaskStatus subtaskStatus
   String id
   User user
}
class TaskBuilder {
  ~ TaskBuilder() 
  + expirationDate(LocalDateTime) TaskBuilder
  + id(String) TaskBuilder
  + name(String) TaskBuilder
  + description(String) TaskBuilder
  + taskStatus(TaskStatus) TaskBuilder
  + subtaskStatus(SubtaskStatus) TaskBuilder
  + toString() String
  + priority(Priority) TaskBuilder
  + goal(Goal) TaskBuilder
  + category(Category) TaskBuilder
  + build() Task
  + user(User) TaskBuilder
}
class TaskService {
<<Interface>>

}
class TaskServiceImpl {
  + TaskServiceImpl() 
  + deleteById(String) void
  + save(Task) Task
  + findAll() List~Task~
  + delete(Task) void
  + findById(String) Optional~Task~
}
class Token {
  + Token(Integer, String, LocalDateTime, LocalDateTime, LocalDateTime, User) 
  + Token() 
  - LocalDateTime validatedAt
  - User user
  - Integer id
  - String token
  - LocalDateTime createdAt
  - LocalDateTime expiresAt
  + builder() TokenBuilder
   LocalDateTime createdAt
   LocalDateTime validatedAt
   String token
   Integer id
   LocalDateTime expiresAt
   User user
}
class TokenBuilder {
  ~ TokenBuilder() 
  + id(Integer) TokenBuilder
  + token(String) TokenBuilder
  + createdAt(LocalDateTime) TokenBuilder
  + expiresAt(LocalDateTime) TokenBuilder
  + validatedAt(LocalDateTime) TokenBuilder
  + user(User) TokenBuilder
  + build() Token
  + toString() String
}
class User {
  + User(String, String, String, String, boolean, boolean, UserType, List~Task~, List~Goal~, List~Role~, LocalDateTime, LocalDateTime) 
  + User() 
  - String password
  - String id
  - List~Task~ taskList
  - String name
  - LocalDateTime createdDate
  - String email
  - UserType userType
  - List~Goal~ goalList
  - List~Role~ roles
  - boolean enabled
  - LocalDateTime lastModifiedDate
  - boolean accountLocked
  + equals(Object) boolean
  # canEqual(Object) boolean
  + toString() String
  + hashCode() int
  + builder() UserBuilder
   boolean accountLocked
   List~Task~ taskList
   Collection~GrantedAuthority~ authorities
   boolean credentialsNonExpired
   boolean accountNonExpired
   String id
   String name
   String password
   boolean accountNonLocked
   String email
   LocalDateTime lastModifiedDate
   LocalDateTime createdDate
   List~Role~ roles
   List~Goal~ goalList
   String username
   UserType userType
   boolean enabled
}
class UserBuilder {
  ~ UserBuilder() 
  + enabled(boolean) UserBuilder
  + id(String) UserBuilder
  + createdDate(LocalDateTime) UserBuilder
  + name(String) UserBuilder
  + toString() String
  + email(String) UserBuilder
  + password(String) UserBuilder
  + lastModifiedDate(LocalDateTime) UserBuilder
  + accountLocked(boolean) UserBuilder
  + userType(UserType) UserBuilder
  + taskList(List~Task~) UserBuilder
  + goalList(List~Goal~) UserBuilder
  + roles(List~Role~) UserBuilder
  + build() User
}
class UserService {
<<Interface>>

}
class UserServiceImpl {
  + UserServiceImpl() 
  + save(User) User
  + delete(User) void
  + findById(String) Optional~User~
  + deleteById(String) void
  + findByEmail(String) Optional~User~
}

Category "1" *--> "taskList *" Task 
Category  -->  CategoryBuilder 
CategoryService  -->  DeleteService~Entity, ID~ 
CategoryService  -->  FindAllService~Entity~ 
CategoryService  -->  SaveService~Entity~ 
CategoryServiceImpl  ..>  CategoryService 
Goal "1" *--> "taskList *" Task 
Goal "1" *--> "user 1" User 
Goal  -->  GoalBuilder 
GoalService  -->  DeleteService~Entity, ID~ 
GoalService  -->  FindAllService~Entity~ 
GoalService  -->  FindByIdService~Entity, ID~ 
GoalService  -->  FindByNameService~Entity, ID~ 
GoalService  -->  SaveService~Entity~ 
GoalServiceImpl  ..>  GoalService 
Priority "1" *--> "taskList *" Task 
Priority  -->  PriorityBuilder 
PriorityService  -->  DeleteService~Entity, ID~ 
PriorityService  -->  FindAllService~Entity~ 
PriorityService  -->  SaveService~Entity~ 
PriorityServiceImpl  ..>  PriorityService 
Role "1" *--> "users *" User 
Role  -->  RoleBuilder 
Task "1" *--> "category 1" Category 
Task "1" *--> "goal 1" Goal 
Task "1" *--> "priority 1" Priority 
Task "1" *--> "user 1" User 
Task  -->  TaskBuilder 
TaskService  -->  DeleteService~Entity, ID~ 
TaskService  -->  FindAllService~Entity~ 
TaskService  -->  FindByIdService~Entity, ID~ 
TaskService  -->  SaveService~Entity~ 
TaskServiceImpl  ..>  TaskService 
Token "1" *--> "user 1" User 
Token  -->  TokenBuilder 
User "1" *--> "goalList *" Goal 
User "1" *--> "roles *" Role 
User "1" *--> "taskList *" Task 
User  -->  UserBuilder 
UserService  -->  DeleteService~Entity, ID~ 
UserService  -->  FindByEmailService~Entity, Email~ 
UserService  -->  FindByIdService~Entity, ID~ 
UserService  -->  SaveService~Entity~ 
UserServiceImpl  ..>  UserService 
