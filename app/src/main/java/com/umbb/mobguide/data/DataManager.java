package com.umbb.mobguide.data;

import com.umbb.mobguide.models.Department;
import com.umbb.mobguide.models.Faculty;
import java.util.ArrayList;

/**
 * Data manager to handle university data using dynamic ArrayLists.
 */
public class DataManager {
    private static ArrayList<Faculty> faculties = new ArrayList<>();
    private static ArrayList<Department> departments = new ArrayList<>();

    static {
        // Initialize Faculties
        faculties.add(new Faculty("sciences", "Faculty of Sciences", "Founded to advance pure and applied sciences.", "024795271", "fs@univ-boumerdes.dz", "geo:36.7621,3.4687"));
        faculties.add(new Faculty("tech", "Faculty of Technology", "Focused on modern engineering and industrial systems.", "024795272", "ft@univ-boumerdes.dz", "geo:36.7615,3.4695"));
        faculties.add(new Faculty("igee", "Institute of Electrical and Electronic Engineering", "Elite institute for electronics and power systems.", "024795273", "igee@univ-boumerdes.dz", "geo:36.7630,3.4705"));

        // Initialize Departments for Sciences
        departments.add(new Department("sciences", "Computer Science", "Study of algorithms, software engineering, and AI.", "Software Engineering, AI, Networking", "024795271", "cs@univ-boumerdes.dz", "geo:36.7621,3.4687"));
        departments.add(new Department("sciences", "Mathematics", "Foundational mathematics and applications.", "Algebra, Analysis, Statistics", "024795271", "math@univ-boumerdes.dz", "geo:36.7621,3.4687"));
        departments.add(new Department("sciences", "Physics", "Theoretical and experimental physics.", "Materials, Energy, Quantum Physics", "024795271", "physics@univ-boumerdes.dz", "geo:36.7621,3.4687"));

        // Initialize Departments for Technology
        departments.add(new Department("tech", "Mechanical Engineering", "Design and manufacturing of mechanical systems.", "Robotics, Material Science, Design", "024795272", "mech@univ-boumerdes.dz", "geo:36.7615,3.4695"));
        departments.add(new Department("tech", "Process Engineering", "Chemical and industrial process optimization.", "Chemical Eng, Industrial Safety", "024795272", "proc@univ-boumerdes.dz", "geo:36.7615,3.4695"));
    }

    public static ArrayList<Faculty> getFaculties() { return faculties; }

    public static ArrayList<Department> getDepartmentsByFaculty(String facultyId) {
        ArrayList<Department> filtered = new ArrayList<>();
        for (Department d : departments) {
            if (d.getFacultyId().equals(facultyId)) {
                filtered.add(d);
            }
        }
        return filtered;
    }

    public static ArrayList<Department> searchAll(String query) {
        ArrayList<Department> results = new ArrayList<>();
        // Search in departments by name or faculty
        for (Department d : departments) {
            if (d.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(d);
            }
        }
        return results;
    }
}
