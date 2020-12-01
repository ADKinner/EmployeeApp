create table employees
(
    employee_id   bigserial    not null,
    date_of_birth date         not null,
    department_id int8         not null,
    first_name    varchar(100) not null,
    gender        varchar(255) not null,
    job_title     varchar(255) not null,
    last_name     varchar(100) not null,
    primary key (employee_id)
)