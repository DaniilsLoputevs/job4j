package models.integrationtests.manytomany;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity(name = "MTM_stud")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "student_university",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id"))
    private List<University> universities;

    public Student() {
    }

    public Student(long id, String name, List<University> universities) {
        this.id = id;
        this.name = name;
        this.universities = universities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setStudents(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Student that = (Student) object;
        return id == that.id
                && Objects.equals(name, that.name)
                && Objects.equals(universities, that.universities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, universities);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("universities=" + universities)
                .toString();
    }

}