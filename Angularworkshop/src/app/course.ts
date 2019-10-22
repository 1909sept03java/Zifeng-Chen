import { Student } from "./student";

export class Course{
    course_id:number;
    course_name:string;
    students:Student[];
    yes:number;
    constructor(course_id:number, course_name:string,students:Student[]){
        this.course_id = course_id;
        this.course_name = course_name;
        this.students = students;
    }
}