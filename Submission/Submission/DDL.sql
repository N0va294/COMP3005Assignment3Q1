create table Students(
    student_id serial primary key,
    first_name text not null,
    last_name text not null,
    email text unique not null,
    enrollment_date date
);