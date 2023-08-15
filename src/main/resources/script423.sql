select s.name, s.age, f.name
from student as s
join faculty as f on s.faculty_id = f.id;

select s.name
from student as s
join avatar as a on a.student_id = s.id;