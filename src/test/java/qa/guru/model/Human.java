package qa.guru.model;

import java.util.List;

public class Human {
        public String name;
        public int age;
        public List<String> hobby;
        public CourseInfo courseInfo;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public List<String> getHobby() {
                return hobby;
        }

        public void setHobby(List<String> hobby) {
                this.hobby = hobby;
        }

        public CourseInfo getCourseInfo() {
                return courseInfo;
        }

        public void setCourseInfo(CourseInfo courseInfo) {
                this.courseInfo = courseInfo;
        }

}
